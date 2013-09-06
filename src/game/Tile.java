package game;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	public static final int SIZE = 64;
	public int id = 0;
	private boolean collide;
	private boolean visible;
	public int x;
	public int y;

	public Tile(int x, int y, int id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.collide = shouldCollide(id);
	}

	public Tile(int id, boolean collide)
	{
		this.id = id;
		this.collide = collide;
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

	public static boolean shouldCollide(int i)
	{
		return (i!=0 && i!=2);
	}
	public List<Tile> getRoom()
	{
		List<Tile> room = new ArrayList<Tile>();
		List<Tile> toCheck = new ArrayList<Tile>();
		toCheck.add(this);
		while(toCheck.size() > 0)
		{
			Tile t = toCheck.get(0);

			Tile t1 = Game.l.getTileAt(t.x+1, t.y);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y+1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y-1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			//Check for Diagonal Walls
			t1 = Game.l.getTileAt(t.x+1, t.y+1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y+1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y-1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x+1, t.y-1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			
			room.add(t);
			toCheck.remove(t);
		}
		return room;
	}
}
