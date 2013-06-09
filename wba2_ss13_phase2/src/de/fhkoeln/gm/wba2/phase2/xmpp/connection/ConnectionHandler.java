package de.fhkoeln.gm.wba2.phase2.xmpp.connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;

public class ConnectionHandler {
	
	private XMPPConnection xmppConn;
	private AccountManager accMngr;
	private PubSubManager pubSubMngr;
	private String hostname;
	private String username;

	public ConnectionHandler(){
	}
	
	public boolean connect(String hostname, int port){
		
		if (xmppConn != null && xmppConn.isConnected()){
			return true;
		}
		
		ConnectionConfiguration connConfig = new ConnectionConfiguration(hostname, port);
		xmppConn = new XMPPConnection(connConfig);
		
		accMngr = new AccountManager(xmppConn);
		
		try{
			xmppConn.connect();
			pubSubMngr = new PubSubManager(xmppConn, "pubsub." + xmppConn.getHost());
		} catch(XMPPException e){
			System.out.println(e);
			return false;
		}
		
		this.hostname = hostname;
		
		return true;
	}
	
	public boolean login(String user, String password){
		try{
			xmppConn.login(user, password);
		} catch(XMPPException e){
			return false;
		}
		
		this.username = user;
		return true;
	}
	
	public List<String> getAllNodes() {
		
		List<String> entries = new ArrayList<String>();
		
		try{
			DiscoverItems itms = pubSubMngr.discoverNodes(null);
			Iterator<DiscoverItems.Item> it = itms.getItems();
			
			for (; it.hasNext();){
				entries.add(it.next().getNode());
			}
			
		} catch(XMPPException e){
			e.printStackTrace();
		}
		
		return entries;
	}
	
	public boolean createNode() {
		try {
			LeafNode leaf = pubSubMngr.createNode("testNode");
			ConfigureForm form = new ConfigureForm(FormType.submit);
			form.setAccessModel(AccessModel.open);
	     	form.setDeliverPayloads(false);
	     	form.setNotifyRetract(true);
	     	form.setPersistentItems(true);
	     	form.setPublishModel(PublishModel.open);
	      
	     	leaf.sendConfigurationForm(form);
	      
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
