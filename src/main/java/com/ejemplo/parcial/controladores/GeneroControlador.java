package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.Genero;
import com.ejemplo.parcial.servicios.GeneroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generos")
@CrossOrigin(origins = "*")
public class GeneroControlador {
    
    @Autowired
    private GeneroServicio generoServi;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Genero> obtenerTodosGeneros() {
        return generoServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Genero> agregarGenero(@RequestBody Genero miObjeto) {
        if (generoServi.agregar(miObjeto)) {
            return ResponseEntity.ok(miObjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseStatus(code = HttpStatus.OK, reason = "Genero eliminado correctamente")
    @RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
    public void eliminarGenero(@PathVariable Integer codigo) {
        generoServi.eliminar(codigo);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public Genero buscarGenero(@PathVariable Integer codigo) {
        return generoServi.buscar(codigo);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Genero actualizado correctamente")
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public Boolean actualizarGenero(@RequestBody Genero miObjeto) {
        return generoServi.actualizar(miObjeto);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Genero relacionado correctamente")
    @RequestMapping(value = "/relacionar/{codigoG}/{codigoP}", method = RequestMethod.PUT)
    public Genero relacionarGenero(@PathVariable Integer codigoG, @PathVariable Integer codigoP) {
        return generoServi.relacionarGeneroPelicula(codigoG,codigoP);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Genero desrelacionado correctamente")
    @RequestMapping(value = "/desrelacionar/{codigoG}/{codigoP}", method = RequestMethod.PUT)
    public Genero desrelacionarGenero(@PathVariable Integer codigoG, @PathVariable Integer codigoP) {
        return generoServi.desrelacionarGeneroPelicula(codigoG,codigoP);
    }
    
    
    
    // OTROS METODOS
    
    
    // El metodo muestra el numero de generos que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarGeneros() {
        return generoServi.cantidadRegistros();
    }
    
    // El metodo muestra los generos por orden ascendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenarasc", method = RequestMethod.GET)
    public List<Genero> ordenarGeneroA() {
        return generoServi.ordenarGeneroA();
    }
    
    // El metodo muestra los generos por orden descendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenardes", method = RequestMethod.GET)
    public List<Genero> ordenarGeneroD() {
        return generoServi.ordenarGeneroD();
    }
}
