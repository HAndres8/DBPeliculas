package com.ejemplo.parcial.entidades;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andres Felipe
 */
@Entity
@Table(name = "actores")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_act")
    private Integer codAct;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "nacimiento")
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    
    @Column(name = "sexo")
    private Character sexo;
    
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @JsonIncludeProperties({"codPel","titulo"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "peliculas_actores",
        joinColumns = @JoinColumn(name = "cod_actor", referencedColumnName = "cod_act"),
        inverseJoinColumns = @JoinColumn(name = "cod_pelicula", referencedColumnName = "cod_pel")
    )
    private List<Pelicula> peliculasList;

    public Actor() {
    }

    public Actor(Integer codAct) {
        this.codAct = codAct;
    }

    public Actor(Integer codAct, String nombre, Date nacimiento, Character sexo, String nacionalidad) {
        this.codAct = codAct;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
    }

    public Integer getCodAct() {
        return codAct;
    }

    public void setCodAct(Integer codAct) {
        this.codAct = codAct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
        hash += (codAct != null ? codAct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.codAct == null && other.codAct != null) || (this.codAct != null && !this.codAct.equals(other.codAct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.parcial.entidades.Actores[ codAct=" + codAct + " ]";
    }
    
}
