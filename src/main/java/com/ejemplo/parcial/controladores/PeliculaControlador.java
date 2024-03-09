package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.Pelicula;
import com.ejemplo.parcial.servicios.PeliculaServicio;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaControlador {
    
    @Autowired
    private PeliculaServicio peliculaServi;
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todas", method = RequestMethod.GET)
    public List<Pelicula> obtenerTodasPeliculas() {
        return peliculaServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Pelicula> agregarPelicula(@RequestBody Pelicula miObjeto) {
        if (peliculaServi.agregar(miObjeto)) {
            return ResponseEntity.ok(miObjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseStatus(code = HttpStatus.OK, reason = "Pelicula eliminada correctamente")
    @RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
    public void eliminarPelicula(@PathVariable Integer codigo) {
        peliculaServi.eliminar(codigo);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public Pelicula buscarPelicula(@PathVariable Integer codigo) {
        return peliculaServi.buscar(codigo);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Pelicula actualizada correctamente")
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public Boolean actualizarPelicula(@RequestBody Pelicula miObjeto) {
        return peliculaServi.actualizar(miObjeto);
    }
    
    
    
    // OTROS METODOS
    
    
    // El metodo muestra el numero de peliculas que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarPeliculas() {
        return peliculaServi.cantidadRegistros();
    }
    
    // El metodo muestra las peliculas por orden ascendente segun el titulo
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenarasc", method = RequestMethod.GET)
    public List<Pelicula> ordenarPeliculaA() {
        return peliculaServi.ordenarPeliculasA();
    }
    
    // El metodo muestra las peliculas por orden descendente segun el titulo
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenardes", method = RequestMethod.GET)
    public List<Pelicula> ordenarPeliculaD() {
        return peliculaServi.ordenarPeliculasD();
    }
    
    // El metodo muestra el titulo y el genero de las peliculas que tengan un genero
    // en especifico
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenergenero/{genero}", method = RequestMethod.GET)
    public List<Map<String, Object>> peliculaGeneroX(@PathVariable String genero) {
        return peliculaServi.obtenerPeliculasGeneroX(genero);
    }
    
    // El metodo muestra el titulo de las peliculas de cierto año
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/fecha/{anio}", method = RequestMethod.GET)
    public List<Map<String, Object>> peliculaAnio(@PathVariable int anio) {
        return peliculaServi.obtenerPeliculasAnio(anio);
    }
    
    // El metodo muestra el titulo y año de las peliculas entre cierto rango de años
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/entre/{anioI}/{anioF}", method = RequestMethod.GET)
    public List<Map<String, Object>> peliculaEntre(@PathVariable int anioI, @PathVariable int anioF) {
        return peliculaServi.obtenerPeliculasEntre(anioI,anioF);
    }
    
    // El metodo muestra el titulo y la duracion de las peliculas con menor duracion a la dada
    // La duracion se da en horas y entero
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/duracionhora/{hora}", method = RequestMethod.GET)
    public List<Map<String, Object>> peliculaDuracionMenorA(@PathVariable int hora) {
        return peliculaServi.obtenerPeliculasDuracionMenorA(hora);
    }
    
    // El metodo muestra el titulo y el total recaudado de las peliculas entre cierto rango
    // El rango se trabaja con millones, si son 2 millones solo es escribe 2 en el endpoint
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recaudo/{rMin}/{rMax}", method = RequestMethod.GET)
    public List<Map<String, Object>> peliculaRecaudo(@PathVariable long rMin, @PathVariable long rMax) {
        return peliculaServi.obtenerPeliculasPorRecaudo(rMin,rMax);
    }
}
