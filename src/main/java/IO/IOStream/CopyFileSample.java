package IO.IOStream;

import java.io.*;

/**
 * @FileInputStream and @FileOutputStream. They are used to read bytes from a file or write bytes to a
 * file, respectively. These classes include constructors that take a File object or String,
 * representing a path to the file. The data in a FileInputStream object is commonly accessed by
 * successive calls to the read() method until a value of -1 is returned, indicating that the end of
 * the stream—in this case the end of the file—has been reached.
 */
public class CopyFileSample {
  /**
   * The copy() method creates instances of FileInputStream and FileOutputStream, and it proceeds to
   * read the FileInputStream one byte at a time, copying the value to the FileOutputStream as it’s
   * read. As soon as the in.read() returns a -1 value, the loop ends. Finally, both streams are
   * closed using the try-with-resource syntax
   *
   * @param source
   * @param destination
   */
  public static void copy(File source, File destination) {
		try (InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination)) {
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

  public static void main(String[] args) {
    /**
     * If the destination fi le already exists, it will be overridden by this code. Both File
     * objects are created using relative paths, so the application would search for the Zoo.class
     * in the current directory to read from, throwing a FileNotFoundException if the fi le is not
     * found, which is a subclass of an IOException .
     */
    File source = new File("Zoo.class");
	  	File destination = new File("ZooCopy.class");
	  	copy(source, destination);


  }
}
