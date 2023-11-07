package agents.turingMario;

import engine.core.MarioForwardModel;

public abstract class Action implements DecisionTreeNode {

	/**
	 * Creates an array of controller inputs for this action.
	 * @return Array of controller inputs for this action.
	 */
	public abstract boolean[] getButtonArray();

	@Override
	public DecisionTreeNode makeDecision(MarioForwardModel model) {
		return this;
	}
}
