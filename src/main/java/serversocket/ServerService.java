package serversocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerService implements Runnable {

  private Socket incomingSocket;
  private InputStream inStream;
  private OutputStream outStream;
  private PrintWriter writer;
  private Scanner reader;

  public ServerService(Socket incoming) {
    this.incomingSocket = incoming;
    System.out.println("Client accepted: " + incomingSocket);
  }

  @Override
  public void run() {
    try {
      inStream = incomingSocket.getInputStream();
      outStream = incomingSocket.getOutputStream();

      reader = new Scanner(inStream, "UTF-8");
      writer = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
      writer.print("Server: Hello! Enter BYE to exit.");

      // In this simple server, we just read the client’s input, a line at a time, and echo it. This
      while (true) {
        String line = reader.nextLine();
        writer.println("Server: " + line);
        if (line.trim().equals("BYE")) {
          break;
        }
      }
    } catch (IOException ex) {
      System.out.println(
          "I/O error in: " + getClass().getEnclosingMethod().getName() + ex.getMessage());
      ex.printStackTrace();
    } finally {
      try {
        reader.close();
        writer.close();
        outStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        incomingSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
