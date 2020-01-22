package clientSocket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado, and prints the
 * time that the server sends.
 *
 * @version 1.21 2016-04-27
 * @author Cay Horstmann
 */
public class SocketTest2 {

  private Socket clientSocket;
  private PrintWriter out;
  private Scanner in;

  public List<String> readFromSocketServer(Socket serverSocket) {
    List<String> data = new ArrayList<>();
    try (BufferedReader readerIn =
        new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))) {
      String s;
      while ((s = readerIn.readLine()) != null) {
        data.add(s);
        System.out.println(s);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }



  public void startConnection(String host, int port) throws IOException {

    clientSocket = new Socket(host, port);
    // Writing to serverSocket
    out = new PrintWriter(clientSocket.getOutputStream(), true /*autoFlush */);
    // Reading from serverSocket
    in = new Scanner(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));

      readFromSocketServer(clientSocket);

//    while (in.hasNextLine()) {
//      String line = in.nextLine();
//      System.out.println(line);
//    }
  }

  public String sendMessage(String msg) {
      out.println(msg);
      String resp = in.nextLine();
      String html = "";
      while (resp.length() > 0) {
          html += resp;
          resp = in.nextLine();
      }
      return html;
  }

  public String scanner() {
      Scanner input = new Scanner(System.in);
      String msg = input.nextLine();
      while (msg != null) {
          System.out.println("Invalid ID. Please input a valid ID: " + msg);
          msg = input.nextLine();
      }
      return msg;
  }

  public void stopConnection() throws IOException {
    in.close();
    out.close();
    clientSocket.close();
  }

  public static void main(String[] args) throws IOException {


    SocketTest2 clientSocket = new SocketTest2();
      String msg = clientSocket.scanner();
      clientSocket.startConnection("localhost", 8189);
    clientSocket.sendMessage(msg);
  }
}
