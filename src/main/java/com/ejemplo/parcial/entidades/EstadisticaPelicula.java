package com.ejemplo.parcial.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Andres Felipe
 */
@Entity
@Table(name = "estadisticas_peliculas")
public class EstadisticaPelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_pel")
    private Integer codPel;
    
    @Column(name = "titulo")
    private String titulo;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_popularidad")
    private BigDecimal porcentajePopularidad;

    public EstadisticaPelicula() {
    }

    public EstadisticaPelicula(Integer codPel) {
        this.codPel = codPel;
    }

    public EstadisticaPelicula(Integer codPel, String titulo, BigDecimal porcentajePopularidad) {
        this.codPel = codPel;
        this.titulo = titulo;
        this.porcentajePopularidad = porcentajePopularidad;
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

    public BigDecimal getPorcentajePopularidad() {
        return porcentajePopularidad;
    }

    public void setPorcentajePopularidad(BigDecimal porcentajePopularidad) {
        this.porcentajePopularidad = porcentajePopularidad;
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
        if (!(object instanceof EstadisticaPelicula)) {
            return false;
        }
        EstadisticaPelicula other = (EstadisticaPelicula) object;
        if ((this.codPel == null && other.codPel != null) || (this.codPel != null && !this.codPel.equals(other.codPel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.parcial.entidades.EstadisticasPeliculas[ codPel=" + codPel + " ]";
    }
    
}
