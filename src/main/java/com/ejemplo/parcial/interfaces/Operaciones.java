package com.ejemplo.parcial.interfaces;

import java.util.List;

public interface Operaciones<T> {
    // Todos los metodos que se deben crear
    
    public List<T> consultar();
    public Boolean agregar(T miObjeto);
    public Integer cantidadRegistros();
    public Boolean eliminar(Integer llavePrimaria);
    public Boolean actualizar(T miObjeto);
    public T buscar(Integer llavePrimaria);
}
