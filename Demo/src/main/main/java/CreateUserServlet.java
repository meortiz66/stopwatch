import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.io.IOException;





@WebServlet(name = "CreateUserServlet", urlPatterns = {"createuser"}, loadOnStartup = 0)
public class CreateUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("create_new_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = request.getParameter("Username");
    	String password = request.getParameter("Password");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
       
        String user_id = "name=" + username;
       Key new_key = KeyFactory.createKey("Person", user_id);
        Entity e = new Entity("Person", new_key);
        e.setProperty("Username", username);
        e.setProperty("Password", password );
        KeyFactory.createKey("Person", username);
        // do validation on input
        // is transaction valid?
        try {
         Key i = ds.put(e);  
        }
        catch (Exception any_exception)
        {
        	request.setAttribute("user_created_success", "unsuccessful");
        }
        finally
        {
        	// request.setAttribute("user", user);
        	request.setAttribute("user_created_success", "successful");
        }
        request.getRequestDispatcher("user_created_confirmation.jsp").forward(request, response);   
    
    }
   
    }
