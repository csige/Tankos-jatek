package parser;

import java.io.File;
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
	
	 
	
	public static void main(String[] args) {
		try{
			Scanner scn = new Scanner(System.in);
			
			String id_, team_, posx_, posy_, orientation_, bposx, bposy, bid;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("tanks");
			
			doc.appendChild(root);
			
			ArrayList<String> bulletContainer = new ArrayList<>();
			
			
			int count = 0, count2=0;
			boolean needBullet = false;
			String userInput = "";
			while(!userInput.equals("q")){
				count++;
				
				Element tank = doc.createElement("tank");
				Element id = doc.createElement("id");
				Element team = doc.createElement("team");
				Element posx = doc.createElement("posx");
				Element posy = doc.createElement("posy");
				Element orientation = doc.createElement("orientation");
				
				id_ = String.valueOf(count);
				
				System.out.println("team:");
				userInput = scn.nextLine();
				team_ = userInput;
				
				System.out.println("posx:");
				userInput = scn.nextLine();
				posx_ = userInput;
				
				System.out.println("posy:");
				userInput = scn.nextLine();
				posy_ = userInput;
				
				System.out.println("orientation:");
				userInput = scn.nextLine();
				orientation_ = userInput;
				
				System.out.println("Ha NE legyen bullet-ja nyomj entert, egyébként y-t aztan entert!");
				userInput = scn.nextLine();
				if(!userInput.equals("")) needBullet = true;
				else needBullet = false;
				
				while(needBullet && !userInput.equals("q")){
					count2++;
					bid = String.valueOf(count2);
					
					System.out.println("Bullet posx:");
					userInput = scn.nextLine();
					bposx = userInput;
					
					System.out.println("Bullet posy:");
					userInput = scn.nextLine();
					bposy = userInput;
					
					bulletContainer.add(bid);  
					bulletContainer.add(bposx);  
					bulletContainer.add(bposy);  
					
					System.out.println("Meg egy bullet felvetelehez nyomj entert, egyebkent 'q'-t!");
					userInput = scn.nextLine();
					
					
				}
				count2=0;
				
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
				
				if(needBullet){
					for(int i = 0;i<bulletContainer.size();i+=3){
						Element bullet = doc.createElement("bullet");
						id = doc.createElement("id");
						id.appendChild(doc.createTextNode(bulletContainer.get(i)));
						
						posx = doc.createElement("posx");
						posx.appendChild(doc.createTextNode(bulletContainer.get(i+1)));
						
						posy = doc.createElement("posy");
						posy.appendChild(doc.createTextNode(bulletContainer.get(i+2)));
						
						bullet.appendChild(id);
						bullet.appendChild(posx);
						bullet.appendChild(posy);
						
						tank.appendChild(bullet);
					}
				}
				
				root.appendChild(tank);
				
				bulletContainer.clear();
			
				
				System.out.println("Meg egy tank felvetelehez nyomj entert, egyebkent 'q'-t!");
				userInput = scn.nextLine();
				
			}
			
			StreamResult result = new StreamResult(new File("D:\\Java projects\\Tankos-jatek\\Tankos_Jatek\\tankos_jatek.xml"));
			saveXML(doc, result);
			
			scn.close();
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	static void saveXML(Document doc, StreamResult result){
		
		try{
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			System.out.println("XML File saved!");
			
		}catch(TransformerException tfe){
			System.out.println("TransformerError!");
			tfe.printStackTrace();
		}
		 
	}
	

}
