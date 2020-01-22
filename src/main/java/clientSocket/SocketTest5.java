package clientSocket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest5 {

  private final String host;
  private final int port;

  private Socket clientSocket;
  private Scanner scanner;

  public SocketTest5(String host, int port) throws IOException {
    this.host = host;
    this.port = port;

    clientSocket = new Socket(host, port);
    scanner = new Scanner(System.in);
  }

  public void start() throws IOException {
    try (// Reading from
         InputStream input = clientSocket.getInputStream();
         // Writing to
         OutputStream output = clientSocket.getOutputStream() ) {

      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      PrintWriter writer = new PrintWriter(output, true);

      String line = reader.readLine();
      System.out.println(line);

      writer.println("hello " + host);

      line = reader.readLine();
      System.out.println(line);

      writer.println("quit");
      line = reader.readLine();
      System.out.println(line);

    } catch (UnknownHostException ex) {
      System.out.println( " Server not found: " + ex.getMessage());
		ex.printStackTrace();
	} catch (IOException ex) {
		System.out.println( " I/O error: " + ex.getMessage());
		ex.printStackTrace();
	} finally {
        clientSocket.close();
        scanner.close();
    }

  }

  public static void main(String[] args) throws IOException {
  	SocketTest5 socketTest5 = new SocketTest5(Main.HOST, Main.PORT);
  	socketTest5.start();
  	//
  }
}
