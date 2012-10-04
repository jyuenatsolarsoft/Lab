package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.UpdateRecordedMaterialResponse;

public class UpdateRecordedMaterialXMLResponseHandler extends ReceiveRecordedMaterialXMLResponseHandler 
{
	/**
	 * Construct the ActionResponse.
	 * 
	 * @param event The event received in the response
	 * @return The response.
	 */
	protected ActionResponse constructResponse(RecordedMaterial material, WorkHistory workHistory)
	{
		// Override the method in the parent class to determine whether the update fails or not
		// by checking if there are any <Messages> element in the response.
		
		if (material == null)
		{
			return new UpdateRecordedMaterialResponse(material, workHistory);
		}
		else
		{
			return new UpdateRecordedMaterialResponse(material.getMessages(), material, workHistory);
		}
	}
}
