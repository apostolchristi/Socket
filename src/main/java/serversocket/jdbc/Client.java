package serversocket.jdbc;

public class Client {


  private int clientId;
  private String hostname;
  private String userName;
  private String internalIP;
  private String processorIdentifier;
  private String osName;
  private String osArchitecture;
  private String rights; /* TODO what kind of rights? */


  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getInternalIP() {
    return internalIP;
  }

  public void setInternalIP(String internalIP) {
    this.internalIP = internalIP;
  }

  public String getProcessorIdentifier() {
    return processorIdentifier;
  }

  public void setProcessorIdentifier(String processorIdentifier) {
    this.processorIdentifier = processorIdentifier;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public String getOsArchitecture() {
    return osArchitecture;
  }

  public void setOsArchitecture(String osArchitecture) {
    this.osArchitecture = osArchitecture;
  }

  public String getRights() {
    return rights;
  }

  public void setRights(String rights) {
    this.rights = rights;
  }

  @Override
  public String toString() {
    return "Client{" +
            "clientId=" + clientId +
            ", hostname='" + hostname + '\'' +
            ", userName='" + userName + '\'' +
            ", internalIP='" + internalIP + '\'' +
            ", processorIdentifier='" + processorIdentifier + '\'' +
            ", osName='" + osName + '\'' +
            ", osArchitecture='" + osArchitecture + '\'' +
            ", rights='" + rights + '\'' +
            '}';
  }
}
