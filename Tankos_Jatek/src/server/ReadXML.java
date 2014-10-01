package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXML extends DefaultHandler {


	int id, teamid, posx, posy, bulletposx, bulletposy;
	Orientation orientation, bulletOrientation;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public static void main(String argv[]) {

		try {

			BufferedReader bf = new BufferedReader(new InputStreamReader(
					System.in));
			String s = "";

			System.out
					.println("[Fájlnév].[kiterjesztés] (a programmal azonos könyvtárban legyen): ");

			String filename = bf.readLine();
			ReadXML handler = new ReadXML();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			saxParser.parse(filename, handler);
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
	private boolean bbulletorientation = false;

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

		else if (qName.equalsIgnoreCase("orientation") && !bbullet) {
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

		else if (qName.equalsIgnoreCase("orientation")) {
			System.out.println("true lett");
			bbulletorientation = true;
		}

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("bullet")) {
			bbullet = false;
			bullets.add(new Bullet(bulletposx, bulletposy, bulletOrientation));
		}

		if (qName.equalsIgnoreCase("tank"))
			btank = false;

	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (btank && !bbullet) {

			if (bid) {
				id = Integer.parseInt(new String(ch, start, length));
				bid = false;
			}

			else if (bteam) {
				teamid = Integer.parseInt(new String(ch, start, length));
				bteam = false;
			}

			else if (bposx) {
				posx = Integer.parseInt(new String(ch, start, length));
				bposx = false;
			}

			else if (bposy) {
				posy = Integer.parseInt(new String(ch, start, length));
				bposy = false;
			}

			else if (borientation) {
				String key = (new String(ch, start, length));
				switch (key) {
				case "E":
					orientation = Orientation.EAST;
					break;
				case "N":
					orientation = Orientation.NORTH;
					break;
				case "S":
					orientation = Orientation.SOUTH;
					break;
				case "W":
					orientation = Orientation.WEST;
					break;
				default:
					break;
				}
				borientation = false;
			}

		}

		if (bbullet) {

			if (bbulletposx) {
				bulletposx = Integer.parseInt(new String(ch, start, length));
				bbulletposx = false;
			}

			else if (bbulletposy) {
				bulletposy = Integer.parseInt(new String(ch, start, length));
				bbulletposy = false;
			}

			else if (bbulletorientation) {
				String key = new String(ch, start, length);
				switch (key) {
				case "E":
					bulletOrientation = Orientation.EAST;
					break;
				case "N":
					bulletOrientation = Orientation.NORTH;
					break;
				case "S":
					bulletOrientation = Orientation.SOUTH;
					break;
				case "W":
					bulletOrientation = Orientation.WEST;
					break;
				default:
					break;
				}
				bbulletorientation = false;
			}

		}

		Tank tank = new Tank(id, teamid, posx, posy, orientation, bullets);
		System.out.println(tank);

	}

}
