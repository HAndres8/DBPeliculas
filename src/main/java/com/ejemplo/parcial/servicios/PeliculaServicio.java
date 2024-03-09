package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.PeliculaRepositorio;
import jakarta.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("PeliculaService")
public class PeliculaServicio implements Operaciones<Pelicula>{

    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    @Override
    public List<Pelicula> consultar() {
        return peliculaRepo.obtenerTodas();
    }

    @Override
    public Boolean agregar(Pelicula miObjeto) {
        Optional<Pelicula> objTemp = peliculaRepo.findById(miObjeto.getCodPel());
        if(objTemp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo de esa pelicula ya existe.");
        } else {
            peliculaRepo.save(miObjeto);
            return true;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        if(peliculaRepo.existsById(llavePrimaria)){
            peliculaRepo.deleteById(llavePrimaria);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta pelicula no existe");

        }
    }
    
    @Override
    public Pelicula buscar(Integer llavePrimaria) {
        return peliculaRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Boolean actualizar(Pelicula miObjeto) {
        Optional<Pelicula> objTemp = peliculaRepo.findById(miObjeto.getCodPel());
        if(objTemp.isPresent()) {
            peliculaRepo.save(miObjeto);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Te Quedo Mal el Json");
        }
    }
    
    @Override
    public Integer cantidadRegistros() {
        return peliculaRepo.contarPeliculas();
    }
    
    
    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    public List<Map<String, Object>> obtenerPeliculasGeneroX(String genero) {
        List<Tuple> tuples = peliculaRepo.mostrarGeneroX(genero);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Código", tuple.get(0, Integer.class));
            mapa.put("Titulo Pelicula", tuple.get(1, String.class));
            mapa.put("Genero", tuple.get(2, String.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Map<String, Object>> obtenerPeliculasAnio(int anio) {
        List<Tuple> tuples = peliculaRepo.peliculaAnio(anio);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Código", tuple.get(0, Integer.class));
            mapa.put("Titulo Pelicula", tuple.get(1, String.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Map<String, Object>> obtenerPeliculasEntre(int anioI, int anioF) {
        List<Tuple> tuples = peliculaRepo.peliculaEntre(anioI,anioF);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Código", tuple.get(0, Integer.class));
            mapa.put("Titulo Pelicula", tuple.get(1, String.class));
            mapa.put("Año", tuple.get(2, Integer.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Map<String, Object>> obtenerPeliculasDuracionMenorA(int hora) {
        List<Tuple> tuples = peliculaRepo.peliculaDuracionMenor(hora);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Código", tuple.get(0, Integer.class));
            mapa.put("Titulo Pelicula", tuple.get(1, String.class));
            mapa.put("Duracion Minutos", tuple.get(2, Integer.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Map<String, Object>> obtenerPeliculasPorRecaudo(long rMin, long rMax) {
        List<Tuple> tuples = peliculaRepo.peliculaPorRecaudo(rMin,rMax);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Código", tuple.get(0, Integer.class));
            mapa.put("Titulo Pelicula", tuple.get(1, String.class));
            mapa.put("Total Recaudado", tuple.get(2, BigDecimal.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Pelicula> ordenarPeliculasA() {
        return peliculaRepo.ordenarA();
    }
    public List<Pelicula> ordenarPeliculasD() {
        return peliculaRepo.ordenarD();
    }
    
}
