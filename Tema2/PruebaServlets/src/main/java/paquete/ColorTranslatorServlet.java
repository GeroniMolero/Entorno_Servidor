package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TraductorServlet
 */
@WebServlet("/ColorTranslatorServlet")
public class ColorTranslatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Map<String, String> translator;
		
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColorTranslatorServlet() {
        translator = new HashMap<>();
        translator.put("green", "verde");
        translator.put("yellow", "amarillo");
        translator.put("white", "blanco");
        translator.put("black", "negro");
        translator.put("brown", "marron");
        translator.put("blue", "azul");
        translator.put("red", "rojo");
        translator.put("pink", "rosa");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h1>Traductor de colores de inglés a español</h1>");
		
		String color = request.getParameter("color");
		if (color!=null) {
			
			color = color.toLowerCase();
			if(translator.containsKey(color)) {
				String colorTraducido = translator.get(color);
				out.println("<h2>"+color+" se traduce por "+colorTraducido+"</h2>");
				
			} else {
				out.println("<h2>Ese color no lo conozco... ummm</h2>");
			}
			
		} else {
			out.println("<h2>Tienes que enviar el parámetro 'color' en la URL</h2>");
			out.println("<p>Ejemplo: URL?color=green</p>");
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
