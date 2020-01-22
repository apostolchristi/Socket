package clientSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest6 {

    private Socket clientSocket;
    private Scanner scanner;
    private InputStream input;
    private OutputStream output;
    BufferedReader reader;
    PrintWriter writer;


    public void establishConnection(String host, int port) {

        try {
            clientSocket = new Socket(host, port);
            scanner = new Scanner(System.in);

            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);

        } catch (IOException exception) {
            System.out.println("I/O error " + exception.getMessage());
            exception.getStackTrace();
        }
    }

    public void executeSocket() throws IOException {


        while (true) {
            String response = readMessage();
            System.out.println(response);
            if(scanner.hasNextLine()) {
                sendMessage(scanner.nextLine());
            } else if (response.equals("null")) {
                scanner.close();
            }

        }

    }


    private String readMessage() throws IOException {
        return reader.readLine();

    }

    private void sendMessage(String message) throws IOException {
        System.out.println("Client: " + message);
        writer.println(message);
    }

    public void closeConnection() {
        try {
            clientSocket.close();
            scanner.close();
            input.close();
            output.close();
        } catch (IOException exception) {
            System.out.println("I/O error " + exception.getMessage());
            exception.getStackTrace();
        }
    }

}
