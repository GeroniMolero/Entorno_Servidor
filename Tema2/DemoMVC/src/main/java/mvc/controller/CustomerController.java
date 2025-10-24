package mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.repository.RepositoryException;
import mvc.model.entity.Customer;
import mvc.model.service.CustomerService;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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
			case "findAll":
				findAllCustomers(request, response);
				break;
			case "newCustomer":
				newCustomer(request, response);
				break;
			case "findById":
				findCustomerById(request, response);
				break;
			case "deleteCustomer":
				deleteCustomerById(request, response);
				break;
			case "updateCustomer":
				updateCustomerById(request, response);
				break;
			}

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

	private void findCustomerById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer customerId = Integer.parseInt(request.getParameter("customer-id"));
			Optional<Customer> customer = CustomerService.findById(customerId);

			if (customer.isPresent()) {
				request.setAttribute("customer", customer.get());
				RequestDispatcher rd = request.getRequestDispatcher("update-delete-customer-form.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("result", "No existe ningún cliente con ese número de registro");
				RequestDispatcher rd = request.getRequestDispatcher("find-customer-form.jsp");
				rd.forward(request, response);
			}

		} catch (RepositoryException e) {
			// Imprimimos el detalle en la consola
			e.printStackTrace();
			request.setAttribute("error", "Se produjo un error al acceder al repositorio de datos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

	private void findAllCustomers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> list;
		try {
			list = CustomerService.findAll();
			request.setAttribute("customerList", list);
			RequestDispatcher rd = request.getRequestDispatcher("find-all.jsp");
			rd.forward(request, response);
		} catch (RepositoryException e) {
			// Imprimimos el detalle en la consola
			e.printStackTrace();
			request.setAttribute("error", "Se produjo un error al acceder al repositorio de datos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

	private void newCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String website = request.getParameter("website");
			Double creditLimit = Double.parseDouble(request.getParameter("credit-limit"));

			CustomerService.newCustomer(name, address, website, creditLimit);
			request.setAttribute("result", "El cliente se ha registrado correctamente");
			RequestDispatcher rd = request.getRequestDispatcher("new-customer-form.jsp");
			rd.forward(request, response);
		} catch (RepositoryException e) {
			// Imprimimos el detalle en la consola
			e.printStackTrace();
			request.setAttribute("error", "Se produjo un error al acceder al repositorio de datos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}

	}

	private void deleteCustomerById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			CustomerService.deleteCustomerById(id);
			request.setAttribute("result", "El cliente se ha eliminado correctamente");
			RequestDispatcher rd = request.getRequestDispatcher("update-delete-customer-form.jsp");
			rd.forward(request, response);
		} catch (RepositoryException e) {
			// Imprimimos el detalle en la consola
			e.printStackTrace();
			request.setAttribute("error", "Se produjo un error al acceder al repositorio de datos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

	private void updateCustomerById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String website = request.getParameter("website");
			Double creditLimit = Double.parseDouble(request.getParameter("credit-limit"));
			CustomerService.updateCustomerById(id, name, address, website, creditLimit);

			request.setAttribute("result", "El cliente se ha actualizado correctamente");
			RequestDispatcher rd = request.getRequestDispatcher("update-delete-customer-form.jsp");
			rd.forward(request, response);
		} catch (RepositoryException e) {
			// Imprimimos el detalle en la consola
			e.printStackTrace();
			request.setAttribute("error", "Se produjo un error al acceder al repositorio de datos");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}
}
