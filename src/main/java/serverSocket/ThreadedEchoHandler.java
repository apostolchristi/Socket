package serverSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadedEchoHandler implements Runnable {

  private Socket incomingSocket;
  private Scanner in;

  public ThreadedEchoHandler(Socket incoming) {
    this.incomingSocket = incoming;

  }

  @Override
  public synchronized void run() {
    // acquires this lock; blocks if the lock is currently owned by another thread
    try {
      // Read from
      InputStream inStream = incomingSocket.getInputStream();
      // Writing to
      OutputStream outStream = incomingSocket.getOutputStream();
      // We transmit text through sockets. We therefore turn the streams into scanners and writers.
      in = new Scanner(inStream, "UTF-8");

      PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
      out.println("Hello! Enter BYE to exit.");

      // In this simple server, we just read the client’s input, a line at a time, and echo it. This
      boolean done = false;
      while (!done && in.hasNextLine()) {
        // puts this thread on the wait set for this condition.
        String line = in.nextLine();
        out.println("Server: " + line);
        if (line.trim().equals("BYE")) {
          done = true;
        }
        // unblocks all threads in the wait set for this condition.
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        incomingSocket.close();
        in.close();
        // releases this lock
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
