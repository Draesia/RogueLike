package game.Entities;

import game.Frame;
import game.Game;
import game.Level;
import game.Tile;

import java.awt.Image;

public class Player extends Entity {

	public int check = 0;
	public Tile t;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		Game.l.showtileList = getTile().getRoom();
	}


	public void moveDown(){
		dir = 2;
		if(willCollide(dir)) return;
		y++;
	}
	public void moveUp(){
		dir = 0;
		if(willCollide(dir)) return;
		y--;
	}

	public void moveLeft(){
		dir = 3;
		if(willCollide(dir)) return;
		x--;
	}

	public void moveRight(){
		dir = 1;
		if(willCollide(dir)) return;
		x++;
	}
	public boolean willCollide(int dir)
	{ 
		
		if(check > 50) {
			img++;
			if(t != null && t.id != this.getTile().id)
			{
				t = this.getTile();
				Game.l.showtileList = t.getRoom();
			} else { t = this.getTile(); }
			check = 0;
		}
		check++;
		boolean collide = false;
		Tile[][] tl = Level.tileList;
		Tile t = null;
		if(dir == 0)
		{
			if(y-1 <= 0) return true;
			t = tl[(x+1)/64][(y-1)/64];
			if(t.isCollidable()) collide = true;
			t = tl[(x-1)/64+1][(y-1)/64];
			if(t.isCollidable()) collide = true;
		}
		if(dir == 1)
		{
			if(x+65 >= Frame.maxX) { Game.nextLevel(); return true; } 
			
			t = tl[(x+65)/64][(y+1)/64];
			if(t.isCollidable()) collide = true;
			t = tl[(x+65)/64][(y-1)/64+1];
			if(t.isCollidable()) collide = true;
			
		}
		if(dir == 2)
		{
			if(y+65 >= Frame.maxY) return true;
			t = tl[(x+1)/64][(y+65)/64];
			if(t.isCollidable()) collide = true;
			t = tl[(x-1)/64+1][(y+65)/64];
			if(t.isCollidable()) collide = true;
		}
		if(dir == 3)
		{
			if(x-1 <= 0) return true;
			t = tl[(x-1)/64][(y+1)/64];
			if(t.isCollidable()) collide = true;
			t = tl[(x-1)/64][(y-1)/64+1];
			if(t.isCollidable()) collide = true;
		}	
		return collide;
	}

}
