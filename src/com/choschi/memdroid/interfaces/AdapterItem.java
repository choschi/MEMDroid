package com.choschi.memdroid.interfaces;

/**
 * 
 * provides an interface for all the self built spinner items, which defines the way of accessing the id of the item
 * 
 * @author Christoph Isch
 *
 */

public interface AdapterItem {
	
	/**
	 * get the id to identify the item
	 * @return the id
	 */
	
	String getId();
}
