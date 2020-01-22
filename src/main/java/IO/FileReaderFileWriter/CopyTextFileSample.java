package IO.FileReaderFileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CopyTextFileSample {

	public static List<String> readFile(File source) {
		List<String> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(source)) ) {
			String s;
			while ((s = reader.readLine()) != null) {
				data.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void writeFile(List<String> data, File destination) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
			for (String s: data) {
				writer.write(s);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		File source = new File("Zoo.csv");
		File destination = new File("ZooCopy.csv");
		List<String> data = readFile(source);
		for(String record: data) {
			System.out.println(record);
		}
		writeFile(data,destination);
	}
}
