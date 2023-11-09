package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class ObstacleTooClose extends Decision {

	private int leftTicks;
	private int lastActiveTick;
	private DecisionTreeNode lastDecision;

	public ObstacleTooClose(DecisionTreeNode[] childNodes) {
		super(childNodes);
		leftTicks = 0;
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] obstacles = model.getScreenSceneObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		if(!model.isMarioOnGround()) {
			if(lastActiveTick != model.getRemainingTime()+30) {
				lastActiveTick = model.getRemainingTime();
				lastDecision = childNodes[2];
				return childNodes[2];
			} else {
				lastActiveTick = model.getRemainingTime();
				return lastDecision;
			}
		}

		int obstacleGroundNear = obstacles[x + 1][y];
		int obstacleAboveNear = obstacles[x + 1][y-1];

		boolean movingLeft = false;
		if(obstacleGroundNear > 0) {
			if(obstacleAboveNear > 0) {
				movingLeft = true;
			}
		}


		if(lastActiveTick != model.getRemainingTime()+30) {
			leftTicks = 0;
		}


		boolean decision;
		if(leftTicks > 0) {
			decision = true;
		} else {
			decision = movingLeft;
			if(movingLeft) {
				leftTicks = 9;
			}
		}

		leftTicks = Math.max(0, --leftTicks);

		lastActiveTick = model.getRemainingTime();
		lastDecision = decision ? childNodes[0] : childNodes[1];

		return lastDecision;
	}
}
