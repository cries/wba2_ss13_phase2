package de.fhkoeln.gm.wba2.phase2.jersey.server;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class RESTServer {
	public static void main(String[] args) throws Exception {
		final String baseUri = "http://localhost:4711/";
		final Map<String, String> params = new HashMap<String, String>();
		
		params.put("com.sun.jersey.config.property.packages", 
				"de.fhkoeln.gm.wba2.phase2.jersey.res");
		
		System.out.println("Starting web server...");
		
		SelectorThread server = GrizzlyWebContainerFactory.create(baseUri,params);
		Logger.getLogger(RESTServer.class.getName())
			.info(String
					.format("Jersey app started with WADL available at %sapplication.wadl\n" + "Try out %shelloworld\nHit enter to stop it", 
							baseUri, baseUri));
		System.in.read();
		if(server.isRunning()){
			server.stopEndpoint();
		}
		System.exit(0);
	}
}
