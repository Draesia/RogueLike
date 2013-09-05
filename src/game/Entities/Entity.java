package game.Entities;

import game.Level;

public abstract class Entity {
	
	public int health;
	public int speed;
	public int attackspeed;
	public int damage;
	public int x,y;
	public Level level;
	
	abstract void moveDown();
	abstract void moveUp();
	abstract void moveLeft();
	abstract void moveRight();
	

	public void move(int direction)
	{
		switch(direction)
		{
			case 0: moveUp(); break;
			case 1: moveRight(); break;
			case 2: moveDown(); break;
			case 3: moveLeft(); break;
		}
	}
	public void attack(int direction)
	{
		
	}
}
