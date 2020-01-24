package clientsocket;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class ClientMain {

    public final static String HOST = "localhost";
    public final static int PORT = 9122;

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        ClientService socketTest6 = new ClientService(HOST, PORT);
        socketTest6.start();
    }






}
