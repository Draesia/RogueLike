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
		Dungeon d = new Dungeon();
		tileList = d.getTileMap();
	}
	public void recreate()
	{
		Dungeon d = new Dungeon();
		tileList = d.getTileMap();
	}
	public Tile[][] getTilesArray() {
		return tileList;
	}
	public Tile getTileAt(int x, int y)
	{
		return tileList[x][y];
	}
	public List<Tile> getTiles() {
		return null;
	}

	public Player getPlayer() {
		return new Player(Frame.maxX/2, Frame.maxY/2);
	}
	
	

}
