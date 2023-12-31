package agents.turingMario;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {

	}

	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		actionArray[MarioActions.RIGHT.getValue()] = true;
		return actionArray;
	}

	@Override
	public String getAgentName() {
		return "TuringMario";
	}
}
