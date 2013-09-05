package game.Entities;

import java.awt.Image;
import java.awt.Toolkit;

import AI.movingFMS.MovingBaseState;
import game.Level;

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
	public boolean canSeePlayer =false;
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
	
	public void seeIfDead(){
		if(health <= 0){
			isDead = true;
		}
	}
	public void loadImages()
	{
		for(int x = 0; x < 12; x++)
		{
			System.out.println("Trying to get image: "+imagePath+x+".png");
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
}
