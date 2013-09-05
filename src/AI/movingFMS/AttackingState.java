package AI.movingFMS;

import game.Entities.Entity;
import game.Entities.NPC;

public class AttackingState implements MovingBaseState{

	public MovingBaseState getUpdatedState(NPC e){
		if(e.canSeePlayer)	return this;
		return new IdleState();
	}
	
	public void performAction(NPC e) {
		if(e.isInRangeToAttackPlayer) e.attack();
		else 
		e.moveToPlayer();
	}
	
}
