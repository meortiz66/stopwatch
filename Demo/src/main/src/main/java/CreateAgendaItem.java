

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

import java.io.IOException;

@WebServlet(name = "CreateAgendaItem", urlPatterns = {"createagendaitem"}, loadOnStartup = 1)
public class CreateAgendaItem extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create_agenda.jsp").forward(request,response);
      
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        // count of speakers in post form
        
        int count_of_speakers = Integer.parseInt(request.getParameter("Number Of Speakers:"));
       // create new entity
        
        Entity agenda_item_entity = new Entity("Agenda Item");
        // set agenda name for entity
        agenda_item_entity.setProperty("Number of Speakers" , new Integer(count_of_speakers));
        agenda_item_entity.setProperty("Agenda Name", request.getParameter("Agenda Name") );
        agenda_item_entity.setProperty("admin", LoginPage.current_user);
        // loop through and set speaker name and their times to entity
        for (int i = 1; i <= count_of_speakers; i++)
        {
        	
        	// set speaker # i field for entity e
        	agenda_item_entity.setProperty("Speaker Name " + new Integer(i).toString(),
        			request.getParameter("Speaker Name " + new Integer(i).toString()));
        	
        	agenda_item_entity.setProperty("Speaker Time " + new Integer(i).toString(),
        			request.getParameter("Speaker Time " + new Integer(i).toString()));
        	
        }
        
        DatastoreService ds =  DatastoreServiceFactory.getDatastoreService();
      
        ds.put(agenda_item_entity);
        request.setAttribute("message", "Agenda Creation Successful");
        request.getRequestDispatcher("admin_page.jsp").forward(request, response);;
       
       
        
        
       
       
    
    }
}