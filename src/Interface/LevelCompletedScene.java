package Interface;

import Application.BrickBall;
import Application.ChangeState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LevelCompletedScene extends Pane implements FXMLLayoutLoader {
	@FXML
	private Label title;
	@FXML
	private Button nextLevel;
	@FXML
	private Button Exit;
	
	public LevelCompletedScene(boolean isEnd) {
		loadXML(this, "ClearTheStage");
		if (isEnd) {
			title.setText("全數通關");
			nextLevel.setText("再玩一次");
			nextLevel.setOnAction(e -> nextLevelHandler(true));
		}
		else {
			nextLevel.setOnAction(e -> nextLevelHandler(false));
		}
		Exit.setOnAction(e -> exitHandler());
	}
	
	private void nextLevelHandler(boolean isEnd) {
		if (!isEnd) {
			ChangeState.nextLevel();
		}
		else {
			ChangeState.restartFromLevel1();
		}
	}

	private void exitHandler() {
		BrickBall.currentStage.close();
	}
	
}
