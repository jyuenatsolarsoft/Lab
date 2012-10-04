package com.cms.gwt.fs.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Messages contains a list of error messages returned from the backend services.
 *
 */
public class Messages {

	private List<Message> messages = new ArrayList<Message>();

	public Messages(List<Message> messages) {	
		this.messages = messages; 
	}
	
	public List<Message> getMessages()
	{
		return messages;
	}
	
	/**
	 * Returns the number of messages received from the backend.
	 * 
	 * @return number of messages.
	 */
	public int size()
	{
		return messages.size();
	}
}
