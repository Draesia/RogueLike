package game;

import game.Entities.Player;
import game.Entities.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Generation.Dungeon;



public class Level {


	public static int sizeX = 28;
	public static int sizeY = 14;
	public static String s;
	public static int counter = 0;
	public static int startX = 0;
	public static int startY = 0;
	public static final int minX = 3, minY = 3, maxX = 6, maxY = 12;

	public static Tile[][] tileList = new Tile[sizeX][sizeY];
	public static List<Tile> showtileList = new ArrayList<Tile>();
	
	public Level(int level)
	{
	}

	public void create()
	{
		Dungeon d = new Dungeon();
		tileList = d.getTileMap();
		Random r = new Random();
		int LootChancePerFloor = 20;

		int x = Frame.maxX/2/64;
		int y = Frame.maxY/2/64;
		System.out.println("Trying!");
		Tile t = getTileAt(x+1, y);
		if(!t.isCollidable()) { startX = x+1; startY = y; }
		t = getTileAt(x-1, y);
		if(!t.isCollidable()) { startX = x-1; startY = y; }
		t = getTileAt(x, y+1);
		if(!t.isCollidable()) { startX = x; startY = y+1; }
		t = getTileAt(x, y-1);
		if(!t.isCollidable()) { startX = x; startY = y-1; }
		
		for(int x1 = 0; x1 < sizeX; x1++)
		{
			for(int y1 = 0; y1 < sizeY; y1++)
			{
				if(tileList[x1][y1].id == 0)
				{
					System.out.println("FLOOR");
					if(r.nextInt(100) < LootChancePerFloor)
					{
						System.out.println("Added LOOT");
						tileList[x1][y1].setItem(new Item(Item.Type.LOOT));
					}
				}
				if(x1 == startX && y1 == startY)
				{
					List<Tile> WalkableTiles = tileList[x1][y1].getAllAreas();
					for(Tile t1 : WalkableTiles)
					{
						t1.everVisible = true;
					}
				}
			}
		}
	}
	public Tile[][] getTilesArray() {
		return tileList;
	}
	public Tile getTileAt(int x, int y)
	{		
		if(x < Level.sizeX && y < Level.sizeY && x > -1  && y > -1)
			return tileList[x][y];
		System.out.println("NULLIFY");
		return null;
	}
	public List<Tile> getTiles() {
		return null;
	}
	public void addMonsters()
	{
		for(int x = 0; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				Random r = new Random();
				int MonsterSpawnChance = 10;
				if(r.nextInt(100) < MonsterSpawnChance && tileList[x][y].id == 0)
				{
					Zombie z = new Zombie();
					z.x = x*64;
					z.y = y*64;
					if(!z.onScreen() && getTileAt(x, y).everVisible)
						Game.entities.add(z);

				}
			}
		}
	}
	public Player getPlayer() {
		if(startX * startY != 0) return new Player(startX*64, startY*64);
		System.out.println("Nope!");
		return new Player(Frame.maxX/2, Frame.maxY/2);
	}



}
