package sub.fwb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XmlReader {

	public void addElementsToSet(InputStream xmlFile, Set<String> elements) {
		XMLInputFactory factory = XMLInputFactory.newInstance();

		try {
			XMLEventReader eventReader = factory.createXMLEventReader(xmlFile);

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				switch (event.getEventType()) {

				case XMLStreamConstants.START_ELEMENT:
					StartElement startTag = event.asStartElement(); 
					String elementName = startTag.getName().getLocalPart();
					
					List<String> attributes = new ArrayList<String>();
					String allAttributesString = "";
					
					Iterator<Attribute> iter = startTag.getAttributes();
					while (iter.hasNext()) {
						Attribute attr = iter.next();
						String prefix = attr.getName().getPrefix();
						String localName = attr.getName().getLocalPart();
						if (prefix == null || "".equals(prefix)) {
							attributes.add(localName);
						} else {
							attributes.add(prefix + ":" + localName);
						}
					}
					Collections.sort(attributes);
					
					for (String a : attributes) {
						allAttributesString += " " + a;
					}
					
					elements.add("<" + elementName + ">" + allAttributesString);
					
					break;
				default:
					// ignore all the other events
				}
			}

			eventReader.close();

		} catch (XMLStreamException e) {
			throw new IllegalArgumentException("Error reading XML", e);
		}

	}
}
