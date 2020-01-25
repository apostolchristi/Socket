package serversocket.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
  static final String DB_NAME = "clients_system_info";
  static final String HOSTNAME = "localhost";
  static final String PORT = "3306";
  static final String USER = "root";
  static final String PASSWORD = "";
  static final String CONNECTION_STRING =
      "jdbc:mysql://"
          + HOSTNAME
          + ":"
          + PORT
          + "/"
          + DB_NAME
          + "?createDatabaseIfNotExist=true&serverTimezone=UTC";
	static final String CREATE_TABLE_clients =
			"CREATE TABLE IF NOT EXISTS clients ("
					+ "client_id INT NOT NULL AUTO_INCREMENT, "
					+ "host_name VARCHAR(255) NOT NULL, "
					+ "user_name VARCHAR(255) NOT NULL, "
					+ "internal_ip VARCHAR(255) NOT NULL, "
					+ "processor_id VARCHAR(255) NOT NULL, "
					+ "os_name VARCHAR(255) NOT NULL, "
					+ "os_architecture VARCHAR(255) NOT NULL, "
					+ "rights VARCHAR(255) NOT NULL, "
					+ "PRIMARY KEY (client_id)"
					+ ")";

  static final String TABLE_CLIENTS = "clients";
  static final String CLIENT_ID = "client_id";
  static final String CLIENT_HOST_NAME = "host_name";
  static final String CLIENT_USER_NAME = "user_name";
  static final String CLIENT_INTERNAL_IP = "internal_ip";
  static final String CLIENT_PROCESSOR_ID = "processor_id";
  static final String CLIENT_OS_NAME = "os_name";
  static final String CLIENT_OS_ARCHITECTURE = "os_architecture";
  static final String CLIENT_RIGHTS = "rights";

  private Connection connection;

  public boolean open() {
    try {
      connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
      return true;
    } catch (SQLException e) {
      System.out.println("Couldn't connect to DB: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  public List<Client> queryClients() {
    Statement statement = null;
    ResultSet results = null;
    try {
      statement = connection.createStatement();
      results = statement.executeQuery("SELECT * FROM " + TABLE_CLIENTS);
      List<Client> clients = new ArrayList<>();
      while (results.next()) {
        Client client = new Client();
        client.setClientId(results.getInt(CLIENT_ID));
        client.setHostname(results.getString(CLIENT_HOST_NAME));
        client.setUserName(results.getString(CLIENT_USER_NAME));
        client.setInternalIP(results.getString(CLIENT_INTERNAL_IP));
        client.setProcessorIdentifier(results.getString(CLIENT_PROCESSOR_ID));
        client.setOsName(results.getString(CLIENT_OS_NAME));
        client.setOsArchitecture(results.getString(CLIENT_OS_ARCHITECTURE));
        client.setRights(results.getString(CLIENT_RIGHTS));
        clients.add(client);
      }
      return clients;

    } catch (SQLException e) {
      System.out.println("Query failed: " + e.getMessage());
      e.printStackTrace();
      return null;
    } finally {
      try {
        if (results != null) results.close();
      } catch (SQLException e) {
        System.out.println("Error closing Results: " + e.getMessage());
        e.printStackTrace();
      }
      try {
        if (statement != null) statement.close();
      } catch (SQLException e) {
        System.out.println("Error closing Statement: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public void close() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println("Couldn't close connection: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }



  public static void main(String[] args) {

    DataSource dataSource = new DataSource();
    if (!dataSource.open()) {
      System.out.println("Can't open datasource");
      return; // stops the execution of the method right there
    }
    List<Client> clients = dataSource.queryClients();
    if(clients == null) {
      System.out.println("No Clients");
      return;
	}
    for (Client client : clients) {
      System.out.println(client);
	}

    //      statement.execute(CREATE_TABLE_clients);

    //      statement.executeUpdate(
    //          "INSERT INTO test (name, phone, email) VALUES ('test', 9038, 'test@test.test')");

  }
}
