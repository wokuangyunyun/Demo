package demo;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;


public class MyServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server();

		Connector connector= new SelectChannelConnector();
		connector.setPort(8080);

		server.setConnectors(new Connector[] { connector });

		WebAppContext webAppContext = new WebAppContext("WebRoot","/myProject");

		webAppContext.setDescriptor("WebRoot/WEB-INF/web.xml");
		webAppContext.setResourceBase("WebRoot");
		webAppContext.setDisplayName("myProject");
		webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webAppContext.setParentLoaderPriority(true);
		server.setHandler(webAppContext);
		System.out.println(webAppContext.getContextPath());
		System.out.println(webAppContext.getDescriptor());
		System.out.println(webAppContext.getResourceBase());
		System.out.println(webAppContext.getBaseResource());

		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("server is  start");
	}
}