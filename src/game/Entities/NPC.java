package game.Entities;

import AI.movingFMS.IdleState;
import AI.movingFMS.MovingBaseState;

public class NPC extends Entity {
	public boolean WillAttack;
	public boolean canSeePlayer =false;
	public boolean isInRangeToAttackPlayer = false;
	public MovingBaseState movingState;
	public String thoughts;
	
	public void updateState(){
		initializeState();
		movingState =  movingState.getUpdatedState(this);
		movingState.performAction(this);
	}

	public void moveToPlayer(){
		thoughts = "Moving To Player";
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
