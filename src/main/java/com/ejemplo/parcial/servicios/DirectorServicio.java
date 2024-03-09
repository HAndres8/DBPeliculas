package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.Director;
import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.DirectorRepositorio;
import com.ejemplo.parcial.repositorios.PeliculaRepositorio;
import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("DirectorService")
public class DirectorServicio implements Operaciones<Director>{

    @Autowired
    private DirectorRepositorio directorRepo;
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    
    @Override
    public List<Director> consultar() {
        return directorRepo.obtenerTodos();
    }

    @Override
    public Boolean agregar(Director miObjeto) {
        Optional<Director> objTemp = directorRepo.findById(miObjeto.getCodDir());
        if(objTemp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo de ese director ya existe.");
        } else {
            directorRepo.save(miObjeto);
            return true;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        if(directorRepo.existsById(llavePrimaria)){
            directorRepo.deleteById(llavePrimaria);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este director no existe");

        }
    }
    
    @Override
    public Director buscar(Integer llavePrimaria) {
        return directorRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Boolean actualizar(Director miObjeto) {
        Optional<Director> objTemp = directorRepo.findById(miObjeto.getCodDir());
        if(objTemp.isPresent()) {
            directorRepo.save(miObjeto);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Te Quedo Mal el Json");
        }
    }
    
    @Override
    public Integer cantidadRegistros() {
        return directorRepo.contarDirectores();
    }
    

    public Director relacionarDirectorPelicula(Integer codigoD, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Director tempD = directorRepo.findById(codigoD).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempD.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el director participa en la pelicula, no puede relacionarlo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El director ya participa en esa pelicula");
        }else{
            listaPelicula.add(tempP);
            tempD.setPeliculasList(listaPelicula);
            return directorRepo.save(tempD);
        }
    }
    
    public Director desrelacionarDirectorPelicula(Integer codigoD, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Director tempD = directorRepo.findById(codigoD).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempD.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el director participa en la pelicula, la desrelaciona
            listaPelicula.remove(tempP);
            tempD.setPeliculasList(listaPelicula);
            return directorRepo.save(tempD);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El director no participa en esa pelicula");
        }
    }
    
    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    public List<Map<String, Object>> mostrarInfoDirector() {
        List<Tuple> tuples = directorRepo.mostrarDirector();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Edad", tuple.get(2, Double.class));
            mapa.put("Sexo", tuple.get(3, Character.class));
            mapa.put("Nacionalidad", tuple.get(4, String.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Director> ordenarDirectorA() {
        return directorRepo.ordenarA();
    }

    public List<Director> ordenarDirectorD() {
        return directorRepo.ordenarD();
    }

    public List<Map<String, Object>> mostrarNacionalidad(String nac) {
        List<Tuple> tuples = directorRepo.nacionalidadDirector(nac);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Edad", tuple.get(2, Double.class));
            mapa.put("Sexo", tuple.get(3, Character.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Map<String, Object>> mostrarSexo(Character miSexo) {
        List<Tuple> tuples = directorRepo.sexoDirector(miSexo);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Edad", tuple.get(2, Double.class));
            mapa.put("Nacionalidad", tuple.get(3, String.class));
            result.add(mapa);
        }
        return result;
    }
    
    public List<Map<String, Object>> mostrarDirectorMasDeX(int nume) {
        List<Tuple> tuples = directorRepo.directorMasDeXPeliculas(nume);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Cantidad", tuple.get(2, Long.class));
            result.add(mapa);
        }
        return result;
    }
}
