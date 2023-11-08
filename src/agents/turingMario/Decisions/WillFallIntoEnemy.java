package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class WillFallIntoEnemy extends Decision {

	public WillFallIntoEnemy(DecisionTreeNode[] childNodes) {
		super(childNodes);
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] enemies = model.getScreenEnemiesObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		boolean willCollide = false;
		if(!model.getMarioCanJumpHigher()) {
			float xVelocity = model.getMarioFloatVelocity()[0];

			int enemyExists;
			if(Math.round(xVelocity) < 1) {
				enemyExists = enemies[x+1][y+1] + enemies[x+1][y+2];
			} else if(Math.round(xVelocity) < 3) {
				enemyExists = enemies[x+2][y+1] + enemies[x+3][y+1]
					+ enemies[x+2][y+2] + enemies[x+3][y+2];
			} else {
				enemyExists = enemies[x+4][y+1] + enemies[x+4][y+2];
			}
			willCollide = enemyExists > 0;
		}

		return willCollide ? childNodes[0] : childNodes[1];
	}
}
