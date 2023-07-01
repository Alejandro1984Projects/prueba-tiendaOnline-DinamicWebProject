package co.edu.TiendaGenerica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.TiendaGenerica.dao.UsuariosDAO;

/**
 * Servlet implementation class UsuariosServlet
 */
@WebServlet("/UsuariosServlet")
public class UsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosServlet() {
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
		String correo="";
		String nombre="";
		String password="";
		String usuario="";
		try {
		cedula=Integer.parseInt(request.getParameter("cedula"));
		correo=request.getParameter("correo");
		nombre=request.getParameter("nombre");
		password=request.getParameter("password");
		usuario=request.getParameter("usuario");
		}catch(Exception e){}
		if(cedula!=0 && correo!="" && nombre!="" && password!="" && usuario!="") {
			UsuariosDAO usuarioDAO=new UsuariosDAO();
			usuarioDAO.actualizar(cedula,correo,nombre,password,usuario);
			try {
            	String revisar="Datos del Usuario Actualizados";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del usuario";
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
			UsuariosDAO usuarioDAO=new UsuariosDAO();
			String consulta="";
			try {
				consulta=usuarioDAO.buscar(cedula);
			}catch(Exception e){}
			if(consulta!="") {
				usuarioDAO.borrar(cedula);
				try {
	            	String revisar="Datos del Usuario Borrados";
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
				UsuariosDAO usuarioDAO=new UsuariosDAO();
				consulta=usuarioDAO.buscar(cedula);
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
	            	String revisar="Usuario Inexistente";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}else {
			try {
            	String revisar="Usuario Inexistente";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}
	public void crear(HttpServletRequest request, HttpServletResponse response) {
		int cedula=0;
		String correo="";
		String nombre="";
		String password="";
		String usuario="";
		try {
		cedula=Integer.parseInt(request.getParameter("cedula"));
		correo=request.getParameter("correo");
		nombre=request.getParameter("nombre");
		password=request.getParameter("password");
		usuario=request.getParameter("usuario");
		}catch(Exception e){}
		if(cedula!=0 && correo!="" && nombre!="" && password!="" && usuario!="") {
			UsuariosDAO usuarioDAO=new UsuariosDAO();
			usuarioDAO.crear(cedula,correo,nombre,password,usuario);
			try {
            	String revisar="Usuario Creado";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}else {
			try {
            	String revisar="Faltan datos del usuario";
	        	request.setAttribute("respuesta",revisar);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
	            rd.forward(request, response);
	        }catch(Exception e){}
		}
	}

}
