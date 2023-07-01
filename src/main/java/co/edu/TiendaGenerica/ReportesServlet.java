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
import co.edu.TiendaGenerica.dao.UsuariosDAO;
import co.edu.TiendaGenerica.dao.VentasDAO;

/**
 * Servlet implementation class ReportesServlet
 */
@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportesServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String listausuarios=request.getParameter("listausuarios");
		String listaclientes=request.getParameter("listaclientes");
		String listaventas=request.getParameter("listaventas");

		if(listausuarios!=null) {
	        ArrayList<String> lista=new ArrayList<>();
			UsuariosDAO usuarioDAO=new UsuariosDAO();
			lista=usuarioDAO.buscarTodos();
			if(lista.size()!=0) {
			try {
	        	request.setAttribute("respuesta",lista);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/RespuestaReportes.jsp");
	            rd.forward(request, response);
	            
	        }catch(Exception e){e.printStackTrace();}
			}else {
				try {
	            	String revisar="La lista esta vacia";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}
		else
		if(listaclientes!=null) {
			ArrayList<String> lista=new ArrayList<>();
			ClientesDAO clienteDAO=new ClientesDAO();
			lista=clienteDAO.buscarTodos();
			if(lista.size()!=0) {
			try {
	        	request.setAttribute("respuesta",lista);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/RespuestaReportes.jsp");
	            rd.forward(request, response);
	            
	        }catch(Exception e){e.printStackTrace();}
			}else {
				try {
	            	String revisar="La lista esta vacia";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}
		else
		if(listaventas!=null) {
			ArrayList<String> lista=new ArrayList<>();
			VentasDAO ventaDAO=new VentasDAO();
			lista=ventaDAO.buscarTodos();
			if(lista.size()!=0) {
			try {
	        	request.setAttribute("respuesta",lista);
	            RequestDispatcher rd=getServletContext().getRequestDispatcher("/RespuestaReportes.jsp");
	            rd.forward(request, response);
	            
	        }catch(Exception e){e.printStackTrace();}
			}else {
				try {
	            	String revisar="La lista esta vacia";
		        	request.setAttribute("respuesta",revisar);
		            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Respuesta.jsp");
		            rd.forward(request, response);
		        }catch(Exception e){}
			}
		}
	}

}
