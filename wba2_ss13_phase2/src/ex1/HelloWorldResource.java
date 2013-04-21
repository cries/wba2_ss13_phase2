package ex1;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import ex1.MySQLConnection;
import generated.*;

@Path("/temperatur")
public class HelloWorldResource  {

	@GET
	@Produces("text/plain")
	@Path("/{raum}")
	public String hallo(@PathParam("raum") String raum){
		return MySQLConnection.getTemperatur(raum);
	}
}