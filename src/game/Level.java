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
		for(int x = 0; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				tileList[x][y] = new Tile(0);
				if(y==0 || x==5 || x == 15)
				{
					tileList[x][y].setCollide(true);
					tileList[x][y].id = 3;
				}
				if(y == 5 || y == 7) { tileList[x][y].setCollide(true); tileList[x][y].id = 3;}
				if(y == 6) { tileList[x][y].setCollide(false); tileList[x][y].id = 0;}
				if(x == 7) {tileList[x][y].setCollide(false); tileList[x][y].id = 0;}
			}
		}
		
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
