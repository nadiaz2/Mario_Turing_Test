package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class ObstacleTooClose extends Decision {

	private int leftTicks;

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

		int obstacleGroundNear = obstacles[x + 1][y];
		int obstacleAboveNear = obstacles[x + 1][y-1];

		boolean movingLeft = false;
		if((obstacleGroundNear > 0) && model.isMarioOnGround()) {
			if((obstacleAboveNear > 0) || (model.getMarioFloatPos()[0] % 1 > 0.7)) {
				movingLeft = true;
			}
		}

		if(movingLeft) {
			leftTicks = 9;
		} else if(leftTicks > 0){
			leftTicks--;
		}

		return movingLeft || leftTicks > 0 ? childNodes[0] : childNodes[1];
	}
}
