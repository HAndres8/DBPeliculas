package com.ejemplo.parcial.entidades;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andres Felipe
 */
@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_pel")
    private Integer codPel;
    
    @Column(name = "titulo")
    private String titulo;
    
    
    @JsonIncludeProperties({"codDir","nombre"}) //Trae cod y nombre de director
    @ManyToMany(mappedBy = "peliculasList")
    private List<Director> directoresList;
    
    @JsonIncludeProperties({"codAct"})  //Trae cod actor (para no sobrecargar JSON)
    @ManyToMany(mappedBy = "peliculasList")
    private List<Actor> actoresList;
    
    
    @Column(name = "anio_estreno")
    private int anioEstreno;
    
    @Column(name = "duracion_min")
    private int duracionMin;
    
    
    @JsonIncludeProperties({"nombre"}) //Trae nombre de genero
    @ManyToMany(mappedBy = "peliculasList")
    private List<Genero> generosList;
    
    
    @Column(name = "clasificacion")
    private String clasificacion;
    
    @Column(name = "recaudacion")
    private long recaudacion;
    
    
    @JsonIncludeProperties({"codPer"})  //Trae cod persona (para no sobrecargar JSON)
    @ManyToMany(mappedBy = "peliculasList")
    private List<Persona> personasList;
    

    public Pelicula() {
    }

    public Pelicula(Integer codPel) {
        this.codPel = codPel;
    }

    public Pelicula(Integer codPel, String titulo, int anioEstreno, int duracionMin, String clasificacion, long recaudacion) {
        this.codPel = codPel;
        this.titulo = titulo;
        this.anioEstreno = anioEstreno;
        this.duracionMin = duracionMin;
        this.clasificacion = clasificacion;
        this.recaudacion = recaudacion;
    }

    public Integer getCodPel() {
        return codPel;
    }

    public void setCodPel(Integer codPel) {
        this.codPel = codPel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public long getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(long recaudacion) {
        this.recaudacion = recaudacion;
    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }

    public List<Genero> getGenerosList() {
        return generosList;
    }

    public void setGenerosList(List<Genero> generosList) {
        this.generosList = generosList;
    }

    public List<Actor> getActoresList() {
        return actoresList;
    }

    public void setActoresList(List<Actor> actoresList) {
        this.actoresList = actoresList;
    }

    public List<Director> getDirectoresList() {
        return directoresList;
    }

    public void setDirectoresList(List<Director> directoresList) {
        this.directoresList = directoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPel != null ? codPel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.codPel == null && other.codPel != null) || (this.codPel != null && !this.codPel.equals(other.codPel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.parcial.entidades.Peliculas[ codPel=" + codPel + " ]";
    }
    
}
