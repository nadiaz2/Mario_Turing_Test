package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class RandomDecision extends Decision {
	private final int numBranches;
	private long lastFrame;
	private DecisionTreeNode currentDecision;

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		long frame = model.getRemainingTime();

		// Check if stored decision is too old
		if(frame > lastFrame + 1) {
			// Make new decision and store it
			currentDecision = childNodes[(int) (numBranches * Math.random())];
		}

		// Either way we need to store when we were last called
		lastFrame = frame;

		return currentDecision;
	}

	public RandomDecision(DecisionTreeNode[] childNodes) {
		super(childNodes);
		this.numBranches = childNodes.length;
	}
}
