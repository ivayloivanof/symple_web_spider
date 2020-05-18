package web_spider.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TextReader {
	private final static Logger LOG = Logger.getLogger(TextReader.class.getName());

	public static List<String> read(String path) {

		ArrayList<String> list = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(path));
			while (scanner.hasNext()){
			    list.add(scanner.next());
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			LOG.info(e.getMessage());
		}
		
		return list;
	}
}
