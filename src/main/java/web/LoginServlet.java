package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Setting up the content type of web page
        resp.setContentType("text/html");
        // Writing the message on the web page
        PrintWriter out = resp.getWriter();
        out.println("<h3>Login:</h3>");
        
        out.println("<form action='' method='POST'>");
        out.println("<label>Username: <input type='text' name='user' minlength='3' required></input></label><br>");
        out.println("<label>Password: <input type='password' name='pass' minlength='3' required></input></label><br>");
        out.println("<input type='submit'></input>");
        out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<h3>Loading...</h3>");
        
		doGet(req, resp);
	}

}
