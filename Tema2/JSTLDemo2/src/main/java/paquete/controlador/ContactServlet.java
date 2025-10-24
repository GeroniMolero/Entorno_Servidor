package paquete.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paquete.modelo.Contact;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Contact> contactList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactServlet() {
		// Creamos un Map "concurrente" y así todas as escrituras en la
		// estructura se realizarán de forma síncrona.
		contactList = Collections.synchronizedList(new ArrayList<Contact>());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		RequestDispatcher rd = null;
		
		if (option==null || option.isEmpty()) {
			request.setAttribute("error", "Opción incorrecta");
			rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else {
			switch(option) {
			case "index":
				indexPage(request, response);
				break;
			case "new":
				newContact(request, response);
				break;
			case "list":
				contactList(request, response);
				break;
			case "delete":
				deleteContactList(request, response);
				break;
			}
		}
		
	}

	

	private void indexPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd;
		Collections.sort(contactList);
		request.setAttribute("contactList", contactList);
		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	private void contactList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		Collections.sort(contactList);
		request.setAttribute("contactList", contactList);
		rd = request.getRequestDispatcher("contact-list.jsp");
		rd.forward(request, response);
	}

	private void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		contactList.add(new Contact(name, phone));
		request.setAttribute("result", "El contacto se agregó correctamente");
		rd = request.getRequestDispatcher("new-contact.jsp");
		rd.forward(request, response);
	}
	
	private void deleteContactList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd;
		contactList.clear();
		request.setAttribute("contactList", contactList);
		rd = request.getRequestDispatcher("index.jsp");
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
