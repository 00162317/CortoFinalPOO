/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;


/**
 *
 * @author Roberto
 * Aca van las ENTIDADES del diagrama
 */
public class Alumnos {
    /*
    Aqui van los campos que tiene la tabla
    */
    
    private int id, edad;
    private String carnet, nombres,apellidos, universidad;
    private boolean estado;

    /*
    Se hacen los SET y GETS de cada atributo 
    */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
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

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
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

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean isEstado() {
        return estado;
    }
    /////////////////////////////////////////////////////////////////
    //CONSTRUCTORES
    public Alumnos(){
        
    }
    //Read By
    public Alumnos(String nombres, String apellidos, int edad, String universidad, boolean estado) {
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.edad=edad;
        this.universidad=universidad;
        this.estado=estado;
    }
    //Read all
    public Alumnos(String carnet, String nombres, String apellidos, int edad, String universidad, boolean estado) {
        this.carnet=carnet;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.edad=edad;
        this.universidad=universidad;
        this.estado=estado;
    }
}
