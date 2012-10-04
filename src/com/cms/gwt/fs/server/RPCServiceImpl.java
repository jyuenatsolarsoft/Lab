package com.cms.gwt.fs.server;

//import java.util.HashMap;
//import java.util.Map;

//import com.cms.gwt.fs.client.rpc.RPCService;
//import com.cms.gwt.fs.client.rpc.action.Action;
//import com.cms.gwt.fs.client.rpc.response.Response;
//import com.cms.gwt.fs.server.command.ActionHandler;
//import com.cms.gwt.fs.server.command.GetTicketDetailCommand;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;

//@SuppressWarnings( { "serial", "unchecked" })
//public class RPCServiceImpl extends RemoteServiceServlet implements RPCService {
//
//	private Map<Class<? extends Action<? extends Response>>, ActionHandler> commands;
//
//	public RPCServiceImpl() {
//		commands = new HashMap<Class<? extends Action<? extends Response>>, ActionHandler>();
//		
//		// register all commands here
//		GetTicketDetailCommand getTicketDetailCommand = new GetTicketDetailCommand();
//		commands.put(getTicketDetailCommand.getActionType(), getTicketDetailCommand);
//	}
//
//	public <T extends Response> T execute(Action<T> action) {
//		try {
//			return (T) commands.get(action.getClass()).execute(action);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//}
