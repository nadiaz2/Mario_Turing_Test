package agents.turingMario.Actions;

import engine.core.MarioForwardModel;
import engine.helper.MarioActions;

public class Jump extends Action {

	@Override
	public boolean[] getButtonArray(MarioForwardModel model) {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		actionArray[MarioActions.JUMP.getValue()] = true;//model.mayMarioJump() || model.getMarioCanJumpHigher();
		return actionArray;
	}
}
