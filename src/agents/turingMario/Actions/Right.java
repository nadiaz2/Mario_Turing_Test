package agents.turingMario.Actions;

import engine.core.MarioForwardModel;
import engine.helper.MarioActions;

public class Right extends Action {

	@Override
	public boolean[] getButtonArray(MarioForwardModel model) {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		actionArray[MarioActions.RIGHT.getValue()] = true;
		return actionArray;
	}
}
