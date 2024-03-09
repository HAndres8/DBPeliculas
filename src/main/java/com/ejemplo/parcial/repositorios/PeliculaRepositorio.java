package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.Pelicula;
import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{
    
    @Query("SELECT pel FROM Pelicula pel ORDER BY codPel ASC")
    public List<Pelicula> obtenerTodas();
    
    @Query("SELECT COUNT(pel) FROM Pelicula pel")
    public Integer contarPeliculas();
    
    
    
    @Query(value = "SELECT * FROM peliculas p"
            +" ORDER BY p.titulo ASC",
            nativeQuery = true)
    public List<Pelicula> ordenarA();
    
    @Query(value = "SELECT * FROM peliculas p"
            +" ORDER BY p.titulo DESC",
            nativeQuery = true)
    public List<Pelicula> ordenarD();
    
    @Query(value = "SELECT p.cod_pel, p.titulo, g.nombre"
            +" FROM peliculas p"
            +" JOIN peliculas_generos pg"
            + " ON p.cod_pel = pg.cod_pelicula"
            +" JOIN generos g"
            + " ON g.cod_gen = pg.cod_genero"
            + " AND g.nombre = %:miGenero%",
            nativeQuery = true)
    public List<Tuple> mostrarGeneroX(String miGenero);

    @Query(value = "SELECT p.cod_pel, p.titulo"
            +" FROM peliculas p"
            +" WHERE p.anio_estreno = %:anio%",
            nativeQuery = true)
    public List<Tuple> peliculaAnio(Integer anio);

    @Query(value = "SELECT p.cod_pel, p.titulo, p.anio_estreno"
            +" FROM peliculas p"
            +" WHERE p.anio_estreno between %:anioI% AND %:anioF%",
            nativeQuery = true)
    public List<Tuple> peliculaEntre(int anioI, int anioF);

    @Query(value = "SELECT p.cod_pel, p.titulo, p.duracion_min"
            +" FROM peliculas p"
            +" WHERE p.duracion_min <= (%:hora% * 60)",
            nativeQuery = true)
    public List<Tuple> peliculaDuracionMenor(int hora);

    @Query(value = "SELECT p.cod_pel, p.titulo, p.recaudacion"
            +" FROM peliculas p"
            +" WHERE p.recaudacion between"
            +" (%:rMin% * 1000000) AND (%:rMax% * 1000000)",
            nativeQuery = true)
    public List<Tuple> peliculaPorRecaudo(long rMin, long rMax);

}
