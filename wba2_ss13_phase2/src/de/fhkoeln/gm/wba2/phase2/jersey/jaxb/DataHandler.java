package de.fhkoeln.gm.wba2.phase2.jersey.jaxb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import de.fhkoeln.gm.wba2.phase2.jersey.generated.*;

public class DataHandler {
	
	static DataHandler instance;
	
	static File XMLFile;
	static JAXBContext context;
	static Gebaeude rootEl;
	
	private DataHandler() {
		
		XMLFile = new File("./misc/building.xml");
		
		try {
			context = JAXBContext.newInstance(Gebaeude.class);
			
			if(!XMLFile.exists()) {
				
				XMLFile.createNewFile();
				Marshaller m = context.createMarshaller();
				
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				
				m.marshal(new ObjectFactory().createGebaeudeEl(new Gebaeude()), XMLFile);
			}
			
			Unmarshaller um = context.createUnmarshaller();
			StreamSource str_src = new StreamSource(XMLFile);
			rootEl = (Gebaeude) um.unmarshal(str_src, Gebaeude.class).getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DataHandler getInstance() {
		if(instance == null)
			instance = new DataHandler();
		return instance;
	}
	
	private Etage getEtageObj(BigInteger etagen_id){
		Etage etageObj = null;
		List<Etage> etagen_list= rootEl.getEtagenEl().getEtageEl();
		
		for(Etage curr_etage: etagen_list){
			if(curr_etage.getId().equals(etagen_id)){
				etageObj = curr_etage;
				break;
			}
		}
		return etageObj;
	}
	
	private Raum getRaumObj(BigInteger etage_id, BigInteger raum_id){
		Raum raumObj = null;
		List<Raum> raeume_list = getEtageObj(etage_id).getRaeumeEl().getRaumEl();
	
		for(Raum curr_raum: raeume_list){
			if(curr_raum.getId().equals(raum_id)){
				raumObj = curr_raum;
				break;
			}
		}
		return raumObj;
	}
	
	public String getGebaeude() {
		Gebaeude gebaeude_elem = rootEl;
		return marshall(gebaeude_elem);
	}
	
	public String getHeizung() {
		Heizung heizung_elem = rootEl.getHeizungEl();
		return marshall(heizung_elem);
	}
	
	public String getHeizungIst() {
		HeizungIst heizungIst_elem = rootEl.getHeizungEl().getHeizungIstEl();
		return marshall(heizungIst_elem);
	}
	
	public String getHeizungSoll() {
		HeizungSoll heizungSoll_elem = rootEl.getHeizungEl().getHeizungSollEl();
		return marshall(heizungSoll_elem);
	}
	
	public String getKameras() {
		Kameras kameras_elem = rootEl.getKamerasEl();
		return marshall(kameras_elem);
	}
	
	public String getKamera(BigInteger kamera_id) {
		Kamera kameraObj = null;
		List<Kamera> kameras_list = rootEl.getKamerasEl().getKameraEl();
		
		for(Kamera curr_kamera: kameras_list){
			if(curr_kamera.getId().equals(kamera_id)){
				kameraObj = curr_kamera;
				break;
			}
		}
		return marshall(kameraObj);
	}
	
	public String getEnergie() {
		Energie energie_elem = rootEl.getEnergieEl();
		return marshall(energie_elem);
	}
	
	public String getEtagen() {
		Etagen etagen_elem = rootEl.getEtagenEl();
		return marshall(etagen_elem);
	}
	
	public String getEtage(BigInteger id) {
		return marshall(getEtageObj(id));
	}
	
	public String getRaeume(BigInteger etage_id) {
		return marshall(getEtageObj(etage_id).getRaeumeEl());
	}
	
	public String getRaum(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id));
	}
	
