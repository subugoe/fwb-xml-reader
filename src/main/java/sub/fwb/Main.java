package sub.fwb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.output.FileWriterWithEncoding;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Syntax: java -jar get-elements.jar <input-dir> <output-file>");
		} else {
			File inputDir = new File(args[0]);
			File outputFile = new File(args[1]);
			
			ArrayList<File> allFiles = new ArrayList<File>();
			fillWithFiles(allFiles, inputDir);
			
			XmlReader xmlReader = new XmlReader();
			Set<String> elements = new TreeSet<String>();

			for (File currentFile : allFiles) {
				xmlReader.addElementsToSet(new FileInputStream(currentFile), elements);
			}
			
			FileWriterWithEncoding fw = new FileWriterWithEncoding(outputFile, "UTF-8");
			for (String elem : elements) {
				fw.write(elem + "\n");
			}
			
			fw.close();
		}


	}

	private static void fillWithFiles(ArrayList<File> allFiles, File currentDir) {
		File[] currentDirChildren = currentDir.listFiles();
		for (File child : currentDirChildren) {
			if (child.isFile() && child.getName().endsWith(".xml")) {
				allFiles.add(child);
			} else if (child.isDirectory()) {
				fillWithFiles(allFiles, child);
			}
		}

	}

}
