package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("option");
		RequestDispatcher rd = null;

		if (option == null || option.isEmpty()) {
			request.setAttribute("error", "Opción incorrecta");
			rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else {
			switch (option) {
			case "noLogin":
				sessionNoLogin(request, response);
				break;
			case "invalidate":
				sessionInvalidate(request, response);
				break;
			case "counter":
				sessionCounter(request, response);
				break;
			case "login":
				sessionLogin(request, response);
				break;
			case "logout":
				sessionLogout(request, response);
				break;
			}
		}
	}

	private void sessionNoLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		request.setAttribute("sessionId", sessionId);
		
		long creationTime = session.getCreationTime();
		Date createDate = new Date(creationTime);
		request.setAttribute("createDate", createDate);
		
		long lastAccessedTime = session.getLastAccessedTime();
		Date lastAccessedDate = new Date(lastAccessedTime);
		request.setAttribute("lastAccessedDate", lastAccessedDate);

		RequestDispatcher rd = request.getRequestDispatcher("session-info.jsp");
		rd.forward(request, response);
	}

	private void sessionInvalidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean createIfNotExist = false;
		HttpSession session = request.getSession(createIfNotExist);
		
		if (session != null) {
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("session-invalidate.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "Estás intentando invalidar una sesión sin haberla creado previamente");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

	
	private void sessionCounter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean createIfNotExist = true;
		HttpSession session = request.getSession(createIfNotExist);
		Integer counter = (Integer) session.getAttribute("counter");
		
		if (counter == null) {
			counter = 1;
		} else {
			counter++;
		}
		
		session.setAttribute("counter", counter);
		PrintWriter out = response.getWriter();
		out.println("<p>Hola usuario. Has visitado esta página "+counter+" veces</p>");
		out.println("<p>Recarga la página para incrementar el contador</p>");
		out.println("<p>Si visitas esta URL desde otro navegador, se creará otra sesión nueva con su propio contador</p>");
		out.println("<p>Pincha en el siguiente <a href=\"index.jsp\">enlace</a> para volver al inicio.</p>");
	}
	
	private void sessionLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if (!userName.isEmpty() && password.length()>=6) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userName);

			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "Usuario o password incorrectos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}

	}
	
	private void sessionLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		RequestDispatcher rd = request.getRequestDispatcher("login-form.jsp");
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
