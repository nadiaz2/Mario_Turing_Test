package agents.turingMario;

import engine.core.MarioForwardModel;

public abstract class Decision implements DecisionTreeNode {

	protected DecisionTreeNode[] childNodes;

	/**
	 * Evaluates this decision and picks a branch to go down.
	 * @return The node to be run after this decision.
	 */
	abstract protected DecisionTreeNode getBranch(MarioForwardModel model);

	@Override
	public DecisionTreeNode makeDecision(MarioForwardModel model) {
		DecisionTreeNode branch = getBranch(model);
		return branch.makeDecision(model);
	}

	protected Decision(DecisionTreeNode[] childNodes) {
		this.childNodes = childNodes;
	}
}
