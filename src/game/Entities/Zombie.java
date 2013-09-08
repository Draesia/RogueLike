package game.Entities;

import game.Game;


public class Zombie extends NPC{
	
	public Zombie()
	{
		super();
		imagePath = "/images/entities/hooded/";
		target = Game.p;
		
		this.dir = (Game.entities.size()+1)%4*3;
		System.out.println("DIR: "+dir);
	}

}
