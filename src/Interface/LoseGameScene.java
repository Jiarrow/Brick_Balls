package Interface;

import Application.BrickBall;
import Application.ChangeState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class LoseGameScene extends Pane implements FXMLLayoutLoader {
	@FXML
	Button restart;
	@FXML
	Button Exit;
	
	public LoseGameScene() {
		loadXML(this, "LoseTheGame");
		restart.setOnAction(e -> restartHandler());
		Exit.setOnAction(e -> exitHandler());
	}

	private void restartHandler() {
		ChangeState.restart();
	}

	private void exitHandler() {
		BrickBall.currentStage.close();
	}
}
