/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import com.mysql.jdbc.Connection;
import com.sun.istack.internal.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 *
 * @author LN710Q
 * Esta nos permite abrir un canal para poder establecer la comunicacion con la BASE
 */
public class Conexion {
    /*
    DriverManager.getConnection()
    Este devuelve un objeto de tipo CONNECTION y recibe como paramaetros:
        -URL : jdbc:mysql://localhost/nombre_bd
        -User : Usuario de la bd.
        -Pass : Es la contraseña del usuario de User.
    
    */
    private String user,pass,url,driver;
    private Connection conex;//Variable de tipo Connection : import com.mysql.jdbc.Connection
    public static Conexion instancia;//Para el singleton
    public synchronized static Conexion conectar(){
        if(instancia == null){
            return new Conexion();
        }
        return instancia;
    } 
    private void cargarCredenciales(){
        user = "root";
        pass="";
        driver = "com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost/labofinal";
    }
    private Conexion(){
        cargarCredenciales();
        try{
            //Le enviamos el driver que usara para la conexion a la base de datos
            Class.forName(this.driver);
            conex=(Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        }
        catch (ClassNotFoundException | SQLException error){
            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,error); //QUE PEDO CON ESTA MIERDA? 
            Logger.getLogger(Conexion.class).log(Level.SEVERE, null,error);
}
    }
    public Connection getConex(){
        return this.conex;
    }
    public void cerrarConexion(){
        instancia = null;
    }
}
