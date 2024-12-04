package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.models.dtos.ReseniaDTO;
import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;
import com.viajaYa.viajaYa.services.patterns.singleton.Productos;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import com.viajaYa.viajaYa.repositories.IReseniaRepository;
import com.viajaYa.viajaYa.services.interfaces.IReseniaService;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//[Aplicando principio experto de información]
@Service
public class ReseniaService implements IReseniaService {

    @Autowired
    IReseniaRepository reseniaRepository;
    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IMapper<ReseniaRequestDTO, ReseniaModel> requestMapper;
    @Autowired
    IProductoRepository productoRepository;
    //Buscando singleton
    Productos productos = Productos.getInstance(productoRepository);

    public ArrayList<ReseniaModel> getResenias(){
        return (ArrayList<ReseniaModel>) reseniaRepository.findAll();
    }
    public Optional<ReseniaModel> getReseniaById(Long id){
        Optional<ReseniaModel> resenia = reseniaRepository.findById(id);
        if(resenia.isEmpty()){
            throw new BusinessException("Resenia con id " + id + " no encontrado");
        }
        return resenia;
    }

    public ArrayList<ReseniaModel> findReseniaByUsuario(Long id) {
        usuarioService.getUsuarioById(id);
        return reseniaRepository.findReseniaByUsuario(id);
    }

    public List<ReseniaModel> findReseniaByProducto(Long idProducto, Long idReferencia) {
        if(!productos.isValidProductoId(idProducto)){
            throw new BusinessException("Tipo de producto no valido");
        }
        return reseniaRepository.findReseniaByProducto(idProducto, idReferencia);
    }


    public ReseniaModel saveResenia(ReseniaRequestDTO resenia){
        ReseniaModel reseniaModel = requestMapper.toEntity(resenia);
        if(resenia.getCalificacion() < 0 || resenia.getCalificacion() > 5){
            throw new BusinessException("La calificación debe estar entre 0 y 5");
        }
        reseniaModel.setFecha(LocalDateTime.now());

        return reseniaRepository.save(reseniaModel);
    }

    public ReseniaModel updateReseniaById(ReseniaDTO request, Long id){
        Optional<ReseniaModel> resenia = reseniaRepository.findById(id);
        if(resenia.isEmpty()){
            throw new BusinessException("Resenia con id " + id + " no encontrado");
        }

        ReseniaModel reseniaActualizada = resenia.get();
        reseniaActualizada.setFecha(LocalDateTime.now()); // setea fecha actual
        reseniaActualizada.setCalificacion(request.getCalificacion());
        reseniaActualizada.setComentario(request.getComentario());
        return reseniaRepository.save(reseniaActualizada);
    }

    public boolean deleteReseniaById(Long id){
        Optional<ReseniaModel> resenia = reseniaRepository.findById(id);
        if(resenia.isEmpty()){
            throw new BusinessException("Resenia con id " + id + " no encontrado");
        }
        reseniaRepository.deleteById(id);
        return true;
    }
}