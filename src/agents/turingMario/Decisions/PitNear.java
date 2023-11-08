package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class PitNear extends Decision {

	private long lastFrameActive;
	private int jumpTicks;

	public PitNear(DecisionTreeNode[] childNodes) {
		super(childNodes);
		jumpTicks = 0;
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] obstacles = model.getScreenSceneObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		int nearestNonHole = 0;
		for(int xOffset = 1; xOffset < 5; xOffset++) {
			if(obstacles[x + xOffset][y + 1] != 0) {
				nearestNonHole = xOffset;
				break;
			}
		}

		boolean shouldJump;
		long frame = model.getRemainingTime();
		if(frame == lastFrameActive-30) {
			shouldJump = (nearestNonHole > 1);
		}
		else {
			shouldJump = (nearestNonHole > 1) && (model.isMarioOnGround());
		}

		if(shouldJump) {
			lastFrameActive = frame;
			jumpTicks = nearestNonHole - 1;
		} else if(jumpTicks > 0) {
			jumpTicks--;
		}

		return shouldJump || (jumpTicks > 0) ? childNodes[0] : childNodes[1];
	}
}
