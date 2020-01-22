package serverSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {

    private Socket incomingSocket;
    Scanner in;

    public ThreadedEchoHandler(Socket incoming) {
        this.incomingSocket = incoming;
    }

    public void run() {

        //This objects gets input and output streams. Everything that the server sends to the server
        //output stream becomes the input of the client program, and all the output from the client
        // program ends up in the server input stream.
        try {
            InputStream inStream = incomingSocket.getInputStream();
            OutputStream outStream = incomingSocket.getOutputStream();

            //We transmit text through sockets. We therefore turn the streams into scanners and writers.
            in = new Scanner(inStream, "UTF-8");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
            out.println("Hello! Enter BYE to exit.");

            //In this simple server, we just read the client’s input, a line at a time, and echo it. This
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Server: " + line);
                if (line.trim().equals("BYE")) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
