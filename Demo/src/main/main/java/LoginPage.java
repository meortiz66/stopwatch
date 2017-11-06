import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;

import java.io.IOException;

@WebServlet(name = "LoginPageServlet", urlPatterns = {"loginpage"}, loadOnStartup = 1)
public class LoginPage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Random Text! 2");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = request.getParameter("Username");
    	String password = request.getParameter("Password");
    	String message = null;
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Entity e = new Entity("Person");
      //  e.setProperty("Username", name);
      //  e.setProperty("Password", password );
        Query q = new Query("Person");
        
        q.addFilter("Username", Query.FilterOperator.EQUAL, username);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(q);
        try {
        if (pq.asSingleEntity().getProperty("Password").equals(password))
        {
        	message = "User found. Login Successful.";
        }
        }
        catch (Exception query_exception)
        {
        message = "User not found or duplicate users are in database.";
        }
request.setAttribute("message", message);       
        
        
        
        
        request.getRequestDispatcher("login_confirmation.jsp").forward(request, response);
    }
}