

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.protobuf.Empty;

import java.io.IOException;

@WebServlet(name = "DisplayAllUsers", urlPatterns = {"display_all_users"}, loadOnStartup = 1)
public class DisplayAllUsers extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
       Query q = new Query("Person");
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(q);
        String all_user_names = null;
        Iterable<Entity> pq_for_each = pq.asIterable(); 
  for (Entity u1: pq_for_each)
  {
        response.getWriter().println(u1.getProperty("Username"));
        
    }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String name = request.getParameter("Username");
//        
//        String password = request.getParameter("Password");
//        if (name == null) name = "World";
//        request.setAttribute("user", name);
//        request.setAttribute("password", password);
//        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//        Entity e = new Entity("Person");
//        e.setProperty("Username", name);
//        e.setProperty("Password", password );
//        
//       ds.put(e);  
//        
        
        
        
        
        request.getRequestDispatcher("response_page.jsp").forward(request, response);
    }
}