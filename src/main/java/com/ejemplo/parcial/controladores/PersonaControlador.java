package com.ejemplo.parcial.controladores;

import com.ejemplo.parcial.entidades.Persona;
import com.ejemplo.parcial.servicios.PersonaServicio;
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
@RequestMapping("/personas")
@CrossOrigin(origins = "*")
public class PersonaControlador {
    
    @Autowired
    private PersonaServicio personaServi;
    
    // El metodo muestra la informacion de la persona con la edad en vez de la fecha de nacimiento
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<Map<String, Object>> infoPersona() {
        return personaServi.mostrarInfoPersona();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/todas", method = RequestMethod.GET)
    public List<Persona> obtenerTodasPersonas() {
        return personaServi.consultar();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Persona> agregarPersona(@RequestBody Persona miObjeto) {
        if (personaServi.agregar(miObjeto)) {
            return ResponseEntity.ok(miObjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseStatus(code = HttpStatus.OK, reason = "Persona eliminada correctamente")
    @RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
    public void eliminarPersona(@PathVariable Integer codigo) {
        personaServi.eliminar(codigo);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/buscar/{codigo}", method = RequestMethod.GET)
    public Persona buscarPersona(@PathVariable Integer codigo) {
        return personaServi.buscar(codigo);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Persona actualizada correctamente")
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public Boolean actualizarPersona(@RequestBody Persona miObjeto) {
        return personaServi.actualizar(miObjeto);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Persona relacionada correctamente")
    @RequestMapping(value = "/relacionar/{codigoPr}/{codigoP}", method = RequestMethod.PUT)
    public Persona relacionarPersona(@PathVariable Integer codigoPr, @PathVariable Integer codigoP) {
        return personaServi.relacionarPersonaPelicula(codigoPr,codigoP);
    }
    
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Persona desrelacionada correctamente")
    @RequestMapping(value = "/desrelacionar/{codigoPr}/{codigoP}", method = RequestMethod.PUT)
    public Persona desrelacionarPersona(@PathVariable Integer codigoPr, @PathVariable Integer codigoP) {
        return personaServi.desrelacionarPersonaPelicula(codigoPr,codigoP);
    }
    
    
    
    // OTROS METODOS
    
    
    // El metodo muestra el numero de personas que hay, entrega el numero y no un texto
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/contar", method = RequestMethod.GET)
    public Integer contarPersonas() {
        return personaServi.cantidadRegistros();
    }
    
    // El metodo muestra las personas por orden ascendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenarasc", method = RequestMethod.GET)
    public List<Persona> ordenarPersonaA() {
        return personaServi.ordenarPersonaA();
    }
    
    // El metodo muestra las personas por orden descendente segun el nombre
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ordenardes", method = RequestMethod.GET)
    public List<Persona> ordenarPersonaD() {
        return personaServi.ordenarPersonaD();
    }
    
    // El metodo muestra las personas de cierto sexo
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sexo/{miSexo}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarSexo(@PathVariable Character miSexo) {
        return personaServi.mostrarSexo(miSexo);
    }
    
    // El metodo muestra las personas que han visto en mas de X peliculas
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/masde/{nume}", method = RequestMethod.GET)
    public List<Map<String, Object>> mostrarPersonasMasDeXPeliculas(@PathVariable int nume) {
        return personaServi.mostrarPersonaMasDeX(nume);
    }
}
