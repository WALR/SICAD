
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author walr
 */
public class db extends Conexion{
    
    public boolean insertar(String sql){
        boolean valor = true;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }      
        finally{  
            try{    
                 consulta.close();  
                 conexion.close();  
             }catch (Exception e){                 
                 e.printStackTrace();  
             }  
        }
        return valor;
     }
        
        
    public ResultSet consultar(String sql){
        conectar();
        ResultSet resultado = null;
        try {
            resultado = consulta.executeQuery(sql);

        } catch (SQLException e) {
                System.out.println("Mensaje:"+e.getMessage());
                System.out.println("Estado:"+e.getSQLState());
                System.out.println("Codigo del error:"+e.getErrorCode());
                JOptionPane.showMessageDialog(null, ""+e.getMessage());
            }
        return resultado;
    }
    
    public boolean usuario(String us, String con){
        ResultSet resultado = null;
        control.usuario dat = new control.usuario();
        boolean res = false;
        int id;
        String usu, cont, nombre, correo, sql = "select * from usuario";
        try {
            resultado = consultar(sql);
            if(resultado != null){
               while(resultado.next()){
                   id = resultado.getInt(1);
                   nombre = resultado.getString(2);
                   usu = resultado.getString(3);
                   cont = resultado.getString(4);
                   correo = resultado.getString(4);
                  if(usu.equals(us) && cont.equals(con)){
                  dat.setUsuario(usu);
                  dat.setContrasena(cont);
                  dat.setNombre(nombre);
                  dat.setCorreo(correo);
                  res = true;
                  }
                    
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }   
       
        return res;
    }
    public boolean Newusuario(int id, String nombre, String us, String con, String correo){
        boolean valor = true;
        String sql = "INSERT INTO USUARIO (ID, USUARIO, CONTRASENA,NOMBRE, CORREO) VALUES("+
                id+",'"+us+"','"+con+"','"+nombre+"','"+correo+"')";
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }      
        finally{  
            try{    
                 consulta.close();  
                 conexion.close();  
             }catch (Exception e){                 
                 e.printStackTrace();  
             }  
        }
        return valor;
    }
    public boolean Eliminar(int id){
        boolean valor = true;
        String sql = "DELETE FROM USUARIO WHERE id="+id;
  
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }      
        finally{  
            try{    
                 consulta.close();  
                 conexion.close();  
             }catch (Exception e){                 
                 e.printStackTrace();  
             }  
        }
        return valor;
    }
    public boolean Modificar(int id, String nombre, String us, String con, String correo){
        boolean valor = true;
        String sql = "UPDATE USUARIO SET nombre='"+nombre+"', usuario='"+us+"', contrasena='"+con+"', CORREO='"+correo+"'"
                + "WHERE id="+id;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }      
        finally{  
            try{    
                 consulta.close();  
                 conexion.close();  
             }catch (Exception e){                 
                 e.printStackTrace();  
             }  
        }
        return valor;
    }
    public void totalUsuario(DefaultTableModel tableModel){
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "select * from USUARIO";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                    
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
}
