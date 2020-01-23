package serverSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

  private ServerSocket serverSocket;
  private Socket incoming;
  private Runnable runnable;
  private Thread thread;

  public void start(int port) throws IOException {
    // Establish server socket
    try {
      serverSocket = new ServerSocket(port);

      int i = 1;

      /**
       * Wait for clients connection. Once someone connects to this port by sending the correct
       * request over the network, this method returns a Socket object that represents the
       * connection that was made.
       */
      while (true) {
        incoming = serverSocket.accept();
        System.out.println("Spawining " + i);
        runnable = new ThreadedEchoHandler(incoming);
        thread = new Thread(runnable);
        thread.start();
        i++;
      }
    } catch (IOException exception) {
      System.out.println("I/O error " + exception.getMessage());
      exception.printStackTrace();
    } finally {
      serverSocket.close();
    }
  }
}
