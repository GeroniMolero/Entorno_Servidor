package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MensajesServlet
 */
@WebServlet("/MensajesServlet")
public class MensajesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Le indicamos que añada una cabecera al mensaje de respuesta con el tipo de datos que va a llevar el mensaje
		response.setContentType("text/html");
		
		
		// Le pedimos al mensaje de salida que nos de un flujo caracteres asociado al cuerpo del mensaje
		PrintWriter out = response.getWriter();
		
		// Escribimos en el cuerpo del mensaje de salida
		out.println("<html><body>");
		out.println("<h1>Imprimimos las cabeceras del mensaje recibido</h1>");
		
		// Le pedimos un enumerado (es como una lista) de las cabeceras disponibles
		Enumeration<String> headerEnumeration = request.getHeaderNames();
        
        while(headerEnumeration.hasMoreElements()) {
            String headerName= headerEnumeration.nextElement();
            String headerValue =request.getHeader(headerName);
            out.println(headerName + ": " + headerValue+"<br/>");
        }
        
        out.println("<h1>Imprimimos los parámetros de la URL</h1>");

        // Le pedimos un enumerado de los parámetros de la URL
		Enumeration<String> paramEnumeration= request.getParameterNames();
		
		if(!paramEnumeration.hasMoreElements()) {
			out.println("<h5>La URL no tiene parámetros. Prueba a añadirle al final algo como ?Param=Valor o como ?p1=v1&p2=v2</h5>");
		}
		
        while(paramEnumeration.hasMoreElements()) {
            String paramName= paramEnumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            out.println(paramName + ": " + paramValue+"<br/>");
            
        }
        out.println("<a href=\"index.html\">Volver</a>");
        out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
