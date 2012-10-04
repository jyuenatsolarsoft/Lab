package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.UpdateScheduledEventResponse;

public class UpdateScheduledEventXMLResponseHandler extends ReceiveScheduledEventXMLResponseHandler 
{
	/**
	 * Construct the ActionResponse.
	 * 
	 * @param event The event received in the response
	 * @return The response.
	 */
	protected ActionResponse constructResponse(ScheduledEvent event)
	{
		// Override the method in the parent class to determine whether the update fails or not
		// by checking if there are any <Messages> element in the response.
		
		if (event == null)
		{
			return new UpdateScheduledEventResponse(event);
		}
		else
		{
			return new UpdateScheduledEventResponse(event.getMessages(), event);
		}
	}
}
