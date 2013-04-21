package ex1;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class Ex1Server {
	public static void main(String[] args) throws Exception {
		final String baseUri = "http://localhost:4711/";
		final Map<String, String> params = new HashMap<String, String>();
		
		params.put("com.sun.jersey.config.property.packages", 
				"ex1");
		
		System.out.println("Starting web server...");
		
		SelectorThread server = GrizzlyWebContainerFactory.create(baseUri,params);
		Logger.getLogger(Ex1Server.class.getName())
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
