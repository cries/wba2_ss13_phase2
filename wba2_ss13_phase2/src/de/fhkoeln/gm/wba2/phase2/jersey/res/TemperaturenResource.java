package de.fhkoeln.gm.wba2.phase2.jersey.res;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.Temperatur;
import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.Temperaturen;

import de.fhkoeln.gm.wba2.phase2.jersey.server.*;


@Path("/temperaturen/")
public class TemperaturenResource {
	
	private static Temperaturen temperaturenList = new Temperaturen();
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Temperaturen getTemperaturen() {
		return temperaturenList;
	}
	
	@Path("/{id}/")
	public TemperaturResource getTemperatur(@PathParam("id") int id){
		if(id >= temperaturenList.getTemperaturEl().size())
			throw new NotFoundException();
		return new TemperaturResource(temperaturenList.getTemperaturEl().get(id));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postTemperatur(Temperatur temperatur){
		URI location = addTemperatur(temperatur);
		
		return Response.created(location).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postTemperatur(
			@FormParam("raum") String raum,
			@FormParam("wert") String wert,
			@FormParam("einheit") String einheit) {
		
		ArrayList<Temperatur> temperaturenDB = new ArrayList<Temperatur>();
		
		if (raum == null || wert == null || einheit == null) {
			throw new WebApplicationException(
					Response.status(400).entity("Temperatur could not be added").build());
		}
		
		Temperatur temperatur = new Temperatur();
		
		temperatur.setRaum(raum);
		temperatur.setWert(wert);
		temperatur.setEinheit(einheit);
		
		
		temperaturenDB.add(temperatur);
		MySQLConnection.putTemperatur(temperaturenDB);
		
		URI location = addTemperatur(temperatur);
		
		return Response.created(location).build();
	}
	
	@OPTIONS
	public Response optionsOptions() {
		return Response.ok().
			header("Allow-Control-Allow-Methods", "POST,GET,OPTIONS").
			header("Access-Control-Allow-Origin", "*").build();
	}
	
	private URI addTemperatur(Temperatur temperatur){
		System.out.println("DEBUG: adding new temperatur ("
				+ temperatur.getRaum() + ", " + temperatur.getWert() + ", "
				+ temperatur.getEinheit() + ")");
		int index = temperaturenList.getTemperaturEl().size();
		
		temperatur.setId(index);
		
		URI location = uriInfo.getAbsolutePathBuilder().path("" + index).build();
		
		temperaturenList.getTemperaturEl().add(temperatur);
		
		return location;
	}
}
