package co.edu.TiendaGenerica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.TiendaGenerica.dao.ProveedoresDAO;

/**
 * Servlet implementation class ProveedoresServlet
 */
@WebServlet("/ProveedoresServlet")
public class ProveedoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedoresServlet() {
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
		String crear=request.getParameter("crear");
		String actualizar=request.getParameter("actualizar");
		String borrar=request.getParameter("borrar");
		String consultar=request.getParameter("consultar");

		if(crear!=null) {
			crear(request,response);
		}
		else
		if(actualizar!=null) {
			actualizar(request,response);
		}
		else
		if(borrar!=null) {
			borrar(request,response);
		}
		else
		if(consultar!=null) {
			buscar(request,response);
		}
	}
	public void actualizar(HttpServletRequest request, HttpServletResponse response) {
		int nit=0;
		String ciudad="";
		String direccion="";
		String nombre="";
		String telefono="";
		try {
			nit=Integer.parseInt(request.getParameter("nit"));
			ciudad=request.getParameter("ciudad");
			direccion=request.getParameter("direccion");
			nombre=request.getParameter("nombre");
			telefono=request.getParameter("telefono");
		}catch(Exception e){}
		if(nit!=0 && ciudad!="" && direccion!="" && nombre!="" && telefono!="") {
			ProveedoresDAO ProveedorDAO=new ProveedoresDAO();
			ProveedorDAO.actualizar(nit,ciudad,direccion,nombre,telefono);
			try {
            	String revisar="Datos del Proveedor Actualizados";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del proveedor";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void borrar(HttpServletRequest request, HttpServletResponse response) {
		int nit=0;
		try {
			nit=Integer.parseInt(request.getParameter("nit"));
		}catch(Exception e) {}
		if(nit!=0) {
			ProveedoresDAO proveedorDAO=new ProveedoresDAO();
			String consulta="";
			try {
				consulta=proveedorDAO.buscar(nit);
			}catch(Exception e){}
			if(consulta!="") {
				proveedorDAO.borrar(nit);
				try {
	            	String revisar="Datos del proveedor Borrados";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}else {
				try {
	            	String revisar="Nit Errado";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="Nit Errado";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void buscar(HttpServletRequest request, HttpServletResponse response) {
		int nit=0;
		try {
			nit=Integer.parseInt(request.getParameter("nit"));
		}catch(Exception e) {}
		if(nit!=0) {
			String consulta="";
			try {
				ProveedoresDAO proveedorDAO=new ProveedoresDAO();
				consulta=proveedorDAO.buscar(nit);
			}catch(Exception e){}
			if(consulta!="") {
				try {
	            	String revisar=consulta;
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}else {
				try {
	            	String revisar="Proveedor Inexistente";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="Proveedor Inexistente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void crear(HttpServletRequest request, HttpServletResponse response) {
		int nit=0;
		String ciudad="";
		String direccion="";
		String nombre="";
		String telefono="";
		try {
			nit=Integer.parseInt(request.getParameter("nit"));
			ciudad=request.getParameter("ciudad");
			direccion=request.getParameter("direccion");
		nombre=request.getParameter("nombre");
		telefono=request.getParameter("telefono");
		}catch(Exception e){}
		if(nit!=0 && direccion!="" && direccion!="" && nombre!="" && telefono!="") {
			ProveedoresDAO proveedorDAO=new ProveedoresDAO();
			proveedorDAO.crear(nit,ciudad,direccion,nombre,telefono);
			try {
            	String revisar="Proveedor Creado";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del proveedor";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}

}
