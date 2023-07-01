package co.edu.TiendaGenerica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.TiendaGenerica.conexion.Conexion;

public class VentasDAO {
	public void crear(int codigo,int cedula,String nombre,Double venta) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("INSERT INTO ventas VALUES (?,?,?,?)");
	        ps.setInt(1, codigo);
	        ps.setInt(2, cedula);
	        ps.setString(3, nombre);
	        ps.setDouble(4, venta);
	       
	        ps.executeUpdate();
	        c.close();
		}catch(Exception e){}
	}
	public ArrayList<String> buscarTodos(){
		Conexion con=new Conexion();
		Connection c=con.conexion();
		ArrayList<String> lista=new ArrayList<>();
		try {
			Statement st=c.createStatement();
	           ResultSet rs=st.executeQuery("SELECT * FROM ventas");
	           while(rs.next()){
	        	   String consulta="";
	        	   consulta+="codigo: "+rs.getInt("codigo_venta")+" ";
	               consulta+="cedula: "+rs.getString("cedula_cliente")+" ";
	               consulta+="nombre: "+rs.getString("nombre_cliente")+" ";
	               consulta+="venta: "+rs.getString("total_venta")+" ";
	               lista.add(consulta);	           
	            }
	           c.close();
		}catch(Exception e){}
		return lista;
	}
}
