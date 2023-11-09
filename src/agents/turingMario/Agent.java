package agents.turingMario;

import agents.turingMario.Actions.*;
import agents.turingMario.Decisions.*;
import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
	private DecisionTreeNode newTree;

	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {
		newTree = new ObstacleNear(new DecisionTreeNode[]{
				new ObstacleTooClose(new DecisionTreeNode[]{
						new Left(), new RightJump(), new Idle()
				}),
				new PitNear(new DecisionTreeNode[]{
						new RightJump(),
						new EnemyNear(new DecisionTreeNode[] {
								new RightJump(),
								new WillFallIntoEnemy(new DecisionTreeNode[]{
										new Left(), new Right()
								})
						})
				})
		});
	}

	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {

		Action action;
		try {
			action = (Action) newTree.makeDecision(model);
		} catch(Exception e) {
			System.out.println("Error");
			boolean[] errorControls = new boolean[MarioActions.numberOfActions()];
			errorControls[MarioActions.RIGHT.getValue()] = true;
			return errorControls;
		}

		return action.getButtonArray(model);
	}

	@Override
	public String getAgentName() {
		return "TuringMario";
	}
}
