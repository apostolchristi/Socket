package clientSocket;


import java.io.IOException;


public class Main {

    public final static String HOST = "localhost";
    public final static int PORT = 9122;

    public static void main(String[] args) throws IOException {

        SocketTest6 socketTest6 = new SocketTest6();
        socketTest6.establishConnection(HOST, PORT);
        socketTest6.executeSocket();
        socketTest6.closeConnection();
    }






}
