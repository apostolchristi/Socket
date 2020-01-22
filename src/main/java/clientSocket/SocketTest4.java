package clientSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado, and prints the
 * time that the server sends.
 *
 * @version 1.21 2016-04-27
 * @author Cay Horstmann
 */
public class SocketTest4 {

  private final String HOST;
  private final int PORT;
  private Socket serverSocket;
  private Scanner input;

  public SocketTest4(String host, int port) throws IOException {
    this.HOST = host;
    this.PORT = port;

    serverSocket = new Socket(host, port);
    input = new Scanner(System.in);
  }

  public void start() throws IOException {
    //Writing to
    OutputStream output = serverSocket.getOutputStream();
    PrintWriter writer = new PrintWriter(output, true);
    String msg = input.nextLine();
    writer.println(msg);

    //Reading from
    InputStream input = serverSocket.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String line;

    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
  }

  public static void main(String[] args) throws IOException {

    SocketTest4 socketTest4 = new SocketTest4(Main.HOST, Main.PORT);
    socketTest4.start();

  }
}
