package co.edu.TiendaGenerica;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.TiendaGenerica.dao.ClientesDAO;
import co.edu.TiendaGenerica.dao.ProductosDAO;
import co.edu.TiendaGenerica.dao.VentasDAO;

/**
 * Servlet implementation class VentasServlet
 */
@WebServlet("/VentasServlet")
public class VentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentasServlet() {
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
		
		int cedula=0;
		String nombre="";
		int codigo1=0;
		int codigo2=0;
		int codigo3=0;
		String nombreproducto1="";
		String nombreproducto2="";
		String nombreproducto3="";
		int cantidad1=0;
		int cantidad2=0;
		int cantidad3=0;
		double precioventa1=0;
		double precioventa2=0;
		double precioventa3=0;
		
		try {
			cedula=Integer.parseInt(request.getParameter("cedula"));
		}catch(Exception e) {}
		if(cedula!=0) {
			try {
				ClientesDAO clienteDAO=new ClientesDAO();
				nombre=clienteDAO.buscarNombre(cedula);
			}catch(Exception e) {}
			if(nombre!="") {
				try {
					codigo1=Integer.parseInt(request.getParameter("codigo1"));
					codigo2=Integer.parseInt(request.getParameter("codigo2"));
					codigo3=Integer.parseInt(request.getParameter("codigo3"));

				}catch(Exception e) {}
				if(codigo1!=0 || codigo2!=0 || codigo3!=0) {
				ProductosDAO productoDAO=new ProductosDAO();
				nombreproducto1=productoDAO.buscarNombre(codigo1);
				nombreproducto2=productoDAO.buscarNombre(codigo2);
				nombreproducto3=productoDAO.buscarNombre(codigo3);
				precioventa1=productoDAO.buscarPrecioVenta(codigo1);
				precioventa2=productoDAO.buscarPrecioVenta(codigo2);
				precioventa3=productoDAO.buscarPrecioVenta(codigo3);
				if(precioventa1!=0 || precioventa2!=0 || precioventa3!=0) {
					try {
						cantidad1=Integer.parseInt(request.getParameter("cantidad1"));
						cantidad2=Integer.parseInt(request.getParameter("cantidad2"));
						cantidad3=Integer.parseInt(request.getParameter("cantidad3"));
					}catch(Exception e) {}
					if(cantidad1!=0 || cantidad2!=0 || cantidad3!=0) {
						VentasDAO ventaDAO=new VentasDAO();
						int codigoventa=ventaDAO.buscarTodos().size()+1;
						double valor1=0;
						double valor2=0;
						double valor3=0;
						double totaliva=0;
						double totalventa=0;
						double totalconiva=0;
						valor1=precioventa1*cantidad1;
						valor2=precioventa2*cantidad2;
						valor3=precioventa3*cantidad3;
						totalventa=valor1+valor2+valor3;
						totaliva=totalventa*0.19;
						totalconiva=totalventa+totaliva;
						ventaDAO.crear(codigoventa, cedula, nombre, totalconiva);
						ArrayList<String> lista=new ArrayList<>();
						lista.add("Cedula Cliente: "+cedula+" "+"Nombre Cliente: "+nombre+" "+"consecutivo: "+codigoventa);
						lista.add("Codigo Producto: "+codigo1+" "+"Nombre Producto: "+nombreproducto1+" "+"Cantidad: "+cantidad1+" "+"Valor Total: "+valor1);
						lista.add("Codigo Producto: "+codigo2+" "+"Nombre Producto: "+nombreproducto2+" "+"Cantidad: "+cantidad2+" "+"Valor Total: "+valor2);
						lista.add("Codigo Producto: "+codigo3+" "+"Nombre Producto: "+nombreproducto3+" "+"Cantidad: "+cantidad3+" "+"Valor Total: "+valor3);
						lista.add("Total Venta: "+totalventa);
						lista.add("Total Iva: "+totaliva);
						lista.add("Total con Iva: "+totalconiva);
						try {
				        	request.setAttribute("respuesta",lista);
				            RequestDispatcher rd=getServletContext().getRequestDispatcher("/RespuestaReportes.jsp");
				            rd.forward(request, response);
				            
				        }catch(Exception e){e.printStackTrace();}
					}else {
						try {
			            	String revisar="El valor de cantidad es incorrecto";
				        	request.setAttribute("respuesta",revisar);
				            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
				            rd.forward(request, response);
				        }catch(Exception e){}
					}
				}else {
					try {
		            	String revisar="El producto no se encuentra registrado";
			        	request.setAttribute("respuesta",revisar);
			            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
			            rd.forward(request, response);
			        }catch(Exception e){}
				}
			}else {
				try {
	            	String revisar="El producto no se encuentra registrado";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}

			}else {
				try {
	            	String revisar="la cedula no se encuentra registrada";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="la cedula no se encuentra registrada";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}

}
