package Scanner;

import java.util.Scanner;

public class TestScanner {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    System.out.println("Enter an SMTHing");
    String id = input.nextLine();
    while (id != null) {
      System.out.println("Invalid ID. Please input a valid ID: " + id);
      id = input.nextLine();
    }
}
}
