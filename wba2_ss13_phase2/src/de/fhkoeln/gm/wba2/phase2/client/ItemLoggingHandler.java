package de.fhkoeln.gm.wba2.phase2.client;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class ItemLoggingHandler implements ItemEventListener<Item>{
	
	private ClientFrame clFrame;
	
	public ItemLoggingHandler(ClientFrame clFrame){
		this.clFrame = clFrame;
	}

	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> event) {
		for (Item currItem: event.getItems()) {
			clFrame.receiveNotification(((PayloadItem<SimplePayload>) currItem).getPayload().toXML());
		}
	}

}