	public String getRaumFeuchtigkeit(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getFeuchtigkeitEl());
	}
	
	public String getRaumEnergie(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getEnergieRaumEl());
	}
	
	public String getRaumTemperatur(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getTemperaturEl());
	}
	
	public String getRaumTemperaturIst(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getTemperaturEl().getTemperaturIstEl());
	}
	
	public String getRaumTemperaturSoll(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getTemperaturEl().getTemperaturSollEl());
	}
	
	public String getRaumLichter(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getLichterEl());
	}
	
	public String getRaumLicht(BigInteger etage_id, BigInteger raum_id, BigInteger licht_id) {
		Licht lichtObj = null;
		List<Licht> lichter_list = getRaumObj(etage_id, raum_id).getLichterEl().getLichtEl();
		
		for(Licht curr_licht: lichter_list){
			if(curr_licht.getId().equals(licht_id)){
				lichtObj = curr_licht;
				break;
			}
		}
		return marshall(lichtObj);
	}
	
	public String getRaumVerschattungen(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getVerschattungenEl());
	}
	
	public String getRaumVerschattung(BigInteger etage_id, BigInteger raum_id, BigInteger licht_id) {
		Verschattung verschattungObj = null;
		List<Verschattung> verschattungen_list = getRaumObj(etage_id, raum_id).getVerschattungenEl().getVerschattungEl();
		
		for(Verschattung curr_versch: verschattungen_list){
			if(curr_versch.getId().equals(licht_id)){
				verschattungObj = curr_versch;
				break;
			}
		}
		return marshall(verschattungObj);
	}
	
	public String getRaumSteckdosen(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getSteckdosenEl());
	}
	
	public String getRaumSteckdose(BigInteger etage_id, BigInteger raum_id, BigInteger licht_id) {
		Steckdose steckdoseObj = null;
		List<Steckdose> steckdosen_list = getRaumObj(etage_id, raum_id).getSteckdosenEl().getSteckdoseEl();
		
		for(Steckdose curr_steckdose: steckdosen_list){
			if(curr_steckdose.getId().equals(licht_id)){
				steckdoseObj = curr_steckdose;
				break;
			}
		}
		return marshall(steckdoseObj);
	}
	
	public BigInteger createEtage(String etage_data){
		Etage newEtage = (Etage)unmarshall(etage_data, Etage.class);
		int arraySize = rootEl.getEtagenEl().getEtageEl().size();
		int new_id = 1;
		
		if(arraySize > 0){
			Etage last_etage = rootEl.getEtagenEl().getEtageEl().get(arraySize-1);
			new_id = last_etage.getId().intValue() + 1;
		}
		
		newEtage.setId(BigInteger.valueOf(new_id));
	
		rootEl.getEtagenEl().getEtageEl().add(newEtage);
		
		root_marshall();
		
		return BigInteger.valueOf(new_id);
	}
	
	public BigInteger createRaum(BigInteger etagen_id, String raum_data){
		Raum newRaum = (Raum)unmarshall(raum_data, Raum.class);
		int arraySize = getEtageObj(etagen_id).getRaeumeEl().getRaumEl().size();
		int new_id = 1;
		
		if(arraySize > 0){
			Raum last_raum = getEtageObj(etagen_id).getRaeumeEl().getRaumEl().get(arraySize-1);
			new_id = last_raum.getId().intValue() + 1;
		}
		
		newRaum.setId(BigInteger.valueOf(new_id));
	
		getEtageObj(etagen_id).getRaeumeEl().getRaumEl().add(newRaum);
		
		root_marshall();
		
		return BigInteger.valueOf(new_id);
	}
	
	private String marshall(Object instance) {

		if(instance == null)
		return null;
		
		String str = null;
		
		ObjectFactory objFact = new ObjectFactory();
		
		
		JAXBElement<?> jaxbe = null;
		
		
		switch(instance.getClass().getSimpleName()){
			case "Gebaeude":
				jaxbe = objFact.createGebaeudeEl((Gebaeude)instance);
			break;
			case "Heizung":
				jaxbe = objFact.createHeizungEl((Heizung)instance);
			break;
			case "HeizungIst":
				jaxbe = objFact.createHeizungIstEl((HeizungIst)instance);
			break;
			case "HeizungSoll":
				jaxbe = objFact.createHeizungSollEl((HeizungSoll)instance);
			break;
			case "Kameras":
				jaxbe = objFact.createKamerasEl((Kameras)instance);
			break;
			case "Kamera":
				jaxbe = objFact.createKameraEl((Kamera)instance);
			break;
			case "Energie":
				jaxbe = objFact.createEnergieEl((Energie)instance);
			break;
			case "Etagen":
				jaxbe = objFact.createEtagenEl((Etagen)instance);
			break;
			case "Etage":
				jaxbe = objFact.createEtageEl((Etage)instance);
			break;
			case "Raeume":
				jaxbe = objFact.createRaeumeEl((Raeume)instance);
			break;
			case "Raum":
				jaxbe = objFact.createRaumEl((Raum)instance);
			break;
			case "Feuchtigkeit":
				jaxbe = objFact.createFeuchtigkeitEl((Feuchtigkeit)instance);
			break;
			case "EnergieRaum":
				jaxbe = objFact.createEnergieRaumEl((EnergieRaum)instance);
			break;
			case "Temperatur":
				jaxbe = objFact.createTemperaturEl((Temperatur)instance);
			break;
			case "TemperaturIst":
				jaxbe = objFact.createTemperaturIstEl((TemperaturIst)instance);
			break;
			case "TemperaturSoll":
				jaxbe = objFact.createTemperaturSollEl((TemperaturSoll)instance);
			break;
			case "Lichter":
				jaxbe = objFact.createLichterEl((Lichter)instance);
			break;
			case "Licht":
				jaxbe = objFact.createLichtEl((Licht)instance);
			break;
			case "Steckdosen":
				jaxbe = objFact.createSteckdosenEl((Steckdosen)instance);
			break;
			case "Steckdose":
				jaxbe = objFact.createSteckdoseEl((Steckdose)instance);
			break;
			case "Verschattungen":
				jaxbe = objFact.createVerschattungenEl((Verschattungen)instance);
			break;
			case "Verschattung":
				jaxbe = objFact.createVerschattungEl((Verschattung)instance);
			break;
			default:
				return null;
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance(instance.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			ByteArrayOutputStream string_out = new ByteArrayOutputStream();
			
			
			m.marshal(jaxbe, string_out);
			
			str = string_out.toString();
			} catch (JAXBException e) {
			e.printStackTrace();
			}
		
		return str;
	}
	
	private void root_marshall() {
		try {
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(new ObjectFactory().createGebaeudeEl(rootEl), XMLFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private Object unmarshall(String str, Class<?> c) {
		Object element = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller um = context.createUnmarshaller();
			element = um.unmarshal(new StreamSource(new StringReader(str)), c).getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return element;
	}



}
