package game.Entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import game.Frame;
import game.Game;
import game.Level;
import game.Tile;

public abstract class Entity {
	
	public int maxHealth;
	public int health = 10;
	public int speed;
	public int attackSpeed;
	public int bulletSpeed = 3;
	public int cooldown = 0;
	public int damage = 3;
	public Tile t;
	public int check = 0;
	public int x,y;
	public Level level;
	public boolean isDead = false;
	public boolean WillAttack;
	public boolean canSeePlayer = false;
	public int img = 0;
	public String imagePath = "/images/entities/player/";
	private Image[] images = new Image[12];
	public int dir = 0;
	public Rectangle rectangle;
	
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
	public void shootDown()
	{
		dir = 2;
		shoot();
	}
	public void shootUp(){
		dir = 0;
		shoot();
	}
	
	public void shootLeft(){
		dir = 3;
		shoot();
	}
	public void shootRight(){
		dir = 1;
		shoot();
	}	
	
	public void shoot(){
		System.out.println("Shooting");
		if(cooldown == 0)
		{
			System.out.println("Shot");
			Game.entities.add(new Bullet(dir, x+24, y+24, this));
			cooldown = 100;
		}
	}

	public void  Initialize(){
		health = maxHealth;
	}

	public void attack()
	{
		
	}
	
	public void takeDamage(int damage){
		health -= damage;
		seeIfDead();
	}
	
	public void update()
	{
	}
	public Rectangle getRectangle()
	{
		if(rectangle == null)
		{
			rectangle = new Rectangle(x, y, getImage().getHeight(null), getImage().getWidth(null));
		}
		rectangle.setLocation(x, y);
		return rectangle;
	}
	public Tile getTile()
	{
		int cx = (x+32)/64;
		int cy = (y+32)/64;
		if(cx < Level.sizeX && cy < Level.sizeY && cx > 0 && cy > 0)
			return Game.l.getTilesArray()[cx][cy];
		return null;
	}
	public void seeIfDead(){
		if(health <= 0){
			isDead = true;
		}
	}
	public void loadImages()
	{
		for(int x = 0; x < 12; x++)
		{
			if(getClass().getResource(imagePath+x+".png") != null) {
				
				images[x] = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath+x+".png"));
			}
		}
	}
	public boolean onScreen()
	{
		return Level.showtileList.contains(getTile());
	}
	public Image getRenderImage()
	{
		if(Level.showtileList.contains(getTile())) return getImage();
		return null;
	}
	public Image getImage()
	{
		if (images[0] == null) { loadImages(); };
		switch(dir)
		{
			case 0: if(img < 9 || img > 11) img = 9; break;
			case 1: if(img < 6 || img > 8) img = 6; break;
			case 3: if(img < 3 || img > 5) img = 3; break;
			case 2: if(img < 0 || img > 2) img = 0; break;
		}
		return images[img];
		
	}
}
