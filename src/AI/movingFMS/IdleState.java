package AI.movingFMS;

import game.Entities.NPC;

public class IdleState implements MovingBaseState{


	public MovingBaseState getUpdatedState(NPC e) {
		if(false){
			return new AttackingState();
		}
		return this;
	}


	public void performAction(NPC e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
