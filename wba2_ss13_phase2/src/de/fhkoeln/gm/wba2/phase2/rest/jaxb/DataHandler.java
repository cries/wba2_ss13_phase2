package de.fhkoeln.gm.wba2.phase2.rest.jaxb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import de.fhkoeln.gm.wba2.phase2.rest.generated.*;
import de.fhkoeln.gm.wba2.phase2.xmpp.connection.ConnectionHandler;

public class DataHandler {

	private ConnectionHandler connHndlr;
	static DataHandler instance;

	static File XMLFile;
	static JAXBContext context;
	static Gebaeude rootEl;

	public DataHandler() {

		connHndlr = new ConnectionHandler();
		if (connHndlr.connect("localhost", 5222)) {
			if (connHndlr.login("server", "login")) {
				System.out.println("Connected from REST!");
			}
		}

		XMLFile = new File("./misc/building.xml");

		try {
			context = JAXBContext.newInstance(Gebaeude.class);

			if (!XMLFile.exists()) {

				XMLFile.createNewFile();
				Marshaller m = context.createMarshaller();

				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				m.marshal(new ObjectFactory().createGebaeudeEl(new Gebaeude()),
						XMLFile);
			}

			Unmarshaller um = context.createUnmarshaller();
			StreamSource str_src = new StreamSource(XMLFile);
			rootEl = (Gebaeude) um.unmarshal(str_src, Gebaeude.class)
					.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DataHandler getInstance() {
		if (instance == null)
			instance = new DataHandler();
		return instance;
	}

	private Etage getEtageObj(BigInteger etagen_id) {
		Etage etageObj = null;
		List<Etage> etagen_list = rootEl.getEtagenEl().getEtageEl();

		for (Etage curr_etage : etagen_list) {
			if (curr_etage.getId().equals(etagen_id)) {
				etageObj = curr_etage;
				break;
			}
		}
		return etageObj;
	}

	private Raum getRaumObj(BigInteger etage_id, BigInteger raum_id) {
		Raum raumObj = null;
		List<Raum> raeume_list = getEtageObj(etage_id).getRaeumeEl()
				.getRaumEl();

		for (Raum curr_raum : raeume_list) {
			if (curr_raum.getId().equals(raum_id)) {
				raumObj = curr_raum;
				break;
			}
		}
		return raumObj;
	}

	private Kamera getKameraObj(BigInteger kamera_id) {
		Kamera kameraObj = null;
		List<Kamera> kamera_list = rootEl.getKamerasEl().getKameraEl();

		for (Kamera curr_kamera : kamera_list) {
			if (curr_kamera.getId().equals(kamera_id)) {
				kameraObj = curr_kamera;
				break;
			}
		}
		return kameraObj;
	}

	private Licht getLichtObj(BigInteger etagen_id, BigInteger raum_id,
			BigInteger licht_id) {
		Licht lichtObj = null;
		List<Licht> licht_list = getRaumObj(etagen_id, raum_id).getLichterEl()
				.getLichtEl();

		for (Licht curr_licht : licht_list) {
			if (curr_licht.getId().equals(licht_id)) {
				lichtObj = curr_licht;
				break;
			}
		}
		return lichtObj;
	}

	private Verschattung getVerschattungObj(BigInteger etagen_id,
			BigInteger raum_id, BigInteger verschattung_id) {
		Verschattung verschattungObj = null;
		List<Verschattung> verschattung_list = getRaumObj(etagen_id, raum_id)
				.getVerschattungenEl().getVerschattungEl();

		for (Verschattung curr_verschattung : verschattung_list) {
			if (curr_verschattung.getId().equals(verschattung_id)) {
				verschattungObj = curr_verschattung;
				break;
			}
		}
		return verschattungObj;
	}

	private Steckdose getSteckdoseObj(BigInteger etagen_id, BigInteger raum_id,
			BigInteger steckdose_id) {
		Steckdose steckdoseObj = null;
		List<Steckdose> steckdose_list = getRaumObj(etagen_id, raum_id)
				.getSteckdosenEl().getSteckdoseEl();

		for (Steckdose curr_steckdose : steckdose_list) {
			if (curr_steckdose.getId().equals(steckdose_id)) {
				steckdoseObj = curr_steckdose;
				break;
			}
		}
		return steckdoseObj;
	}

	private Kontakt getKontaktObj(BigInteger etagen_id, BigInteger raum_id,
			BigInteger kontakt_id) {
		Kontakt kontaktObj = null;
		List<Kontakt> kontakte_list = getRaumObj(etagen_id, raum_id)
				.getKontakteEl().getKontaktEl();

		for (Kontakt curr_kontakt : kontakte_list) {
			if (curr_kontakt.getId().equals(kontakt_id)) {
				kontaktObj = curr_kontakt;
				break;
			}
		}
		return kontaktObj;
	}

	private Feuermeld getFeuermeldObj(BigInteger etagen_id, BigInteger raum_id,
			BigInteger feuermeld_id) {
		Feuermeld feuermeldObj = null;
		List<Feuermeld> feuermelder_list = getRaumObj(etagen_id, raum_id)
				.getFeuermelderEl().getFeuermeldEl();

		for (Feuermeld curr_feuermeld : feuermelder_list) {
			if (curr_feuermeld.getId().equals(feuermeld_id)) {
				feuermeldObj = curr_feuermeld;
				break;
			}
		}
		return feuermeldObj;
	}

	private Bewegung getBewegungsmeldObj(BigInteger etagen_id,
			BigInteger raum_id, BigInteger bewegungsmeld_id) {
		Bewegung bewegungsmeldObj = null;
		List<Bewegung> bewegungsmelder_list = getRaumObj(etagen_id, raum_id)
				.getBewegungenEl().getBewegungEl();

		for (Bewegung curr_bewegungsmeld : bewegungsmelder_list) {
			if (curr_bewegungsmeld.getId().equals(bewegungsmeld_id)) {
				bewegungsmeldObj = curr_bewegungsmeld;
				break;
			}
		}
		return bewegungsmeldObj;
	}

	public String getGebaeude() {
		Gebaeude gebaeude_elem = rootEl;
		return marshall(gebaeude_elem);
	}

	public String getHeizung() {
		Heizung heizung_elem = rootEl.getHeizungEl();
		return marshall(heizung_elem);
	}

	public Boolean createHeizung(String heizung_data) {
		Heizung newHeizung = (Heizung) unmarshall(heizung_data, Heizung.class);
		HeizungIst newHeizungIst = new HeizungIst();
		HeizungSoll newHeizungSoll = new HeizungSoll();

		newHeizungIst.setEinheit("Celsius");
		newHeizungIst.setWert(BigDecimal.valueOf(20.0));
		newHeizungSoll.setEinheit("Celsius");
		newHeizungSoll.setWert(BigDecimal.valueOf(21.0));

		newHeizung.setHeizungIstEl(newHeizungIst);
		newHeizung.setHeizungSollEl(newHeizungSoll);

		rootEl.setHeizungEl(newHeizung);

		root_marshall();

		return true;
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

		for (Kamera curr_kamera : kameras_list) {
			if (curr_kamera.getId().equals(kamera_id)) {
				kameraObj = curr_kamera;
				break;
			}
		}
		return marshall(kameraObj);
	}

	public BigInteger createKamera(String kamera_data) {
		Kamera newKamera = (Kamera) unmarshall(kamera_data, Kamera.class);
		int arraySize = rootEl.getKamerasEl().getKameraEl().size();
		int new_id = 1;

		if (arraySize > 0) {
			Kamera last_kamera = rootEl.getKamerasEl().getKameraEl()
					.get(arraySize - 1);
			new_id = last_kamera.getId().intValue() + 1;
		}

		newKamera.setId(BigInteger.valueOf(new_id));

		rootEl.getKamerasEl().getKameraEl().add(newKamera);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public String getEnergie() {
		Energie energie_elem = rootEl.getEnergieEl();
		return marshall(energie_elem);
	}

	public Boolean createEnergie(String energie_data) {
		Energie newEnergie = (Energie) unmarshall(energie_data, Energie.class);

		rootEl.setEnergieEl(newEnergie);
		root_marshall();
		return true;
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
		return marshall(getRaumObj(etage_id, raum_id).getTemperaturEl()
				.getTemperaturIstEl());
	}

	public String getRaumTemperaturSoll(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getTemperaturEl()
				.getTemperaturSollEl());
	}

	public String getRaumLichter(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getLichterEl());
	}

	public String getRaumLicht(BigInteger etage_id, BigInteger raum_id,
			BigInteger licht_id) {
		Licht lichtObj = null;
		List<Licht> lichter_list = getRaumObj(etage_id, raum_id).getLichterEl()
				.getLichtEl();

		for (Licht curr_licht : lichter_list) {
			if (curr_licht.getId().equals(licht_id)) {
				lichtObj = curr_licht;
				break;
			}
		}
		return marshall(lichtObj);
	}

	public String getRaumVerschattungen(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getVerschattungenEl());
	}

