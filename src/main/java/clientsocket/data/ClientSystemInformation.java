package clientsocket.data;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSystemInformation {

   public static final String DELIMITER = ";";

  private String userHomeDir;
  private String userName;
  private String internalIP;
  private String processorIdentifier;
  private String osName;
  private String osArchitecture;
  private String rights; /* TODO what kind of rights? */

  public String getAndBuildClientSystemInformation() {
    String information =
              getUserHomeDir() + DELIMITER
            + getUserName() + DELIMITER
            + getInternalIP() + DELIMITER
            + getProcessorIdentifier() + DELIMITER
            + getOsName() + DELIMITER
            + getOsArchitecture() + DELIMITER
            + getRights() + DELIMITER;
    return information;
  }

  public static String getUserHomeDir() {
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
