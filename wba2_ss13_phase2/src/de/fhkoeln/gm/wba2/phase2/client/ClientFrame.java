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
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
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

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private Client restClient;
	private ConnectionHandler connHndlr;
	private LoginFrame myParent;
	private DefaultMutableTreeNode rootNode;
	private JTree tree;
	private TreeModel treeModel; 
	private JTextArea textOutputSel;
	

	/**
	 * Create the frame.
	 */
	public ClientFrame(LoginFrame parent) {
		rootNode = new DefaultMutableTreeNode("Gebaeude"); 
		myParent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 567, 364);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("REST ", null, panel, null);
		panel.setLayout(null);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				Client restClient = Client.create();
				WebResource clientResource = restClient.resource("http://localhost:4711/");
				ClientResponse clientResponse = clientResource.type(MediaType.APPLICATION_XML).get(ClientResponse.class);
				Gebaeude myObj = clientResponse.getEntity(Gebaeude.class);
				List<Etage> etagenListe = myObj.getEtagenEl().getEtageEl();
				
				rootNode.removeAllChildren();
				for(Etage currEtage: etagenListe){
					DefaultMutableTreeNode etageNode = new DefaultMutableTreeNode(currEtage.getInfo()); 
					rootNode.add(etageNode);
					List<Raum> raumListe = null;
					try {
						raumListe = currEtage.getRaeumeEl().getRaumEl();
					} catch(NullPointerException err) { continue; }
					for(Raum currRaum: raumListe){
						DefaultMutableTreeNode raumNode = new DefaultMutableTreeNode(currRaum.getInfo());
						etageNode.add(raumNode);
						List<Licht> lichtListe = null;
						List<Steckdose> steckdoseListe = null;
						List<Verschattung> verschattungListe = null;
						List<Kontakt> kontaktListe = null;
						List<Bewegung> bewegungListe = null;
						List<Feuermeld> feuermeldListe = null;
						try {
							lichtListe = currRaum.getLichterEl().getLichtEl();
						} catch(NullPointerException err) { continue; }
						DefaultMutableTreeNode lichtNode = new DefaultMutableTreeNode("Lichter");
						raumNode.add(lichtNode);
						for (Licht currLicht: lichtListe){
							DefaultMutableTreeNode lichtElem = new DefaultMutableTreeNode(currLicht.getInfo());
							lichtNode.add(lichtElem);
						}
						try {
							steckdoseListe = currRaum.getSteckdosenEl().getSteckdoseEl();
						} catch (NullPointerException err) { continue; }
						DefaultMutableTreeNode steckdoseNode = new DefaultMutableTreeNode("Steckdosen");
						raumNode.add(steckdoseNode);
						for (Steckdose currSteckdose: steckdoseListe){
							DefaultMutableTreeNode steckdoseElem = new DefaultMutableTreeNode(currSteckdose.getInfo());
							steckdoseNode.add(steckdoseElem);
						}
						try {
							verschattungListe = currRaum.getVerschattungenEl().getVerschattungEl();
						} catch (NullPointerException err) { continue; }
						DefaultMutableTreeNode verschattungNode = new DefaultMutableTreeNode("Verschattungen");
						raumNode.add(verschattungNode);
						for (Verschattung currVerschattung: verschattungListe){
							DefaultMutableTreeNode verschattungElem = new DefaultMutableTreeNode(currVerschattung.getInfo());
							verschattungNode.add(verschattungElem);
						}
						try {
							kontaktListe = currRaum.getKontakteEl().getKontaktEl();
						} catch (NullPointerException err) { continue; }
						DefaultMutableTreeNode kontaktNode = new DefaultMutableTreeNode("Kontakte");
						raumNode.add(kontaktNode);
						for (Kontakt currKontakt: kontaktListe){
							DefaultMutableTreeNode kontaktElem = new DefaultMutableTreeNode(currKontakt.getInfo());
							kontaktNode.add(kontaktElem);
						}
						try {
							bewegungListe = currRaum.getBewegungenEl().getBewegungEl();
						} catch (NullPointerException err) { continue; }
						DefaultMutableTreeNode bewegungNode = new DefaultMutableTreeNode("Bewegungsmelder");
						raumNode.add(bewegungNode);
						for (Bewegung currBewegung: bewegungListe){
							DefaultMutableTreeNode bewegungElem = new DefaultMutableTreeNode(currBewegung.getInfo());
							bewegungNode.add(bewegungElem);
						}
					}
				}
				((DefaultTreeModel)tree.getModel()).setRoot(rootNode);	
			}
		});
		btnAktualisieren.setBounds(240, 9, 110, 23);
		panel.add(btnAktualisieren);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 9, 220, 265);
		panel.add(scrollPane);
		
		tree = new JTree();
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
				for(int currRow: tree.getSelectionRows()){
					System.out.println(currRow);
				}
						
				
				if (node == null) return;
				Object myObj = node.getUserObject();
				System.out.println(myObj);
			}
		});
		scrollPane.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Gebaeude") {
				{
				}
			}
		));
		tree.setBorder(null);
		
		textOutputSel = new JTextArea();
		textOutputSel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		textOutputSel.setBounds(10, 285, 220, 40);
		panel.add(textOutputSel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("XMPP", null, panel_1, null);
	}
	
	public void setConnectionHandler(ConnectionHandler connHndlr){
		this.connHndlr = connHndlr;
	}
}
