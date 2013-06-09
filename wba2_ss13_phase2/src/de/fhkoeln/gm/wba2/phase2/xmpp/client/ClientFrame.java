package de.fhkoeln.gm.wba2.phase2.xmpp.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import de.fhkoeln.gm.wba2.phase2.xmpp.connection.ConnectionHandler;

public class ClientFrame extends JFrame{
	
	private JPanel contentPane;
	private ConnectionHandler connHndlr;
	private MainFrame parentFrame;
	private DefaultListModel<String> listmodel;
	private JList listNodes; 
	private JButton btnUpdate;
	
	public ClientFrame(MainFrame parent){
		parentFrame = parent;
		
		setTitle("XMPP-Client");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        listmodel = new DefaultListModel<>();
        
        listNodes = new JList(listmodel);
        listNodes.setBorder(new LineBorder(new Color(0, 0, 0)));
        listNodes.setBounds(636, 152, 104, 219);
        contentPane.add(listNodes);
        
        JButton btnCreateNode = new JButton();
        btnCreateNode.setText("Create test-Node...");
        btnCreateNode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (connHndlr.createNode()) {
					System.out.println("Node created!");
				} else {
					System.out.println("Node creation failed!");
				}
			}
        	
        });

        
        btnCreateNode.setBounds(100, 100, 80, 20);
        contentPane.add(btnCreateNode);
        
        btnUpdate = new JButton("Aktu.");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                refreshLists();
            }
        });
        btnUpdate.setBounds(380, 149, 104, 25);
        contentPane.add(btnUpdate);
	}
	
	private void refreshLists() {
        List<String> entries = connHndlr.getAllNodes();
        listmodel.clear();

        for (String curr : entries) {
            listmodel.addElement(curr);
       
        }
    }


	
	public void setConnectionHandler(ConnectionHandler connHndlr){
		this.connHndlr = connHndlr;
	}
}
