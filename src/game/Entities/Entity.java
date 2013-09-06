package game.Entities;

import java.awt.Image;
import java.awt.Toolkit;

import AI.movingFMS.MovingBaseState;
import game.Frame;
import game.Game;
import game.Level;
import game.Tile;

public abstract class Entity {
	public int maxHealth;
	public int health;
	public int speed;
	public int attackSpeed;
	public int damage;
	public int x,y;
	public Level level;
	public boolean isDead = false;;
	public boolean WillAttack;
	public int img = 0;
	public String imagePath = "/images/entities/player/";
	private Image[] images = new Image[12];
	public int dir = 0;
	
	public void moveDown(){
		y++;
	}
	public void moveUp(){
		y--;
	}
	
	public void moveLeft(){
		x--;
	}
	
	public void moveRight(){
		x++;
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
	
	public void update(){
		
	}
	public Tile getTile()
	{
		return Game.l.getTilesArray()[(x+32)/64][(y+32)/64];
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
	
	public boolean willCollide(int dir)
	{ 
		

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
