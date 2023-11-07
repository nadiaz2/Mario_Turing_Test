package agents.turingMario;

import engine.core.MarioForwardModel;

public class EnemyNear extends Decision {

	protected EnemyNear(DecisionTreeNode[] childNodes) {
		super(childNodes);
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] enemies = model.getScreenEnemiesObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		int enemyNear = enemies[x+1][y] + enemies[x+2][y]
				+ enemies[x+1][y-1] + enemies[x+2][y-1]
				+ enemies[x+1][y-2] + enemies[x+2][y-2];

		return enemyNear > 0 ? childNodes[0] : childNodes[1];
	}
}
