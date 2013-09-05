package game;

public class Tile {
	public static final int SIZE = 64;
	public int id = 0;
	private boolean collide;
	private boolean visible;
	public Tile(int id)
	{
		this.id = id;
	}
	
	public boolean isCollidable() {
		return collide;
	}
	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
