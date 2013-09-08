package game;

import game.Entities.Entity;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {
	public static final int SIZE = 64;

	public int id = 0;
	public int id1 = 0;
	private boolean collide;
	private boolean visible;
	public int x;
	public int y;
	public Item item;
	public boolean everVisible = false;
	private Random r = new Random();
	public Tile(int x, int y, int id)
	{
		this.id1 = r.nextInt(4);
		this.everVisible = true;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y+1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y-1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			t1 = Game.l.getTileAt(t.x+1, t.y+1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y+1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y-1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);
			t1 = Game.l.getTileAt(t.x+1, t.y-1);
			if(t1 != null)
				if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id != t.id && t1.id != 0) room.add(t1);

			room.add(t);
			toCheck.remove(t);
		}
		// Amount of doors = the amount of tiles with 
		return room;
	}

	public List<Tile> getAllAreas()
	{
		List<Tile> room = new ArrayList<Tile>();
		List<Tile> toCheck = new ArrayList<Tile>();
		toCheck.add(this);

		while(toCheck.size() > 0)
		{
			Tile t = toCheck.get(0);
			if(t.x < 1 || t.y < 1 || t.x > Game.l.sizeX-1 || t.y > Game.l.sizeY-1) 
			{
				toCheck.remove(this);
				continue;
			}
			Tile t1 = Game.l.getTileAt(t.x+1, t.y);
			if(t1 != null) if(!room.contains(t1) && !toCheck.contains(t1)) if (!t1.isCollidable()) toCheck.add(t1);
			t1 = Game.l.getTileAt(t.x-1, t.y);
			if(t1 != null) if(!room.contains(t1) && !toCheck.contains(t1)) if (!t1.isCollidable()) toCheck.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y+1);
			if(t1 != null) if(!room.contains(t1) && !toCheck.contains(t1)) if (!t1.isCollidable()) toCheck.add(t1);
			t1 = Game.l.getTileAt(t.x, t.y-1);
			if(t1 != null) if(!room.contains(t1) && !toCheck.contains(t1)) if (!t1.isCollidable()) toCheck.add(t1);
			t1 = Game.l.getTileAt(t.x+1, t.y+1);

			room.add(t);
			toCheck.remove(t);
		}
		// Amount of doors = the amount of tiles with 
		return room;
	}

	public List<Tile> getFullMap()
	{
		List<Tile> map = new ArrayList<Tile>();
		for(int x1 = 0; x < Level.sizeX; x++)
		{
			for(int y1 = 0; y < Level.sizeY; y++)
			{
				map.add(Game.l.getTileAt(x1, y1));
			}
		}
		return map;
	}
	public Image getImage(String s)
	{
		Image i = Game.images.get(s);
		if(i == null)
		{
			Game.images.put(""+s, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tiles/"+s+".png")));
		}
		return i;
	}
	public Image getImage() {
//		switch(id)
//		{
//			case 0:
//				return getImage("p"+id1);
//		}
		return getImage(id+"");
	}

}
