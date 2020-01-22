package IO.IOStream;

import java.io.*;

/**
 *
 *
 * <h1>Instead of reading the data one byte at a time, we use the underlying read(byte[]) method of
 * @BufferedInputStream, which returns the number of bytes read into the provided byte array. The
 * number of bytes read is important for two reasons.</h1>
 *
 * @First, if the value returned is 0, then we know that we have reached the end of the file and can
 * stop reading from the BufferedInputStream.
 * @Second, the last read of the file will likely only partially fill the byte array, since it is unlikely
 * for the file size to be an exact multiple of our buffer array size.
 */
public class CopyBufferFileSample {

	public static void copy(File source, File destination) throws IOException {
		try (InputStream in = new BufferedInputStream(new FileInputStream(source));
			 OutputStream out = new BufferedOutputStream(new FileOutputStream(destination)) ) {
			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer,0,lengthRead);
				out.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
