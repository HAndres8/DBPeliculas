package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.Actor;
import com.ejemplo.parcial.servicios.ActorServicio;
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
@RequestMapping("/actores")
@CrossOrigin(origins = "*")
public class ActorControlador {
    
    @Autowired
    private ActorServicio actorServi;
    
    // El metodo muestra la informacion del actor con la edad en vez de la fecha de nacimiento
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<Map<String, Object>> infoActor() {
        return actorServi.mostrarInfoActor();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Actor> obtenerTodosActores() {
        return actorServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Actor> agregarActor(@RequestBody Actor miObjeto) {
        if (actorServi.agregar(miObjeto)) {
            return ResponseEntity.ok(miObjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseStatus(code = HttpStatus.OK, reason = "Actor eliminado correctamente")
    @RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
    public void eliminarActor(@PathVariable Integer codigo) {
        actorServi.eliminar(codigo);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public Actor buscarActor(@PathVariable Integer codigo) {
        return actorServi.buscar(codigo);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Actor actualizado correctamente")
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public Boolean actualizarActor(@RequestBody Actor miObjeto) {
        return actorServi.actualizar(miObjeto);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Actor relacionado correctamente")
    @RequestMapping(value = "/relacionar/{codigoA}/{codigoP}", method = RequestMethod.PUT)
    public Actor relacionarActor(@PathVariable Integer codigoA, @PathVariable Integer codigoP) {
        return actorServi.relacionarActorPelicula(codigoA,codigoP);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Actor desrelacionado correctamente")
    @RequestMapping(value = "/desrelacionar/{codigoA}/{codigoP}", method = RequestMethod.PUT)
    public Actor desrelacionarActor(@PathVariable Integer codigoA, @PathVariable Integer codigoP) {
        return actorServi.desrelacionarActorPelicula(codigoA,codigoP);
    }

    
    
    // OTROS METODOS
    
    
    // El metodo muestra el numero de actores que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarActores() {
        return actorServi.cantidadRegistros();
    }
    
    // El metodo muestra los actores por orden ascendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenarasc", method = RequestMethod.GET)
    public List<Actor> ordenarActorA() {
        return actorServi.ordenarActorA();
    }
    
    // El metodo muestra los actores por orden descendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenardes", method = RequestMethod.GET)
    public List<Actor> ordenarActorD() {
        return actorServi.ordenarActorD();
    }
    
    // El metodo muestra los actores de cierta nacionalidad
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/nacionalidad/{nac}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarNacionalidad(@PathVariable String nac) {
        return actorServi.mostrarNacionalidad(nac);
    }
    
    // El metodo muestra los actores de cierto sexo
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sexo/{miSexo}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarSexo(@PathVariable Character miSexo) {
        return actorServi.mostrarSexo(miSexo);
    }
    
    // El metodo muestra los actores que han participado en mas de X peliculas
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/masde/{nume}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarActoresMasDeXPeliculas(@PathVariable int nume) {
        return actorServi.mostrarActorMasDeX(nume);
    }
    
}
