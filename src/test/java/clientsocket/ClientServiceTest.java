package clientsocket;

import clientsocket.data.ClientSystemInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

  ClientService clientService;
  private Scanner scanner;

  @Mock private Socket clientSocket;
  @Mock private InputStream input;
  @Mock private OutputStream output;
  @Mock private BufferedReader reader;
  @Mock private PrintWriter writer;
  @Mock private ClientSystemInformation clientSystemInformation;

  private String host = "localhost";
  private int port = 9122;

  @Before
  public void setUp() throws Exception {
    clientService = new ClientService(host, port);
    clientSocket = new Socket(host, port);
    scanner = new Scanner(System.in);
    input = clientSocket.getInputStream();
    output = clientSocket.getOutputStream();
    reader = new BufferedReader(new InputStreamReader(input));
    writer = new PrintWriter(output, true);
  }

  @Test
  public void test() throws IOException, InterruptedException, ExecutionException, TimeoutException {

    System.out.println("I work");
  }

  @After
  public void tearDown() throws Exception {
    clientSocket.close();
    scanner.close();
    input.close();
    output.close();
    reader.close();
    writer.close();
  }
}
