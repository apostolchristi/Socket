package serversocket;

import java.io.IOException;

public class ServerMain {

  public static void main(String[] args) throws IOException {

	ThreadedServer server = new ThreadedServer();
	server.start(9122);
  }
}
