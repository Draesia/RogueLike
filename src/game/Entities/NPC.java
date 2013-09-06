package game.Entities;

import game.Game;
import AI.movingFMS.IdleState;
import AI.movingFMS.MovingBaseState;

public class NPC extends Entity {
	public boolean WillAttack;
	public boolean isInRangeToAttackPlayer = false;
	public MovingBaseState movingState;
	public String thoughts;
	public int VisibleLookingRange=5;
	public boolean movingTowardsPlayer;
	public int speed = 2;
	public void updateState(){
		initializeState();
		movingState =  movingState.getUpdatedState(this);
		movingState.performAction(this);
	}
	
	public void update(){
		//System.out.println(canSeePlayer());
		if(isInRange(VisibleLookingRange) && !isInRange(1)){
			moveToPlayer();
		}
	}

	public boolean isInRange(int range){
		double distance =  Math.sqrt(Math.pow(this.x - Game.p.x,2) + Math.pow(this.y - Game.p.y,2));
		if(distance < range*64) return true;
		return false;
	}
	
	public void moveToPlayer(){
		thoughts = "Moving To Player";
		
		double diffX = Game.p.x - this.x;
		double diffY =  Game.p.y - this.y;
		
		double hyp = Math.sqrt(diffX*diffX + diffY*diffY);
		diffX /= hyp;
		diffY /= hyp;
		
		int dir=0;
		
		if(diffX > 0) dir = 1;
		else dir = 3;		
		
		if(!willCollide(dir))this.x += diffX*speed;
		
	
		
		
		if(diffY > 0) dir = 2;
		else dir = 0;
		if(!willCollide(dir)){
			this.y += diffY*speed;
		}
		
	}
	
	public void attack(){
		thoughts = "Attacking";
	}
	
	private void initializeState() {
		if(movingState == null){
			movingState = new IdleState();
		}
	}
	

}
