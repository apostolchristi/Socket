package clientsocket;

import clientsocket.data.ClientSystemInformation;
import clientsocket.data.Cookie;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class ClientService {

  private String host;
  private int port;

  private Socket clientSocket;
  private Scanner consol;
  private InputStream input;
  private OutputStream output;
  private BufferedReader reader;
  private PrintWriter writer;
  private ClientSystemInformation clientSystemInformation;
  private Cookie cookie;

  public ClientService(String host, int port) {
    this.host = host;
    this.port = port;
    cookie = new Cookie();
    try {
      clientSocket = new Socket(host, port);
      consol = new Scanner(System.in);

      input = clientSocket.getInputStream();
      output = clientSocket.getOutputStream();

      reader = new BufferedReader(new InputStreamReader(input));
      writer = new PrintWriter(output, true);
      sendMessage(helloMessage());
    } catch (IOException ex) {
      System.out.println(
          "I/O error in: " + getClass().getEnclosingMethod().getName() + ex.getMessage());
      ex.getStackTrace();
    }
  }

  public void execute() throws IOException {

    Object clientCookieID = cookie.execute();
    System.out.println(clientCookieID);

    repeatedTaskTimerSendClientSystemInfo();

    while (true) {
      String incoming = readMessage();
      System.out.println(incoming);

      if (readMessage().equals("null")) {
        System.out.println("Session is close, BYE");
        closeConnection();
        break;
      }

      sendMessage(consol.nextLine());

    }
  }

  private String readMessage() throws IOException {
    return reader.readLine();
  }

  void sendMessage(String message) throws IOException {
    writer.println(message);
  }

  public void repeatedTaskTimerSendClientSystemInfo() {
    TimerTask repeatedTask =
        new TimerTask() {
          @Override
          public void run() {
            System.out.println("Task performed on ");
            try {
              sendMessage(helloMessage());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        };
    Timer timer = new Timer("Timer");
    long delay = 10000L; // 1000 = 1 sec
    long period = 10000L;
    timer.scheduleAtFixedRate(repeatedTask, delay, period);
  }

  public String helloMessage() {
    clientSystemInformation = new ClientSystemInformation();
    return clientSystemInformation.getAndBuildClientSystemInformation();
  }

  public void closeConnection() {
    try {
      writer.close();
      consol.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
