package de.fhkoeln.gm.wba2.phase2.jersey.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.Temperatur;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.*;

public class TemperaturResource {

	private Temperatur temperatur;
	
	public TemperaturResource(Temperatur temperatur){
		super();
		this.temperatur = temperatur;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Temperatur getTemperatur() {
		return this.temperatur;
	}
}
