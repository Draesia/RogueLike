package Generation;

import game.Game;
import game.Level;
import game.Tile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Dungeon extends Generation {
	// max size of the map
	private int xmax = Level.sizeX; // 80 columns
	private int ymax = Level.sizeY; // 25 rows

	// size of the map
	private int xsize = xmax;
	private int ysize = ymax;

	// number of "objects" to generate on the map
	private int objects = 0;

	// define the %chance to generate either a room or a corridor on the map
	// BTW, rooms are 1st priority so actually it's enough to just define the
	// chance
	// of generating a room
	private int chanceRoom = 90;
	// our map
	private int[] dungeon_map = {};

	// the old seed from the RNG is saved in this one
	private long oldseed = 0;

	// a list over tile types we're using
	final private int tileUnused = 0;
	final private int tileWall = 1; // not in use
	final private int tileFloor = 2;
	final private int tileStoneWall = 3;
	final private int tileCorridor = 4;
	final private int tileDoor = 5;
	final private int tileUpStairs = 6;
	final private int tileDownStairs = 7;
	final private int tileChest = 8;



	// setting a tile's type
	private void setCell(int x, int y, int celltype) {
		dungeon_map[x + xsize * y] = celltype;
	}

	// returns the type of a tile
	private int getCell(int x, int y) {
		return dungeon_map[x + xsize * y];
	}
	private Tile getTile(int x, int y) {
		int i = 1;
		switch (getCell(x, y)) {
			case tileUnused:
			case tileFloor:
			case tileCorridor:
				i=0;
				break;
			case tileDoor: i = 2; break;
			case tileStoneWall: break;
			case tileWall: break;
		};
		return new Tile(x, y, i);
	}

	private int getRand(int min, int max) {

		Date now = new Date();
		long seed = now.getTime() + oldseed;
		oldseed = seed;

		Random randomizer = new Random(seed);
		int n = max - min + 1;
		int i = randomizer.nextInt(n);
		if (i < 0)
			i = -i;
		return min + i;
	}
	public static int getDoors(Tile tile)
	{
		List<Tile> toCheck = new ArrayList<Tile>();
		List<Tile> room = new ArrayList<Tile>();
		toCheck.add(tile);
		int doors = 0;
		while(toCheck.size() > 0)
		{
			Tile t = toCheck.get(0);

			Tile t1 = Game.l.getTileAt(t.x+1, t.y);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			if(t1.id == 2) doors++;
			t1 = Game.l.getTileAt(t.x-1, t.y);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			if(t1.id == 2) doors++;
			t1 = Game.l.getTileAt(t.x, t.y+1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			if(t1.id == 2) doors++;
			t1 = Game.l.getTileAt(t.x, t.y-1);
			if(!room.contains(t1) && !toCheck.contains(t1)) if (t1.id == t.id || t1.id == 0) toCheck.add(t1); else room.add(t1);
			if(t1.id == 2) doors++;

			room.add(t);
			toCheck.remove(t);
		}
		// Amount of doors = the amount of tiles with 
		System.out.println("Doors ="+doors);
		return doors;
	}
	private boolean makeCorridor(int x, int y, int lenght, int direction) {
		// define the dimensions of the corridor (er.. only the width and
		// height..)
		int len = getRand(2, lenght);
		int floor = tileCorridor;
		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		int xtemp = 0;
		int ytemp = 0;

		switch (dir) {
			case 0:
				// north
				// check if there's enough space for the corridor
				// start with checking it's not out of the boundaries
				if (x < 0 || x > xsize)
					return false;
				else
					xtemp = x;

				// same thing here, to make sure it's not out of the boundaries
				for (ytemp = y; ytemp > (y - len); ytemp--) {
					if (ytemp < 0 || ytemp > ysize)
						return false; // oh boho, it was!
					if (getCell(xtemp, ytemp) != tileUnused)
						return false;
				}

				// if we're still here, let's start building
				for (ytemp = y; ytemp > (y - len); ytemp--) {
					setCell(xtemp, ytemp, floor);
				}
				break;
			case 1:
				// east
				if (y < 0 || y > ysize)
					return false;
				else
					ytemp = y;

				for (xtemp = x; xtemp < (x + len); xtemp++) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != tileUnused)
						return false;
				}

				for (xtemp = x; xtemp < (x + len); xtemp++) {
					setCell(xtemp, ytemp, floor);
				}
				break;
			case 2:
				// south
				if (x < 0 || x > xsize)
					return false;
				else
					xtemp = x;

				for (ytemp = y; ytemp < (y + len); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					if (getCell(xtemp, ytemp) != tileUnused)
						return false;
				}

				for (ytemp = y; ytemp < (y + len); ytemp++) {
					setCell(xtemp, ytemp, floor);
				}
				break;
			case 3:
				// west
				if (ytemp < 0 || ytemp > ysize)
					return false;
				else
					ytemp = y;

				for (xtemp = x; xtemp > (x - len); xtemp--) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != tileUnused)
						return false;
				}

				for (xtemp = x; xtemp > (x - len); xtemp--) {
					setCell(xtemp, ytemp, floor);
				}
				break;
		}

		// woot, we're still here! let's tell the other guys we're done!!
		return true;
	}

	private boolean makeRoom(int x, int y, int xlength, int ylength, int direction) {
		// define the dimensions of the room, it should be at least 4x4 tiles
		// (2x2 for walking on, the rest is walls)
		int xlen = getRand(4, xlength);
		int ylen = getRand(4, ylength);
		// the tile type it's going to be filled with
		int floor = tileFloor; // jordgolv..
		int wall = tileWall; // jordv????gg
		// choose the way it's pointing at
		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		switch (dir) {
			case 0:
				// north
				// Check if there's enough space left for it
				for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false; // no space left...
					}
				}

				// we're still here, build
				for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						// start with the walls
						if (xtemp == (x - xlen / 2))
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + (xlen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == y)
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen + 1))
							setCell(xtemp, ytemp, wall);
						// and then fill with the floor
						else
							setCell(xtemp, ytemp, floor);
					}
				}
				break;
			case 1:
				// east
				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					for (int xtemp = x; xtemp < (x + xlen); xtemp++) {

						if (xtemp == x)
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + xlen - 1))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + (ylen - 1) / 2))
							setCell(xtemp, ytemp, wall);

						else
							setCell(xtemp, ytemp, floor);
					}
				}
				break;
			case 2:
				// south
				for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {

						if (xtemp == (x - xlen / 2))
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + (xlen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == y)
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + ylen - 1))
							setCell(xtemp, ytemp, wall);

						else
							setCell(xtemp, ytemp, floor);
					}
				}
				break;
			case 3:
				// west
				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					for (int xtemp = x; xtemp > (x - xlen); xtemp--) {

						if (xtemp == x)
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x - xlen + 1))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + (ylen - 1) / 2))
							setCell(xtemp, ytemp, wall);

						else
							setCell(xtemp, ytemp, floor);
					}
				}
				break;
		}
		System.out.println("Y:"+y+" X:"+x+" YLength:"+ylen+" XLength:"+xlen);
		setCell(x,y, tileStoneWall);
		// yay, all done
		return true;
	}

	// used to print the map on the screen
	public void showDungeon() {
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				tileMap [x][y] = getTile(x, y);
				switch (getCell(x, y)) {
					case tileUnused:
						System.out.print(" ");
						break;
					case tileWall:
						System.out.print("#");
						break;
					case tileFloor:
						System.out.print("F");
						break;
					case tileStoneWall:
						System.out.print("O");
						break;
					case tileCorridor:
						System.out.print("C");
						break;
					case tileDoor:
						System.out.print("D");
						break;
					case tileChest:
						System.out.print("@");
						break;
				};
			}
			if (xsize <= xmax)
				System.out.println();
		}
	}






	// and here's the one generating the whole map
	public boolean createDungeon() {

		objects = 70;


		// redefine the map var, so it's adjusted to our new map size
		dungeon_map = new int[xsize * ysize];

		// start with making the "standard stuff" on the map
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				// ie, making the borders of unwalkable walls
				if (y == 0)
					setCell(x, y, tileStoneWall);
				else if (y == ysize - 1)
					setCell(x, y, tileStoneWall);
				else if (x == 0)
					setCell(x, y, tileStoneWall);
				else if (x == xsize - 1)
					setCell(x, y, tileStoneWall);

				else
					setCell(x, y, tileUnused);
			}
		}

		makeRoom(xsize / 2, ysize / 2, 8, 6, getRand(0, 3));
		int currentFeatures = 1; 

		for (int countingTries = 0; countingTries < 1000; countingTries++) {
			// check if we've reached our quota
			if (currentFeatures == objects) {
				break;
			}

			// start with a random wall

			int newx = 0;
			int xmod = 0;
			int newy = 0;
			int ymod = 0;
			int validTile = -1;

			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 1);
				validTile = -1;
				// System.out.println("tempx: " + newx + "\ttempy: " + newy);
				if (getCell(newx, newy) == tileWall || getCell(newx, newy) == tileCorridor) {
					// check if we can reach the place
					if (getCell(newx, newy + 1) == tileFloor || getCell(newx, newy + 1) == tileCorridor) {
						validTile = 0; //
						xmod = 0;
						ymod = -1;
					} else if (getCell(newx - 1, newy) == tileFloor || getCell(newx - 1, newy) == tileCorridor) {
						validTile = 1; //
						xmod = +1;
						ymod = 0;
					} else if (getCell(newx, newy - 1) == tileFloor || getCell(newx, newy - 1) == tileCorridor) {
						validTile = 2; //
						xmod = 0;
						ymod = +1;
					} else if (getCell(newx + 1, newy) == tileFloor || getCell(newx + 1, newy) == tileCorridor) {
						validTile = 3; //
						xmod = -1;
						ymod = 0;
					}

					if (validTile > -1) {
						if (getCell(newx, newy + 1) == tileDoor) // north
							validTile = -1;
						else if (getCell(newx - 1, newy) == tileDoor)// east
							validTile = -1;
						else if (getCell(newx, newy - 1) == tileDoor)// south
							validTile = -1;
						else if (getCell(newx + 1, newy) == tileDoor)// west
							validTile = -1;
					}

					// if we can, jump out of the loop and continue with the
					// rest
					if (validTile > -1)
						break;
				}
			}
			if (validTile > -1) {
				// choose what to build now at our newly found place, and at
				// what direction
				int feature = getRand(0, 100);
				if (feature <= chanceRoom) { // a new room
					if (makeRoom((newx + xmod), (newy + ymod), 8, 6, validTile)) {
						currentFeatures++; // add to our quota

						// then we mark the wall opening with a door
						setCell(newx, newy, tileDoor);

						// clean up infront of the door so we can reach it
						setCell((newx + xmod), (newy + ymod), tileFloor);
					}
				} else if (feature >= chanceRoom) { // new corridor
					if (makeCorridor((newx + xmod), (newy + ymod), 6, validTile)) {
						// same thing here, add to the quota and a door
						currentFeatures++;

						setCell(newx, newy, tileDoor);
					}
				}
			}
		}

		int newx = 0;
		int newy = 0;
		int ways = 0; // from how many directions we can reach the random spot
		// from
		int state = 0; // the state the loop is in, start with the stairs
		while (state != 10) {
			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 2); // 

				// System.out.println("x: " + newx + "\ty: " + newy);
				ways = 4; // the lower the better

				// check if we can reach the spot
				if (getCell(newx, newy + 1) == tileFloor || getCell(newx, newy + 1) == tileCorridor) {
					// north
					if (getCell(newx, newy + 1) != tileDoor)
						ways--;
				}
				if (getCell(newx - 1, newy) == tileFloor || getCell(newx - 1, newy) == tileCorridor) {
					// east
					if (getCell(newx - 1, newy) != tileDoor)
						ways--;
				}
				if (getCell(newx, newy - 1) == tileFloor || getCell(newx, newy - 1) == tileCorridor) {
					// south
					if (getCell(newx, newy - 1) != tileDoor)
						ways--;
				}
				if (getCell(newx + 1, newy) == tileFloor || getCell(newx + 1, newy) == tileCorridor) {
					// west
					if (getCell(newx + 1, newy) != tileDoor)
						ways--;
				}

				if (state == 0) {
					if (ways == 0) {

						setCell(newx, newy, tileChest);
						state = 1;
						break;
					}
				} else if (state == 1) {
					if (ways == 0) {

						setCell(newx, newy, tileChest);
						state = 10;
						break;
					}
				}
			}
		}

		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				// ie, making the borders of unwalkable walls
				if (getCell(x, y) == tileDoor)
				{
					int i = 0;
					if(getCell(x, y-1) == tileWall) i++;
					if(getCell(x, y+1) == tileWall) i++;
					if(getCell(x-1, y) == tileWall) i++;
					if(getCell(x+1, y) == tileWall) i++;
					if(i != 2) setCell(x, y, tileFloor);
				}
			}
		}
		
		return true;
	}


	public Dungeon() {
		boolean made = false;
		while(!made) {
			if (createDungeon()) {
				made = true;
				showDungeon();
			}
		}
	}
}
