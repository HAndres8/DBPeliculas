package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.Director;
import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepositorio extends JpaRepository<Director, Integer> {
    
    @Query("SELECT d FROM Director d ORDER BY codDir ASC")
    public List<Director> obtenerTodos();
    
    @Query("SELECT COUNT(d) FROM Director d")
    public Integer contarDirectores();
    
    
    
    @Query(value = "SELECT * FROM directores d"
            +" ORDER BY d.nombre ASC",
            nativeQuery = true)
    public List<Director> ordenarA();
    
    @Query(value = "SELECT * FROM directores d"
            +" ORDER BY d.nombre DESC",
            nativeQuery = true)
    public List<Director> ordenarD();
    
    @Query(value = "SELECT d.cod_dir,"
            + " d.nombre,"
            + " date_part('year', age(d.nacimiento)),"
            + " d.sexo,"
            + " d.nacionalidad"
            + " FROM directores d"
            + " ORDER BY d.cod_dir ASC",
            nativeQuery = true)
    public List<Tuple> mostrarDirector();

    @Query(value = "SELECT d.cod_dir,"
            + " d.nombre,"
            + " date_part('year', age(d.nacimiento)),"
            + " d.sexo"
            + " FROM directores d"
            + " WHERE d.nacionalidad = %:nac%",
            nativeQuery = true)
    public List<Tuple> nacionalidadDirector(String nac);

    @Query(value = "SELECT d.cod_dir,"
            + " d.nombre,"
            + " date_part('year', age(d.nacimiento)),"
            + " d.nacionalidad"
            + " FROM directores d"
            + " WHERE d.sexo = %:miSexo%",
            nativeQuery = true)
    public List<Tuple> sexoDirector(Character miSexo);
    
    
    @Query(value = "SELECT * FROM ("
            +" SELECT d.cod_dir AS codigo,"
            +" d.nombre AS nombre,"
            +" COUNT(pd.cod_pelicula) AS numero"
            +" FROM directores AS d"
            + " JOIN peliculas_directores AS pd"
            +  " ON d.cod_dir = pd.cod_director"
            + " GROUP BY d.cod_dir) as foo"
            +" WHERE foo.numero >= %:nume%",
            nativeQuery = true)
    public List<Tuple> directorMasDeXPeliculas(int nume);
}