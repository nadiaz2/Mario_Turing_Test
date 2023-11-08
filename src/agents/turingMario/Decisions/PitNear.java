package agents.turingMario.Decisions;

import agents.turingMario.DecisionTreeNode;
import engine.core.MarioForwardModel;

public class PitNear extends Decision {

	private long lastFrameActive;

	public PitNear(DecisionTreeNode[] childNodes) {
		super(childNodes);
	}

	@Override
	protected DecisionTreeNode getBranch(MarioForwardModel model) {
		int[][] obstacles = model.getScreenSceneObservation(2);
		int[] marioPos = model.getMarioScreenTilePos();
		int x = marioPos[0];
		int y = marioPos[1];

		int pitNear = 0;
		for(int worldY = y+1; worldY < 16; worldY++) {
			pitNear += obstacles[x + 2][worldY];
		}

		boolean shouldJump;
		long frame = model.getRemainingTime();
		if(frame == lastFrameActive-30) {
			shouldJump = pitNear == 0;
		}
		else {
			shouldJump = (pitNear == 0) && (model.isMarioOnGround());
		}

		if(shouldJump) {
			lastFrameActive = frame;
		}

		return shouldJump ? childNodes[0] : childNodes[1];
	}
}
