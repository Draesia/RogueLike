package game.Entities;

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
}
