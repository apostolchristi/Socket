package clientsocket;

import clientsocket.data.ClientSystemInformation;

public class Tests {

  public static void main(String[] args) {

	  ClientSystemInformation input = new ClientSystemInformation();
	  String[] array = input.getUserInfo().split(";");
	  for (int i = 0; i < array.length; i++) {
		  System.out.println(array[i]);
		  //
	  }

	  String[] Parameter = { "user1", "Administrator" };
	  String query = "select * from userinfo where firstname in (";
	  String temp = "";

	  for(int i = 0; i < Parameter.length; i++) {
		  temp += ",?";
	  }

	  temp = temp.replaceFirst(",", "");
	  temp += ")";
	  query = query + temp;
    System.out.println(query);
  }
}
