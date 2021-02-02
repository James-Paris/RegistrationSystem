package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.*;

import hibernate.HibernateUtils;
import model.UserEntity;

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
		//out.println("<h3>Loading...</h3>");
        
		String checkUser = req.getParameter("user");
		String checkPass = req.getParameter("pass");
		
		Session session = HibernateUtils.buildSessionFactory().openSession();
		session.beginTransaction();
		
		//check to see if user exists
		Query query = session.createQuery("from UserEntity where username = :usr ", UserEntity.class);
		query.setParameter("usr", checkUser);
		
		List results = query.list();
		if(results.isEmpty()) {
			//that user doesnt exist
			out.println("<h3>That user does not exist.</h3>");
			out.println("<p><b>Please <a href=''>Register</a> it if you would to login using this username.</b></p>");
			doGet(req, resp);
		} else {
			//check to see if passwords match
			UserEntity fetchedData = (UserEntity) results.get(0);
			if(checkPass.equals(fetchedData.getPass())) {
				//user exists and passwords match! Log the user in and redirect:
				out.println("<h3>Login Successful! Redirecting...</h3>");
				successfulLogin(req, resp);
			} else {
				out.println("<h3>Invalid login credentials. Please try again!</h3>");
				doGet(req, resp);
				//passwords did not match. handle that now
			}
			
			
			
		}
				
		
		//doGet(req, resp);
	}
	
		protected void successfulLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			// Setting up the content type of web page
	        resp.setContentType("text/html");
	        // Writing the message on the web page
	        PrintWriter out = resp.getWriter();
			resp.setHeader("Refresh", "5; URL=http://localhost:8050/widgets/theforums");
	    	
			
		}
		

}
