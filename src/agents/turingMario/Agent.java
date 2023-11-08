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
				new PitNear(new DecisionTreeNode[] {
						new Jump(),
						new EnemyNear(new DecisionTreeNode[] {
								new Jump(), new Idle()
						})
				})
		});

		horizontalDecisionTree = new WillFallIntoEnemy(new DecisionTreeNode[] {
				new Idle(),
				new ObstacleTooClose(new DecisionTreeNode[]{
						new Left(), new Right()
				})
		});
	}

	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {

		Action verticalAction;
		Action horizontalAction;
		try {
			verticalAction = (Action) verticalDecisionTree.makeDecision(model);
			horizontalAction = (Action) horizontalDecisionTree.makeDecision(model);
		} catch(Exception e) {
			return new boolean[MarioActions.numberOfActions()];
		}

		boolean[] verticalActionArray = verticalAction.getButtonArray(model);
		boolean[] horizontalActionArray = horizontalAction.getButtonArray(model);

		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];
		for(int i = 0; i < actionArray.length; i++) {
			actionArray[i] = verticalActionArray[i] || horizontalActionArray[i];
		}

		//System.out.println(Arrays.toString(actionArray));
		//System.out.println(Arrays.toString(model.getMarioFloatVelocity()));
		//System.out.println(model.getRemainingTime());
		System.out.println(model.getMarioScreenTilePos()[0] + " " + model.getMarioFloatPos()[0]);

		return actionArray;
	}

	@Override
	public String getAgentName() {
		return "TuringMario";
	}
}
