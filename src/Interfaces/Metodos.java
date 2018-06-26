/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author Roberto
 * El objetivo de esta mierda para establecer los metodos para:
 *      -Insertar
 *      -Eliminar
 *      -Buscar
 *      -Actualizar
 */
public interface Metodos <Generic> {
    public boolean create(Generic variable);
    public boolean delete (Object key);
    public boolean update (Generic c);
    
    public Generic read(Object key);
    public ArrayList<Generic> readAll();
}