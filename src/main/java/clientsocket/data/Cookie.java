package clientsocket.data;

import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static clientsocket.data.ClientSystemInformation.getUserHomeDir;

public class Cookie {
  private final String FILE_NAME = ".config";
  private String userHomeDir = getUserHomeDir();;

  public Object execute() {
    String cookie;
    if (exist()) {
      cookie = read();
      return cookie;
    } else {
      create();
      String hashedCookie = generateHash(randomData());
      write(hashedCookie);
      cookie = read();
      return cookie;
    }
  }

  private boolean create() {
    boolean file;
    try {
      file = new File(userHomeDir, FILE_NAME).createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return file;
  }

  private boolean exist() {
    return new File(userHomeDir, FILE_NAME).exists();
  }

  private void write(String data) {
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

  private String read() {
    File file = new File(userHomeDir, FILE_NAME);
    String readData = null;
    try {
      readData = new String(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readData;
  }

  private String generateHash(String message) {
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
        // see the explanation
        // https://stackoverflow.com/questions/36491665/byte-to-integer-and-then-to-string-conversion-in-java
        stringBuilder.append(Integer.toString((bytes[i & 0xff]) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      generatedHashedCookie = stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedHashedCookie;
  }

  private String randomData() {
    String data = "";
    int[] numbers = new int[10];
    for (int i = 0; i < 10; i++) {
      numbers[i] = (int) (Math.random() * 10000 + 1);
      data += String.valueOf(numbers[i]);
    }
    return data;
  }
}
