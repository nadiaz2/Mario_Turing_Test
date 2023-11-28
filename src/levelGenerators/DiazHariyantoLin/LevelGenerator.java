package levelGenerators.DiazHariyantoLin;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LevelGenerator implements MarioLevelGenerator {

	private final String PATH = "./src/levelGenerators/DiazHariyantoLin/";
	private final Map<String, String> SPECIAL_ENDINGS = Map.ofEntries(
			Map.entry("caveEntrance", "caveFlag"),
			Map.entry("caveMiddleEmpty", "caveFlag"),
			Map.entry("caveMiddleEnemy1", "caveFlag"),
			Map.entry("caveMiddleEnemy2", "caveFlag")
	);
	private Map<String, List<String>> transitionTable;
	private int xOffset;

	@Override
	public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
		loadTransitions();
		xOffset = 0;
		model.clearMap();

		String currentState = "begin";
		while(!currentState.equals("end")) {
			List<String> possibilities = transitionTable.get(currentState);
			int index = (int) (Math.random() * possibilities.size());
			currentState = addState(possibilities.get(index), currentState, model);
		}

		return model.getMap();
	}

	@Override
	public String getGeneratorName() {
		return "DiazHariyantoLinLevelGenerator";
	}

	private void loadTransitions() {
		transitionTable = new HashMap<>();

		Scanner sc;
		try {
			File file = new File(PATH+"transitionWeights.txt");
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("transitionWeights.txt file not found");
			return;
		}

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.length() == 0 || line.charAt(0) == '#') {
				continue;
			}

			String[] dictionaryParts = line.split(" : ", 2);
			String key = dictionaryParts[0];

			List<String> transitionList = new ArrayList<>();

			for (String transitionWeight : dictionaryParts[1].split(" ; ")) {
				String[] transitionPair = transitionWeight.split(",");
				int weight = Integer.parseInt(transitionPair[1]);

				for(int i = 0; i < weight; i++) {
					transitionList.add(transitionPair[0]);
				}
			}

			transitionTable.put(key, transitionList);
		}
	}

	private String addState(String currentState, String previousState, MarioLevelModel model) {
		if(currentState.equals("end")) {
			return currentState;
		}

		Scanner sc = openStateFile(currentState);

		int y = 0;
		String line = sc.nextLine();
		int lineLength = line.length();

		if(model.getWidth() - (xOffset + lineLength) < 16 ) {
			//ran out of room to build level; add flag
			currentState = SPECIAL_ENDINGS.getOrDefault(previousState, "basicFlag");
			sc = openStateFile(currentState);
			line = sc.nextLine();
		}

		do {
			for (int x = 0; x < line.length(); x++) {
				model.setBlock(x + xOffset, y, line.charAt(x));
			}
			y++;

			line = sc.hasNextLine() ? sc.nextLine() : null;
		} while (line != null);

		xOffset += lineLength;
		return currentState;
	}

	private Scanner openStateFile(String stateName) {
		Scanner sc;
		try {
			File file = new File(PATH+"levelChunks/"+stateName+".txt");
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println(stateName+".txt file not found");
			return null;
		}
		return sc;
	}
}
