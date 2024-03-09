package com.ejemplo.parcial.repositorios;

import com.ejemplo.parcial.entidades.Genero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Integer> {
    
    @Query("SELECT g FROM Genero g ORDER BY codGen ASC")
    public List<Genero> obtenerTodos();
    
    @Query("SELECT COUNT(g) FROM Genero g")
    public Integer contarGeneros();
    
    
    
    @Query(value = "SELECT * FROM generos g"
            +" ORDER BY g.nombre ASC",
            nativeQuery = true)
    public List<Genero> ordenarA();
    
    @Query(value = "SELECT * FROM generos g"
            +" ORDER BY g.nombre DESC",
            nativeQuery = true)
    public List<Genero> ordenarD();
}
