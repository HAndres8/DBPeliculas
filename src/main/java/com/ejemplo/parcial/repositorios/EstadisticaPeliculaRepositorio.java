package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.EstadisticaPelicula;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaPeliculaRepositorio extends JpaRepository<EstadisticaPelicula, Integer>{
    
    @Query("SELECT ep FROM EstadisticaPelicula ep ORDER BY codPel ASC")
    public List<EstadisticaPelicula> obtenerTodas();
    
    @Query("SELECT COUNT(ep) FROM EstadisticaPelicula ep")
    public Integer contarDatos();
    
    //mostrar mas y menos de cierto rango
    
    @Query("SELECT ep FROM EstadisticaPelicula ep WHERE ep.porcentajePopularidad >= %:porc%")
    public List<EstadisticaPelicula> obtenerMas(BigDecimal porc);
    
    @Query("SELECT ep FROM EstadisticaPelicula ep WHERE ep.porcentajePopularidad < %:porc%")
    public List<EstadisticaPelicula> obtenerMenos(BigDecimal porc);
}
