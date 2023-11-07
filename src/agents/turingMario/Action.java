package agents.turingMario;

import engine.core.MarioForwardModel;

public class Action implements DecisionTreeNode {

	@Override
	public DecisionTreeNode makeDecision(MarioForwardModel model) {
		return this;
	}


}
