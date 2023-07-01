package co.edu.TiendaGenerica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.TiendaGenerica.conexion.Conexion;

public class ClientesDAO {
	public void actualizar(int cedula, String direccion, String correo, String nombre, String telefono) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			String sql="UPDATE clientes SET ";
	        sql+="direccion_cliente = ?, ";
	        sql+="email_cliente = ?, ";
	        sql+="nombre_cliente = ?, ";
	        sql+="telefono_cliente = ? ";
	        sql+="WHERE cedula_cliente = ?";
	        
	            PreparedStatement ps=c.prepareStatement(sql);
	            ps.setString(1, direccion);
	            ps.setString(2, correo);
	            ps.setString(3, nombre);
	            ps.setString(4, telefono);
	            ps.setInt(5, cedula);
	            ps.executeUpdate();
	        c.close();
		}catch(Exception e){}
	}
	public void borrar(int cedula) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("DELETE FROM clientes WHERE cedula_cliente = ?");
            ps.setInt(1, cedula);
            ps.executeUpdate();
            c.close();
		}catch(Exception e){}
	}
	public String buscar(int cedula) {
		String consulta="";
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("SELECT * FROM clientes WHERE cedula_cliente = ?");
            ps.setInt(1, cedula);
            ResultSet rs=ps.executeQuery();
            rs.next();
            consulta+="cedula: "+rs.getInt("cedula_cliente")+" ";
            consulta+="direccion: "+rs.getString("direccion_cliente")+" ";
            consulta+="correo: "+rs.getString("email_cliente")+" ";
            consulta+="nombre: "+rs.getString("nombre_cliente")+" ";
            consulta+="telefono: "+rs.getString("telefono_cliente")+" ";
            c.close();
		}catch(Exception e){}
		return consulta;
	}
	public String buscarNombre(int cedula) {
		String consulta="";
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("SELECT * FROM clientes WHERE cedula_cliente = ?");
            ps.setInt(1, cedula);
            ResultSet rs=ps.executeQuery();
            rs.next();
            consulta+=rs.getString("nombre_cliente")+" ";
            c.close();
		}catch(Exception e){}
		return consulta;
	}
	public ArrayList<String> buscarTodos(){
		Conexion con=new Conexion();
		Connection c=con.conexion();
		ArrayList<String> lista=new ArrayList<>();
		try {
			Statement st=c.createStatement();
	           ResultSet rs=st.executeQuery("SELECT * FROM clientes");
	           while(rs.next()){
	        	   String consulta="";
	        	   consulta+="cedula: "+rs.getInt("cedula_cliente")+" ";
	               consulta+="direccion: "+rs.getString("direccion_cliente")+" ";
	               consulta+="correo: "+rs.getString("email_cliente")+" ";
	               consulta+="nombre: "+rs.getString("nombre_cliente")+" ";
	               consulta+="telefono: "+rs.getString("telefono_cliente")+" ";
	               lista.add(consulta);	           
	            }
	           c.close();
		}catch(Exception e){}
		return lista;
	}
	public void crear(int cedula, String direccion, String correo, String nombre, String telefono) {
	try {
		Conexion con=new Conexion();
		Connection c=con.conexion();
		PreparedStatement ps=c.prepareStatement("INSERT INTO clientes VALUES (?,?,?,?,?)");
        ps.setInt(1, cedula);
        ps.setString(2, direccion);
        ps.setString(3, correo);
        ps.setString(4, nombre);
        ps.setString(5, telefono);
       
        ps.executeUpdate();
        c.close();
	}catch(Exception e){}
}
}
