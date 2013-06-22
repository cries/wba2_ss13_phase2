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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
	
	private JTextPane textOutput;
	private String selCat;

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

		rootNode = new DefaultMutableTreeNode("Gebaeude");
		myParent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 894, 562);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("REST ", null, panel, null);
		panel.setLayout(null);

		JButton btnEtagen = new JButton("Aktualisieren");
		btnEtagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				JList list = (JList) mouseEvent.getSource();
				int etagenId = Integer.valueOf(etagenIdModel.getElementAt(
						list.getSelectedIndex()).toString());
				raeumeListe = restHandler.getRaumListe(etagenId);
				if (raeumeListe == null) {
					textOutput.setText(textOutput.getText() + 
							"<" + now("hh:mm:ss") + ">\tKeine Raeume vorhanden!\n");
					return;
				}
				for (Raum currRaum : raeumeListe) {
					raeumeModel.addElement(currRaum.getInfo());
					raeumeIdModel.addElement(currRaum.getId());
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
				JList list = (JList) mouseEvent.getSource();
				int etagenid = Integer.valueOf(etagenIdModel.getElementAt(
						etagenView.getSelectedIndex()).toString());
				int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
						raeumeView.getSelectedIndex()).toString());
				switch (list.getSelectedIndex()) {
					case 0:
						Temperatur myTemp = restHandler.getRaumTemperatur(etagenid, raumid);
						if (myTemp == null) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKein Temperatursensor vorhanden!\n");
							return;
						}
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " : \n" +
								"\tTemperatur Soll : " + 
								myTemp.getTemperaturSollEl().getWert() +
								myTemp.getTemperaturSollEl().getEinheit() + "\n" +
								"\tTemperatur Ist : " + 
								myTemp.getTemperaturIstEl().getWert() +
								myTemp.getTemperaturIstEl().getEinheit() + "\n");
	
					break;
					case 1: 
						Feuchtigkeit myFeucht = restHandler.getRaumFeuchtigkeit(etagenid, raumid);
						if (myFeucht == null) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKein Feuchtigkeitssensor vorhanden!\n");
							return;
						}
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " : \n" +
								"\tLuftfeuchtigkeit : " + 
								myFeucht.getWert() + myFeucht.getEinheit() + "\n");
					break;
					case 2:
						EnergieRaum myRaumEnergie = restHandler.getRaumEnergie(etagenid, raumid);
						if (myRaumEnergie == null) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKein Verbrauchszähler vorhanden!\n");
							return;
						}
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " : \n" +
								"\tDurchschnittsverbrauch : " + 
								myRaumEnergie.getWert() + myRaumEnergie.getEinheit() + "\n");
					break;
					case 3:
						List<Licht> lichtListe = restHandler.getLichtListe(etagenid, raumid);
						if (lichtListe == null || lichtListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Lichter vorhanden!\n");
							return;
						}
						for (Licht currLicht: lichtListe){
							elemModel.addElement(currLicht.getInfo());
							elemIdModel.addElement(currLicht.getId());
						}
					break;
					case 4:
						List<Verschattung> verschattungListe = restHandler.getVerschattungListe(etagenid, raumid);
						if (verschattungListe == null || verschattungListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Verschattungen vorhanden!\n");
							return;
						}
						for (Verschattung currVerschattung: verschattungListe){
							elemModel.addElement(currVerschattung.getInfo());
							elemIdModel.addElement(currVerschattung.getId());
						}
					break;
					case 5:
						List<Steckdose> steckdoseListe = restHandler.getSteckdoseListe(etagenid, raumid);
						if (steckdoseListe == null || steckdoseListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Steckdosen vorhanden!\n");
							return;
						}
						for (Steckdose currSteckdose: steckdoseListe){
							elemModel.addElement(currSteckdose.getInfo());
							elemIdModel.addElement(currSteckdose.getId());
						}
					break;
					case 6:
						List<Kontakt> kontaktListe = restHandler.getKontaktListe(etagenid, raumid);
						if (kontaktListe == null || kontaktListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Tür- bzw. Fensterkontakte vorhanden!\n");
							return;
						}
						for (Kontakt currKontakt: kontaktListe){
							elemModel.addElement(currKontakt.getInfo());
							elemIdModel.addElement(currKontakt.getId());
						}
					break;
					case 7:
						List<Bewegung> bewegungsmelderListe = restHandler.getBewegungsmelderListe(etagenid, raumid);
						if (bewegungsmelderListe == null || bewegungsmelderListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Bewegungsmelder vorhanden!\n");
							return;
						}
						for (Bewegung currBewegungsmeld: bewegungsmelderListe){
							elemModel.addElement(currBewegungsmeld.getInfo());
							elemIdModel.addElement(currBewegungsmeld.getId());
						}
					break;
					case 8:
						List<Feuermeld> feuermelderListe = restHandler.getFeuermelderListe(etagenid, raumid);
						if (feuermelderListe == null || feuermelderListe.size() < 1) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tKeine Feuermelder vorhanden!\n");
							return;
						}
						for (Feuermeld currFeuermeld: feuermelderListe){
							elemModel.addElement(currFeuermeld.getInfo());
							elemIdModel.addElement(currFeuermeld.getId());
						}
					break;
				}
			}
		});

		JLabel lblSensorenAktoren = new JLabel("Kategorien");
		lblSensorenAktoren.setBounds(330, 11, 106, 14);
		panel.add(lblSensorenAktoren);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 376, 630, 147);
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
				JList list = (JList) mouseEvent.getSource();
				int etagenid = Integer.valueOf(etagenIdModel.getElementAt(
						etagenView.getSelectedIndex()).toString());
				int raumid = Integer.valueOf(raeumeIdModel.getElementAt(
						raeumeView.getSelectedIndex()).toString());
				int katid = kategorienView.getSelectedIndex();
				int elemid = Integer.valueOf(elemIdModel.getElementAt(
						elemView.getSelectedIndex()).toString());
				switch(katid) {
					case 3:
						Licht myLicht = restHandler.getLichtObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + myLicht.getInfo() + " : " +myLicht.isZustand() + "\n");
					break;
					case 4:
						Verschattung myVerschattung = restHandler.getVerschattungObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + myVerschattung.getInfo() + " : " + myVerschattung.getWert() + "%\n");
					break;
					case 5:
						Steckdose mySteckdose = restHandler.getSteckdoseObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + mySteckdose.getInfo() + " : " + mySteckdose.isZustand() + "\n");
					break;
					case 6:
						Kontakt myKontakt = restHandler.getKontaktObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + myKontakt.getInfo() + " : " + myKontakt.isZustand() + "\n");
					break;
					case 7:
						Bewegung myBewegung = restHandler.getBewegungObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + myBewegung.getInfo() + " : " + myBewegung.isZustand() + "\n");
					break;
					case 8:
						Feuermeld myFeuermeld = restHandler.getFeuermeldObj(etagenid, raumid, elemid);
						textOutput.setText(textOutput.getText() + 
								"<" + now("hh:mm:ss") + ">\t" + etagenView.getSelectedValue() + " > " +
								raeumeView.getSelectedValue() + " > " + elemView.getSelectedValue() + " :\n" +
								"\t" + myFeuermeld.getInfo() + " : " + myFeuermeld.isZustand() + "\n");
					break;
				}
			}
		});
		
		JLabel lblSensorenAktoren_1 = new JLabel("Elemente");
		lblSensorenAktoren_1.setBounds(490, 11, 119, 14);
		panel.add(lblSensorenAktoren_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(653, 36, 2, 487);
		panel.add(separator);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(10, 343, 46, 14);
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
								if (restHandler.deleteElement(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()),
										Integer.valueOf(raeumeIdModel.getElementAt(raeumeView.getSelectedIndex()).toString()), selCat,
										Integer.valueOf(elemIdModel.getElementAt(elemView.getSelectedIndex()).toString()))) {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tElement '"+ elemView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tElement '"+ elemView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
								}
								updateEtagen();
							} else {
								if (restHandler.deleteKategorie(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()),
										Integer.valueOf(raeumeIdModel.getElementAt(raeumeView.getSelectedIndex()).toString()), selCat)) {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tKategorie '"+ kategorienView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tKategorie '"+ kategorienView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
								}
								updateEtagen();
							}
						} else {
							if (restHandler.deleteRaum(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()),
									Integer.valueOf(raeumeIdModel.getElementAt(raeumeView.getSelectedIndex()).toString()))) {
								textOutput.setText(textOutput.getText() + 
										"<" + now("hh:mm:ss") + ">\tRaum '"+ raeumeView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
							} else {
								textOutput.setText(textOutput.getText() + 
										"<" + now("hh:mm:ss") + ">\tRaum '"+ raeumeView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
							}
							updateEtagen();
						}
					} else {
						if (restHandler.deleteEtage(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()))) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tEtage '"+ etagenView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
						} else {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tEtage '"+ etagenView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
						}
						updateEtagen();
					}
				}
			}
		});
		btnLoeschen.setBounds(665, 104, 117, 23);
		panel.add(btnLoeschen);
		
		JButton btnHinzufuegen = new JButton("Hinzuf\u00FCgen");
		btnHinzufuegen.addActionListener(new ActionListener() {
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
								if (restHandler.deleteElement(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()),
										Integer.valueOf(raeumeIdModel.getElementAt(raeumeView.getSelectedIndex()).toString()), selCat,
										Integer.valueOf(elemIdModel.getElementAt(elemView.getSelectedIndex()).toString()))) {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tElement '"+ elemView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tElement '"+ elemView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
								}
							} else {
								if (restHandler.deleteKategorie(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()),
										Integer.valueOf(raeumeIdModel.getElementAt(raeumeView.getSelectedIndex()).toString()), selCat)) {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tKategorie '"+ kategorienView.getSelectedValue() +"' wurde erfolgreich gelöscht!\n");
								} else {
									textOutput.setText(textOutput.getText() + 
											"<" + now("hh:mm:ss") + ">\tKategorie '"+ kategorienView.getSelectedValue() +"' konnte nicht gelöscht werden!\n");
								}
							}
						} else {
							String info = JOptionPane.showInputDialog(null,"Geben sie einen Namen ein: ", "Neuen Raum erstellen", JOptionPane.PLAIN_MESSAGE);
							if ((info.length() > 0) && (info != null) && (restHandler.addRaum(Integer.valueOf(etagenIdModel.getElementAt(etagenView.getSelectedIndex()).toString()), info))) {
								textOutput.setText(textOutput.getText() + 
										"<" + now("hh:mm:ss") + ">\tRaum '"+ info +"' wurde erfolgreich hinzugefügt!\n");
							} else {
								textOutput.setText(textOutput.getText() + 
										"<" + now("hh:mm:ss") + ">\tRaum '"+ info +"' konnte nicht hinzugefügt werden!\n");
							}
							updateEtagen();
						}
					} else {
						String info = JOptionPane.showInputDialog(null,"Geben sie einen Namen ein: ", "Neue Etage erstellen", JOptionPane.PLAIN_MESSAGE);
						if ((info.length() > 0) && (info != null) && (restHandler.addEtage(info))) {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tEtage '"+ info +"' wurde erfolgreich hinzugefügt!\n");
						} else {
							textOutput.setText(textOutput.getText() + 
									"<" + now("hh:mm:ss") + ">\tEtage '"+ info  +"' konnte nicht hinzugefügt werden!\n");
						}
						updateEtagen();
					}
				} else {
					// Keine Etagen ausgewählt
				}
			}
		});
		btnHinzufuegen.setBounds(665, 70, 117, 23);
		panel.add(btnHinzufuegen);
		
		JButton btnndern = new JButton("\u00C4ndern");
		btnndern.setBounds(665, 138, 117, 23);
		panel.add(btnndern);
		
		JButton btnClearOutput = new JButton("Clear Output");
		btnClearOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textOutput.setText("");
			}
		});
		btnClearOutput.setBounds(665, 500, 117, 23);
		panel.add(btnClearOutput);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("XMPP", null, panel_1, null);
	}

	public void setConnectionHandlers(ConnectionHandler connHndlr,
			RESTHandler restHandler) {
		this.connHndlr = connHndlr;
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
		if (etagenListe == null) {
			textOutput.setText(textOutput.getText() + 
					"<" + now("hh:mm:ss") + ">\tKeine Etagen vorhanden!\n");
			return;
		}
		for (Etage currEtage : etagenListe) {
			etagenModel.addElement(currEtage.getInfo());
			etagenIdModel.addElement(currEtage.getId());
		}
	}
}
