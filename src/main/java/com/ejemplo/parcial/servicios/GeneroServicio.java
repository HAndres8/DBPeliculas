package com.ejemplo.parcial.servicios;

import com.ejemplo.parcial.entidades.Genero;
import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.interfaces.Operaciones;
import com.ejemplo.parcial.repositorios.GeneroRepositorio;
import com.ejemplo.parcial.repositorios.PeliculaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("GeneroService")
public class GeneroServicio implements Operaciones<Genero> {
    
    @Autowired
    private GeneroRepositorio generoRepo;
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    
    @Override
    public List<Genero> consultar() {
        return generoRepo.obtenerTodos();
    }

    @Override
    public Boolean agregar(Genero miObjeto) {
        Optional<Genero> objTemp = generoRepo.findById(miObjeto.getCodGen());
        if(objTemp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo de ese genero ya existe.");
        } else {
            generoRepo.save(miObjeto);
            return true;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        if(generoRepo.existsById(llavePrimaria)){
            generoRepo.deleteById(llavePrimaria);
            return true;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este genero no existe");

        }
    }
    
    @Override
    public Genero buscar(Integer llavePrimaria) {
        return generoRepo.findById(llavePrimaria).get();
    }
    
    @Override
    public Boolean actualizar(Genero miObjeto) {
        Optional<Genero> objTemp = generoRepo.findById(miObjeto.getCodGen());
        if(objTemp.isPresent()) {
            generoRepo.save(miObjeto);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Te Quedo Mal el Json");
        }
    }
    
    @Override
    public Integer cantidadRegistros() {
        return generoRepo.contarGeneros();
    }

    
    public Genero relacionarGeneroPelicula(Integer codigoG, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Genero tempG = generoRepo.findById(codigoG).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempG.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el genero ya esta en la pelicula, no puede relacionarlo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El genero ya esta en esa pelicula");
        }else{
            listaPelicula.add(tempP);
            tempG.setPeliculasList(listaPelicula);
            return generoRepo.save(tempG);
        }
    }
    
    public Genero desrelacionarGeneroPelicula(Integer codigoG, Integer codigoP) {
        List<Pelicula> listaPelicula;
        Genero tempG = generoRepo.findById(codigoG).get();
        Pelicula tempP = peliculaRepo.findById(codigoP).get();
        listaPelicula = tempG.getPeliculasList();
        
        if(listaPelicula.contains(tempP)){      // Si el genero ya esta en la pelicula, puede desrelacionarlo
            listaPelicula.remove(tempP);
            tempG.setPeliculasList(listaPelicula);
            return generoRepo.save(tempG);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El genero no esta en esa pelicula");
        }
        
    }
    
    
    
    // METODOS CON CONSULTAS PROPIAS
    
    
    public List<Genero> ordenarGeneroA() {
        return generoRepo.ordenarA();
    }

    public List<Genero> ordenarGeneroD() {
        return generoRepo.ordenarD();
    }
}
