package com.cms.gwt.fs.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.gwt.fs.client.TextConstants;
import com.google.gwt.core.client.GWT;

/**
 * The Message model can be contained in various backend responses.
 * Occurrence of the Message element indicates that errors or warnings have occurred
 * and the request is failed on the backend. 
 * 
 * The error detail is contained in the properties of this class.
 *
 */
public class Message extends FSModel {
		
	public enum MessageType {
		ERROR("E"), INFO("I"), WARNING("W");

		public final String value;

		MessageType(String value) {
			this.value = value;
		}

		public static MessageType get(String type) {
			for (MessageType currentType : MessageType.values()) {
				if (currentType.value.equals(type)) {
					return currentType;
				}
			}
			return ERROR;
		}
	}
	
	private TextConstants txtConsts = 
		(TextConstants) GWT.create(TextConstants.class);
	
	/**
	 * The map containing the key field and the key value of the record
	 * this problem occurs.
	 */
	private Map<String, String> keys = new HashMap<String, String>();
	
	/**
	 * The name of the field where the problem occurs.
	 */
	private List<String> elements = new ArrayList<String>();
	
	/**
	 * Message id.
	 */
	private String messageId;
	
	/**
	 * Message Type. It can be Error, Warning, Information or something else in the future.
	 */
	private MessageType messageType;
	
	/**
	 * Error Message from the backend.
	 */
	private String messageText;

	public List<String> getElements() {
		return elements;
	}

	public void setElements(List<String> elements) {
		this.elements = elements;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Map<String, String> getKeys() {
		return keys;
	}

	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}   
			
	
	/**
	 * Returns a html formatted messaage string.
	 * 
	 * @return the content of this Message class in html format.
	 */
	public String getFormattedMessage()
	{
		StringBuffer alertMessage = new StringBuffer();
				
		
		// Print out the keys.
		for (String key : getKeys().keySet())
		{
			alertMessage.append(key + ": " + keys.get(key) + "<br>");
		}
		
		// Print out the element names.
		for (String element : getElements())
		{
			alertMessage.append(txtConsts.MessageElement() + ": " + element + "<br>");
		}
		
		// Now print the message content.
		alertMessage.append("<br>" + getMessageText());
		
		return alertMessage.toString();
	}	
	
}
