package server;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML {
	
	 
	
	public static String createXMLString(String[] args) {
		try{
			
			String id_, team_, posx_, posy_, orientation_, bposx, bposy, bid, borientation;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("tanks");
			
			doc.appendChild(root);
			
			ArrayList<String> bulletContainer = new ArrayList<>();
			
			
				Element tank = doc.createElement("tank");
				Element id = doc.createElement("id");
				Element team = doc.createElement("team");
				Element posx = doc.createElement("posx");
				Element posy = doc.createElement("posy");
				Element orientation = doc.createElement("orientation");
				
				id_ = args[0];
				
				team_ = args[1];
				
				posx_ = args[2];
				
				posy_ = args[3];
				
				orientation_ = args[4];
				
					bid = args[5];
					
					bposx = args[6];
					
					bposy = args[7];
					
					borientation = args[8];
					
				//write to xml
				id.appendChild(doc.createTextNode(id_));
				team.appendChild(doc.createTextNode(team_));
				posx.appendChild(doc.createTextNode(posx_));
				posy.appendChild(doc.createTextNode(posy_));
				orientation.appendChild(doc.createTextNode(orientation_));
				
				tank.appendChild(id);
				tank.appendChild(team);
				tank.appendChild(posx);
				tank.appendChild(posy);
				tank.appendChild(orientation);
				
						Element bullet = doc.createElement("bullet");
						id = doc.createElement("id");
						id.appendChild(doc.createTextNode(bid));
						
						posx = doc.createElement("posx");
						posx.appendChild(doc.createTextNode(bposx));
						
						posy = doc.createElement("posy");
						posy.appendChild(doc.createTextNode(bposy));
						
						orientation = doc.createElement("orientation");
						orientation.appendChild(doc.createTextNode(borientation));
						
						bullet.appendChild(id);
						bullet.appendChild(posx);
						bullet.appendChild(posy);
						bullet.appendChild(orientation);
						
						tank.appendChild(bullet);
				
				root.appendChild(tank);
				
			return getStringFromDocument(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//method to convert Document to String
	public static String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	} 
	

}
