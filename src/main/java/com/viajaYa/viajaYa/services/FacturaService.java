package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;
import com.viajaYa.viajaYa.repositories.IFacturaRepository;
import com.viajaYa.viajaYa.services.interfaces.IFacturaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService  implements IFacturaService {

    @Autowired
    IFacturaRepository facturaRepository;

    @Autowired
    IMapper<FacturaDTO, FacturaModel> mapper;

    @Override
    public FacturaDTO generateFactura(FacturaDTO facturad) {
        FacturaModel factura = mapper.toEntity(facturad);

        String xml = convertirFacturaToXML(factura);
        factura.setXml(xml);


        return mapper.toDto(facturaRepository.save(factura));
    }


    private String convertirFacturaToXML(FacturaModel factura) {
        try {
            JAXBContext context = JAXBContext.newInstance(FacturaModel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(factura, writer);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la factura a XML", e);
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
            throw new RuntimeException("Factura con id " + id + " no encontrada");
        }
        return factura;
    }
}
