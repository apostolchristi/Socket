package serversocket.command;

/*
Invoker acts on receiver
 */
public class Invoker {

	private Command turnOnMonitor;
	private Command turnOffMonitor;
	private Command openBrowserChrome;
	private Command closeBrowserChrome;

	public Invoker(Command turnOnMonitor, Command turnOffMonitor, Command openBrowserChrome, Command closeBrowserChrome) {
		this.turnOnMonitor = turnOnMonitor;
		this.turnOffMonitor = turnOffMonitor;
		this.openBrowserChrome = openBrowserChrome;
		this.closeBrowserChrome = closeBrowserChrome;
	}

	public void turnOn() {
		this.turnOnMonitor.execute();
	}

	public void turnOff() {
		this.turnOffMonitor.execute();
	}

	public void openBrowser() {
		this.openBrowserChrome.execute();
	}

	public void closeBrowser() {
		this.closeBrowserChrome.execute();
	}



}
