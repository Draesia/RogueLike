package AI.movingFMS;

import game.Entities.NPC;

public interface MovingBaseState {

	public MovingBaseState getUpdatedState(NPC e);
	public void performAction(NPC e);
}
