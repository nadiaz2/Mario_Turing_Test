package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class ObstacleNear extends Decision {

	public ObstacleNear(DecisionTreeNode[] childNodes) {
		super(childNodes);
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] obstacles = model.getScreenCompleteObservation(2, 2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		int obstacleNear = obstacles[x][y] + obstacles[x + 1][y] + obstacles[x + 2][y];

		return (obstacleNear > 0) ? childNodes[0] : childNodes[1];
	}
}
