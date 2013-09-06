package game.Entities;

import game.Frame;
import game.Game;
import game.Level;
import game.Tile;

import java.awt.Image;

public class Player extends Entity {

	
	public Tile t;
	public int check = 0;
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
	
	public boolean willCollide(int dir){
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
		
		return super.willCollide(dir);
	}

}
