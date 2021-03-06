package de.fhkoeln.gm.wba2.phase2.rest.resource;

import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.wba2.phase2.rest.generated.*;
import de.fhkoeln.gm.wba2.phase2.rest.jaxb.DataHandler;

@Path("/")
public class GebaeudeRessource {

	private DataHandler dh;

	public GebaeudeRessource() {
		dh = DataHandler.getInstance();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getGebaeude() {
		String gebaeude = dh.getGebaeude();
		return Response.ok().entity(gebaeude).type(MediaType.APPLICATION_XML)
				.build();
	}

	@GET
	@Path("/etage")
	@Produces(MediaType.APPLICATION_XML)
	public Response getEtagen() {
		String etagen = dh.getEtagen();
		if (etagen != null)
			return Response.ok().entity(etagen).type(MediaType.APPLICATION_XML)
					.build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createEtage(String body) {
		BigInteger etagen_id = dh.createEtage(body);
		if (etagen_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage")
	public Response deleteEtagen() {
		Boolean success = dh.deleteEtagen();
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getEtage(@PathParam("etagenid") BigInteger etagen_id) {
		String etage = dh.getEtage(etagen_id);
		if (etage != null)
			return Response.ok().entity(etage).type(MediaType.APPLICATION_XML)
					.build();
		else
			return Response.status(404).build();
	}

	@DELETE
	@Path("/etage/{etagenid}")
	public Response deleteEtage(@PathParam("etagenid") BigInteger etagen_id) {
		Boolean success = dh.deleteEtage(etagen_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaeume(@PathParam("etagenid") BigInteger etagen_id) {
		String raeume = dh.getRaeume(etagen_id);
		if (raeume != null)
			return Response.ok().entity(raeume).type(MediaType.APPLICATION_XML)
					.build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createRaum(@PathParam("etagenid") BigInteger etagen_id,
			String body) {
		BigInteger raum_id = dh.createRaum(etagen_id, body);
		if (raum_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum")
	public Response deleteRaeume(@PathParam("etagenid") BigInteger etagen_id) {
		Boolean success = dh.deleteRaeume(etagen_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaum(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String raeume = dh.getRaum(etagen_id, raum_id);
		if (raeume != null)
			return Response.ok().entity(raeume).type(MediaType.APPLICATION_XML)
					.build();
		else
			return Response.status(404).build();
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}")
	public Response deleteRaum(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteRaum(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumFeuchtigkeit(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String feuchte = dh.getRaumFeuchtigkeit(etagen_id, raum_id);
		if (feuchte != null)
			return Response.ok().entity(feuchte)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createFeuchtigkeit(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		Boolean feuchtigkeit_id = dh.createFeuchtigkeit(etagen_id, raum_id,
				body);
		if (feuchtigkeit_id) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/feuchtigkeit";
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateFeuchtigkeit(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		Boolean success = dh.updateFeuchtigkeit(etagen_id, raum_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/feuchtigkeit")
	public Response deleteFeuchtigkeit(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteFeuchtigkeit(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/energie")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumEnergie(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String energie = dh.getRaumEnergie(etagen_id, raum_id);
		if (energie != null)
			return Response.ok().entity(energie)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/energie")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createRaumEnergie(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		Boolean energie_id = dh.createRaumEnergie(etagen_id, raum_id, body);
		if (energie_id) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/energie";
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/energie")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateRaumEnergie(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			String body) {
		Boolean success = dh.updateRaumEnergie(etagen_id, raum_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/energie")
	public Response deleteEnergie(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteRaumEnergie(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumTemperatur(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String temperatur = dh.getRaumTemperatur(etagen_id, raum_id);
		if (temperatur != null)
			return Response.ok().entity(temperatur)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createTemperatur(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		Boolean energie_id = dh.createTemperatur(etagen_id, raum_id, body);
		if (energie_id) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/temperatur";
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur")
	public Response deleteTemperatur(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteTemperatur(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/ist")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumTemperaturIst(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String temperaturIst = dh.getRaumTemperaturIst(etagen_id, raum_id);
		return Response.ok().entity(temperaturIst)
				.type(MediaType.APPLICATION_XML).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/ist")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateTemperaturIst(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			String body) {
		Boolean success = dh.updateTemperaturIst(etagen_id, raum_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/soll")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumTemperaturSoll(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String temperaturSoll = dh.getRaumTemperaturSoll(etagen_id, raum_id);
		return Response.ok().entity(temperaturSoll)
				.type(MediaType.APPLICATION_XML).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/temperatur/soll")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateTemperaturSoll(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			String body) {
		Boolean success = dh.updateTemperaturSoll(etagen_id, raum_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/licht")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumLichter(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String lichter = dh.getRaumLichter(etagen_id, raum_id);
		if (lichter != null)
			return Response.ok().entity(lichter)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/licht")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createLicht(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger licht_id = dh.createLicht(etagen_id, raum_id, body);
		if (licht_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/licht/" + licht_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/licht")
	public Response deleteLichter(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteLichter(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/licht/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumLicht(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String licht = dh.getRaumLicht(etagen_id, raum_id, elem_id);
		if (licht != null)
			return Response.ok().entity(licht).type(MediaType.APPLICATION_XML)
					.build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/licht/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateLicht(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateLicht(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/licht/{elemid}")
	public Response deleteLicht(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteLicht(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumVerschattungen(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String verschattungen = dh.getRaumVerschattungen(etagen_id, raum_id);
		if (verschattungen != null)
			return Response.ok().entity(verschattungen)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createVerschattung(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger verschattung_id = dh.createVerschattung(etagen_id, raum_id,
				body);
		if (verschattung_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/verschattung/" + verschattung_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung")
	public Response deleteVerschattungen(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteVerschattungen(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumVerschattung(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String verschattung = dh.getRaumVerschattung(etagen_id, raum_id,
				elem_id);
		if (verschattung != null)
			return Response.ok().entity(verschattung)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateVerschattung(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateVerschattung(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/verschattung/{elemid}")
	public Response deleteVerschattung(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteVerschattung(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumSteckdosen(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String steckdosen = dh.getRaumSteckdosen(etagen_id, raum_id);
		if (steckdosen != null)
			return Response.ok().entity(steckdosen)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createSteckdose(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger steckdose_id = dh.createSteckdose(etagen_id, raum_id, body);
		if (steckdose_id != null) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/steckdose/" + steckdose_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}
	

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose")
	public Response deleteSteckdosen(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteSteckdosen(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumSteckdose(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String steckdose = dh.getRaumSteckdose(etagen_id, raum_id, elem_id);
		if (steckdose != null)
			return Response.ok().entity(steckdose)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateSteckdose(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateSteckdose(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/steckdose/{elemid}")
	public Response deleteSteckdose(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteSteckdose(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumKontakte(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String kontakte = dh.getRaumKontakte(etagen_id, raum_id);
		if (kontakte != null)
			return Response.ok().entity(kontakte)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createKontakt(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger kontakt_id = dh.createKontakt(etagen_id, raum_id, body);
		if (kontakt_id.intValue() > 0) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/kontakt/" + kontakt_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt")
	public Response deleteKontakte(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteKontakte(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumKontakt(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String kontakt = dh.getRaumKontakt(etagen_id, raum_id, elem_id);
		if (kontakt != null)
			return Response.ok().entity(kontakt)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateKontakt(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateKontakt(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/kontakt/{elemid}")
	public Response deleteKontakt(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteKontakt(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumFeuermelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String kontakte = dh.getRaumFeuermelder(etagen_id, raum_id);
		if (kontakte != null)
			return Response.ok().entity(kontakte)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createFeuermelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger feuermelder_id = dh.createFeuermelder(etagen_id, raum_id,
				body);
		if (feuermelder_id.intValue() > 0) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/feuermelder/" + feuermelder_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder")
	public Response deleteFeuermelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteFeuermelder(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumFeuermeld(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String feuermeld = dh.getRaumFeuermeld(etagen_id, raum_id, elem_id);
		if (feuermeld != null)
			return Response.ok().entity(feuermeld)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateFeuermelder(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateFeuermelder(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/feuermelder/{elemid}")
	public Response deleteFeuermeld(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteFeuermeld(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumBewegungsmelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		String bewegungsmelder = dh.getRaumBewegungsmelder(etagen_id, raum_id);
		if (bewegungsmelder != null)
			return Response.ok().entity(bewegungsmelder)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}

	@POST
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBewegungsmelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id, String body) {
		BigInteger bewegungsmelder_id = dh.createBewegungsmelder(etagen_id,
				raum_id, body);
		if (bewegungsmelder_id.intValue() > 0) {
			String location = "http://localhost:4711/etage/" + etagen_id
					+ "/raum/" + raum_id + "/bewegungsmelder/"
					+ bewegungsmelder_id;
			return Response.status(201).header("Location", location).build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder")
	public Response deleteBewegungsmelder(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id) {
		Boolean success = dh.deleteBewegungsmelder(etagen_id, raum_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@GET
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder/{elemid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getRaumBewegungsmeld(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		String bewegungsmelder = dh.getRaumBewegungsmeld(etagen_id, raum_id,
				elem_id);
		if (bewegungsmelder != null)
			return Response.ok().entity(bewegungsmelder)
					.type(MediaType.APPLICATION_XML).build();
		else
			return Response.status(404).build();
	}
	
	@PUT
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder/{elemid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateBewegungsmelder(@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id,
			String body) {
		Boolean success = dh.updateBewegungsmelder(etagen_id, raum_id, elem_id, body);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/etage/{etagenid}/raum/{raumid}/bewegungsmelder/{elemid}")
	public Response deleteBewegungsmeld(
			@PathParam("etagenid") BigInteger etagen_id,
			@PathParam("raumid") BigInteger raum_id,
			@PathParam("elemid") BigInteger elem_id) {
		Boolean success = dh.deleteBewegungsmeld(etagen_id, raum_id, elem_id);
		if (success)
			return Response.noContent().build();
		else {
			return Response.status(404).build();
		}
	}
}
