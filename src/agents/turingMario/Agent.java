package agents.turingMario;

import agents.turingMario.Actions.*;
import agents.turingMario.Decisions.*;
import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

import java.util.Arrays;

public class Agent implements MarioAgent {
	private DecisionTreeNode verticalDecisionTree;
	private DecisionTreeNode horizontalDecisionTree;

	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {
		verticalDecisionTree = new ObstacleNear(new DecisionTreeNode[] {
				new Jump(),
				new EnemyNear(new DecisionTreeNode[] {
						new Jump(), new Idle()
				})
		});

		horizontalDecisionTree = new MakingProgress(
				new DecisionTreeNode[] {
						new Left(), new Right()
				}
		);
	}

	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {

		Action verticalAction = (Action) verticalDecisionTree.makeDecision(model);
		boolean[] verticalActionArray = verticalAction.getButtonArray(model);

		Action horizontalAction = (Action) horizontalDecisionTree.makeDecision(model);
		boolean[] horizontalActionArray = horizontalAction.getButtonArray(model);

		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		for(int i = 0; i < actionArray.length; i++) {
			actionArray[i] = verticalActionArray[i] || horizontalActionArray[i];
		}

		System.out.println(Arrays.toString(actionArray));

		return actionArray;
	}

	@Override
	public String getAgentName() {
		return "TuringMario";
	}
}
