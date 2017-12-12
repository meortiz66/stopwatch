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
	public static String current_user;
	
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Random Text! 2");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = request.getParameter("Username");
    	String password = request.getParameter("Password");
    	String message = null;
    	Boolean login_successful = Boolean.FALSE;
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
        	message = "Logged in as " + username;
        	LoginPage.current_user =  username;
        	login_successful = Boolean.TRUE;
        }
        }
        catch (Exception query_exception)
        {
        message = "User not found.";
        LoginPage.current_user = null;
    
        }
request.setAttribute("message", message);       
        
        
        request.setAttribute("username", username);
        request.setAttribute("login_successful", login_successful);
        if (login_successful.equals(Boolean.TRUE)) {
        	 String agenda_names = "";
         	
         	  DatastoreService ds2 = DatastoreServiceFactory.getDatastoreService();
               Query q2 = new Query("Agenda Item");
               q2.addFilter("admin", Query.FilterOperator.EQUAL, LoginPage.current_user);
            //   q.addFilter("Username", Query.FilterOperator.EQUAL, username);
               PreparedQuery pq2 = DatastoreServiceFactory.getDatastoreService().prepare(q2);
         	
               for (Entity a : pq2.asIterable())
               {
             	agenda_names += (String) (a.getProperty("Agenda Name")) + " ";  
             	  
               }
               request.setAttribute("agenda_names", agenda_names);
        request.getRequestDispatcher("admin_page.jsp").forward(request, response);
        
      
        }
        else
        {
        	request.getRequestDispatcher("login_unsuccessful.jsp").forward(request,response);
        	
        	
        }
    }
}