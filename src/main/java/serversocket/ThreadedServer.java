package serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {

  private ServerSocket serverSocket;
  private Socket incoming;
  private Runnable runnable;
  private Thread thread;

  public void start(int port) throws IOException {
    // Establish server socket
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("Server started: " + serverSocket);
      System.out.println("Waiting for a client ...");

      int count = 1;

      /**
       * Wait for clients connection. Once someone connects to this port by sending the correct
       * request over the network, this method returns a Socket object that represents the
       * connection that was made.
       */
      while (true) {
        incoming = serverSocket.accept();
        System.out.println("Spawned. Connection nr: " + count);
        runnable = new ServerService(incoming);
        thread = new Thread(runnable);
        thread.start();
        count++;
      }
    } catch (IOException exception) {
      System.out.println("I/O error " + exception.getMessage());
      exception.printStackTrace();
    } finally {
      serverSocket.close();
    }
  }
}
