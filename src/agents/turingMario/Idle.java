package agents.turingMario;

import engine.core.MarioForwardModel;
import engine.helper.MarioActions;

public class Idle extends Action {

	@Override
	public boolean[] getButtonArray(MarioForwardModel model) {
		return new boolean[MarioActions.numberOfActions()];
	}
}
