package game.Entities;

import game.Frame;
import game.Game;
import game.Item;
import game.Level;
import game.Tile;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {



	public List<Item> itemList = new ArrayList<Item>();
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		Level.showtileList = getTile().getRoom();
		Game.entities.add(this);
	}

	public void update()
	{
		super.update();
		
		if(Game.controls[2]) moveDown();
		if(Game.controls[1]) moveUp();
		if(Game.controls[0]) moveLeft();
		if(Game.controls[3]) moveRight();

		if(Game.controls[6]) { shootDown(); Game.controls[6] = false; }
		if(Game.controls[8]) { shootUp(); Game.controls[8] = false; }
		if(Game.controls[5]) { shootLeft(); Game.controls[5] = false; }
		if(Game.controls[7]) { shootRight(); Game.controls[7] = false; }
		
		if(this.getTile().getItem() != null)
		{
			this.getTile().getItem().onPickup(this);
			this.getTile().setItem(null);
		}
	}

}
