package co.edu.TiendaGenerica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.TiendaGenerica.dao.ClientesDAO;

/**
 * Servlet implementation class ClientesServlet
 */
@WebServlet("/ClientesServlet")
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		int cedula=0;
		String direccion="";
		String correo="";
		String nombre="";
		String telefono="";
		try {
		cedula=Integer.parseInt(request.getParameter("cedula"));
		direccion=request.getParameter("direccion");
		correo=request.getParameter("correo");
		nombre=request.getParameter("nombre");
		telefono=request.getParameter("telefono");
		}catch(Exception e){}
		if(cedula!=0 && !direccion.equals("") && !correo.equals("") && !nombre.equals("") && !telefono.equals("")) {
			ClientesDAO clienteDAO=new ClientesDAO();
			clienteDAO.actualizar(cedula,direccion,correo,nombre,telefono);
			try {
            	String revisar="Datos del Cliente Actualizados";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del cliente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void borrar(HttpServletRequest request, HttpServletResponse response) {
		int cedula=0;
		try {
			cedula=Integer.parseInt(request.getParameter("cedula"));
		}catch(Exception e) {}
		if(cedula!=0) {
			ClientesDAO clienteDAO=new ClientesDAO();
			String consulta="";
			try {
				consulta=clienteDAO.buscar(cedula);
			}catch(Exception e){}
			if(!consulta.equals("")) {
				clienteDAO.borrar(cedula);
				try {
	            	String revisar="Datos del cliente Borrados";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}else {
				try {
	            	String revisar="Cedula Errada";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="Cedula Errada";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void buscar(HttpServletRequest request, HttpServletResponse response) {
		int cedula=0;
		try {
			cedula=Integer.parseInt(request.getParameter("cedula"));
		}catch(Exception e) {}
		if(cedula!=0) {
			String consulta="";
			try {
				ClientesDAO clienteDAO=new ClientesDAO();
				consulta=clienteDAO.buscar(cedula);
			}catch(Exception e){}
			if(!consulta.equals("")) {
				try {
	            	String revisar=consulta;
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}else {
				try {
	            	String revisar="Cliente Inexistente";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="Cliente Inexistente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void crear(HttpServletRequest request, HttpServletResponse response) {
		int cedula=0;
		String direccion="";
		String correo="";
		String nombre="";
		String telefono="";
		try {
		cedula=Integer.parseInt(request.getParameter("cedula"));
		direccion=request.getParameter("direccion");
		correo=request.getParameter("correo");
		nombre=request.getParameter("nombre");
		telefono=request.getParameter("telefono");
		}catch(Exception e){}
		if(cedula!=0 && !direccion.equals("") && !correo.equals("") && !nombre.equals("") && !telefono.equals("")) {
			ClientesDAO clienteDAO=new ClientesDAO();
			clienteDAO.crear(cedula,direccion,correo,nombre,telefono);
			try {
            	String revisar="Cliente Creado";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del cliente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}


}
