package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class ObstacleTooClose extends Decision {

	int leftTicks;

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

		int obstacleNear = obstacles[x + 1][y] + obstacles[x + 2][y];
		boolean movingLeft = (obstacleNear > 0) && model.isMarioOnGround();

		if(movingLeft) {
			leftTicks = 8;
		} else if(leftTicks > 0){
			leftTicks--;
		}

		return movingLeft || leftTicks > 0 ? childNodes[0] : childNodes[1];
	}
}
