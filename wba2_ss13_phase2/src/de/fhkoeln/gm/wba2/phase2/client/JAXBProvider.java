package de.fhkoeln.gm.wba2.phase2.client;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import de.fhkoeln.gm.wba2.phase2.rest.generated.Bewegung;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Bewegungen;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Energie;
import de.fhkoeln.gm.wba2.phase2.rest.generated.EnergieRaum;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Etage;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Etagen;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Feuchtigkeit;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Feuermeld;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Feuermelder;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Gebaeude;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Heizung;
import de.fhkoeln.gm.wba2.phase2.rest.generated.HeizungIst;
import de.fhkoeln.gm.wba2.phase2.rest.generated.HeizungSoll;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Kamera;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Kameras;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Kontakt;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Kontakte;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Licht;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Lichter;
import de.fhkoeln.gm.wba2.phase2.rest.generated.ObjectFactory;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Raeume;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Raum;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Steckdose;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Steckdosen;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Temperatur;
import de.fhkoeln.gm.wba2.phase2.rest.generated.TemperaturIst;
import de.fhkoeln.gm.wba2.phase2.rest.generated.TemperaturSoll;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Verschattung;
import de.fhkoeln.gm.wba2.phase2.rest.generated.Verschattungen;

public class JAXBProvider {

	public static String marshall(Object instance) {

		if (instance == null)
			return null;

		String str = null;

		ObjectFactory objFact = new ObjectFactory();

		JAXBElement<?> jaxbe = null;

		switch (instance.getClass().getSimpleName()) {
		case "Gebaeude":
			jaxbe = objFact.createGebaeudeEl((Gebaeude) instance);
			break;
		case "Heizung":
			jaxbe = objFact.createHeizungEl((Heizung) instance);
			break;
		case "HeizungIst":
			jaxbe = objFact.createHeizungIstEl((HeizungIst) instance);
			break;
		case "HeizungSoll":
			jaxbe = objFact.createHeizungSollEl((HeizungSoll) instance);
			break;
		case "Kameras":
			jaxbe = objFact.createKamerasEl((Kameras) instance);
			break;
		case "Kamera":
			jaxbe = objFact.createKameraEl((Kamera) instance);
			break;
		case "Energie":
			jaxbe = objFact.createEnergieEl((Energie) instance);
			break;
		case "Etagen":
			jaxbe = objFact.createEtagenEl((Etagen) instance);
			break;
		case "Etage":
			jaxbe = objFact.createEtageEl((Etage) instance);
			break;
		case "Raeume":
			jaxbe = objFact.createRaeumeEl((Raeume) instance);
			break;
		case "Raum":
			jaxbe = objFact.createRaumEl((Raum) instance);
			break;
		case "Feuchtigkeit":
			jaxbe = objFact.createFeuchtigkeitEl((Feuchtigkeit) instance);
			break;
		case "EnergieRaum":
			jaxbe = objFact.createEnergieRaumEl((EnergieRaum) instance);
			break;
		case "Temperatur":
			jaxbe = objFact.createTemperaturEl((Temperatur) instance);
			break;
		case "TemperaturIst":
			jaxbe = objFact.createTemperaturIstEl((TemperaturIst) instance);
			break;
		case "TemperaturSoll":
			jaxbe = objFact.createTemperaturSollEl((TemperaturSoll) instance);
			break;
		case "Lichter":
			jaxbe = objFact.createLichterEl((Lichter) instance);
			break;
		case "Licht":
			jaxbe = objFact.createLichtEl((Licht) instance);
			break;
		case "Steckdosen":
			jaxbe = objFact.createSteckdosenEl((Steckdosen) instance);
			break;
		case "Steckdose":
			jaxbe = objFact.createSteckdoseEl((Steckdose) instance);
			break;
		case "Verschattungen":
			jaxbe = objFact.createVerschattungenEl((Verschattungen) instance);
			break;
		case "Verschattung":
			jaxbe = objFact.createVerschattungEl((Verschattung) instance);
			break;
		case "Kontakte":
			jaxbe = objFact.createKontakteEl((Kontakte) instance);
			break;
		case "Kontakt":
			jaxbe = objFact.createKontaktEl((Kontakt) instance);
			break;
		case "Feuermelder":
			jaxbe = objFact.createFeuermelderEl((Feuermelder) instance);
			break;
		case "Feuermeld":
			jaxbe = objFact.createFeuermeldEl((Feuermeld) instance);
			break;
		case "Bewegungen":
			jaxbe = objFact.createBewegungenEl((Bewegungen) instance);
			break;
		case "Bewegung":
			jaxbe = objFact.createBewegungEl((Bewegung) instance);
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

	public static Object unmarshall(String str, Class<?> c) {
		Object element = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller um = context.createUnmarshaller();
			element = um.unmarshal(new StreamSource(new StringReader(str)), c)
					.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return element;
	}
}
