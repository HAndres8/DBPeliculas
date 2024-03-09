package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.EstadisticaPelicula;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.EstadisticaPeliculaRepositorio;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EstadisticaPeliculaService")
public class EstadisticaPeliculaServicio implements Operaciones<EstadisticaPelicula>{
    
    @Autowired
    private EstadisticaPeliculaRepositorio estadisticaRepo;

    
    @Override
    public List<EstadisticaPelicula> consultar() {
        return estadisticaRepo.obtenerTodas();
    }

    @Override
    public EstadisticaPelicula buscar(Integer llavePrimaria) {
        return estadisticaRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Integer cantidadRegistros() {
        return estadisticaRepo.contarDatos();
    }
    
    
    
    
    
    @Override
    public Boolean agregar(EstadisticaPelicula miObjeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean actualizar(EstadisticaPelicula miObjeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    
    public List<EstadisticaPelicula> obetenerMas(BigDecimal porc) {
        return estadisticaRepo.obtenerMas(porc);
    }
    
    public List<EstadisticaPelicula> obetenerMenos(BigDecimal porc) {
        return estadisticaRepo.obtenerMenos(porc);
    }
}
