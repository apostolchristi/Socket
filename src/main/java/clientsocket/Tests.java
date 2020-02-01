package clientsocket;

import clientsocket.data.ClientSystemInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static clientsocket.data.ClientSystemInformation.DELIMITER;
import static clientsocket.data.ClientSystemInformation.getUserHomeDir;

public class Tests {

  public static void main(String[] args) throws IOException {



  }

  public static void splitClientSystemInformationIntoSmallChunks(
      ClientSystemInformation clientSystemInformation) {
    String[] splitClientSystemInformation = clientSystemInformation.getAndBuildClientSystemInformation().split(DELIMITER);
    for (int i = 0; i < splitClientSystemInformation.length; i++) {
      System.out.println(splitClientSystemInformation[i]);
    }
  }

  public static void someQueryExample() {
	  String[] Parameter = {"user1", "Administrator"};
	  String query = "select * from userinfo where firstname in (";
	  String temp = "";

	  for (int i = 0; i < Parameter.length; i++) {
		  temp += ",?";
	  }

	  temp = temp.replaceFirst(",", "");
	  temp += ")";
	  query = query + temp;
	  System.out.println(query);
  }


}
