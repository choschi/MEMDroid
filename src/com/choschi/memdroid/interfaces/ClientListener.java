package com.choschi.memdroid.interfaces;

import com.choschi.memdroid.Client.ClientMessages;

/**
 * 
 * ClientListener is implemented by all memebers of the UI waiting for messages from the Client
 * 
 * @author Christoph Isch
 *
 */

public interface ClientListener {
	
	/**
	 * get notified by the client
	 * @param message
	 */
	
	void notify (ClientMessages message);
}
