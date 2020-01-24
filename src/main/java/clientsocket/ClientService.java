package clientsocket;

import clientsocket.data.ClientSystemInformation;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class ClientService {

  private String host;
  private int port;

  private Socket clientSocket;
  private Scanner scanner;
  private InputStream input;
  private OutputStream output;
  private BufferedReader reader;
  private PrintWriter writer;
  private ClientSystemInformation clientSystemInformation;

  public ClientService(String host, int port) {
    this.host = host;
    this.port = port;
    try {
      clientSocket = new Socket(host, port);
      scanner = new Scanner(System.in);
      input = clientSocket.getInputStream();
      output = clientSocket.getOutputStream();
      reader = new BufferedReader(new InputStreamReader(input));
      writer = new PrintWriter(output, true);
      //      sendTheMessage(helloMessage());
    } catch (IOException ex) {
      System.out.println(
          "I/O error in: " + getClass().getEnclosingMethod().getName() + ex.getMessage());
      ex.getStackTrace();
    }
  }

  public void start()
      throws IOException, InterruptedException, ExecutionException, TimeoutException {

    // repeatedTaskTimerSendClientSystemInfo()

    while (true) {
      String response = readMessage();
      System.out.println(response);
      sendMessage(scanner.nextLine());
      if (response.equals("null")) {
        System.out.println("Session is close, BYE");
        closeConnection();
        break;
      }
    }
  }

  private String readMessage() throws IOException {
    return reader.readLine();
  }

  void sendMessage(String message) throws IOException {
    System.out.println(message);
    writer.println(message);
  }

  public void repeatedTaskTimerSendClientSystemInfo() {
    TimerTask repeatedTask =
        new TimerTask() {
          @Override
          public void run() {
            System.out.println("Task performed on " + new Date());
            try {
              sendMessage(helloMessage());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        };
    Timer timer = new Timer();
    long delay = 10000L; // 1000 = 1 sec
    long period = 10000L;
    timer.scheduleAtFixedRate(repeatedTask, delay, period);
  }

  public String helloMessage() {
    clientSystemInformation = new ClientSystemInformation();
    return clientSystemInformation.getUserInfo();
  }

  public void closeConnection() {
    try {
      clientSocket.close();
      scanner.close();
      input.close();
      output.close();
      reader.close();
      writer.close();
    } catch (IOException exception) {
      System.out.println(
          "I/O error in " + getClass().getEnclosingMethod().getName() + exception.getMessage());
      exception.getStackTrace();
    }
  }
}
