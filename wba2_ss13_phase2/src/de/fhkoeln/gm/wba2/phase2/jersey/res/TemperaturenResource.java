package de.fhkoeln.gm.wba2.phase2.jersey.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.Temperaturen;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.*;

@Path("/temperaturen")
public class TemperaturenResource {
	
	private static Temperaturen temperaturenList = new Temperaturen();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Temperaturen getTemperaturen() {
		return temperaturenList;
	}
	
	@Path("/{id}")
	public TemperaturResource getTemperatur(@PathParam("id") int id){
		if(id >= temperaturenList.getTemperaturEl().size())
			throw new NotFoundException();
		return new TemperaturResource(temperaturenList.getTemperaturEl().get(id));
	}
	
}
