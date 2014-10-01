package server;

import java.util.ArrayList;

public class Tank {

	int id;
	int teamid;
	int posx;
	int posy;
	Orientation orientation;
	@Override
	public String toString() {
		return "Tank [id=" + id + ", teamid=" + teamid + ", posx=" + posx
				+ ", posy=" + posy + ", orientation=" + orientation
				+ ", bullets=" + bullets + "]";
	}

	ArrayList<Bullet> bullets;

	public Tank(int id, int teamid, int posx, int posy,
			Orientation orientation, ArrayList<Bullet> bullets) {
		super();
		this.id = id;
		this.teamid = teamid;
		this.posx = posx;
		this.posy = posy;
		this.orientation = orientation;
		this.bullets = bullets;
	}

	public Tank() {
		super();
	}
	
	

}
