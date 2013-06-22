package de.fhkoeln.gm.wba2.phase2.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import de.fhkoeln.gm.wba2.phase2.rest.generated.*;

public class RESTHandler {
	
	private String hostname;
	private int port;
	private WebResource service;
	
	public RESTHandler(String hostname, int port){
		this.hostname = hostname;
		this.port = port;
	}
	
	public boolean getConnection(){
		
		if (this.hostname == null || this.port <= 0) 
			return false;
		
		this.service = Client.create().resource("http://" + this.hostname + ":" + this.port);
		
		Socket sock = null;
        try {
            sock = new Socket(hostname, port);
            return true;
        } catch (IOException e) {}
        finally {
            if(sock != null) { try { sock.close(); } catch (IOException e) {} }
        }
        
        return false;
	}
	
	public List<Etage> getEtagenListe(){
		List<Etage> myList = new ArrayList<Etage>();
		
		ClientResponse response;
        try {
            response = service.path("/etage").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
            return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = response.getEntity(Etagen.class).getEtageEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Raum> getRaumListe(int etagenid){
		List<Raum> myList = new ArrayList<Raum>();
		
		ClientResponse response;
        try {
            response = service.path("/etage/" + etagenid + "/raum").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
            return myList;
        }
        
        if(response.getStatus() == 200) {
        	myList = response.getEntity(Raeume.class).getRaumEl(); 
        } else { return null; }
        
        return myList;
	}
	
	public Temperatur getRaumTemperatur(int etagenid, int raumid){
		Temperatur myObj = null;
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/temperatur").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Temperatur.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Feuchtigkeit getRaumFeuchtigkeit(int etagenid, int raumid){
		Feuchtigkeit myObj = null;
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/feuchtigkeit").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Feuchtigkeit.class);
        } else { return null; }
        
        return myObj;
	}
	
	public EnergieRaum getRaumEnergie(int etagenid, int raumid){
		EnergieRaum myObj = null;
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/energie").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(EnergieRaum.class);
        } else { return null; }
        
        return myObj;
	}
	
	public List<Licht> getLichtListe(int etagenid, int raumid){
		List<Licht> myList = new ArrayList<Licht>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/licht").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = (List<Licht>) response.getEntity(Lichter.class).getLichtEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Verschattung> getVerschattungListe(int etagenid, int raumid){
		List<Verschattung> myList = new ArrayList<Verschattung>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/verschattung").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = (List<Verschattung>) response.getEntity(Verschattungen.class).getVerschattungEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Steckdose> getSteckdoseListe(int etagenid, int raumid){
		List<Steckdose> myList = new ArrayList<Steckdose>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/steckdose").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = response.getEntity(Steckdosen.class).getSteckdoseEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Kontakt> getKontaktListe(int etagenid, int raumid){
		List<Kontakt> myList = new ArrayList<Kontakt>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/kontakt").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = response.getEntity(Kontakte.class).getKontaktEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Feuermeld> getFeuermelderListe(int etagenid, int raumid){
		List<Feuermeld> myList = new ArrayList<Feuermeld>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/feuermelder").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = response.getEntity(Feuermelder.class).getFeuermeldEl();
        } else { return null; }
        
        return myList;
	}
	
	public List<Bewegung> getBewegungsmelderListe(int etagenid, int raumid){
		List<Bewegung> myList = new ArrayList<Bewegung>();
		ClientResponse response = null;
        try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/bewegungsmelder").type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myList;
        }
        
        if(response.getStatus() == 200) {
            myList = response.getEntity(Bewegungen.class).getBewegungEl();
        } else { return null; }
        
        return myList;
	}
	
	public Licht getLichtObj(int etagenid, int raumid, int elemid){
		Licht myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/licht/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Licht.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Verschattung getVerschattungObj(int etagenid, int raumid, int elemid){
		Verschattung myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/verschattung/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Verschattung.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Steckdose getSteckdoseObj(int etagenid, int raumid, int elemid){
		Steckdose myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/steckdose/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Steckdose.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Kontakt getKontaktObj(int etagenid, int raumid, int elemid){
		Kontakt myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/kontakt/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Kontakt.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Bewegung getBewegungObj(int etagenid, int raumid, int elemid){
		Bewegung myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/bewegungsmelder/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Bewegung.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Feuermeld getFeuermeldObj(int etagenid, int raumid, int elemid){
		Feuermeld myObj = null;
		ClientResponse response = null;
		try {
            response = service.path("/etage/" + etagenid + "/raum/" + raumid + "/feuermelder/" + elemid).type(MediaType.APPLICATION_XML).get(ClientResponse.class);
        }
        catch(ClientHandlerException che) {
        	return myObj;
        }
        
        if(response.getStatus() == 200) {
            myObj = response.getEntity(Feuermeld.class);
        } else { return null; }
        
        return myObj;
	}
	
	public Boolean deleteEtage(int etagenid){
		ClientResponse response = null;
		try {
			response = service.path("/etage/"+ etagenid).type(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 204) {
			return true;
		}
		 
		return false;	
	}
	
	public Boolean deleteRaum(int etagenid, int raumid){
		ClientResponse response = null;
		try {
			response = service.path("/etage/"+ etagenid + "/raum/" + raumid).type(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 204) {
			return true;
		}
		 
		return false;	
	}
	
	public Boolean deleteKategorie(int etagenid, int raumid, String cat){
		ClientResponse response = null;
		try {
			response = service.path("/etage/"+ etagenid + "/raum/" + raumid + "/" + cat).type(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 204) {
			return true;
		}
		 
		return false;	
	}
	
	public Boolean deleteElement(int etagenid, int raumid, String cat, int elemid){
		ClientResponse response = null;
		try {
			response = service.path("/etage/"+ etagenid + "/raum/" + raumid + "/" + cat + "/" + elemid).type(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 204) {
			return true;
		}
		 
		return false;	
	}
	
	public Boolean addEtage(String info){
		Etage newEtage = new Etage();
		newEtage.setInfo(info);
		ClientResponse response = null;
		try {
			response = service.path("/etage").type(MediaType.APPLICATION_XML)
					.entity(newEtage).post(ClientResponse.class, JAXBProvider.marshall(newEtage));
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 201) {
			String location = response.getLocation().toString();
			ClientResponse subresponse;
			try {
				subresponse = Client.create().resource(location).accept(MediaType.APPLICATION_XML)
						.get(ClientResponse.class);
			} catch (ClientHandlerException che) {
				return false;
			}
			if (subresponse.getStatus() == 200) {
				return true;
			}
		}
		 
		return false;	
	}
	
	public Boolean addRaum(int etagenid, String info){
		Raum newRaum = new Raum();
		newRaum.setInfo(info);
		ClientResponse response = null;
		try {
			response = service.path("/etage/" + etagenid + "/raum").type(MediaType.APPLICATION_XML)
					.entity(newRaum).post(ClientResponse.class, JAXBProvider.marshall(newRaum));
		} catch (ClientHandlerException che) {
			return false;
		}
		
		if(response.getStatus() == 201) {
			String location = response.getLocation().toString();
			ClientResponse subresponse;
			try {
				subresponse = Client.create().resource(location).accept(MediaType.APPLICATION_XML)
						.get(ClientResponse.class);
			} catch (ClientHandlerException che) {
				return false;
			}
			if (subresponse.getStatus() == 200) {
				return true;
			}
		}
		 
		return false;	
	}
}
