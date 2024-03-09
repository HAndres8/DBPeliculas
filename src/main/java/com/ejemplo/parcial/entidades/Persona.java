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
@Table(name = "personas")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_per")
    private Integer codPer;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "nacimiento")
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    
    @Column(name = "sexo")
    private Character sexo;
    
    @JsonIncludeProperties({"codPel","titulo"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "peliculas_personas",
        joinColumns = @JoinColumn(name = "cod_persona", referencedColumnName = "cod_per"),
        inverseJoinColumns = @JoinColumn(name = "cod_pelicula", referencedColumnName = "cod_pel")
    )
    private List<Pelicula> peliculasList;

    public Persona() {
    }

    public Persona(Integer codPer) {
        this.codPer = codPer;
    }

    public Persona(Integer codPer, String nombre, String correo, Date nacimiento, Character sexo) {
        this.codPer = codPer;
        this.nombre = nombre;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
    }

    public Integer getCodPer() {
        return codPer;
    }

    public void setCodPer(Integer codPer) {
        this.codPer = codPer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public List<Pelicula> getPeliculasList() {
        return peliculasList;
    }

    public void setPeliculasList(List<Pelicula> peliculasList) {
        this.peliculasList = peliculasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPer != null ? codPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codPer == null && other.codPer != null) || (this.codPer != null && !this.codPer.equals(other.codPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.parcial.entidades.Personas[ codPer=" + codPer + " ]";
    }
    
}
