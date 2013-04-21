package ex1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ex1.MySQLConnection;

@Path("/temperatur")
public class HelloWorldResource {
	@GET
	@Produces("text/html")
	@Path("/{raum}")
	public String hallo(@PathParam("raum") String raum){
		return MySQLConnection.printTemperaturen(raum);
	}
}