package game;

import game.Entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Generation.Dungeon;



public class Level {


	public static int sizeX = 28;
	public static int sizeY = 14;
	public static String s;
	public static int counter = 0;
	
	public static final int minX = 3, minY = 3, maxX = 6, maxY = 12;
	
	public static Tile[][] tileList = new Tile[sizeX][sizeY];
	public static List<Tile> showtileList = new ArrayList<Tile>();
	public Level(int level)
	{
		create();
	}
	public void create()
	{
		Dungeon d = new Dungeon();
		Random r = new Random();
		int LootChancePerFloor = 20;
		tileList = d.getTileMap();
		for(int x = 0; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				if(tileList[x][y].id == 0 && r.nextInt(100) < LootChancePerFloor) 
				{
					tileList[x][y].setItem(new Item(Item.Type.LOOT));
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
		return null;
	}
	public List<Tile> getTiles() {
		return null;
	}

	public Player getPlayer() {
		return new Player(Frame.maxX/2, Frame.maxY/2);
	}
	
	

}
