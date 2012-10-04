package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.AddRecordedMaterialsResponse;

public class AddRecordedMaterialsXMLResponseHandler extends ServiceXMLResponseHandler implements Instantiable
{
	@Override
	protected ActionResponse constructResponse(Messages msgs) {
		return new AddRecordedMaterialsResponse(msgs);
	}			
}
