package agents.turingMario;

import engine.helper.MarioActions;

public class Idle extends Action {

	@Override
	public boolean[] getButtonArray() {
		return new boolean[MarioActions.numberOfActions()];
	}
}
