package clientsocket.data;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSystemInformation {

  private char delimiter = ';';

  private String hostname;
  private String userName;
  private String internalIP;
  private String processorIdentifier;
  private String osName;
  private String osArchitecture;
  private String rights; /* TODO what kind of rights? */

  public String getUserInfo() {
    String information =
              getHostname() + delimiter
            + getUserName() + delimiter
            + getInternalIP() + delimiter
            + getProcessorIdentifier() + delimiter
            + getOsName() + delimiter
            + getOsArchitecture() + delimiter
            + getRights() +delimiter;
    return information;
  }

  public static String getHostname() {
    return System.getProperty("user.home");
  }

  public static String getUserName() {
    return  System.getProperty("user.name");
  }

  public String getInternalIP() {
    String localIP = "";
    try {
      InetAddress localHost = InetAddress.getLocalHost();

      localIP = localHost.toString();
    } catch (UnknownHostException ex) {
      System.out.println("UnknownHostException error:  -->" + ex);
    }
    return  localIP;
  }

  public String getProcessorIdentifier() {
    return System.getenv("PROCESSOR_IDENTIFIER");
  }

  public String getOsArchitecture() {
    return  System.getProperty("os.arch");
  }

  public String getOsName() {
    return  System.getProperty("os.name");
  }

  public String getRights() {
    return  rights;
  }
}
