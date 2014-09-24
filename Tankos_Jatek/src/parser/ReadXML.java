package parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXML extends DefaultHandler {

	public static void main(String argv[]) {

		try {

			BufferedReader bf = new BufferedReader(new InputStreamReader(
					System.in));
			String s = "";

			System.out
					.println("[Fájlnév].[kiterjesztés] (a programmal azonos könyvtárban legyen): ");
			
			tank= new Tank();
			String filename = bf.readLine();
			ReadXML handler = new ReadXML();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			saxParser.parse(filename, handler);
			System.out.println(handler.tank.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean btank = false;
	private boolean bid = false;
	private boolean bteam = false;
	private boolean bposx = false;
	private boolean bposy = false;
	private boolean borientation = false;
	private boolean bbullet = false;
	private boolean bbulletid = false;
	private boolean bbulletposx = false;
	private boolean bbulletposy = false;

	

	static class Tank {

		@Override
		public String toString() {
			return "Tank [id=" + id + ", team=" + team + ", posx=" + posx
					+ ", posy=" + posy + ", orientation=" + orientation
					+ ", bulletid=" + bulletid + ", bulletposx=" + bulletposx
					+ ", bulletposy=" + bulletposy + "]";
		}
		String id;
		String team;
		String posx;
		String posy;
		String orientation;
		String bulletid;
		String bulletposx;
		String bulletposy;
	}
	
	public static Tank tank;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("tank")) {
			btank = true;
		}

		else if (qName.equalsIgnoreCase("id") && !bbullet) {
			bid = true;
		}

		else if (qName.equalsIgnoreCase("team")) {
			bteam = true;
		}

		else if (qName.equalsIgnoreCase("posx") && !bbullet) {
			bposx = true;
		}

		else if (qName.equalsIgnoreCase("posy") && !bbullet) {
			bposy = true;
		}

		else if (qName.equalsIgnoreCase("orientation")) {
			borientation = true;
		}

		else if (qName.equalsIgnoreCase("bullet")) {
			bbullet = true;
		}

		else if (qName.equalsIgnoreCase("id")) {
			bbulletid = true;
		}

		else if (qName.equalsIgnoreCase("posx")) {
			bbulletposx = true;
		}

		else if (qName.equalsIgnoreCase("posy")) {
			bbulletposy = true;
		}

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("bullet"))
			bbullet = false;

		if (qName.equalsIgnoreCase("tank"))
			btank = false;

	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (btank && !bbullet) {

			if (bid) {
				tank.id = new String(ch, start, length);
				bid = false;
			}

			if (bteam) {
				tank.team = new String(ch, start, length);
				bteam = false;
			}

			if (bposx) {
				tank.posx = new String(ch, start, length);
				bposx = false;
			}

			if (bposy) {
				tank.posy = new String(ch, start, length);
				bposy = false;
			}

			if (borientation) {
				tank.orientation = new String(ch, start, length);
				borientation = false;
			}

		}

		if (bbullet) {
			if (bbulletid) {
				tank.bulletid = new String(ch, start, length);
				bbulletid = false;
			}

			if (bbulletposx) {
				tank.bulletposx = new String(ch, start, length);
				bbulletposx = false;
			}

			if (bbulletposy) {
				tank.bulletposy = new String(ch, start, length);
				bbulletposy = false;
			}
		}

	}

}
