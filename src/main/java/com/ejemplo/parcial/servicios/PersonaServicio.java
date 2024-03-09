package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.entidades.Persona;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.PeliculaRepositorio;
import com.ejemplo.parcial.repositorios.PersonaRepositorio;
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

@Service("PersonaService")
public class PersonaServicio implements Operaciones<Persona> {
    
    @Autowired
    private PersonaRepositorio personaRepo;
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    
    @Override
    public List<Persona> consultar() {
        return personaRepo.obtenerTodas();
    }

    @Override
    public Boolean agregar(Persona miObjeto) {
        Optional<Persona> objTemp = personaRepo.findById(miObjeto.getCodPer());
        if(objTemp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo de esa persona ya existe.");
        } else {
            personaRepo.save(miObjeto);
            return true;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        if(personaRepo.existsById(llavePrimaria)){
            personaRepo.deleteById(llavePrimaria);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta persona no existe");

        }
    }
    
    @Override
    public Persona buscar(Integer llavePrimaria) {
        return personaRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Boolean actualizar(Persona miObjeto) {
        Optional<Persona> objTemp = personaRepo.findById(miObjeto.getCodPer());
        if(objTemp.isPresent()) {
            personaRepo.save(miObjeto);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Te Quedo Mal el Json");
        }
    }
    
    @Override
    public Integer cantidadRegistros() {
        return personaRepo.contarPersonas();
    }

    
    public Persona relacionarPersonaPelicula(Integer codigoPr, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Persona tempPr = personaRepo.findById(codigoPr).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempPr.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si la persona ya vio la pelicula, no puede relacionarlo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La persona ya ha visto esa pelicula");
        }else{
            listaPelicula.add(tempP);
            tempPr.setPeliculasList(listaPelicula);
            return personaRepo.save(tempPr);
        }
    }
    
    public Persona desrelacionarPersonaPelicula(Integer codigoPr, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Persona tempPr = personaRepo.findById(codigoPr).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempPr.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si la persona ya vio la pelicula,puede desrelacionarlo
            listaPelicula.remove(tempP);
            tempPr.setPeliculasList(listaPelicula);
            return personaRepo.save(tempPr);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La persona no ha visto esa pelicula");
        }
    }
    
    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    public List<Map<String, Object>> mostrarInfoPersona() {
        List<Tuple> tuples = personaRepo.mostrarPersona();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Correo", tuple.get(2, String.class));
            mapa.put("Edad", tuple.get(3, Double.class));
            mapa.put("Sexo", tuple.get(4, Character.class));
            result.add(mapa);
        }
        return result;
    }

    public List<Persona> ordenarPersonaA() {
        return personaRepo.ordenarA();
    }

    public List<Persona> ordenarPersonaD() {
        return personaRepo.ordenarD();
    }
    
    public List<Map<String, Object>> mostrarSexo(Character miSexo) {
        List<Tuple> tuples = personaRepo.sexoPersona(miSexo);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Codigo", tuple.get(0, Integer.class));
            mapa.put("Nombre", tuple.get(1, String.class));
            mapa.put("Correo", tuple.get(2, String.class));
            mapa.put("Edad", tuple.get(3, Double.class));
            result.add(mapa);
        }
        return result;
    }
    
    public List<Map<String, Object>> mostrarPersonaMasDeX(int nume) {
        List<Tuple> tuples = personaRepo.personaMasDeXPeliculas(nume);
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
