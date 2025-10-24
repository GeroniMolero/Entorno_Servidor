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
			request.setAttribute("result", "ERROR");
			request.setAttribute("description", "el nombre del usuario no puede estar vacío");
			
		} else if (users.contains(user)) {
			request.setAttribute("result", "ERROR");
			request.setAttribute("description", "el usuario " + user + " ya está dado de alta");
			
		} else {
			synchronized (users) {
				users.add(user);
			}

			request.setAttribute("result", "OK");
			request.setAttribute("userlist", users);

		}
		// Por último, reenviamos el mensaje a la misma página que nos lo envió
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
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
