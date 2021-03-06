
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
    int id, permiso;
    String usu, cont, nombre, correo; 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public int getPermiso() {
        return permiso;
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }

    public String getUsuario() {
        return usu;
    }

    public void setUsuario(String usuario) {
        this.usu = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        //JOptionPane.showMessageDialog(null, "Usuario: "+this.nombre);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return cont;
    }

    public void setContrasena(String contrasena) {
        this.cont = contrasena;
    }
    
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
        boolean res = false;
        String sql = "select * from usuario";
        try {
            resultado = consultar(sql);
            if(resultado != null){
               while(resultado.next()){
                   usu = resultado.getString(3);
                   cont = resultado.getString(4);
                  if(usu.equals(us) && cont.equals(con)){
                   id = resultado.getInt(1);
                   nombre = resultado.getString(2);
                   usu = resultado.getString(3);
                   cont = resultado.getString(4);
                   correo = resultado.getString(5);
                   permiso = resultado.getInt(6);
                   setId(id);
                   setNombre(nombre);
                   setUsuario(usu);
                   setContrasena(cont);
                   setCorreo(correo);
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
  
    public boolean Newusuario(int id, String nombre, String us, String con, String correo, int permiso){
        boolean valor = true;
        String sql = "INSERT INTO USUARIO (ID, USUARIO, CONTRASENA,NOMBRE, CORREO, PERMISO) VALUES("+
                id+",'"+us+"','"+con+"','"+nombre+"','"+correo+"',"+permiso+")";
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
    public boolean Modificar(int id, String nombre, String us, String con, String correo, int permiso){
        boolean valor = true;
        String sql = "UPDATE USUARIO SET nombre='"+nombre+"', usuario='"+us+"', contrasena='"+con+"', CORREO='"+correo+"', permiso="+permiso
                + " WHERE id="+id;
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
