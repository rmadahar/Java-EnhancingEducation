

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContentServ
 */
public class ContentServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
		
		  response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String User=request.getParameterValues("user")[0];
			  String Request=request.getParameterValues("request")[0];
		    out.println("<html>");
		    out.println("<body>");
		    out.println("<p style='font-size:250%'>");
		    out.println("Thanks" + "  " + User + "  " + "for submitting your request<br>" );
		    out.println("Your request will be in a waiting list<br> so please be patient");
		    out.println("</p>");
		    out.println("<img height='450px' align='center' width='450px' src='http://www.imageenvision.com/150/36881-clip-art-graphic-of-a-dark-blue-guy-character-handy-man-with-a-spanner-by-jester-arts.jpg' />");
		    out.println("</body></html>");
	}

}
