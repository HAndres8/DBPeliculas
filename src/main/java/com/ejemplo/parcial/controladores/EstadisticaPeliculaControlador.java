package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.EstadisticaPelicula;
import com.ejemplo.parcial.servicios.EstadisticaPeliculaServicio;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin(origins = "*")
public class EstadisticaPeliculaControlador {
    
    @Autowired
    private EstadisticaPeliculaServicio estadisticaServi;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todas", method = RequestMethod.GET)
    public List<EstadisticaPelicula> obtenerTodasEstadisticas() {
        return estadisticaServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public EstadisticaPelicula buscarActor(@PathVariable Integer codigo) {
        return estadisticaServi.buscar(codigo);
    }
    
    
    
    // OTROS METODOS
    
    
    
    // El metodo muestra el numero de peliculas que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarDatos() {
        return estadisticaServi.cantidadRegistros();
    }
    
    // El metodo muestra las peliculas con la misma o mas de la popularidad dada
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/masde/{porc}", method = RequestMethod.GET)
    public List<EstadisticaPelicula> mostrarPeliculasMasde(@PathVariable BigDecimal porc) {
        return estadisticaServi.obetenerMas(porc);
    }
    
    // El metodo muestra las peliculas con menos de la popularidad dada
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/menosde/{porc}", method = RequestMethod.GET)
    public List<EstadisticaPelicula> mostrarPeliculasMenosde(@PathVariable BigDecimal porc) {
        return estadisticaServi.obetenerMenos(porc);
    }
}
