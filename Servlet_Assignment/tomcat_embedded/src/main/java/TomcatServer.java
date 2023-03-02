import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import view.*;

import java.io.File;

public class TomcatServer {
    public static void main(String[] args) throws Exception {
        Tomcat server = new Tomcat();
        server.setPort(8080);
        Context context = server.addContext("", (new File(".")).getAbsolutePath());
        Tomcat.addServlet(context, "book", new viewHTML());
        Tomcat.addServlet(context, "add", new addHTML());
        Tomcat.addServlet(context, "dispatcher", new dispatcherHTML());
        Tomcat.addServlet(context, "update", new updateHTML());
        Tomcat.addServlet(context, "delete", new deleteHTML());
        context.addServletMappingDecoded("", "dispatcher");
        context.addServletMappingDecoded("/dispatcher", "dispatcher");
        context.addServletMappingDecoded("/view", "book");
        context.addServletMappingDecoded("/add", "add");
        context.addServletMappingDecoded("/update", "update");
        context.addServletMappingDecoded("/delete", "delete");
        System.out.println("Start Tomcat embedded server");
        server.start();
        server.getServer().await();
    }
}