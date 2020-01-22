package clientSocket.netExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BaeldungSocket {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public String sendMessage(String msg) throws IOException {
		out.println(msg);
		String resp = in.readLine();

		String html = " \n ";
		while (resp.length() > 0) {
			html += " \n " + resp;
			resp = in.readLine();
		}
		return html;
	}

	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}

  public static void main(String[] args) throws IOException {

		String ip = "mfa-pmr.org";
		int port = 80;

		String msg = "GET / HTTP/1.1\n" +
				"Host: mfa-pmr.org\n" +
				"User-Agent: Mozilla/5.0 test\n" +
				"Accept: text/html\n" +
				"Connection: close\n";


		BaeldungSocket clientSocket = new BaeldungSocket();
		clientSocket.startConnection(ip, port );
	  String resp = clientSocket.sendMessage(msg);
    System.out.println(resp);
	  clientSocket.stopConnection();
		//
  }
}
