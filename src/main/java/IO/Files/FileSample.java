package IO.Files;

import java.io.File;
import java.io.IOException;

public class FileSample {

  public static void main(String[] args) throws IOException {

    File file =
        new File("C:\\Users\\capostol\\IdeaProjects\\Socket\\src\\main\\resources\\sample.txt");
    System.out.println(file.getAbsolutePath());
    System.out.println(file.getCanonicalPath());
    System.out.println(file.getParent());
  	//
  }
}
