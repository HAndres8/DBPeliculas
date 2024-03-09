package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.Actor;
import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.ActorRepositorio;
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

@Service("ActorService")
public class ActorServicio implements Operaciones<Actor> {

    @Autowired
    private ActorRepositorio actorRepo;
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    
    @Override
    public List<Actor> consultar() {
        return actorRepo.obtenerTodos();
    }

    @Override
    public Boolean agregar(Actor miObjeto) {
        Optional<Actor> objTemp = actorRepo.findById(miObjeto.getCodAct());
        if(objTemp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo de ese actor ya existe.");
        } else {
            actorRepo.save(miObjeto);
            return true;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        if(actorRepo.existsById(llavePrimaria)){
            actorRepo.deleteById(llavePrimaria);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este actor no existe");

        }
    }
    
    @Override
    public Actor buscar(Integer llavePrimaria) {
        return actorRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Boolean actualizar(Actor miObjeto) {
        Optional<Actor> objTemp = actorRepo.findById(miObjeto.getCodAct());
        if(objTemp.isPresent()) {
            actorRepo.save(miObjeto);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Te Quedo Mal el Json.");
        }
    }
    
    @Override
    public Integer cantidadRegistros() {
        return actorRepo.contarActores();
    }

    
    public Actor relacionarActorPelicula(Integer codigoA, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Actor tempA = actorRepo.findById(codigoA).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempA.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el actor participa en la pelicula, no puede relacionarlo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El actor ya participa en esa pelicula");
        }else{
            listaPelicula.add(tempP);
            tempA.setPeliculasList(listaPelicula);
            return actorRepo.save(tempA);
        }
    }
    
    public Actor desrelacionarActorPelicula(Integer codigoA, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Actor tempA = actorRepo.findById(codigoA).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempA.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el actor participa en la pelicula, la desrelaciona
            listaPelicula.remove(tempP);
            tempA.setPeliculasList(listaPelicula);
            return actorRepo.save(tempA);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El actor no participa en esa pelicula");
        }
    }
    
    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    public List<Map<String, Object>> mostrarInfoActor() {
        List<Tuple> tuples = actorRepo.mostrarActor();
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

    public List<Actor> ordenarActorA() {
        return actorRepo.ordenarA();
    }

    public List<Actor> ordenarActorD() {
        return actorRepo.ordenarD();
    }

    public List<Map<String, Object>> mostrarNacionalidad(String nac) {
        List<Tuple> tuples = actorRepo.nacionalidadActor(nac);
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
        List<Tuple> tuples = actorRepo.sexoActor(miSexo);
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
    
    public List<Map<String, Object>> mostrarActorMasDeX(int nume) {
        List<Tuple> tuples = actorRepo.actorMasDeXPeliculas(nume);
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
