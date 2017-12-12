

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;








import java.io.FileWriter;
import java.io.IOException;
@WebServlet(name = "Stopwatch", urlPatterns = {"timer_page"}, loadOnStartup = 1)
public class Stopwatch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String agenda_name = request.getParameter("agenda_names");
    	
    	  DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
          Query q = new Query("Agenda Item");
          q.addFilter("Agenda Name", Query.FilterOperator.EQUAL, agenda_name);
       //   q.addFilter("Username", Query.FilterOperator.EQUAL, username);
          PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(q);
    	
        Entity e = pq.asSingleEntity();
        
        
        
          request.setAttribute("agenda_names", agenda_name);
          
    	
    	 request.getRequestDispatcher("timer_page.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    	
    }
}