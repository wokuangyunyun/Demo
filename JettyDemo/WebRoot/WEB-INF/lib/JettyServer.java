package com.lzy.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;


public class JettyServer {
	
	private int port;
	private String webApp;
	private String contextPath;
	private Server server;
	/**
	 * 使用指定参数创建JettyServer
	 * @param port 服务器端口号:8080
	 * @param webApp web工程路径 :D:/OIMSV2_WorkSpace/EIS/WebContent
	 * @param contextPath web工程上下文路径:/EIS
	 */
	public JettyServer(int port,String webApp,String contextPath){
		this.port = port;
		this.webApp = webApp;
		this.contextPath = contextPath;
	}
	public void start(){
		try {
			server = new Server(port);
			new WebAppContext(server, webApp, contextPath);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void stop(){
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
