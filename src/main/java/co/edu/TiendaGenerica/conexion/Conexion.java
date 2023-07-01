package co.edu.TiendaGenerica.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public Connection conexion(){
        Connection conectar=null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mariadb://tiendavirtualjrgs.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/tiendagenerica_grupo17_E5","admin","820420044878");
        }catch(Exception e){e.printStackTrace();}
        return conectar;
    }
}
