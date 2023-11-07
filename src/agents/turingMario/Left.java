package agents.turingMario;

import engine.core.MarioForwardModel;
import engine.helper.MarioActions;

public class Left extends Action {

	@Override
	public boolean[] getButtonArray(MarioForwardModel model) {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		actionArray[MarioActions.LEFT.getValue()] = true;
		return actionArray;
	}
}
