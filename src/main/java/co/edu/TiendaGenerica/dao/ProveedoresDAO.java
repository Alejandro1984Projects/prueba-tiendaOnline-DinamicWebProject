package co.edu.TiendaGenerica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.TiendaGenerica.conexion.Conexion;

public class ProveedoresDAO {
	public void actualizar(int nit,String ciudad,String direccion,String nombre,String telefono) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			String sql="UPDATE proveedores SET ";
	        sql+="ciudad_proveedor = ?, ";
	        sql+="direccion_proveedor = ?, ";
	        sql+="nombre_proveedor = ?, ";
	        sql+="telefono_proveedor = ? ";
	        sql+="WHERE nitproveedor = ?";
	        PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1, ciudad);
            ps.setString(2, direccion);
            ps.setString(3, nombre);
            ps.setString(4, telefono);
            ps.setInt(5, nit);
            ps.executeUpdate();
            c.close();
		}catch(Exception e){}
	}
	public void borrar(int nit) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("DELETE FROM proveedores WHERE nitproveedor = ?");
            ps.setInt(1, nit);
            ps.executeUpdate();
            c.close();
		}catch(Exception e){}
	}
	public String buscar(int nit) {
		String consulta="";
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("SELECT * FROM proveedores WHERE nitproveedor = ?");
            ps.setInt(1, nit);
            ResultSet rs=ps.executeQuery();
            rs.next();
            consulta+="nit: "+rs.getInt("nitproveedor")+" ";
            consulta+="ciudad: "+rs.getString("ciudad_proveedor")+" ";
            consulta+="direccion: "+rs.getString("direccion_proveedor")+" ";
            consulta+="nombre: "+rs.getString("nombre_proveedor")+" ";
            consulta+="telefono: "+rs.getString("telefono_proveedor")+" ";
            c.close();
		}catch(Exception e){}
		return consulta;
	}
	public void crear(int nit,String ciudad,String direccion,String nombre,String telefono) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("INSERT INTO proveedores VALUES (?,?,?,?,?)");
	        ps.setInt(1, nit);
	        ps.setString(2, ciudad);
	        ps.setString(3, direccion);
	        ps.setString(4, nombre);
	        ps.setString(5, telefono);
	        ps.executeUpdate();
	        c.close();
		}catch(Exception e){}
	}

}
