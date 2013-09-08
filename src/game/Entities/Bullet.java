package game.Entities;

import game.Frame;
import game.Game;
import game.Level;
import game.Tile;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bullet extends Entity {

	public static final Image bullet = Toolkit.getDefaultToolkit().getImage(Toolkit.getDefaultToolkit().getClass().getResource("/images/entities/bullet.png"));

	public Entity e;
	public Bullet(int dir, int x, int y, Entity e)
	{
		this.e = e;
		this.dir = dir;
		this.x = x;
		this.y = y;
		isDead = false;
	}
	public Image getImage()
	{
		return bullet;
	}
	public Tile getTile()
	{
		int cx = x/64;
		int cy = y/64;
		if(cx < Level.sizeX && cy < Level.sizeY && cx > 0 && cy > 0)
			return Game.l.getTilesArray()[cx][cy];
		return null;
	}
	public void update()
	{
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


		if(x < 0 || y < 0 || x > Frame.maxX() || y > Frame.maxY) { kill(); } 
		if(this.getTile() != null)
		{
			if(this.getTile().isCollidable()) kill();
		}
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Entity en = Game.entities.get(i);
			//System.out.println("e: "+e.getClass().getCanonicalName()+" : en"+en.getClass().getCanonicalName());
			if(en.onScreen() && !en.equals(e) && !(en instanceof Bullet))
			{
				Rectangle r = this.getRectangle();
				Rectangle m = en.getRectangle();
				if(r != null && m != null)
					if(m.intersects(r)) {
						System.out.println("Hit: "+en.getClass().getCanonicalName());
						en.takeDamage(e.damage);
						kill();
					}
				//return;
			}
		}
	}
}
