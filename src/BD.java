/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MEMO0464
 */
public class BD {
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;

    public BD() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_practica1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","root");
            transaccion = conexion.createStatement();   
        }catch(SQLException ex){
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertar(Producto producto){
        String SQL_Insert = "INSERT INTO PRODUCTO VALUES(NULL, '%DES%','%PRE%','%EXI%')";
        
        SQL_Insert = SQL_Insert.replaceAll("%DES%", producto.descripcion);
        SQL_Insert = SQL_Insert.replaceAll("%PRE%", producto.precio+"");
        SQL_Insert = SQL_Insert.replaceAll("%EXI%", producto.existencia+"");

        
        try {
            transaccion.execute(SQL_Insert);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public ArrayList<String[]> consultarTodos(){
        ArrayList<String[]> resultado = new ArrayList<String[]>();
        
        try {
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTO");
            if(cursor.next()){
                do{
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
                    resultado.add(renglon);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public Producto consultarID(String ID){
        Producto productoResultado = new Producto();
        
        try {
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTO WHERE IDPRODUCTOS="+ID);
            if(cursor.next()){
                productoResultado.descripcion = cursor.getString(2);
                productoResultado.precio = cursor.getFloat(3);
                productoResultado.existencia = cursor.getInt(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productoResultado;
    }
    
    public boolean eliminar(String ID){
        try {
            transaccion.execute("DELETE FROM PRODUCTO WHERE IDPRODUCTOS="+ID);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Producto producto){
        String uptate = "UPDATE PRODUCTO SET DESCRIPCION='%DES%',PRECIO='%PRE%',EXISTENCIA='%EXI%' WHERE IDPRODUCTOS="+producto.id;
        
        uptate = uptate.replaceAll("%DES%", producto.descripcion);
        uptate = uptate.replaceAll("%PRE%", producto.precio+"");
        uptate = uptate.replaceAll("%EXI%", producto.existencia+"");
        
        
        try {
            transaccion.executeUpdate(uptate);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
}
