package co.edu.TiendaGenerica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.TiendaGenerica.conexion.Conexion;

public class UsuariosDAO {
	public void actualizar(int cedula, String correo, String nombre, String password, String usuario) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			String sql="UPDATE usuarios SET ";
	        sql+="email_usuario = ?, ";
	        sql+="nombre_usuario = ?, ";
	        sql+="password = ?, ";
	        sql+="usuario = ? ";
	        sql+="WHERE cedula_usuario = ?";
	        
	            PreparedStatement ps=c.prepareStatement(sql);
	            ps.setString(1, correo);
	            ps.setString(2, nombre);
	            ps.setString(3, password);
	            ps.setString(4, usuario);
	            ps.setInt(5, cedula);
	            ps.executeUpdate();
	        c.close();
		}catch(Exception e){}
	}
	public void borrar(int cedula) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("DELETE FROM usuarios WHERE cedula_usuario = ?");
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
			PreparedStatement ps=c.prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario = ?");
            ps.setInt(1, cedula);
            ResultSet rs=ps.executeQuery();
            rs.next();
            consulta+="cedula: "+rs.getInt("cedula_usuario")+" ";
            consulta+="correo: "+rs.getString("email_usuario")+" ";
            consulta+="nombre: "+rs.getString("nombre_usuario")+" ";
            consulta+="password: "+rs.getString("password")+" ";
            consulta+="usuario: "+rs.getString("usuario")+" ";
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
	           ResultSet rs=st.executeQuery("SELECT * FROM usuarios");
	           while(rs.next()){
	        	   String linea="";
	        	   linea+="cedula: "+rs.getInt("cedula_usuario")+" ";
	        	   linea+="correo: "+rs.getString("email_usuario")+" ";
	        	   linea+="nombre: "+rs.getString("nombre_usuario")+" ";
	        	   linea+="password: "+rs.getString("password")+" ";
	        	   linea+="usuario: "+rs.getString("usuario")+" ";
	               lista.add(linea);	           
	            }
	           c.close();
		}catch(Exception e){}
		return lista;
	}
	public void crear(int cedula, String correo, String nombre, String password, String usuario) {
	try {
		Conexion con=new Conexion();
		Connection c=con.conexion();
		PreparedStatement ps=c.prepareStatement("INSERT INTO usuarios VALUES (?,?,?,?,?)");
        ps.setInt(1, cedula);
        ps.setString(2, correo);
        ps.setString(3, nombre);
        ps.setString(4, password);
        ps.setString(5, usuario);
       
        ps.executeUpdate();
        c.close();
	}catch(Exception e){}
}
}
