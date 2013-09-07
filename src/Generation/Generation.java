package Generation;

import game.Level;
import game.Tile;

public class Generation {
	public Tile[][] tileMap = new Tile[Level.sizeX][Level.sizeY];
	public Tile[][] getTileMap()
	{
		return tileMap;
	}
}
