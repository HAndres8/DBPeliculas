package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.Director;
import com.ejemplo.parcial.servicios.DirectorServicio;
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
@RequestMapping("/directores")
@CrossOrigin(origins = "*")
public class DirectorControlador {
    
    @Autowired
    private DirectorServicio directorServi;
    
    // El metodo muestra la informacion del director con la edad en vez de la fecha de nacimiento
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<Map<String, Object>> infoDirector() {
        return directorServi.mostrarInfoDirector();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Director> obtenerTodosDirectores() {
        return directorServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Director> agregarDirector(@RequestBody Director miObjeto) {
        if (directorServi.agregar(miObjeto)) {
            return ResponseEntity.ok(miObjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseStatus(code = HttpStatus.OK, reason = "Director eliminado correctamente")
    @RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
    public void eliminarDirector(@PathVariable Integer codigo) {
        directorServi.eliminar(codigo);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public Director buscarDirector(@PathVariable Integer codigo) {
        return directorServi.buscar(codigo);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Director actualizado correctamente")
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public Boolean actualizarDirector(@RequestBody Director miObjeto) {
        return directorServi.actualizar(miObjeto);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Director relacionado correctamente")
    @RequestMapping(value = "/relacionar/{codigoD}/{codigoP}", method = RequestMethod.PUT)
    public Director relacionarDirector(@PathVariable Integer codigoD, @PathVariable Integer codigoP) {
        return directorServi.relacionarDirectorPelicula(codigoD,codigoP);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Director desrelacionado correctamente")
    @RequestMapping(value = "/desrelacionar/{codigoD}/{codigoP}", method = RequestMethod.PUT)
    public Director desrelacionarDirector(@PathVariable Integer codigoD, @PathVariable Integer codigoP) {
        return directorServi.desrelacionarDirectorPelicula(codigoD,codigoP);
    }
    
    
    
    // OTROS METODOS
    
    
    // El metodo muestra el numero de directores que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarDirectores() {
        return directorServi.cantidadRegistros();
    }
    
    // El metodo muestra los directores por orden ascendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenarasc", method = RequestMethod.GET)
    public List<Director> ordenarDirectorA() {
        return directorServi.ordenarDirectorA();
    }
    
    // El metodo muestra los directores por orden descendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenardes", method = RequestMethod.GET)
    public List<Director> ordenarDirectorD() {
        return directorServi.ordenarDirectorD();
    }
    
    // El metodo muestra los directores de cierta nacionalidad
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/nacionalidad/{nac}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarNacionalidad(@PathVariable String nac) {
        return directorServi.mostrarNacionalidad(nac);
    }
    
    // El metodo muestra los directores de cierto sexo
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sexo/{miSexo}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarSexo(@PathVariable Character miSexo) {
        return directorServi.mostrarSexo(miSexo);
    }
    
    // El metodo muestra los directores que han participado en mas de X peliculas
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/masde/{nume}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarDirectoresMasDeXPeliculas(@PathVariable int nume) {
        return directorServi.mostrarDirectorMasDeX(nume);
    }
    
}
