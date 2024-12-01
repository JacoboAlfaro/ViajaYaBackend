package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.models.dtos.ReseniaDTO;
import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IReseniaService {
    public ArrayList<ReseniaModel> getResenias();
    public Optional<ReseniaModel> getReseniaById(Long id);
    public ArrayList<ReseniaModel> findReseniaByUsuario(Long id);
    public List<ReseniaModel> findReseniaByProducto(Long idProducto, Long idReferencia);
    public ReseniaModel saveResenia(ReseniaRequestDTO resenia);
    public ReseniaModel updateReseniaById(ReseniaDTO request, Long id);
    public boolean deleteReseniaById(Long id);

}
