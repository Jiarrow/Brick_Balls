package Application;

import Interface.LevelCompletedScene;
import Interface.LoseGameScene;
import javafx.scene.Scene;

public class ChangeState {
	
	public static void Level_Completed(boolean isEnd) {
		LevelCompletedScene scene = new LevelCompletedScene(isEnd);
		BrickBall.currentStage.setScene(new Scene(scene));
	}
	
	public static void nextLevel() {
		BrickBall.game.enterNextLevel();
		BrickBall.currentStage.setScene(BrickBall.gameScene);
	}

	public static void LoseGame() {
		LoseGameScene scene = new LoseGameScene();
		BrickBall.currentStage.setScene(new Scene(scene));
	}
	
	public static void restart() {
		BrickBall.game.restartGame();
		BrickBall.currentStage.setScene(BrickBall.gameScene);
	}

	public static void restartFromLevel1() {
		BrickBall.game = new GameScene(BrickBall.WIDTH, BrickBall.HEIGHT);
		BrickBall.gameScene = new Scene(BrickBall.game, BrickBall.WIDTH, BrickBall.HEIGHT);
		BrickBall.currentStage.setScene(BrickBall.gameScene);
	}
}
