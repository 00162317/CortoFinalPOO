/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Conexion.Conexion;
import Entidades.Alumnos;
import Interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 *  * Objeto: PreparedStatement
 * Objeto: Satament
 * Objeto: ResultSet
 */
public class AlumnosDao implements Metodos<Alumnos>/*Tomo mis metodos y los hago de tipo ALUMNOS*/{

    private static final String SQL_INSERT = "INSERT INTO alumnos(carnet,nombres,apellidos,edad,universidad,estado) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE alumnos SET nombres=?, apellidos=?,edad=?,universidad=?,estado=? WHERE carnet = ?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL_READ = " SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL = "SELECT * FROM alumnos";
    private static final Conexion con = Conexion.conectar();
    @Override
    public boolean create(Alumnos variable) {
        /*
        El PREPAREDSTATEMENT se ocupa para consultas parametrizadas 
        para asi poder retornar un objeto de tipo RESULTSET
        */
        PreparedStatement consultaCrear;
        try{
            /*
            Con el metodo ".preparedStatement("SQL") que convierte el 
            valor parametrizado "?" es donde tambien se PREPARA la consulta
            */
            consultaCrear = con.getConex().prepareStatement(SQL_INSERT);
            /*
            Con el ".setTipoDato(posicion,dato). Aqui nos permite cambiar el valor "?" por el dato
            que deseamos enviar a la base de datos. La POSICION comienza en el numero 1
            */
            consultaCrear.setString(1, variable.getCarnet());
            consultaCrear.setString(2, variable.getNombres());
            consultaCrear.setString(3, variable.getApellidos());
            consultaCrear.setInt(4, variable.getEdad());
            consultaCrear.setString(5, variable.getUniversidad());
            consultaCrear.setBoolean(6, true);
            /*
            Ejecutamos la consulta, si fue exitosa nos devolvera un numero mayor a cero
            */
            if(consultaCrear.executeUpdate()>0){
                return true;
            }
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
            Logger.getLogger(AlumnosDao.class.getName()).log(Level.SEVERE,null,error);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement consultaDelete;
        try{
            consultaDelete = con.getConex().prepareStatement(SQL_DELETE);//Preparando la consulta
            consultaDelete.setString(1, key.toString());//Cambiando el valor "?"
            if(consultaDelete.executeUpdate()>0){
                return true;
            }
        }
        catch(SQLException error){
            System.err.println(error.getMessage());
            Logger.getLogger(AlumnosDao.class.getName()).log(Level.SEVERE, null,error);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Alumnos c) {
        PreparedStatement consultaUpdate;
        try{
            //Con el ".getConex" tomo mi conexion
            consultaUpdate=con.getConex().prepareStatement(SQL_UPDATE);//Preparando la consulta
            consultaUpdate.setString(1, c.getNombres());
            consultaUpdate.setString(2, c.getApellidos());
            consultaUpdate.setInt(3, c.getEdad());
            consultaUpdate.setString(4, c.getUniversidad());
            consultaUpdate.setBoolean(5, true);
            if(consultaUpdate.executeUpdate()>0){
                return true;
            }
        }
        catch (SQLException error){
            System.err.println(error.getMessage());
            Logger.getLogger(AlumnosDao.class.getName()).log(Level.SEVERE,null,error);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Alumnos read(Object key) {
         Alumnos variable = null;    
        PreparedStatement consultaRead;
        /*
        El RESULTSET nos funciona para mantener al cursor apuntando a una FILA de una tabla
        Inicialmente, el cursos apunta a ANTES de la primera fila
        */
        ResultSet resultset;
        try{
            consultaRead = con.getConex().prepareStatement(SQL_READ);
            consultaRead.setString(1, key.toString());
            
            resultset = consultaRead.executeQuery();
            /*
            el ".next()" mueve el cursor a la siguiente fila de la tabla
            */
            while(resultset.next()){
                /*
                Extraemos el dato de una columna especifica. Por ende, se ponen TODOS los atributos de la tabla
                    - O se podria recibir como parametro el numero de la columna (ACA SE INICIA DEL UNO, UNOOOO)
                    - O el nombre de la columna
                */
                variable = new Alumnos(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), resultset.getInt(5), resultset.getString(6), resultset.getBoolean(7));

            }
            resultset.close();//Cierra el cursor
        }
        catch(SQLException error){
            System.err.println(error.getMessage());
            Logger.getLogger(AlumnosDao.class.getName()).log(Level.SEVERE, null,error);
        }
        finally{
            con.cerrarConexion();
        }
        return variable;
    }

    @Override
    public ArrayList<Alumnos> readAll() {
        Statement consultaReadAll;
        ArrayList<Alumnos> all  = new ArrayList();
        ResultSet resultset;
        try{
            consultaReadAll = con.getConex().prepareStatement(SQL_READALL);
            resultset = consultaReadAll.executeQuery(SQL_READALL);
            while(resultset.next()){
                all.add(new Alumnos(resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getInt(5),resultset.getString(6),resultset.getBoolean(7)));
            }
            resultset.close();
        }
        catch(SQLException error){
            System.err.println(error.getMessage());
            Logger.getLogger(AlumnosDao.class.getName()).log(Level.SEVERE, null,error);
        }
        finally{
            con.cerrarConexion();
        }
        return all;
    }
}
