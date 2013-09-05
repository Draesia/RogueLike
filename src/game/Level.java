package game;

import java.util.List;


public class Level {


	public static int sizeX = 100;
	public static int sizeY = 100;
	public static String s;
	public static int counter = 0;
	public static Tile[][] tileList = new Tile[sizeX][sizeY];

	public Level(int[][][] map)
	{
		for(int x = 0; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				tileList[x][y] = new Tile();
			}
		}
	}

	public Tile[][] getTilesArray() {
		return tileList;
	}

	public List<Tile> getTiles() {
		return null;
	}

}
