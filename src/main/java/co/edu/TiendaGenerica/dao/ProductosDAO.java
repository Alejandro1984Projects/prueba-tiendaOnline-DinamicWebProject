package co.edu.TiendaGenerica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.TiendaGenerica.conexion.Conexion;

public class ProductosDAO {
	public String buscarNombre(int codigo) {
		String consulta="";
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("SELECT * FROM productos WHERE codigo_producto = ?");
            ps.setInt(1, codigo);
            ResultSet rs=ps.executeQuery();
            rs.next();
            consulta+=rs.getString("nombre_producto")+" ";
            c.close();
		}catch(Exception e){}
		return consulta;
	}
	public double buscarPrecioVenta(int codigo) {
		double precioventa=0;
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("SELECT * FROM productos WHERE codigo_producto = ?");
            ps.setInt(1, codigo);
            ResultSet rs=ps.executeQuery();
            rs.next();
            precioventa=rs.getDouble("precio_venta");
		}catch(Exception e){}
		return precioventa;
	}
	public void crear(int codigo,double iva,int nit,String nombre,double compra,double venta) {
		try {
			Conexion con=new Conexion();
			Connection c=con.conexion();
			PreparedStatement ps=c.prepareStatement("INSERT INTO productos VALUES (?,?,?,?,?,?)");
	        ps.setInt(1, codigo);
	        ps.setDouble(2, iva);
	        ps.setInt(3, nit);
	        ps.setString(4, nombre);
	        ps.setDouble(5, compra);
	        ps.setDouble(6, venta);
	        ps.executeUpdate();
	        c.close();
		}catch(Exception e){}
	}
}
