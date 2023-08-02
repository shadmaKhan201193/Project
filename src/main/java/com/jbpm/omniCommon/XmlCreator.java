package com.jbpm.omniCommon;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class XmlCreator {
	public void createfile(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			 transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		     transformer.setOutputProperty(OutputKeys.INDENT, "yes");	
		     
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("output.xml"));
			transformer.transform(source, result);			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
