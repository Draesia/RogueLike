package game;

public class Tile {
	public static final int SIZE = 64;
	public int id = 0;
	private boolean collide;
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
	
}
