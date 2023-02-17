package Application;

import Object.Brick;

public class Level {
	private int[][] level1 = {{0, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 0}};
	private int[][] level2 = {{0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0}};
	private int[][] level3 = {{1, 0, 0, 1, 1, 0, 0, 1}, {1, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1, 0, 1}, {1, 0, 0, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1}};
	
//	private int[][] level1 = {{0,0,1,1}};
//	private int[][] level3 = {{0,0,1,1}};
//	private int[][] level2 = {{0,0,1,1}};
	
	private GameScene scene;
	
	public Level(GameScene scene) {
		this.scene = scene;
	}
	
	public void load(int level) {
		if (level == 1) {
			Level_1_Load();
		}
		else if (level == 2) {
			Level_2_Load();
		}
		else if (level == 3) {
			Level_3_Load();
		}
	}

	private void Level_1_Load() {
		int[][] data = level1;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] != 0) {
					createBrick(2.5 + j * 75, 75 + 35 * i);
				}
			}
		}
	}

	private void Level_2_Load() {
		int[][] data = level2;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] != 0) {
					createBrick(2.5 + j * 75, 75 + 35 * i);
				}
			}
		}
	}

	private void Level_3_Load() {
		int[][] data = level3;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] != 0) {
					createBrick(2.5 + j * 75, 75 + 35 * i);
				}
			}
		}
	}

	private void createBrick(double x, double y) {
		Brick brick = null;
		int color = (int)(Math.random() * 5.0);
		switch (color) {
			case 0:
				brick = new Brick(Brick.Type.RED, 1);
				break;
			case 1:
				brick = new Brick(Brick.Type.BLUE, 1);
				break;
			case 2:
				brick = new Brick(Brick.Type.YELLOW, 1);
				break;
			default:
				brick = new Brick(Brick.Type.GENERAL, 1);
				break;
		}
		brick.setLayoutX(x);
		brick.setLayoutY(y);
		this.scene.getBricks().add(brick);
		this.scene.addChildren(brick);
	}
}
