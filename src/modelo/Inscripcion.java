/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */

//Las ENTIDADES de la tablas
public class Inscripcion {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }
    private int id,edad;
    private String nombres, apellidos, universidad;

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    private boolean existencia;

    
    
    public Inscripcion(){
        
    }
    public Inscripcion(int id, String apellido, String marca, int edad, boolean existencia){
        this.id=id;
        this.nombres=marca;
        this.edad=edad;
        this.existencia=existencia;
        this.apellidos=apellido;
    }
    public Inscripcion(String nombre, String apellidos, int edad, boolean existencia){
        this.id=edad;
        this.nombres=apellidos;
        this.edad=edad;
        this.existencia=existencia;
    }
    public Inscripcion(String nombre, int edad, boolean existencia){
        this.nombres=nombre;
        this.edad=edad;
        this.existencia=existencia;
    }
}
