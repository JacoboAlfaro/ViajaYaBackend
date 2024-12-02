package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;
import com.viajaYa.viajaYa.repositories.IFacturaRepository;
import com.viajaYa.viajaYa.repositories.IReservaRepository;
import com.viajaYa.viajaYa.services.interfaces.IFacturaService;
import com.viajaYa.viajaYa.services.interfaces.IReservaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//[Aplicando principio experto de información]
@Service
public class FacturaService  implements IFacturaService {

    @Autowired
    IFacturaRepository facturaRepository;
    @Autowired
    IReservaRepository reservaRepository;

    @Autowired
    IMapper<FacturaDTO, FacturaModel> mapper;

    @Override
    public FacturaDTO generateFactura(FacturaDTO facturad) {
        FacturaModel factura = mapper.toEntity(facturad);

        String xml = convertirFacturaToXML(factura);
        factura.setXml(xml);


        return mapper.toDto(facturaRepository.save(factura));
    }

    private double calcularPrecioTotal(ReservaModel reserva){
        double total = 0;

        if(reserva.getVuelos() != null){
            total+=reserva.getVuelos().stream().mapToDouble(vuelo -> vuelo.getPrecio()).sum();
        }
        if(reserva.getHoteles() != null){
            total+=reserva.getHoteles().stream().mapToDouble(hotel -> hotel.getPrecioNoche()).sum();
        }
        if(reserva.getPaquetesTuristicos() != null){
            total+=reserva.getPaquetesTuristicos().stream().mapToDouble(paquete -> paquete.getPrecio()).sum();
        }
        return total;
    }


    //[Aplica principio OCP]
    private String convertirFacturaToXML(FacturaModel factura) {
        try {
            JAXBContext context = JAXBContext.newInstance(FacturaModel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(factura, writer);

            return writer.toString();
        } catch (Exception e) {
            throw new BusinessException("Error al convertir la factura a XML", e);
        }
    }

    @Override
    public ArrayList<FacturaModel> getFacturas() {
        return (ArrayList<FacturaModel>) facturaRepository.findAll();
    }

    @Override
    public Optional<FacturaModel> getFacturaById(Long id){
        Optional<FacturaModel> factura = facturaRepository.findById(id);
        if (factura.isEmpty()) {
            throw new BusinessException("Factura con id " + id + " no encontrada");
        }
        return factura;
    }

    @Override
    public List<FacturaModel> getFacturasByUsuario(Long idUsuario){
        List<ReservaModel> reservas = reservaRepository.findByUsuarioId(idUsuario);
        List<FacturaModel> facturas = new ArrayList<>(); //[Aplicando principio experto de información]
        for (ReservaModel reserva : reservas) {
            facturas.add(facturaRepository.getFacturaByReservaId(reserva.getId()));
        }
        facturas = facturas.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return facturas;
    }
}
