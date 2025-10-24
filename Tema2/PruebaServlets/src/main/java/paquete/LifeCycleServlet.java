package paquete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
        super();
        System.out.println("Llamada al constructor");
    }
    
    /**
     * @see HttpServlet#init()
     */
    public void init() throws ServletException{
        System.out.println("Llamada al método init");
    }

    /**
     * @see HttpServlet#service()
     */
    public void service(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        System.out.println("Llamada al método service");
        
        // Si no se sobrescribe este método, lo que hace es lo siguiente:
        switch(request.getMethod()) {
	        case "GET": doGet(request, response); break;
	        case "POST": doPost(request, response); break;
	        case "PUT": doPut(request, response); break;
	        case "DELETE": doDelete(request, response); break;        
        }
    }
    
    /**
     * @see HttpServlet#destroy()
     */
    public void destroy() {
        System.out.println("Llamada al método destroy");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Llamada al método doGet");
		PrintWriter out = response.getWriter();
		out.println("<p><a href=\"LifeCycleServlet\">Vuelve a llamar al Servlet</a> y mira en la consola</p>");
		out.println("<p>Por último, detiene el servidor Tomcat para ver el mensaje del método destroy en la consola</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Llamada al método doPost");
	}
}
