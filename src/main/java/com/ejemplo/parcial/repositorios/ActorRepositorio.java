package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.Actor;
import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepositorio extends JpaRepository<Actor, Integer> {
    
    @Query("SELECT a FROM Actor a ORDER BY codAct ASC")
    public List<Actor> obtenerTodos();
    
    @Query("SELECT COUNT(ac) FROM Actor ac")
    public Integer contarActores();
    
    
    
    @Query(value = "SELECT * FROM actores ac"
            +" ORDER BY ac.nombre ASC",
            nativeQuery = true)
    public List<Actor> ordenarA();
    
    @Query(value = "SELECT * FROM actores ac"
            +" ORDER BY ac.nombre DESC",
            nativeQuery = true)
    public List<Actor> ordenarD();
    
    @Query(value = "SELECT ac.cod_act,"
            + " ac.nombre,"
            + " date_part('year', age(ac.nacimiento)),"
            + " ac.sexo,"
            + " ac.nacionalidad"
            + " FROM actores ac"
            + " ORDER BY ac.cod_act ASC",
            nativeQuery = true)
    public List<Tuple> mostrarActor();

    @Query(value = "SELECT ac.cod_act,"
            + " ac.nombre,"
            + " date_part('year', age(ac.nacimiento)),"
            + " ac.sexo"
            + " FROM actores ac"
            + " WHERE ac.nacionalidad = %:nac%",
            nativeQuery = true)
    public List<Tuple> nacionalidadActor(String nac);

    @Query(value = "SELECT ac.cod_act,"
            + " ac.nombre,"
            + " date_part('year', age(ac.nacimiento)),"
            + " ac.nacionalidad"
            + " FROM actores ac"
            + " WHERE ac.sexo = %:miSexo%",
            nativeQuery = true)
    public List<Tuple> sexoActor(Character miSexo);
    
    
    @Query(value = "SELECT * FROM ("
            +" SELECT ac.cod_act AS codigo,"
            +" ac.nombre AS nombre,"
            +" COUNT(pa.cod_pelicula) AS numero"
            +" FROM actores AS ac"
            + " JOIN peliculas_actores AS pa"
            +  " ON ac.cod_act = pa.cod_actor"
            + " GROUP BY ac.cod_act) as foo"
            +" WHERE foo.numero >= %:nume%",
            nativeQuery = true)
    public List<Tuple> actorMasDeXPeliculas(int nume);
}
