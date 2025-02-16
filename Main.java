package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.ResourceServer;
import resources.ResourceServerMBean;
import resources.ResourceServlet;

import javax.management.*;
import java.lang.management.ManagementFactory;


public class Main {
    public static void main(String[] args) throws Exception {
        ResourceServer resourceServer = new ResourceServer();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceServer, name);

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new org.eclipse.jetty.servlet.ServletHolder(new ResourceServlet(resourceServer)), "/resources");

        server.start();
        System.out.println("Server started");
        server.join();
    }
}