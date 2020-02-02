package serversocket.command;

import clientsocket.Receiver;

public class CloseBrowserChromeCommand implements Command{

	private Receiver receiver;

	public CloseBrowserChromeCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {

	}

	@Override
	public void unExecute() {

	}
}
