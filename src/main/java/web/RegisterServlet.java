package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.HibernateUtils;
import model.UserEntity;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting up the content type of web page
        resp.setContentType("text/html");
        // Writing the message on the web page
        PrintWriter out = resp.getWriter();
        out.println("<h3>Required Information:</h3>");
      
        //registration form
        out.println("<form action='' method='POST'>");
        out.println("<label>Username: <input type='text' name='user' minlength='3' required></input></label><br>");
        out.println("<label>Password: <input type='password' name='pass' minlength='3' required></input></label><br>");
        out.println("<input type='submit'></input>");
        out.println("</form>");
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	String user = req.getParameter("user");
    	String pass = req.getParameter("pass");

    	PrintWriter out = resp.getWriter();
    	
    	Session session = HibernateUtils.buildSessionFactory().openSession();
    	session.beginTransaction();
    	
    	//check if user already exists
    	Query query = session.createQuery("from UserEntity where username = :usr ", UserEntity.class);
        query.setParameter("usr", user);
    	
    	List results = query.list();
    	if(!results.isEmpty()) {
    		//user already exists. so it fails.
    		out.println("<h3>This username has been taken already. Please try again.</h3>");
    		this.doGet(req, resp);
    	} else {
    
    		//adds the user to database
        	UserEntity newUser = new UserEntity();
        	newUser.setUser(user);
        	newUser.setPass(pass);
        	session.save(newUser);
			session.getTransaction().commit();
			
			//inform the user that it worked and redirect back to index page
        	out.println("<h3>You have Successfully Registered! Redirecting... </h3>");
        	resp.setHeader("Refresh", "5; URL=http://localhost:8050/widgets/");
    	}
    	
    }

}
