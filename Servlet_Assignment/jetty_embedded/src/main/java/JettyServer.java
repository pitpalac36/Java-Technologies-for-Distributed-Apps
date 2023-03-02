import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        contextHandler.addServlet(view.dispatcherHTML.class, "/dispatcher");
        contextHandler.addServlet(view.addHTML.class, "/add");
        contextHandler.addServlet(view.deleteHTML.class, "/delete");
        contextHandler.addServlet(view.updateHTML.class, "/update");
        contextHandler.addServlet(view.viewHTML.class, "/view");
        System.out.println("Start jetty embedded server");
        server.start();
        server.join();
    }
}
