package serversocket.command;

import clientsocket.Receiver;

public class OpenBrowserChromeCommand implements Command{

	private Receiver receiver;

	public OpenBrowserChromeCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {

	}

	@Override
	public void unExecute() {

	}
}
