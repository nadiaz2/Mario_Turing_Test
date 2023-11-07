package agents.turingMario;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

import java.util.Arrays;
import java.util.Random;

public class Agent implements MarioAgent {
	private long startTime;
	private double waitDuration;

	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {
		double minWait = 0.0;
		double maxWait = 3.0;
		Random generator = new Random(System.currentTimeMillis());
		waitDuration = minWait + generator.nextDouble() * (maxWait - minWait);

		startTime = model.getRemainingTime();
	}

	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
		boolean[] actionArray = new boolean[MarioActions.numberOfActions()];

		//System.out.println(Arrays.deepToString(model.getMarioCompleteObservation()));
		int[][] observation = model.getMarioCompleteObservation();
		//observation[x][y] y increases downwards

		int[] marioPos = model.getMarioScreenTilePos();
		int marioX = marioPos[0];
		int marioY = marioPos[1];

		actionArray[MarioActions.RIGHT.getValue()] =
				(startTime - model.getRemainingTime()) >= waitDuration*1000;

		return actionArray;
	}

	@Override
	public String getAgentName() {
		return "TuringMario";
	}
}
