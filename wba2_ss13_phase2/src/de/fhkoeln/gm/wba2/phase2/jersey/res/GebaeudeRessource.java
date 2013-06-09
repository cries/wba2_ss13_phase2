package de.fhkoeln.gm.wba2.phase2.jersey.res;

import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.wba2.phase2.jersey.generated.*;
import de.fhkoeln.gm.wba2.phase2.jersey.jaxb.DataHandler;

@Path("/")
public class GebaeudeRessource {

	private DataHandler dh;
	
	public GebaeudeRessource() {
		dh = DataHandler.getInstance();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getGebaeude(){
		String gebaeude = dh.getGebaeude();
		return Response.ok().entity(gebaeude).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/heizung")
	public Response getHeizung() {
		String heizung = dh.getHeizung();
		return Response.ok().entity(heizung).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/heizung/ist")
	public Response getHeizungIst() {
		String heizungIst = dh.getHeizungIst();
		return Response.ok().entity(heizungIst).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/heizung/soll")
	public Response getHeizungSoll() {
		String heizungSoll = dh.getHeizungSoll();
		return Response.ok().entity(heizungSoll).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/kamera")
	public Response getKameras() {
		String kameras = dh.getKameras();
		return Response.ok().entity(kameras).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/kamera/{kameraid}")
	public Response getKameras(@PathParam("kameraid") BigInteger kamera_id) {
		String kameras = dh.getKamera(kamera_id);
		return Response.ok().entity(kameras).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/energie")
	public Response getEnergie() {
		String energie = dh.getEnergie();
		return Response.ok().entity(energie).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage")
	public Response getEtagen() {
		String etagen = dh.getEtagen();
		return Response.ok().entity(etagen).type(MediaType.APPLICATION_XML).build();
	}
	
	@POST
	@Path("/etage")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createEtage(String body) {
		BigInteger etagen_id = dh.createEtage(body);
		if(etagen_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id;
			return Response.status(201).header("Location", location).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Path("/etage/{etagenid}")
	public Response getEtage(@PathParam("etagenid") BigInteger etagen_id){
		String etage = dh.getEtage(etagen_id);
		return Response.ok().entity(etage).type(MediaType.APPLICATION_XML).build();
	}
	
	
	@GET
	@Path("/etage/{etagenid}/raum")
	public Response getRaeume(@PathParam("etagenid") BigInteger etagen_id){
		String raeume = dh.getRaeume(etagen_id);
		return Response.ok().entity(raeume).type(MediaType.APPLICATION_XML).build();
	}
	
	@POST
	@Path("/etage/{etagenid}/raum")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createRaum(@PathParam("etagenid") BigInteger etagen_id, String body) {
		BigInteger raum_id = dh.createRaum(etagen_id, body);
		if(raum_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id + "/raum/" + raum_id;
			return Response.status(201).header("Location", location).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}")
	public Response getRaum(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String raeume = dh.getRaum(etagen_id, raum_id);
		return Response.ok().entity(raeume).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
	public Response getRaumFeuchtigkeit(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String feuchte = dh.getRaumFeuchtigkeit(etagen_id, raum_id);
		return Response.ok().entity(feuchte).type(MediaType.APPLICATION_XML).build();
	}
	
//	@POST
//	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
//	@Consumes(MediaType.APPLICATION_XML)
//	public Response createFeuchtigkeit(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id, String body) {
//		BigInteger feuchtigkeit_id = dh.createFeuchtigkeit(etagen_id, raum_id body);
//		if(feuchtigkeit_id != null) {
//			String location = "http://localhost:4711/etage/" + etagen_id + "/raum/" + raum_id + "/feuchtigkeit";
//			return Response.status(201).header("Location", location).build();
//		}
//		else {
//			return Response.status(404).build();
//		}
//	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/energie")
	public Response getRaumEnergie(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String energie = dh.getRaumEnergie(etagen_id, raum_id);
		return Response.ok().entity(energie).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur")
	public Response getRaumTemperatur(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String temperatur = dh.getRaumTemperatur(etagen_id, raum_id);
		return Response.ok().entity(temperatur).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/ist")
	public Response getRaumTemperaturIst(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String temperaturIst = dh.getRaumTemperaturIst(etagen_id, raum_id);
		return Response.ok().entity(temperaturIst).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/soll")
	public Response getRaumTemperaturSoll(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String temperaturSoll = dh.getRaumTemperaturSoll(etagen_id, raum_id);
		return Response.ok().entity(temperaturSoll).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/licht")
	public Response getRaumLichter(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String lichter = dh.getRaumLichter(etagen_id, raum_id);
		return Response.ok().entity(lichter).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/licht/{elemid}")
	public Response getRaumLicht(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id, @PathParam("elemid") BigInteger elem_id){
		String licht = dh.getRaumLicht(etagen_id, raum_id, elem_id);
		return Response.ok().entity(licht).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung")
	public Response getRaumVerschattungen(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String verschattungen = dh.getRaumVerschattungen(etagen_id, raum_id);
		return Response.ok().entity(verschattungen).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung/{elemid}")
	public Response getRaumVerschattung(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id, @PathParam("elemid") BigInteger elem_id){
		String verschattung = dh.getRaumVerschattung(etagen_id, raum_id, elem_id);
		return Response.ok().entity(verschattung).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose")
	public Response getRaumSteckdosen(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id){
		String steckdosen = dh.getRaumSteckdosen(etagen_id, raum_id);
		return Response.ok().entity(steckdosen).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose/{elemid}")
	public Response getRaumSteckdose(@PathParam("etagenid") BigInteger etagen_id, @PathParam("raumid") BigInteger raum_id, @PathParam("elemid") BigInteger elem_id){
		String steckdose = dh.getRaumSteckdose(etagen_id, raum_id, elem_id);
		return Response.ok().entity(steckdose).type(MediaType.APPLICATION_XML).build();
	}
	
}
