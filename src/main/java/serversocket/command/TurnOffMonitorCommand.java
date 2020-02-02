package serversocket.command;

import clientsocket.Receiver;

public class TurnOffMonitorCommand implements Command{

	private Receiver receiver;

	public TurnOffMonitorCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {

	}

	@Override
	public void unExecute() {

	}
}
