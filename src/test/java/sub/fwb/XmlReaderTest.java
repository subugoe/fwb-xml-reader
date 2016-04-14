package sub.fwb;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XmlReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		XmlReader reader = new XmlReader();
		Set<String> elements = new TreeSet<String>();
		InputStream xml = readFile("tei1.xml");
		
		reader.addElementsToSet(xml, elements);
		
		for (String s : elements) {
			System.out.println(s);
		}
	}
	
	private InputStream readFile(String file) throws FileNotFoundException {
		File dir = new File(System.getProperty("user.dir") + "/src/test/resources/files");
		return new FileInputStream(new File(dir, file));
	}

}
