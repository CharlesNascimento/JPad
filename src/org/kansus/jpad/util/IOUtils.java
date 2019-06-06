package org.kansus.jpad.util;

import java.io.*;

/**
 * Class with utility methods to deal with IO operations.
 * 
 * @author Charles Nascimento
 */
public class IOUtils {

	public File file;
	public StringBuilder contents = new StringBuilder();

	/**
	 * Saves a text file at the specified location.
	 * 
	 * @param path The path to the location.
	 * @param value The text.
	 * @throws IOException
	 */
	public void saveFile(String path, String value) throws IOException {
		file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(value);
		bw.close();
		contents = new StringBuilder();
		contents.append(value);
	}

	/**
	 * Loads the content of a text file to the application.
	 * 
	 * @param The path to the location.
	 * @return The text.
	 * @throws IOException
	 */
	public String openFile(String path) throws IOException {
		contents = new StringBuilder();

		file = new File(path);
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = input.readLine()) != null) {
			contents.append(line);
			contents.append("\n");
		}
		if (contents.length() != 0)
			contents.deleteCharAt(contents.length() - 1);
		input.close();

		return contents.toString();
	}
}
