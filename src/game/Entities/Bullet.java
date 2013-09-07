package game.Entities;

import game.Frame;
import game.Game;
import game.Level;
import game.Tile;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends Entity {
	
	public static final Image bullet = Toolkit.getDefaultToolkit().getImage(Toolkit.getDefaultToolkit().getClass().getResource("/images/entities/bullet.png"));
	
	public Entity e;
	public Bullet(int dir, int x, int y, Entity e)
	{
		this.e = e;
		this.dir = dir;
		this.x = x;
		this.y = y;
		
	}
	public Image getImage()
	{
		return bullet;
	}
	public Tile getTile()
	{
		int cx = (x+32)/64;
		int cy = (y+32)/64;
		if(cx < Level.sizeX && cy < Level.sizeY && cx > 0 && cy > 0)
			return Game.l.getTilesArray()[cx][cy];
		return null;
	}
	public void update()
	{
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Entity e = Game.entities.get(i);
			if(e.onScreen() && e.getClass().isInstance(this.e.getClass()))
			{
				if(e.getRectangle().intersects(getRectangle()))
				{
					System.out.println("Deleted me!");
					e.takeDamage(e.damage);
					Game.entities.remove(this);
					isDead = true;
					return;
				}
			}
		}
		switch(dir)
		{
			case 0:
				y -= e.bulletSpeed;
			break;
			case 1:
				x += e.bulletSpeed;
			break;
			case 2:
				y += e.bulletSpeed;
			break;
			case 3:
				x -= e.bulletSpeed;
			break;
		}
		
		
		if(x < 0 || y < 0 || x > Frame.maxX() || y > Frame.maxY) { Game.entities.remove(this); isDead = true;} 
	}
}
