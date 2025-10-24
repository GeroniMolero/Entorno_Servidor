package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThoughtsServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Esta lista la usaremos como nuestra "base de datos" para almacenar
	// a los usuarios dados de alta. Esto será un dato compartido y habrá 
	// que modificarlo síncronamente para evitar problemas de concurrencia
	private List<String> users;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserServlet() {
		users = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("user");

		if (user == null || user.isEmpty()) {
			// Añadimos un mensaje que describa el error para que lo recupere la vista
			request.setAttribute("description", "el nombre del usuario no puede estar vacío");

			// Reenviamos el mensaje a la vista
			RequestDispatcher rd = request.getRequestDispatcher("/error-view.jsp");
			rd.forward(request, response);
			
		} else if (users.contains(user)) {
			// Añadimos un mensaje que describa el error para que lo recupere la vista
			request.setAttribute("description", "el usuario " + user + " ya está dado de alta");

			// Reenviamos el mensaje a la vista 
			RequestDispatcher rd = request.getRequestDispatcher("/error-view.jsp");
			rd.forward(request, response);
			
		} else {
			// Tenemos que modificar de forma síncrona este objeto ya que es un dato
			// compartido entre todos los hilos que usen el servlet
			synchronized (users) {
				users.add(user);
			}

			// Añadimos la lista de usuarios como atributo al mensaje HTTP request.
			// Lo asociamos al nombre "userlist"
			request.setAttribute("userlist", users);

			// Reenviamos el mensaje a la vista de resultados
			RequestDispatcher rd = request.getRequestDispatcher("/result-view.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
