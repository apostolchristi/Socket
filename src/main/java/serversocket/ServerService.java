package serversocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerService implements Runnable {

  private Socket incomingSocket;
  private Scanner in;

  public ServerService(Socket incoming) {
    this.incomingSocket = incoming;
  }

  @Override
  public void run() {
    try {
      InputStream inStream = incomingSocket.getInputStream();
      OutputStream outStream = incomingSocket.getOutputStream();

      in = new Scanner(inStream, "UTF-8");
      PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
      out.println("Server: Hello! Enter BYE to exit.");

      // In this simple server, we just read the client’s input, a line at a time, and echo it. This
      while (true) {
        String line = in.nextLine();
        out.println("Server: " + line);
        if (line.trim().equals("BYE")) {
          break;
        }
      }
    } catch (IOException ex) {
      System.out.println("I/O error in: " + getClass().getEnclosingMethod().getName() + ex.getMessage());
      ex.printStackTrace();
    } finally {
      try {
        incomingSocket.close();
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
