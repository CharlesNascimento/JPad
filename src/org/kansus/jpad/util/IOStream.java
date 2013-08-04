package org.kansus.jpad.util;

import java.io.*;

public class IOStream {

	public File file;
	public StringBuilder contents = new StringBuilder();

	public void SaveFile(String path, String value) throws IOException {
		file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(value);
		bw.close();
		contents = new StringBuilder();
		contents.append(value);
	}

	public String OpenFile(String path) throws IOException {
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
