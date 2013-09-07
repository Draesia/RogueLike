package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import game.Entities.Entity;
import game.Entities.Player;

public class Item {

	public enum Type {
		LOOT, PASSIVE, WEAPON
	}
	
	private Random r;
	private Type type = Type.LOOT;
	public Image i = null;

	public Item(Type e)
	{
		this.type = e;
		this.r = new Random();
		setImage();
	}
	
	public void onPickup(Player pl)
	{
		if(type == Type.LOOT) { Game.score =+ r.nextInt(10); return;}
	}
	
	public void doPassive(Player pl)
	{
		
	}
	
	public void doAttack(Player pl, Entity e)
	{
		
	}
	
	public void onAttack(Entity e, Player pl)
	{
		
	}

	public Image getImage()
	{
		return i;
	}

	public void setImage()
	{
		if(getClass().getResource("/images/items/loot1.png") != null) {
			
			i = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/items/loot1.png"));
		}
	}
	
	

}
