package serverSocket;

import java.io.IOException;

public class ServerMain {

  public static void main(String[] args) throws IOException {

	ThreadedEchoServer server = new ThreadedEchoServer();
	server.start(9122);

  }
}
