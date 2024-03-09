package com.ejemplo.parcial.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andres Felipe
 */
@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_gen")
    private Integer codGen;
    
    @Column(name = "nombre")
    private String nombre;
    
    @JsonIgnore     //AL LLAMAR GENERO NO MUESTRE LAS PELICULAS
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "peliculas_generos",
        joinColumns = @JoinColumn(name = "cod_genero", referencedColumnName = "cod_gen"),
        inverseJoinColumns = @JoinColumn(name = "cod_pelicula", referencedColumnName = "cod_pel")
    )
    private List<Pelicula> peliculasList;

    public Genero() {
    }

    public Genero(Integer codGen) {
        this.codGen = codGen;
    }

    public Integer getCodGen() {
        return codGen;
    }

    public void setCodGen(Integer codGen) {
        this.codGen = codGen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pelicula> getPeliculasList() {
        return peliculasList;
    }

    public void setPeliculasList(List<Pelicula> peliculasList) {
        this.peliculasList = peliculasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codGen != null ? codGen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.codGen == null && other.codGen != null) || (this.codGen != null && !this.codGen.equals(other.codGen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.parcial.entidades.Generos[ codGen=" + codGen + " ]";
    }
    
}
