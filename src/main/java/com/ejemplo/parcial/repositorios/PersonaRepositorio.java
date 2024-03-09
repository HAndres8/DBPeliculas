package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.Persona;
import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    
    @Query("SELECT pr FROM Persona pr ORDER BY codPer ASC")
    public List<Persona> obtenerTodas();
    
    @Query("SELECT COUNT(pr) FROM Persona pr")
    public Integer contarPersonas();
    
    
    
    @Query(value = "SELECT * FROM personas pr"
            +" ORDER BY pr.nombre ASC",
            nativeQuery = true)
    public List<Persona> ordenarA();
    
    @Query(value = "SELECT * FROM personas pr"
            +" ORDER BY pr.nombre DESC",
            nativeQuery = true)
    public List<Persona> ordenarD();
    
    @Query(value = "SELECT pr.cod_per,"
            + " pr.nombre,"
            + " pr.correo,"
            + " date_part('year', age(pr.nacimiento)),"
            + " pr.sexo"
            + " FROM personas pr"
            + " ORDER BY pr.cod_per ASC",
            nativeQuery = true)
    public List<Tuple> mostrarPersona();

    @Query(value = "SELECT pr.cod_per,"
            + " pr.nombre,"
            + " pr.correo,"
            + " date_part('year', age(pr.nacimiento))"
            + " FROM personas pr"
            + " WHERE pr.sexo = %:miSexo%",
            nativeQuery = true)
    public List<Tuple> sexoPersona(Character miSexo);
    
    @Query(value = "SELECT * FROM ("
            +" SELECT pr.cod_per AS codigo,"
            +" pr.nombre AS nombre,"
            +" COUNT(pp.cod_pelicula) AS numero"
            +" FROM personas AS pr"
            + " JOIN peliculas_personas AS pp"
            +  " ON pr.cod_per = pp.cod_persona"
            + " GROUP BY pr.cod_per) as foo"
            +" WHERE foo.numero >= %:nume%",
            nativeQuery = true)
    public List<Tuple> personaMasDeXPeliculas(int nume);
}
