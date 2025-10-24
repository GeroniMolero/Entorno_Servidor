package paquete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paquete.modelo.ShoppingCart;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer adult = Integer.parseInt(request.getParameter("adult"));
		Integer younger = Integer.parseInt(request.getParameter("younger"));
		Integer senior = Integer.parseInt(request.getParameter("senior"));
		
		if(adult + younger + senior == 0) {
			request.setAttribute("error", "Al menos debes comprar 1 entrada");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else {
			ShoppingCart shoppingCart = new ShoppingCart(adult, younger, senior);
			request.setAttribute("cart", shoppingCart);
			RequestDispatcher rd = request.getRequestDispatcher("confirmation.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
