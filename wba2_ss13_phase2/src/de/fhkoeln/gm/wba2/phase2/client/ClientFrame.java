package de.fhkoeln.gm.wba2.phase2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import de.fhkoeln.gm.wba2.phase2.rest.generated.*;
import de.fhkoeln.gm.wba2.phase2.xmpp.connection.ConnectionHandler;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private Client restClient;
	private ConnectionHandler connHndlr;
	private LoginFrame myParent;
	private DefaultMutableTreeNode rootNode;
	private TreeModel treeModel;
	private JTabbedPane tabbedPane;
	private RESTHandler restHandler;
	private List<Etage> etagenListe;
	private List<Raum> raeumeListe;
	private List<Licht> lichtListe;

	private DefaultListModel etagenIdModel;
	private DefaultListModel etagenModel;
	private JList etagenView;
	private DefaultListModel raeumeIdModel;
	private DefaultListModel raeumeModel;
	private JList raeumeView;
	private DefaultListModel kategorienIdModel;
	private DefaultListModel kategorieModel;
	private JList kategorienView;
	private DefaultListModel elemIdModel;
	private DefaultListModel elemModel;
	private JList elemView;
	private DefaultListModel nodeModel;
	private JList nodeList;
	private DefaultListModel subModel;
	private JList subList;
	private DefaultListModel nodeItemsModel;
	private DefaultListModel nodeSubModel;
	private JTextPane nodeInfo;

	private JTextPane textOutput;
	private String selCat;
	private JButton btnAendern;
	private JButton btnNeueEtage;
	private JButton btnNeuerRaum;
	private JButton btnNeuElement;
	private JTextPane xmppOutput;
	

	// private JOptionPane updatePane;

	/**
	 * Create the frame.
	 */
	public ClientFrame(LoginFrame parent) {

		etagenModel = new DefaultListModel();
		etagenIdModel = new DefaultListModel();
		raeumeModel = new DefaultListModel();
		raeumeIdModel = new DefaultListModel();
		kategorieModel = new DefaultListModel();
		kategorienIdModel = new DefaultListModel();
		elemModel = new DefaultListModel();
		elemIdModel = new DefaultListModel();

		etagenListe = null;
		nodeModel = new DefaultListModel();
		subModel = new DefaultListModel();
		nodeItemsModel = new DefaultListModel();
		nodeSubModel = new DefaultListModel();
		
		rootNode = new DefaultMutableTreeNode("Gebaeude");
		myParent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 799, 582);
		contentPane.add(tabbedPane);

		btnAendern = new JButton("\u00C4ndern");
		btnAendern.setEnabled(false);
		
		btnNeueEtage = new JButton("Neue Etage");
		btnNeueEtage.setEnabled(false);
		
		btnNeuerRaum = new JButton("Neuer Raum");
		btnNeuerRaum.setEnabled(false);
		
		btnNeuElement = new JButton("Neu...");
		btnNeuElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int etageid = Integer.valueOf(etagenIdModel.getElementAt(
						etagenView.getSelectedIndex()).toString());
				int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
						raeumeView.getSelectedIndex()).toString());
				String info = "";
				switch (kategorienView.getSelectedIndex()) {
				case 0:
					selCat = "temperatur";
					break;
				case 1:
					selCat = "feuchtigkeit";
					break;
				case 2:
					selCat = "energie";
					break;
				case 3:
					selCat = "licht";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neues Licht anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				case 4:
					selCat = "verschattung";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neue Verschattung anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				case 5:
					selCat = "steckdose";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neue Steckdose anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				case 6:
					selCat = "kontakt";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neuen Tür-/Fensterkontakt anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				case 7:
					selCat = "bewegungsmelder";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neuen Bewegungsmelder anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				case 8:
					selCat = "feuermelder";
					info = JOptionPane.showInputDialog(null,
							"Geben sie einen Namen ein: ",
							"Neuen Feuermelder anlegen",
							JOptionPane.PLAIN_MESSAGE);
					if (info == null && info.length() == 0)
						return;
					break;
				}
				if ((restHandler.createElement(etageid, raumid, selCat, info))){
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tElement wurde erfolgreich hinzugefügt!\n");
				} else {
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tElement konnte nicht hinzugefügt werden!\n");
				}
			}
		});
		btnNeuElement.setEnabled(false);

		JPanel panel = new JPanel();
		tabbedPane.addTab("REST ", null, panel, null);
		panel.setLayout(null);

		JButton btnEtagen = new JButton("Aktualisieren");
		btnEtagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNeueEtage.setEnabled(true);
				updateEtagen();
			}
		});
		btnEtagen.setBounds(665, 36, 117, 23);
		panel.add(btnEtagen);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 38, 150, 294);
		panel.add(scrollPane_1);

		etagenView = new JList(etagenModel);
		scrollPane_1.setViewportView(etagenView);

		etagenView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				raeumeModel.removeAllElements();
				kategorieModel.removeAllElements();
				elemModel.removeAllElements();
				btnAendern.setEnabled(false);
				btnNeuElement.setEnabled(false);
				if (etagenView.getSelectedIndex() > -1){
					btnNeuerRaum.setEnabled(true);
					JList list = (JList) mouseEvent.getSource();
					int etagenId = Integer.valueOf(etagenIdModel.getElementAt(
							list.getSelectedIndex()).toString());
					raeumeListe = restHandler.getRaumListe(etagenId);
					if (raeumeListe == null) {
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\tKeine Raeume vorhanden!\n");
						return;
					}
					for (Raum currRaum : raeumeListe) {
						raeumeModel.addElement(currRaum.getInfo());
						raeumeIdModel.addElement(currRaum.getId());
					}
				}
			}
		});

		JLabel lblEtagen = new JLabel("Etagen");
		lblEtagen.setBounds(10, 11, 46, 14);
		panel.add(lblEtagen);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 38, 150, 294);
		panel.add(scrollPane);

		raeumeView = new JList(raeumeModel);
		scrollPane.setViewportView(raeumeView);

		raeumeView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList list = (JList) mouseEvent.getSource();
				btnAendern.setEnabled(false);
				btnNeuElement.setEnabled(false);
				if (list.getSelectedIndex() > -1) {
					kategorieModel.removeAllElements();
					elemModel.removeAllElements();
					kategorieModel.addElement("Temperatur");
					kategorieModel.addElement("Feuchtigkeit");
					kategorieModel.addElement("Energie");
					kategorieModel.addElement("Lichter");
					kategorieModel.addElement("Verschattungen");
					kategorieModel.addElement("Steckdosen");
					kategorieModel.addElement("Tuer- & Fensterkontakte");
					kategorieModel.addElement("Bewegungsmelder");
					kategorieModel.addElement("Feuermelder");
				}
			}
		});

		JLabel lblRume = new JLabel("Raeume");
		lblRume.setBounds(170, 11, 46, 14);
		panel.add(lblRume);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(330, 38, 150, 294);
		panel.add(scrollPane_2);

		kategorienView = new JList(kategorieModel);
		scrollPane_2.setViewportView(kategorienView);

		kategorienView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				elemModel.removeAllElements();
				elemIdModel.removeAllElements();
				btnAendern.setEnabled(false);
				if (kategorienView.getSelectedIndex() > -1){
					btnNeuElement.setEnabled(true);
					JList list = (JList) mouseEvent.getSource();
					int etagenid = Integer.valueOf(etagenIdModel.getElementAt(
							etagenView.getSelectedIndex()).toString());
					int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
							raeumeView.getSelectedIndex()).toString());
					switch (list.getSelectedIndex()) {
					case 0:
						Temperatur myTemp = restHandler.getRaumTemperatur(etagenid,
								raumid);
						if (myTemp == null) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKein Temperatursensor vorhanden!\n");
							btnAendern.setEnabled(false);
							return;
						}
						btnNeuElement.setEnabled(false);
						btnAendern.setEnabled(true);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " : \n"
								+ "\tTemperatur Soll : "
								+ myTemp.getTemperaturSollEl().getWert()
								+ myTemp.getTemperaturSollEl().getEinheit() + "\n"
								+ "\tTemperatur Ist : "
								+ myTemp.getTemperaturIstEl().getWert()
								+ myTemp.getTemperaturIstEl().getEinheit() + "\n");

						break;
					case 1:
						Feuchtigkeit myFeucht = restHandler.getRaumFeuchtigkeit(
								etagenid, raumid);
						if (myFeucht == null) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKein Feuchtigkeitssensor vorhanden!\n");
							return;
						}
						btnNeuElement.setEnabled(false);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " : \n"
								+ "\tLuftfeuchtigkeit : " + myFeucht.getWert()
								+ myFeucht.getEinheit() + "\n");
						break;
					case 2:
						EnergieRaum myRaumEnergie = restHandler.getRaumEnergie(
								etagenid, raumid);
						if (myRaumEnergie == null) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKein Verbrauchszähler vorhanden!\n");
							return;
						}
						btnNeuElement.setEnabled(false);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " : \n"
								+ "\tDurchschnittsverbrauch : "
								+ myRaumEnergie.getWert()
								+ myRaumEnergie.getEinheit() + "\n");
						break;
					case 3:
						List<Licht> lichtListe = restHandler.getLichtListe(
								etagenid, raumid);
						if (lichtListe == null || lichtListe.size() < 1) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Lichter vorhanden!\n");
							return;
						}
						for (Licht currLicht : lichtListe) {
							elemModel.addElement(currLicht.getInfo());
							elemIdModel.addElement(currLicht.getId());
						}
						break;
					case 4:
						List<Verschattung> verschattungListe = restHandler
								.getVerschattungListe(etagenid, raumid);
						if (verschattungListe == null
								|| verschattungListe.size() < 1) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Verschattungen vorhanden!\n");
							return;
						}
						for (Verschattung currVerschattung : verschattungListe) {
							elemModel.addElement(currVerschattung.getInfo());
							elemIdModel.addElement(currVerschattung.getId());
						}
						break;
					case 5:
						List<Steckdose> steckdoseListe = restHandler
								.getSteckdoseListe(etagenid, raumid);
						if (steckdoseListe == null || steckdoseListe.size() < 1) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Steckdosen vorhanden!\n");
							return;
						}
						for (Steckdose currSteckdose : steckdoseListe) {
							elemModel.addElement(currSteckdose.getInfo());
							elemIdModel.addElement(currSteckdose.getId());
						}
						break;
					case 6:
						List<Kontakt> kontaktListe = restHandler.getKontaktListe(
								etagenid, raumid);
						if (kontaktListe == null || kontaktListe.size() < 1) {
							textOutput.setText(textOutput.getText()
									+ "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Tür- bzw. Fensterkontakte vorhanden!\n");
							return;
						}
						for (Kontakt currKontakt : kontaktListe) {
							elemModel.addElement(currKontakt.getInfo());
							elemIdModel.addElement(currKontakt.getId());
						}
						break;
					case 7:
						List<Bewegung> bewegungsmelderListe = restHandler
								.getBewegungsmelderListe(etagenid, raumid);
						if (bewegungsmelderListe == null
								|| bewegungsmelderListe.size() < 1) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Bewegungsmelder vorhanden!\n");
							return;
						}
						for (Bewegung currBewegungsmeld : bewegungsmelderListe) {
							elemModel.addElement(currBewegungsmeld.getInfo());
							elemIdModel.addElement(currBewegungsmeld.getId());
						}
						break;
					case 8:
						List<Feuermeld> feuermelderListe = restHandler
								.getFeuermelderListe(etagenid, raumid);
						if (feuermelderListe == null || feuermelderListe.size() < 1) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss")
									+ ">\tKeine Feuermelder vorhanden!\n");
							return;
						}
						for (Feuermeld currFeuermeld : feuermelderListe) {
							elemModel.addElement(currFeuermeld.getInfo());
							elemIdModel.addElement(currFeuermeld.getId());
						}
						break;
					}
				}
			}
		});

		JLabel lblSensorenAktoren = new JLabel("Kategorien");
		lblSensorenAktoren.setBounds(330, 11, 106, 14);
		panel.add(lblSensorenAktoren);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 396, 630, 147);
		panel.add(scrollPane_3);
		
				textOutput = new JTextPane();
				scrollPane_3.setViewportView(textOutput);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(490, 38, 150, 294);
		panel.add(scrollPane_4);

		elemView = new JList(elemModel);
		scrollPane_4.setViewportView(elemView);

		elemView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (elemView.getSelectedIndex() > -1) {
					JList list = (JList) mouseEvent.getSource();
					btnAendern.setEnabled(false);
					int etagenid = Integer.valueOf(etagenIdModel.getElementAt(
							etagenView.getSelectedIndex()).toString());
					int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
							raeumeView.getSelectedIndex()).toString());
					int katid = kategorienView.getSelectedIndex();
					int elemid = Integer.valueOf(elemIdModel.getElementAt(
							elemView.getSelectedIndex()).toString());
					switch (katid) {
					case 3:
						btnAendern.setEnabled(true);
						Licht myLicht = restHandler.getLichtObj(etagenid, raumid,
								elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ myLicht.getInfo() + " : " + myLicht.isZustand()
								+ "\n");
						break;
					case 4:
						btnAendern.setEnabled(true);
						Verschattung myVerschattung = restHandler
								.getVerschattungObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ myVerschattung.getInfo() + " : "
								+ myVerschattung.getWert() + "%\n");
						break;
					case 5:
						btnAendern.setEnabled(true);
						Steckdose mySteckdose = restHandler.getSteckdoseObj(
								etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ mySteckdose.getInfo() + " : "
								+ mySteckdose.isZustand() + "\n");
						break;
					case 6:
						btnAendern.setEnabled(false);
						Kontakt myKontakt = restHandler.getKontaktObj(etagenid,
								raumid, elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ myKontakt.getInfo() + " : "
								+ myKontakt.isZustand() + "\n");
						break;
					case 7:
						btnAendern.setEnabled(false);
						Bewegung myBewegung = restHandler.getBewegungObj(etagenid,
								raumid, elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ myBewegung.getInfo() + " : "
								+ myBewegung.isZustand() + "\n");
						break;
					case 8:
						btnAendern.setEnabled(false);
						Feuermeld myFeuermeld = restHandler.getFeuermeldObj(
								etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\t"
								+ etagenView.getSelectedValue() + " > "
								+ raeumeView.getSelectedValue() + " > "
								+ kategorienView.getSelectedValue() + " :\n" + "\t"
								+ myFeuermeld.getInfo() + " : "
								+ myFeuermeld.isZustand() + "\n");
						break;
					}
				}
			}
		});

		JLabel lblSensorenAktoren_1 = new JLabel("Elemente");
		lblSensorenAktoren_1.setBounds(490, 11, 119, 14);
		panel.add(lblSensorenAktoren_1);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(653, 36, 2, 507);
		panel.add(separator);

		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(10, 371, 46, 14);
		panel.add(lblOutput);

		JButton btnLoeschen = new JButton("L\u00F6schen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (etagenView.getSelectedIndex() > -1) {
					if (raeumeView.getSelectedIndex() > -1) {
						if (kategorienView.getSelectedIndex() > -1) {
							switch (kategorienView.getSelectedIndex()) {
							case 0:
								selCat = "temperatur";
								break;
							case 1:
								selCat = "feuchtigkeit";
								break;
							case 2:
								selCat = "energie";
								break;
							case 3:
								selCat = "licht";
								break;
							case 4:
								selCat = "verschattung";
								break;
							case 5:
								selCat = "steckdose";
								break;
							case 6:
								selCat = "kontakt";
								break;
							case 7:
								selCat = "bewegungsmelder";
								break;
							case 8:
								selCat = "feuermelder";
								break;
							}
							if (elemView.getSelectedIndex() > -1) {
								if (restHandler.deleteElement(Integer
										.valueOf(etagenIdModel.getElementAt(
												etagenView.getSelectedIndex())
												.toString()), Integer
										.valueOf(raeumeIdModel.getElementAt(
												raeumeView.getSelectedIndex())
												.toString()), selCat, Integer
										.valueOf(elemIdModel.getElementAt(
												elemView.getSelectedIndex())
												.toString()))) {
									textOutput.setText(textOutput.getText()
											+ "<" + now("hh:mm:ss")
											+ ">\tElement '"
											+ elemView.getSelectedValue()
											+ "' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText()
											+ "<"
											+ now("hh:mm:ss")
											+ ">\tElement '"
											+ elemView.getSelectedValue()
											+ "' konnte nicht gelöscht werden!\n");
								}
								updateEtagen();
							} else {
								if (restHandler.deleteKategorie(Integer
										.valueOf(etagenIdModel.getElementAt(
												etagenView.getSelectedIndex())
												.toString()), Integer
										.valueOf(raeumeIdModel.getElementAt(
												raeumeView.getSelectedIndex())
												.toString()), selCat)) {
									textOutput.setText(textOutput.getText()
											+ "<" + now("hh:mm:ss")
											+ ">\tKategorie '"
											+ kategorienView.getSelectedValue()
											+ "' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText()
											+ "<"
											+ now("hh:mm:ss")
											+ ">\tKategorie '"
											+ kategorienView.getSelectedValue()
											+ "' konnte nicht gelöscht werden!\n");
								}
								updateEtagen();
							}
						} else {
							if (restHandler.deleteRaum(Integer
									.valueOf(etagenIdModel.getElementAt(
											etagenView.getSelectedIndex())
											.toString()), Integer
									.valueOf(raeumeIdModel.getElementAt(
											raeumeView.getSelectedIndex())
											.toString()))) {
								textOutput.setText(textOutput.getText() + "<"
										+ now("hh:mm:ss") + ">\tRaum '"
										+ raeumeView.getSelectedValue()
										+ "' wurde erfolgreich gelöscht!\n");
							} else {
								textOutput.setText(textOutput.getText() + "<"
										+ now("hh:mm:ss") + ">\tRaum '"
										+ raeumeView.getSelectedValue()
										+ "' konnte nicht gelöscht werden!\n");
							}
							updateEtagen();
						}
					} else {
						if (restHandler.deleteEtage(Integer
								.valueOf(etagenIdModel.getElementAt(
										etagenView.getSelectedIndex())
										.toString()))) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss") + ">\tEtage '"
									+ etagenView.getSelectedValue()
									+ "' wurde erfolgreich gelöscht!\n");
						} else {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss") + ">\tEtage '"
									+ etagenView.getSelectedValue()
									+ "' konnte nicht gelöscht werden!\n");
						}
						updateEtagen();
					}
				}
			}
		});
		btnLoeschen.setBounds(665, 70, 117, 23);
		panel.add(btnLoeschen);

		btnAendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (kategorienView.getSelectedIndex() > -1) {
					int etageid = Integer.valueOf(etagenIdModel.getElementAt(
							etagenView.getSelectedIndex()).toString());
					int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
							raeumeView.getSelectedIndex()).toString());
					switch (kategorienView.getSelectedIndex()) {
					case 0:
						String wert = JOptionPane.showInputDialog(null,
								"Geben sie einen Soll-Wert ein: ",
								"Temperatur Soll-Wert festlegen",
								JOptionPane.PLAIN_MESSAGE);
						if (wert == null)
							return;
						if ((wert.length() > 0)
								&& (wert != null)
								&& (restHandler.updateTemperaturSoll(etageid,
										raumid, wert))) {
							textOutput.setText(textOutput.getText() + "<"
									+ now("hh:mm:ss") + ">\t"
									+ etagenView.getSelectedValue() + " > "
									+ raeumeView.getSelectedValue() + " > "
									+ kategorienView.getSelectedValue()
									+ " :\n" + "\tSoll-Wert wurde auf '" + wert
									+ "' gesetzt!\n");
						} else {
							textOutput.setText(textOutput.getText()
									+ "<"
									+ now("hh:mm:ss")
									+ ">\t"
									+ etagenView.getSelectedValue()
									+ " > "
									+ raeumeView.getSelectedValue()
									+ " > "
									+ kategorienView.getSelectedValue()
									+ " :\n"
									+ "\tSoll-Wert konnte nicht gesetzt werden!\n");
						}
					break;
					}
					if (elemView.getSelectedIndex() > -1) {
						int elemid = Integer.valueOf(elemIdModel.getElementAt(
								elemView.getSelectedIndex()).toString());
						switch (kategorienView.getSelectedIndex()) {
						case 3:
							JComboBox cbStatus = new JComboBox();
							Licht myLicht = restHandler.getLichtObj(etageid,
									raumid, elemid);
							if (myLicht.isZustand()) {
								cbStatus.addItem("an");
								cbStatus.addItem("aus");
							} else {
								cbStatus.addItem("aus");
								cbStatus.addItem("an");
							}
							Object[] lichtMsg = { "Status", cbStatus };
							JOptionPane updateLichtPane = new JOptionPane(
									lichtMsg, JOptionPane.PLAIN_MESSAGE,
									JOptionPane.OK_CANCEL_OPTION);
							updateLichtPane.createDialog(null,
									"Licht an/auschalten").setVisible(true);
							int updateLichtPaneVal = ((Integer) updateLichtPane
									.getValue()).intValue();
							if (updateLichtPaneVal == JOptionPane.CANCEL_OPTION)
								return;
							Boolean status = null;
							switch (cbStatus.getSelectedItem().toString()) {
							case "an":
								status = true;
								break;
							case "aus":
								status = false;
								break;
							}
							if ((cbStatus.getSelectedItem().toString().length()) > 0
									&& (cbStatus.getSelectedItem() != null)
									&& (restHandler.updateLicht(etageid,
											raumid, elemid, status))) {
								textOutput.setText(textOutput.getText() + "<"
										+ now("hh:mm:ss") + ">\t"
										+ etagenView.getSelectedValue() + " > "
										+ raeumeView.getSelectedValue() + " > "
										+ kategorienView.getSelectedValue()
										+ " :\n" + "\tLicht wurde "
										+ cbStatus.getSelectedItem().toString()
										+ "geschaltet!\n");
							} else {
								textOutput.setText(textOutput.getText()
										+ "<"
										+ now("hh:mm:ss")
										+ ">\t"
										+ etagenView.getSelectedValue()
										+ " > "
										+ raeumeView.getSelectedValue()
										+ " > "
										+ kategorienView.getSelectedValue()
										+ " :\n"
										+ "\tLicht konnte nicht an/ausgeschaltet werden!");
							}
						break;
						case 4:
							JSlider slWert = new JSlider();
							slWert.setMinimum(0);
							slWert.setMaximum(100);
							final JLabel lbProzent = new JLabel();
							Verschattung currVerschattung = restHandler
									.getVerschattungObj(etageid, raumid, elemid);
							lbProzent.setText(currVerschattung.getWert()
									.toString() + "%");
							slWert.setValue(currVerschattung.getWert()
									.intValue());
							slWert.addChangeListener(new ChangeListener() {

								@Override
								public void stateChanged(ChangeEvent ce) {
									JSlider slider = (JSlider) ce.getSource();
									if (!slider.getValueIsAdjusting()) {
										lbProzent.setText(String.valueOf(slider
												.getValue()) + "%");
									}
								}
							});
							Object[] verschattungMsg = { "Wert", slWert, null,
									lbProzent };
							JOptionPane updateVerschPane = new JOptionPane(
									verschattungMsg, JOptionPane.PLAIN_MESSAGE,
									JOptionPane.OK_CANCEL_OPTION);
							updateVerschPane.createDialog(null,
									"Verschattungen positionieren").setVisible(
									true);
							int updateVerschPaneVal = ((Integer) updateVerschPane
									.getValue()).intValue();
							if (updateVerschPaneVal == JOptionPane.CANCEL_OPTION)
								return;
							if ((restHandler.updateVerschattung(etageid,
									raumid, elemid, slWert.getValue()))) {
								textOutput.setText(textOutput.getText() + "<"
										+ now("hh:mm:ss") + ">\t"
										+ etagenView.getSelectedValue() + " > "
										+ raeumeView.getSelectedValue() + " > "
										+ kategorienView.getSelectedValue()
										+ " :\n" + "\tVerschattung wird auf "
										+ slWert.getValue() + "% gefahren!\n");
							} else {
								textOutput.setText(textOutput.getText()
										+ "<"
										+ now("hh:mm:ss") + ">\t"
										+ etagenView.getSelectedValue() + " > "
										+ raeumeView.getSelectedValue() + " > "
										+ kategorienView.getSelectedValue() + " :\n"
										+ "\tVerschattung konnte nicht bewegt werden!");
							}
						break;
						case 5:
								JComboBox cbStatus1 = new JComboBox();
								Steckdose mySteckdose = restHandler.getSteckdoseObj(etageid,
										raumid, elemid);
								if (mySteckdose.isZustand()) {
									cbStatus1.addItem("an");
									cbStatus1.addItem("aus");
								} else {
									cbStatus1.addItem("aus");
									cbStatus1.addItem("an");
								}
								Object[] steckdoseMsg = { "Status", cbStatus1 };
								JOptionPane updateSteckdosePane = new JOptionPane(
										steckdoseMsg, JOptionPane.PLAIN_MESSAGE,
										JOptionPane.OK_CANCEL_OPTION);
								updateSteckdosePane.createDialog(null,
										"Steckdose an/auschalten").setVisible(true);
								int updateSteckdosePaneVal = ((Integer) updateSteckdosePane
										.getValue()).intValue();
								if (updateSteckdosePaneVal == JOptionPane.CANCEL_OPTION)
									return;
								Boolean status1 = null;
								switch (cbStatus1.getSelectedItem().toString()) {
									case "an":
										status1 = true;
									break;
									case "aus":
										status1 = false;
									break;
								}	
								if ((cbStatus1.getSelectedItem().toString().length()) > 0
										&& (cbStatus1.getSelectedItem() != null)
										&& (restHandler.updateSteckdose(etageid,
												raumid, elemid, status1))) {
									textOutput.setText(textOutput.getText() + "<"
											+ now("hh:mm:ss") + ">\t"
											+ etagenView.getSelectedValue() + " > "
											+ raeumeView.getSelectedValue() + " > "
											+ kategorienView.getSelectedValue()
											+ " :\n" + "\tSteckdose wurde "
											+ cbStatus1.getSelectedItem().toString()
											+ "geschaltet!\n");
								} else {
									textOutput.setText(textOutput.getText()
											+ "<"
											+ now("hh:mm:ss") + ">\t"
											+ etagenView.getSelectedValue() + " > "
											+ raeumeView.getSelectedValue() + " > "
											+ kategorienView.getSelectedValue() + " :\n"
											+ "\tSteckdose konnte nicht an/ausgeschaltet werden!");
								}
						break;
						}
					}
				}
			}
		});
		btnAendern.setBounds(665, 104, 117, 23);
		panel.add(btnAendern);

		JButton btnClearOutput = new JButton("Clear Output");
		btnClearOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textOutput.setText("");
			}
		});
		btnClearOutput.setBounds(665, 520, 117, 23);
		panel.add(btnClearOutput);
		
		
		btnNeueEtage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String info = JOptionPane.showInputDialog(null,
						"Geben sie einen Namen ein: ",
						"Neue Etage erstellen",
						JOptionPane.PLAIN_MESSAGE);
				if ((info.length() > 0) && (info != null)
						&& (restHandler.addEtage(info))) {
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tEtage '" + info
							+ "' wurde erfolgreich hinzugefügt!\n");
				} else {
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tEtage '" + info
							+ "' konnte nicht hinzugefügt werden!\n");
				}
				updateEtagen();
			}
		});
		btnNeueEtage.setBounds(41, 337, 89, 23);
		panel.add(btnNeueEtage);
		
		
		btnNeuerRaum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int etageid = Integer.valueOf(etagenIdModel.getElementAt(
						etagenView.getSelectedIndex()).toString());
				String info = JOptionPane.showInputDialog(null,
						"Geben sie einen Namen ein: ",
						"Neuen Raum erstellen",
						JOptionPane.PLAIN_MESSAGE);
				if ((info.length() > 0) && (info != null)
						&& (restHandler.addRaum(etageid, info))) {
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tRaum '" + info
							+ "' wurde erfolgreich hinzugefügt!\n");
				} else {
					textOutput.setText(textOutput.getText() + "<"
							+ now("hh:mm:ss") + ">\tRaum '" + info
							+ "' konnte nicht hinzugefügt werden!\n");
				}
				updateEtagen();
			}
		});
		btnNeuerRaum.setBounds(191, 337, 106, 23);
		panel.add(btnNeuerRaum);
		
		
		btnNeuElement.setBounds(362, 337, 89, 23);
		panel.add(btnNeuElement);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("XMPP", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 34, 140, 170);
		panel_1.add(scrollPane_5);
		nodeList = new JList(nodeModel);
		nodeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(nodeList.getSelectedIndex() > -1){
					nodeInfo.setText("");
					String info = connHndlr.getNodeInformation(nodeList.getSelectedValue().toString());
					nodeInfo.setText(nodeInfo.getText() + info);
				}
			}
		});
		scrollPane_5.setViewportView(nodeList);
		
		JButton btnDiscoverNodes = new JButton("Discover");
		btnDiscoverNodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> entries = connHndlr.getAllNodes();
				nodeModel.clear();
				
				for (String curr : entries){
					nodeModel.addElement(curr);
				}
			}
		});
		btnDiscoverNodes.setBounds(30, 215, 100, 23);
		panel_1.add(btnDiscoverNodes);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(644, 34, 140, 170);
		panel_1.add(scrollPane_6);
		
		subList = new JList(subModel);
		scrollPane_6.setViewportView(subList);
		
		JLabel lblAvailableNodes = new JLabel("Available Nodes");
		lblAvailableNodes.setBounds(10, 9, 94, 14);
		panel_1.add(lblAvailableNodes);
		
		JLabel lblMySubscriptions = new JLabel("My Subscriptions");
		lblMySubscriptions.setBounds(644, 9, 94, 14);
		panel_1.add(lblMySubscriptions);
		
		JButton btnNewButton = new JButton("Subscribe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nodeList.getSelectedIndex() > -1){
					String selNode = nodeList.getSelectedValue().toString();
					if (connHndlr.subscribeToNode(selNode)){
						xmppOutput.setText(xmppOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\tNode '" + selNode
								+ "' wurde erfolgreich abonniert!\n");
					} else {
						xmppOutput.setText(xmppOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\tNode '" + selNode
								+ "' konnte nicht abonniert werden!\n");
					}
				}
			}
		});
		btnNewButton.setBounds(30, 249, 100, 23);
		panel_1.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> entries = connHndlr.getAllSubscriptions();
				subModel.clear();
				
				for (String curr: entries) {
					subModel.addElement(curr);
				}
			}
		});
		btnUpdate.setBounds(665, 215, 100, 23);
		panel_1.add(btnUpdate);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 373, 774, 170);
		panel_1.add(scrollPane_7);
		
		xmppOutput = new JTextPane();
		scrollPane_7.setViewportView(xmppOutput);
		
		JLabel lblOutput_1 = new JLabel("Output");
		lblOutput_1.setBounds(10, 348, 46, 14);
		panel_1.add(lblOutput_1);
		
		JButton btnUnsubscribe = new JButton("Unsubscribe");
		btnUnsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subList.getSelectedIndex() > -1) {
					String selNode = subList.getSelectedValue().toString();
					if (connHndlr.unsubscribeToNode(selNode)){
						xmppOutput.setText(xmppOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\tNode '" + selNode
								+ "' wurde erfolgreich unsubscribed!\n");
					} else {
						xmppOutput.setText(xmppOutput.getText() + "<"
								+ now("hh:mm:ss") + ">\tNode '" + selNode
								+ "' konnte nicht unsubscribed werden!\n");
					}
				}
			}
		});
		btnUnsubscribe.setBounds(665, 249, 100, 23);
		panel_1.add(btnUnsubscribe);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(160, 34, 140, 170);
		panel_1.add(scrollPane_8);
		
		nodeInfo = new JTextPane();
		scrollPane_8.setViewportView(nodeInfo);
		
		JLabel lblInformation = new JLabel("Node Information");
		lblInformation.setBounds(160, 9, 88, 14);
		panel_1.add(lblInformation);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(624, 9, 1, 351);
		panel_1.add(separator_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Server", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(10, 42, 150, 250);
		panel_2.add(scrollPane_9);
		
		JList list = new JList();
		scrollPane_9.setViewportView(list);
		
		JLabel lblKategorien = new JLabel("Kategorien");
		lblKategorien.setBounds(10, 17, 73, 14);
		panel_2.add(lblKategorien);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(170, 42, 150, 250);
		panel_2.add(scrollPane_10);
		
		JList list_1 = new JList();
		scrollPane_10.setViewportView(list_1);
		
		JLabel lblElemente = new JLabel("Elemente");
		lblElemente.setBounds(170, 17, 46, 14);
		panel_2.add(lblElemente);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(10, 383, 774, 160);
		panel_2.add(scrollPane_11);
		
		JTextPane textPane = new JTextPane();
		scrollPane_11.setViewportView(textPane);
		
		JLabel lblOutput_2 = new JLabel("Output");
		lblOutput_2.setBounds(10, 358, 46, 14);
		panel_2.add(lblOutput_2);
		
		JButton btnndern = new JButton("\u00C4ndern");
		btnndern.setBounds(330, 71, 109, 23);
		panel_2.add(btnndern);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.setBounds(330, 40, 109, 23);
		panel_2.add(btnAktualisieren);
	}

	public void setConnectionHandlers(ConnectionHandler connHndlr,
			RESTHandler restHandler) {
		this.connHndlr = connHndlr;
		this.connHndlr.addItemListener(new ItemLoggingHandler(this));
		this.restHandler = restHandler;
	}

	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());

	}

	private void updateEtagen() {
		etagenModel.removeAllElements();
		etagenIdModel.removeAllElements();
		raeumeModel.removeAllElements();
		raeumeIdModel.removeAllElements();
		kategorieModel.removeAllElements();
		elemModel.removeAllElements();
		elemIdModel.removeAllElements();
		etagenListe = restHandler.getEtagenListe();
		btnNeuerRaum.setEnabled(false);
		if (etagenListe.size() == 1) {
			textOutput.setText(textOutput.getText() + "<" + now("hh:mm:ss")
					+ ">\tKeine Etagen vorhanden!\n");
			return;
		}
		for (Etage currEtage : etagenListe) {
			etagenModel.addElement(currEtage.getInfo());
			etagenIdModel.addElement(currEtage.getId());
		}
	}
	
	public void receiveNotification(String data){
		xmppOutput.setText(xmppOutput.getText() + "<"
				+ now("hh:mm:ss") + ">" + data + "\n");
	}
}
