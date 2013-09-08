package game.Entities;

import game.Game;
import game.Tile;


public class NPC extends Entity {

	public Entity target = null;
	public int speedMod = 0;
	public int speed = 5;
	public boolean startled = false;
	public void NPC()
	{
		target = Game.p;
		this.dir = (Game.entities.size()+1)%12;
		System.out.println("DIR: "+dir);
	}
	public void takeDamage(int damage)
	{
		super.takeDamage(damage);
		this.startled = true;
	}
	public void update()
	{
		super.update();
		if(target != null && target.onScreen())
		{
			Tile myTile = getTile();
			
			Tile tTile = target.getTile();
			int myX = x;
			int myY = y;
			int tX =  target.x;
			int tY =  target.y;
			int dX = tX - myX;
			int dY = tY - myY;
			double distance = Math.sqrt(dX*dX + dY*dY);
			if(distance > 3*64 && !startled) return;
			if(speedMod < speed)
			{
				speedMod++;
			}
			else
			{
				speedMod = 0;
				if(dX < 0) moveLeft();
				if(dX > 0) moveRight();
				if(dY < 0) moveUp();
				if(dY > 0) moveDown();
			}
			if(myX == tX)
			{
				if(myY < tY) shootDown();
				if(myY > tY) shootUp();
			}
			if(myY == tY)
			{
				if(myX < tX) shootRight();
				if(myX > tX) shootLeft();
			}
		}
	}

}
