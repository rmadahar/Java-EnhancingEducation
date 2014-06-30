

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordServ
 */
public class WordServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 // URL of the Chamonix ski resort
		  URL WordService = new URL("http://192.168.10.1/EducationGamesApp/Words.jsp");
		  URLConnection wc = WordService.openConnection();
		  BufferedReader in = new BufferedReader(new InputStreamReader(wc.getInputStream()));
		  String inputLine;
		  int posPrice=0;
		  // Loop until an identifcation string is found
		  while ((inputLine = in.readLine()) != null){
		     // Find the content you require and generate UI
		     posPrice=inputLine.indexOf("Starts");
		     if ( posPrice>0 ) break;
		  }
		  in.close();
		  if ( posPrice>0){
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<title>Word Service</title>");
		    out.println("<p style='font-size: 10pt; font-weight: bold; color: 00066;'>Words to choose from:</p>");
		    out.println("<p style='font-size: 12pt; font-weight: bold; color: FF0000;'>"+inputLine.substring(posPrice+8, posPrice+430)+"</p>");
		    out.println("<p style='font-weight:bold; color: FF0000; font-size: 11pt;'>Generated from Word data</p>");
		  }
		  else System.out.println("ERROR: Cannot find the Lift price");
		  
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
