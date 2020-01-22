package clientSocket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado, and prints the
 * time that the server sends.
 *
 * @version 1.21 2016-04-27
 * @author Cay Horstmann
 */
public class SocketTest3 {

  private final String HOST;
  private final int PORT;
  private Socket serverSocket;
  private Scanner input;

  public SocketTest3(String host, int port) throws IOException {
    this.HOST = host;
    this.PORT = port;

    serverSocket = new Socket(host, port);
    input = new Scanner(new InputStreamReader(serverSocket.getInputStream(), StandardCharsets.UTF_8));
    while (input.hasNextLine()) {
      String line = input.nextLine();
      System.out.println(line);
    }

  }

  public void run() throws IOException {

    InputStream inStream = serverSocket.getInputStream();
    OutputStream outStream = serverSocket.getOutputStream();

    Scanner in = new Scanner(inStream,"UTF-8");
    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
    PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

    boolean done = false;
    while (!done && in.hasNextLine()) {
      String line = in.nextLine();
      out.println("Client: " + line);
      out.write(line);
    }
  }



  public static void main(String[] args) throws IOException {
    SocketTest3 socketTest3 = new SocketTest3("localhost", 8189);
    socketTest3.run();
  }
}


