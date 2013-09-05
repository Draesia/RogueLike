package game.Entities;

import game.Level;

public abstract class Entity {
	public int maxHealth;
	public int health;
	public int speed;
	public int attackSpeed;
	public int damage;
	public int x,y;
	public Level level;
	
	public void moveDown(){
		y++;
	}
	public void moveUp(){
		y--;
	}
	
	public void moveLeft(){
		x++;
	}
	
	public void moveRight(){
		x--;
	}
	
	public void  Initialize(){
		health = maxHealth;
	}

	public void attack(int direction)
	{
		
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	
}
