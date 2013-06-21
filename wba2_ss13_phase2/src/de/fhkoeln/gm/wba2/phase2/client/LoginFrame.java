package de.fhkoeln.gm.wba2.phase2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.fhkoeln.gm.wba2.phase2.xmpp.connection.ConnectionHandler;

public class LoginFrame extends JFrame {
	
	private ClientFrame clntFrm;
	
	private JPanel contentPane;
	private JTextField txtHostname;
	private JTextField txtPort;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblStatus;
	private ConnectionHandler connHndlr;
	
	
	public LoginFrame() {
		
		clntFrm = new ClientFrame(this);
		
		setTitle("Anmelden");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 250);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblHostname = new JLabel("Hostname");
        lblHostname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHostname.setBounds(20, 39, 82, 15);
        contentPane.add(lblHostname);
        
        JLabel lblPort = new JLabel("Port");
        lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPort.setBounds(20, 69, 82, 15);
        contentPane.add(lblPort);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(20, 99, 82, 15);
        contentPane.add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(20, 129, 82, 15);
        contentPane.add(lblPassword);
        
        txtHostname = new JTextField();
        txtHostname.setText("localhost");
        txtHostname.setBounds(120, 34, 144, 25);
        contentPane.add(txtHostname);
        txtHostname.setColumns(15);
        
        txtPort = new JTextField();
        txtPort.setText("5222");
        txtPort.setBounds(120, 64, 144, 25);
        contentPane.add(txtPort);
        txtPort.setColumns(15);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(120, 94, 144, 25);
        contentPane.add(txtUsername);
        txtUsername.setColumns(15);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 124, 144, 25);
        contentPane.add(txtPassword);
        
        JButton btnConnect = new JButton();
        btnConnect.setText("Connect...");
        
        btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblStatus.setText("");
				String host = txtHostname.getText();
				String user = txtUsername.getText();
				String password = txtPassword.getText();
				
				int port = -1;
				
				try{
				  port = Integer.parseInt(txtPort.getText());
				} catch(NumberFormatException err_num_form){
					System.out.println(err_num_form);
				}
				
				if (host.length() > 3 && port > 0 && port <= 65536
                        && user.length() > 0 && password.length() > 0){
					
					connHndlr = new ConnectionHandler();
					
					if (connHndlr.connect(host, port)){
						if (connHndlr.login(user, password)){
							System.out.println("Connect & Anmeldung erfolgreich!");
							// Open Second Frame
							clntFrm.setVisible(true);
							clntFrm.setEnabled(true);
							setVisible(false);
							clntFrm.setConnectionHandler(connHndlr);
						} else {
							lblStatus.setText("Anmeldung fehlgeschlagen!");
						}
					} else {
						lblStatus.setText("Connect fehlgeschlagen!");
					}
				} else {
					lblStatus.setText("Fehler bei Verbindungsherstellung!");
				}
			}
        });
        
        btnConnect.setBounds(20, 165, 90, 25);
        contentPane.add(btnConnect);
        
        lblStatus = new JLabel("");
        lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
        lblStatus.setBounds(120, 165, 245, 25);
        contentPane.add(lblStatus);
        
        setVisible(true);
	}
	
}
