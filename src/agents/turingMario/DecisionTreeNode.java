package agents.turingMario;

import engine.core.MarioForwardModel;

public interface DecisionTreeNode {
	/**
	 * Recursively walk through the tree.
	 * @return next node to step through.
	 */
	DecisionTreeNode makeDecision(MarioForwardModel model);
}
