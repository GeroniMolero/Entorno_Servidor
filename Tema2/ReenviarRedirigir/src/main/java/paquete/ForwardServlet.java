package paquete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwarderServlet
 */
@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ForwardServlet: comienzo de doPost");
		
		// Obtenemos los parámetros
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		if (user.equals("admin") && pass.equals("1234")) {
			// Solicitamos al mensaje entrante que no dé una referencia del objeto del contexto
			// que lo "despachó" indicándole además la URL a la que queremos reenviar el mensaje
			RequestDispatcher rd = request.getRequestDispatcher("LoginOKServlet");
			
			// Solicitamos al RequestDispatcher que reenvíe a la nueva URL los mensajes que teníamos
			rd.forward(request, response);
		} else {
			// Idem que antes
			RequestDispatcher rd = request.getRequestDispatcher("login-error.html");
			rd.forward(request, response);
		}
		// Cuando se termina la ejecución del método forward, este método doPost
		// sigue ejecutándose. Observa los mensajes en la consola
		System.out.println("ForwardServlet: fin de doPost");
	}

}
