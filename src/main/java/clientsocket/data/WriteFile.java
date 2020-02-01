package clientsocket.data;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static clientsocket.data.ClientSystemInformation.getUserHomeDir;

public class WriteFile {
  private final String FILE_NAME = "test.txt";
  private String userHomeDir;
  private String fileSeparator;

  public WriteFile() {
    userHomeDir = getUserHomeDir();
    fileSeparator = System.getProperty("file.separator");
  }

  public void createFileIntoClientUserHomeDir() {
    try {
      boolean file = new File(userHomeDir, FILE_NAME).createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isAnyFileInClientUserHomeDir() {
    File file = new File(userHomeDir, FILE_NAME);
    return file.exists();
  }

  private void writeDataToClientUserHomeDirFile(String data) {
    File file = new File(userHomeDir, FILE_NAME);
    FileWriter fileWriter = null;

    try {
      fileWriter = new FileWriter(file);
      fileWriter.write(data);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String readDataFromClientUserHomeDirFile() {
    File file = new File(userHomeDir, FILE_NAME);
    String readData = null;
    try {
      readData = new String(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readData;
  }

  /**
   * Breaking down Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1):
   *
   * <p>Adding 0x100 (which is 256 decimal) sets the 9th bit to 1, which guarantees the binary
   * number representation of the result has exactly 9-bits. You could equivalently do & 0x100.
   * After setting bit 8, the result from the toString() will be 9 chars long (of zeroes and ones).
   * substring(1) effectively ignores bit 8 and outputs the lower 8 bits So what?
   *
   * <p>This code puts leading zeroes on values, so all values are exactly 8 binary characters.
   * There's no way to make Integer.toString() alone do this.
   *
   * @param message
   */
  private String generateRandomHashCookie(String message) {
    String generatedHashedCookie = null;
    try {
      // Create MessageDigest instance for MD5
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      // Add password bytes to digest
      messageDigest.update(message.getBytes());
      // Get the hash's bytes
      byte[] bytes = messageDigest.digest();
      // This bytes[] has bytes in decimal format;
      // Convert it to hexadecimal format
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        stringBuilder.append(Integer.toString((bytes[i & 0xff]) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      generatedHashedCookie = stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    System.out.println(generatedHashedCookie);
    return generatedHashedCookie;
  }

  private String generateRandomMessage() {
    String data = "";
    int[] numbers = new int[10];
    for (int i = 0; i < 10; i++) {
      numbers[i] = (int) (Math.random() * 10000 + 1);
      data += String.valueOf(numbers[i]);
    }
    System.out.println(data);
    return data;
  }

  public static void main(String[] args) {

	  WriteFile file = new WriteFile();
	  String hashedCookie = file.generateRandomHashCookie(file.generateRandomMessage());

	  System.out.println(file.isAnyFileInClientUserHomeDir());

	  file.createFileIntoClientUserHomeDir();

    System.out.println(file.isAnyFileInClientUserHomeDir());

    file.writeDataToClientUserHomeDirFile(hashedCookie);
    System.out.println(file.readDataFromClientUserHomeDirFile());

    //
  }
}
