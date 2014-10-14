package server;

public class Bullet {

	@Override
	public String toString() {
		return "Bullet [posx=" + posx + ", posy=" + posy + ", orientation="
				+ orientation + "]";
	}

	public Bullet(int posx, int posy, Orientation orientation) {
		super();
		this.posx = posx;
		this.posy = posy;
		this.orientation = orientation;
	}

	int posx;
	int posy;
	Orientation orientation;

}
