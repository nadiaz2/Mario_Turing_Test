package agents.turingMario;

import engine.core.MarioForwardModel;

public class ObstacleNear extends Decision {

	protected ObstacleNear(DecisionTreeNode[] childNodes) {
		super(childNodes);
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] obstacles = model.getScreenSceneObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		int obstacleNear = obstacles[x + 2][y] + obstacles[x + 3][y];

		return obstacleNear > 0 ? childNodes[0] : childNodes[1];
	}
}
