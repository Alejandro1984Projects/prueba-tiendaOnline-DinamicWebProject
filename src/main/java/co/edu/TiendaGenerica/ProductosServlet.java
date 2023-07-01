package co.edu.TiendaGenerica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import co.edu.TiendaGenerica.dao.ProductosDAO;
import co.edu.TiendaGenerica.dao.ProveedoresDAO;

/**
 * Servlet implementation class ProductosServlet
 */
@WebServlet("/ProductosServlet")
@MultipartConfig
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Part p=request.getPart("entradaarchivo");
			BufferedReader br= new BufferedReader(new InputStreamReader(p.getInputStream()));
			String nombrearchivo=p.getSubmittedFileName();
			
			if(nombrearchivo!="") {
			char letra3=nombrearchivo.charAt(nombrearchivo.length()-1);
			char letra2=nombrearchivo.charAt(nombrearchivo.length()-2);
			char letra1=nombrearchivo.charAt(nombrearchivo.length()-3);
			String tipodearchivo="";
			tipodearchivo+=letra1;
			tipodearchivo+=letra2;
			tipodearchivo+=letra3;

			if(tipodearchivo.equals("csv")) {
				
				String linea="";
				String lineas[]=null;
				ArrayList<String[]> arraylineas=new ArrayList<>();

				while((linea=br.readLine())!=null) {
					lineas=linea.split(",");
					arraylineas.add(lineas);
				}
				if(arraylineas.size()!=0) {
					revisarCSV(request, response,arraylineas);
					}else {
						try {
			            	String revisar="Error: datos leidos invalidos";
				        	request.setAttribute("respuesta",revisar);
				            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
				            rd.forward(request, response);
				        }catch(Exception e){}
					}
			}else {
				try {
	            	String revisar="Error: formato de archivo invalido";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
			}else {
				try {
	            	String revisar="Error: no se selecciono archivo para cargar";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
			
			br.close();
			}catch(Exception e) {}
	}
	public void revisarCSV(HttpServletRequest request,HttpServletResponse response,ArrayList<String[]> arraylineas) {
		int cantidad=0;
		String consulta="";	
		boolean consultaNIT=false;
		for(String lineas[]:arraylineas) {
			int codigo=0;
			double iva=0;
			int nit=0;
			String nombre="";
			double compra=0;
			double venta=0;
			if(lineas.length==6) {				
				try {
					codigo=Integer.parseInt(lineas[0]);
					iva=Double.parseDouble(lineas[4]);
					nit=Integer.parseInt(lineas[2]);
					nombre=lineas[1];
					compra=Double.parseDouble(lineas[3]);
					venta=Double.parseDouble(lineas[5]);
				}catch(Exception e) {}
				try {
					ProveedoresDAO proveedorDAO=new ProveedoresDAO();
					consulta=proveedorDAO.buscar(nit);
					if(consulta=="") {
						consultaNIT=true;
					}
				}catch(Exception e) {}
				if(codigo!=0 && iva!=0 && nit!=0 && !nombre.equals("") && compra!=0 && venta!=0 && !consulta.equals("")) {
					cantidad++;
				}
			}
			
		}
		if(cantidad==arraylineas.size()) {
			ProductosDAO productoDAO=new ProductosDAO();
			for(String lineas[]:arraylineas) {
				
				productoDAO.crear(Integer.parseInt(lineas[0]),Double.parseDouble(lineas[4]),Integer.parseInt(lineas[2]),lineas[1],Double.parseDouble(lineas[3]),Double.parseDouble(lineas[5]));
			}
			try {
            	String revisar="Archivo Cargado Exitosamente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}	
		}
		else
		if(consultaNIT) {
			try {
            	String revisar="Error: NIT de Proveedor Inexistente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
		else {
			try {
            	String revisar="Error: datos leidos invalidos";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}

}
