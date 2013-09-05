package game;

import game.Entities.Player;

import java.util.List;


public class Level {


	public static int sizeX = 32;
	public static int sizeY = 32;
	public static String s;
	public static int counter = 0;
	public static Tile[][] tileList = new Tile[sizeX][sizeY];

	public Level(int level)
	{
		
	}

	public Tile[][] getTilesArray() {
		return tileList;
	}

	public List<Tile> getTiles() {
		return null;
	}

	public Player getPlayer() {
		return new Player(Frame.maxX/2, Frame.maxY/2);
	}

}
