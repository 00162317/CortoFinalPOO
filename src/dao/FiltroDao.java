/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Inscripcion;
/**
 *
 * @author LN710Q
 */

public class FiltroDao implements Metodos<Inscripcion> {
    //Creando nuestra querys 
    private static final String SQL_INSERT="INSERT INTO alumnos(carnet,nombres,apellidos,edad,universidad) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE alumnos SET nombres = ?, apellidos=?, edad=?,universidad=?, WHERE carnet=?";
    private static final String SQL_DELETE="DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL_READ="SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL="SELECT*FROM alumnos";
    private static final Conexion con = Conexion.conectar();
    @Override
    public boolean create(Inscripcion g) {
        //Nos servira para preparar la consulta de INSERT 
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombres());
            ps.setString(2,g.getApellidos());
            ps.setInt(3, g.getEdad());
            ps.setInt(4, g.getId());
            ps.setBoolean(5,true);
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
             ps=con.getCnx().prepareStatement(SQL_DELETE);
             ps.setString(1, key.toString());
             if(ps.executeUpdate()>0){
                 return true;
             }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Inscripcion c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getId());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombres());
            ps.setInt(2, c.getEdad());
            ps.setBoolean(3, c.isExistencia());
            ps.setString(4,c.getApellidos());
            ps.setInt(5, c.getId());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }

    @Override
    public Inscripcion read(Object key) {
        PreparedStatement ps;
        Inscripcion f = null;
        ResultSet rs;
         try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs=ps.executeQuery();
            while(rs.next()){
                f=new Inscripcion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5));
            }
            rs.close();
         }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return f;
    }

    @Override
    public ArrayList<Inscripcion> readAll() {
          ArrayList<Inscripcion> all = new ArrayList();
          Statement s;
          ResultSet rs;
          try{
              s=con.getCnx().prepareStatement(SQL_READALL);
              rs=s.executeQuery(SQL_READALL);
              while(rs.next()){
                  all.add(new Inscripcion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
              }
              rs.close();
          }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);        
    }
          return all;
    }
}
