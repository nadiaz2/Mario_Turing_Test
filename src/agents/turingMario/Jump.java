package agents.turingMario;

import engine.helper.MarioActions;

public class Jump extends Action {

	@Override
	public boolean[] getButtonArray() {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		actionArray[MarioActions.JUMP.getValue()] = true;
		return actionArray;
	}
}