	public String getRaumVerschattung(BigInteger etage_id, BigInteger raum_id,
			BigInteger verschattung_id) {
		Verschattung verschattungObj = null;
		List<Verschattung> verschattungen_list = getRaumObj(etage_id, raum_id)
				.getVerschattungenEl().getVerschattungEl();

		for (Verschattung curr_versch : verschattungen_list) {
			if (curr_versch.getId().equals(verschattung_id)) {
				verschattungObj = curr_versch;
				break;
			}
		}
		return marshall(verschattungObj);
	}

	public String getRaumSteckdosen(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getSteckdosenEl());
	}

	public String getRaumSteckdose(BigInteger etage_id, BigInteger raum_id,
			BigInteger steckdose_id) {
		Steckdose steckdoseObj = null;
		List<Steckdose> steckdosen_list = getRaumObj(etage_id, raum_id)
				.getSteckdosenEl().getSteckdoseEl();

		for (Steckdose curr_steckdose : steckdosen_list) {
			if (curr_steckdose.getId().equals(steckdose_id)) {
				steckdoseObj = curr_steckdose;
				break;
			}
		}
		return marshall(steckdoseObj);
	}

	public String getRaumKontakte(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getKontakteEl());
	}

	public String getRaumKontakt(BigInteger etage_id, BigInteger raum_id,
			BigInteger kontakt_id) {
		Kontakt kontaktObj = null;
		List<Kontakt> kontakte_list = getRaumObj(etage_id, raum_id)
				.getKontakteEl().getKontaktEl();

		for (Kontakt curr_kontakt : kontakte_list) {
			if (curr_kontakt.getId().equals(kontakt_id)) {
				kontaktObj = curr_kontakt;
				break;
			}
		}
		return marshall(kontaktObj);
	}

	public String getRaumFeuermelder(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getFeuermelderEl());
	}

	public String getRaumFeuermeld(BigInteger etage_id, BigInteger raum_id,
			BigInteger feuermeld_id) {
		Feuermeld feuermeldObj = null;
		List<Feuermeld> feuermelder_list = getRaumObj(etage_id, raum_id)
				.getFeuermelderEl().getFeuermeldEl();

		for (Feuermeld curr_feuermeld : feuermelder_list) {
			if (curr_feuermeld.getId().equals(feuermeld_id)) {
				feuermeldObj = curr_feuermeld;
				break;
			}
		}
		return marshall(feuermeldObj);
	}

	public String getRaumBewegungsmelder(BigInteger etage_id, BigInteger raum_id) {
		return marshall(getRaumObj(etage_id, raum_id).getBewegungenEl());
	}

	public String getRaumBewegungsmeld(BigInteger etage_id, BigInteger raum_id,
			BigInteger bewegungsmeld_id) {
		Bewegung bewegungsmeldObj = null;
		List<Bewegung> bewegungsmelder_list = getRaumObj(etage_id, raum_id)
				.getBewegungenEl().getBewegungEl();

		for (Bewegung curr_bewegungsmeld : bewegungsmelder_list) {
			if (curr_bewegungsmeld.getId().equals(bewegungsmeld_id)) {
				bewegungsmeldObj = curr_bewegungsmeld;
				break;
			}
		}
		return marshall(bewegungsmeldObj);
	}

	public BigInteger createEtage(String etage_data) {
		Etage newEtage = (Etage) unmarshall(etage_data, Etage.class);
		int arraySize = 0;
		int new_id = 1;

		try {
			arraySize = rootEl.getEtagenEl().getEtageEl().size();
		} catch (NullPointerException e) {
			rootEl.setEtagenEl(new Etagen());
		}

		if (arraySize > 0) {
			Etage last_etage = rootEl.getEtagenEl().getEtageEl()
					.get(arraySize - 1);
			new_id = last_etage.getId().intValue() + 1;
		}

		newEtage.setId(BigInteger.valueOf(new_id));

		rootEl.getEtagenEl().getEtageEl().add(newEtage);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createRaum(BigInteger etagen_id, String raum_data) {
		Raum newRaum = (Raum) unmarshall(raum_data, Raum.class);
		int arraySize = 0;
		int new_id = 1;

		try {
			arraySize = getEtageObj(etagen_id).getRaeumeEl().getRaumEl().size();
		} catch (NullPointerException e) {
			getEtageObj(etagen_id).setRaeumeEl(new Raeume());
		}

		if (arraySize > 0) {
			Raum last_raum = getEtageObj(etagen_id).getRaeumeEl().getRaumEl()
					.get(arraySize - 1);
			new_id = last_raum.getId().intValue() + 1;
		}

		newRaum.setId(BigInteger.valueOf(new_id));

		getEtageObj(etagen_id).getRaeumeEl().getRaumEl().add(newRaum);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public Boolean createFeuchtigkeit(BigInteger etage_id, BigInteger raum_id,
			String feuchtigkeit_data) {
		Feuchtigkeit newFeuchtigkeit = (Feuchtigkeit) unmarshall(
				feuchtigkeit_data, Feuchtigkeit.class);
		newFeuchtigkeit.setWert(BigDecimal.valueOf(20.0));
		newFeuchtigkeit.setEinheit("%");
		getRaumObj(etage_id, raum_id).setFeuchtigkeitEl(newFeuchtigkeit);
		root_marshall();
		return true;
	}

	public Boolean createRaumEnergie(BigInteger etage_id, BigInteger raum_id,
			String energie_data) {
		EnergieRaum newRaumEnergie = (EnergieRaum) unmarshall(energie_data,
				EnergieRaum.class);
		newRaumEnergie.setWert(BigDecimal.valueOf(0));
		newRaumEnergie.setEinheit("W");
		getRaumObj(etage_id, raum_id).setEnergieRaumEl(newRaumEnergie);
		root_marshall();
		return true;
	}

	public Boolean createTemperatur(BigInteger etage_id, BigInteger raum_id,
			String temperatur_data) {
		Temperatur newTemperatur = (Temperatur) unmarshall(temperatur_data,
				Temperatur.class);
		TemperaturIst newTemperaturIst = new TemperaturIst();
		TemperaturSoll newTemperaturSoll = new TemperaturSoll();

		newTemperatur.setTemperaturIstEl(newTemperaturIst);
		newTemperatur.setTemperaturSollEl(newTemperaturSoll);

		newTemperatur.getTemperaturIstEl().setEinheit("C");
		newTemperatur.getTemperaturSollEl().setEinheit("C");
		newTemperatur.getTemperaturIstEl().setWert(BigDecimal.valueOf(20.0));
		newTemperatur.getTemperaturSollEl().setWert(BigDecimal.valueOf(20.0));

		getRaumObj(etage_id, raum_id).setTemperaturEl(newTemperatur);
		root_marshall();
		return true;
	}

	public BigInteger createLicht(BigInteger etagen_id, BigInteger raum_id,
			String licht_data) {
		Licht newLicht = (Licht) unmarshall(licht_data, Licht.class);
		int arraySize = 0;
		int new_id = 1;
		newLicht.setZustand(true);
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getLichterEl()
					.getLichtEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setLichterEl(new Lichter());
		}

		if (arraySize > 0) {
			Licht last_licht = getRaumObj(etagen_id, raum_id).getLichterEl()
					.getLichtEl().get(arraySize - 1);
			new_id = last_licht.getId().intValue() + 1;
		}

		newLicht.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getLichterEl().getLichtEl()
				.add(newLicht);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createVerschattung(BigInteger etagen_id,
			BigInteger raum_id, String verschattung_data) {
		Verschattung newVerschattung = (Verschattung) unmarshall(
				verschattung_data, Verschattung.class);
		int arraySize = 0;
		int new_id = 1;
		newVerschattung.setWert(BigDecimal.valueOf(0));
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getVerschattungenEl()
					.getVerschattungEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setVerschattungenEl(
					new Verschattungen());
		}

		if (arraySize > 0) {
			Verschattung last_versch = getRaumObj(etagen_id, raum_id)
					.getVerschattungenEl().getVerschattungEl()
					.get(arraySize - 1);
			new_id = last_versch.getId().intValue() + 1;
		}

		newVerschattung.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getVerschattungenEl()
				.getVerschattungEl().add(newVerschattung);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createSteckdose(BigInteger etagen_id, BigInteger raum_id,
			String steckdose_data) {
		Steckdose newSteckdose = (Steckdose) unmarshall(steckdose_data,
				Steckdose.class);
		int arraySize = 0;
		int new_id = 1;
		newSteckdose.setZustand(true);
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getSteckdosenEl()
					.getSteckdoseEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setSteckdosenEl(new Steckdosen());
		}

		if (arraySize > 0) {
			Steckdose last_steckdose = getRaumObj(etagen_id, raum_id)
					.getSteckdosenEl().getSteckdoseEl().get(arraySize - 1);
			new_id = last_steckdose.getId().intValue() + 1;
		}

		newSteckdose.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getSteckdosenEl().getSteckdoseEl()
				.add(newSteckdose);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createKontakt(BigInteger etagen_id, BigInteger raum_id,
			String kontakt_data) {
		Kontakt newKontakt = (Kontakt) unmarshall(kontakt_data, Kontakt.class);
		int arraySize = 0;
		int new_id = 1;
		newKontakt.setZustand(true);
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getKontakteEl()
					.getKontaktEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setKontakteEl(new Kontakte());
		}

		if (arraySize > 0) {
			Kontakt last_kontakt = getRaumObj(etagen_id, raum_id)
					.getKontakteEl().getKontaktEl().get(arraySize - 1);
			new_id = last_kontakt.getId().intValue() + 1;
		}

		newKontakt.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getKontakteEl().getKontaktEl()
				.add(newKontakt);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createFeuermelder(BigInteger etagen_id,
			BigInteger raum_id, String feuermelder_data) {
		Feuermeld newFeuermelder = (Feuermeld) unmarshall(feuermelder_data,
				Feuermeld.class);
		int arraySize = 0;
		int new_id = 1;
		newFeuermelder.setZustand(false);
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getFeuermelderEl()
					.getFeuermeldEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setFeuermelderEl(new Feuermelder());
		}

		if (arraySize > 0) {
			Feuermeld last_feuermelder = getRaumObj(etagen_id, raum_id)
					.getFeuermelderEl().getFeuermeldEl().get(arraySize - 1);
			new_id = last_feuermelder.getId().intValue() + 1;
		}

		newFeuermelder.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getFeuermelderEl().getFeuermeldEl()
				.add(newFeuermelder);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public BigInteger createBewegungsmelder(BigInteger etagen_id,
			BigInteger raum_id, String bewegungsmelder_data) {
		Bewegung newBewegungsmelder = (Bewegung) unmarshall(
				bewegungsmelder_data, Feuermeld.class);
		int arraySize = 0;
		int new_id = 1;
		newBewegungsmelder.setZustand(false);
		try {
			arraySize = getRaumObj(etagen_id, raum_id).getBewegungenEl()
					.getBewegungEl().size();
		} catch (NullPointerException e) {
			getRaumObj(etagen_id, raum_id).setBewegungenEl(new Bewegungen());
		}

		if (arraySize > 0) {
			Bewegung last_bewegungsmelder = getRaumObj(etagen_id, raum_id)
					.getBewegungenEl().getBewegungEl().get(arraySize - 1);
			new_id = last_bewegungsmelder.getId().intValue() + 1;
		}

		newBewegungsmelder.setId(BigInteger.valueOf(new_id));

		getRaumObj(etagen_id, raum_id).getBewegungenEl().getBewegungEl()
				.add(newBewegungsmelder);

		root_marshall();

		return BigInteger.valueOf(new_id);
	}

	public Boolean deleteHeizung() {
		if (rootEl.getHeizungEl() == null)
			return false;

		rootEl.setHeizungEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteKamera(BigInteger kamera_id) {
		Kamera found_kamera;
		if ((found_kamera = getKameraObj(kamera_id)) != null) {
			rootEl.getKamerasEl().getKameraEl().remove(found_kamera);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteKameras() {
		if (rootEl.getKamerasEl() == null)
			return false;
		rootEl.setKamerasEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteEnergie() {
		if (rootEl.getEnergieEl() == null)
			return false;

		rootEl.setEnergieEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteEtage(BigInteger etagen_id) {
		Etage found_etage;
		if ((found_etage = getEtageObj(etagen_id)) != null) {
			rootEl.getEtagenEl().getEtageEl().remove(found_etage);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteEtagen() {
		if (rootEl.getEtagenEl() == null)
			return false;
		rootEl.setEtagenEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteRaum(BigInteger etagen_id, BigInteger raum_id) {
		Raum found_raum;
		if ((found_raum = getRaumObj(etagen_id, raum_id)) != null) {
			getEtageObj(etagen_id).getRaeumeEl().getRaumEl().remove(found_raum);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteRaeume(BigInteger etagen_id) {
		if (getEtageObj(etagen_id).getRaeumeEl() == null)
			return false;
		getEtageObj(etagen_id).setRaeumeEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteFeuchtigkeit(BigInteger etagen_id, BigInteger raum_id) {
		Raum found_raum;
		if ((found_raum = getRaumObj(etagen_id, raum_id)) != null) {
			found_raum.setFeuchtigkeitEl(null);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteRaumEnergie(BigInteger etagen_id, BigInteger raum_id) {
		Raum found_raum;
		if ((found_raum = getRaumObj(etagen_id, raum_id)) != null) {
			found_raum.setEnergieRaumEl(null);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteTemperatur(BigInteger etagen_id, BigInteger raum_id) {
		Raum found_raum;
		if ((found_raum = getRaumObj(etagen_id, raum_id)) != null) {
			found_raum.setTemperaturEl(null);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteLicht(BigInteger etagen_id, BigInteger raum_id,
			BigInteger elem_id) {
		Licht found_licht;
		if ((found_licht = getLichtObj(etagen_id, raum_id, elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getLichterEl().getLichtEl()
					.remove(found_licht);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteLichter(BigInteger etagen_id, BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getLichterEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setLichterEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteVerschattung(BigInteger etagen_id, BigInteger raum_id,
			BigInteger elem_id) {
		Verschattung found_verschattung;
		if ((found_verschattung = getVerschattungObj(etagen_id, raum_id,
				elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getVerschattungenEl()
					.getVerschattungEl().remove(found_verschattung);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteVerschattungen(BigInteger etagen_id, BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getVerschattungenEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setVerschattungenEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteSteckdose(BigInteger etagen_id, BigInteger raum_id,
			BigInteger elem_id) {
		Steckdose found_steckdose;
		if ((found_steckdose = getSteckdoseObj(etagen_id, raum_id, elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getSteckdosenEl().getSteckdoseEl()
					.remove(found_steckdose);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteSteckdosen(BigInteger etagen_id, BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getSteckdosenEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setSteckdosenEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteKontakt(BigInteger etagen_id, BigInteger raum_id,
			BigInteger elem_id) {
		Kontakt found_kontakt;
		if ((found_kontakt = getKontaktObj(etagen_id, raum_id, elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getKontakteEl().getKontaktEl()
					.remove(found_kontakt);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteKontakte(BigInteger etagen_id, BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getKontakteEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setKontakteEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteFeuermelder(BigInteger etagen_id, BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getFeuermelderEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setFeuermelderEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteFeuermeld(BigInteger etagen_id, BigInteger raum_id,
			BigInteger elem_id) {
		Feuermeld found_feuermeld;
		if ((found_feuermeld = getFeuermeldObj(etagen_id, raum_id, elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getFeuermelderEl().getFeuermeldEl()
					.remove(found_feuermeld);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean deleteBewegungsmelder(BigInteger etagen_id,
			BigInteger raum_id) {
		if (getRaumObj(etagen_id, raum_id).getBewegungenEl() == null)
			return false;
		getRaumObj(etagen_id, raum_id).setBewegungenEl(null);
		root_marshall();
		return true;
	}

	public Boolean deleteBewegungsmeld(BigInteger etagen_id,
			BigInteger raum_id, BigInteger elem_id) {
		Bewegung found_bewegungsmeld;
		if ((found_bewegungsmeld = getBewegungsmeldObj(etagen_id, raum_id,
				elem_id)) != null) {
			getRaumObj(etagen_id, raum_id).getFeuermelderEl().getFeuermeldEl()
					.remove(found_bewegungsmeld);
			root_marshall();
			return true;
		} else
			return false;
	}

	public Boolean updateFeuchtigkeit(BigInteger etage_id, BigInteger raum_id,
			String body) {
		Raum found_raum = getRaumObj(etage_id, raum_id);

		if ((found_raum.getFeuchtigkeitEl()) != null) {
			Feuchtigkeit feuchtigkeit_data = (Feuchtigkeit) unmarshall(body,
					Feuchtigkeit.class);

			if (feuchtigkeit_data.getWert() != null)
				found_raum.getFeuchtigkeitEl().setWert(
						feuchtigkeit_data.getWert());
			if (feuchtigkeit_data.getEinheit() != null)
				found_raum.getFeuchtigkeitEl().setEinheit(
						feuchtigkeit_data.getEinheit());

			root_marshall();

			return true;
		}
		return false;
	}

	public Boolean updateHeizungSoll(String body) {

		if ((rootEl.getHeizungEl()) != null) {
			HeizungSoll heizungSoll_data = (HeizungSoll) unmarshall(body,
					HeizungSoll.class);

			if (heizungSoll_data.getWert() != null)
				rootEl.getHeizungEl().setHeizungSollEl(heizungSoll_data);

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateHeizungIst(String body) {

		if ((rootEl.getHeizungEl()) != null) {
			HeizungIst heizungIst_data = (HeizungIst) unmarshall(body,
					HeizungIst.class);

			if (heizungIst_data.getWert() != null)
				rootEl.getHeizungEl().setHeizungIstEl(heizungIst_data);

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateKamera(BigInteger kamera_id, String body) {
		Kamera found_kamera = getKameraObj(kamera_id);
		if (found_kamera != null) {
			Kamera kamera_data = (Kamera) unmarshall(body,
					Kamera.class);
			
				found_kamera.setZustand(kamera_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateEnergie(String body) {
		if ((rootEl.getEnergieEl()) != null) {
			Energie energie_data = (Energie) unmarshall(body,
					Energie.class);

			if (energie_data.getWert() != null)
				rootEl.getEnergieEl().getWert();

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateRaumEnergie(BigInteger etage_id, BigInteger raum_id, String body) {
		Raum found_raum = getRaumObj(etage_id, raum_id);
		if ((found_raum.getEnergieRaumEl()) != null) {
			EnergieRaum energie_data = (EnergieRaum) unmarshall(body,
					EnergieRaum.class);

			if (energie_data.getWert() != null)
				found_raum.getEnergieRaumEl().setWert(energie_data.getWert());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateTemperaturIst(BigInteger etage_id, BigInteger raum_id, String body) {
		Raum found_raum = getRaumObj(etage_id, raum_id);
		if ((found_raum.getTemperaturEl().getTemperaturIstEl()) != null) {
			TemperaturIst temperatur_data = (TemperaturIst) unmarshall(body,
					TemperaturIst.class);

			if (temperatur_data.getWert() != null)
				found_raum.getTemperaturEl().setTemperaturIstEl(temperatur_data);

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateTemperaturSoll(BigInteger etage_id, BigInteger raum_id, String body) {
		Raum found_raum = getRaumObj(etage_id, raum_id);
		if ((found_raum.getTemperaturEl().getTemperaturSollEl()) != null) {
			TemperaturSoll temperatur_data = (TemperaturSoll) unmarshall(body,
					TemperaturSoll.class);

			if (temperatur_data.getWert() != null)
				found_raum.getTemperaturEl().getTemperaturSollEl().setWert(temperatur_data.getWert());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateLicht(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Licht found_licht = getLichtObj(etage_id, raum_id, elem_id);
		if (found_licht != null) {
			Licht licht_data = (Licht) unmarshall(body,
					Licht.class);
			
			found_licht.setZustand(licht_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateVerschattung(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Verschattung found_versch = getVerschattungObj(etage_id, raum_id, elem_id);
		if (found_versch != null) {
			Verschattung versch_data = (Verschattung) unmarshall(body,
					Verschattung.class);
			
			found_versch.setWert(versch_data.getWert());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateSteckdose(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Steckdose found_steckdose = getSteckdoseObj(etage_id, raum_id, elem_id);
		if (found_steckdose != null) {
			Steckdose steckdose_data = (Steckdose) unmarshall(body,
					Steckdose.class);
			
			found_steckdose.setZustand(steckdose_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateKontakt(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Kontakt found_kontakt = getKontaktObj(etage_id, raum_id, elem_id);
		if (found_kontakt != null) {
			Kontakt kontakt_data = (Kontakt) unmarshall(body,
					Kontakt.class);
			
			connHndlr.publishItemPayload("Kontakte", "kontaktEl", body);
			found_kontakt.setZustand(kontakt_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateFeuermelder(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Feuermeld found_feuermelder = getFeuermeldObj(etage_id, raum_id, elem_id);
		if (found_feuermelder != null) {
			Feuermeld feuermelder_data = (Feuermeld) unmarshall(body,
					Feuermeld.class);
			
			found_feuermelder.setZustand(feuermelder_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}
	
	public Boolean updateBewegungsmelder(BigInteger etage_id, BigInteger raum_id, BigInteger elem_id, String body) {
		Bewegung found_bewegungsmelder = getBewegungsmeldObj(etage_id, raum_id, elem_id);
		if (found_bewegungsmelder != null) {
			Bewegung bewegungsmelder_data = (Bewegung) unmarshall(body,
					Bewegung.class);
			
			found_bewegungsmelder.setZustand(bewegungsmelder_data.isZustand());

			root_marshall();

			return true;
		}
		return false;
	}

	private String marshall(Object instance) {

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
			element = um.unmarshal(new StreamSource(new StringReader(str)), c)
					.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return element;
	}

}
