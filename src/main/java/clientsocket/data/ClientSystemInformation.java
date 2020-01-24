package clientsocket.data;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSystemInformation {

  private String hostname;
  private String userName;
  private String internalIP;
  private String processorIdentifier;
  private String osName;
  private String osArchitecture;
  private String rights; /* TODO what kind of rights? */

  public String getUserInfo() {
    String information =
        getHostname()
            + getUser()
            + getInternalIP()
            + getProcessorIdentifier()
            + getOsName()
            + getOsArchitecture()
            + getRights();
    return information;
  }

  public static String getHostname() {
    return "HostName: " + System.getProperty("user.home") + "\n";
  }

  public static String getUser() {
    return "UserName: " + System.getProperty("user.name") + "\n";
  }

  public String getInternalIP() {
    String localIP = "";
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      localIP = localHost.toString();
    } catch (UnknownHostException ex) {
      System.out.println("UnknownHostException error:  -->" + ex);
    }
    return "InternalIP: " + localIP + "\n";
  }

  public String getProcessorIdentifier() {
    return "ProcessorIdentifier: " + System.getenv("PROCESSOR_IDENTIFIER") + "\n";
  }

  public String getOsArchitecture() {
    return "OsArchitecture: " + System.getProperty("os.arch") + "\n";
  }

  public String getOsName() {
    return "OsName: " + System.getProperty("os.name") + "\n";
  }

  public String getRights() {
    return "Rights: " + rights  + "\n";
  }
}
