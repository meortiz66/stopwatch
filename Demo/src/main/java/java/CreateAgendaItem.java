

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.io.IOException;

@WebServlet(name = "CreateAgendaItem", urlPatterns = {"createagendaitem"}, loadOnStartup = 1)
public class CreateAgendaItem extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create_agenda.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("Username");
        
        String password = request.getParameter("Password");
        if (name == null) name = "World";
        request.setAttribute("user", name);
        request.setAttribute("password", password);
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