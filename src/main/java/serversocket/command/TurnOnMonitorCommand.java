package serversocket.command;

import clientsocket.Receiver;

public class TurnOnMonitorCommand implements Command{

	private Receiver receiver;

	public TurnOnMonitorCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {

	}

	@Override
	public void unExecute() {

	}
}
