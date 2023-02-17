package Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MenuHandler implements EventHandler<KeyEvent> {
	@FXML
	TextField name;
	
	@FXML
	Button Go;
	
	@FXML
	Button Exit;
	
	// Go Button 的控制函式
	public void GoHandler() {
		if (validName(name.getText())) {
			// 載入遊戲介面
			BrickBall.game = new GameScene(BrickBall.WIDTH, BrickBall.HEIGHT);
			BrickBall.gameScene = new Scene(BrickBall.game, BrickBall.WIDTH, BrickBall.HEIGHT);
			BrickBall.currentStage.setScene(BrickBall.gameScene);
		}
		else {
			System.out.println("Invalid name");
		}
	}

	// Exit Button 的控制函式
	public void ExitHandler() {
		BrickBall.currentStage.close();
	}
	
	// 判斷名字是否有效
	public boolean validName(String name1) {
		if (name1.length() == 0) {
			return false;
		}
		
		String name_sub = "";
		for (int i = 0; i < name1.length(); i++) {
			if (name1.charAt(i) != ' ') {
				name_sub = name1.substring(i);
				break;
			}
		}
		
		if (name1.length() != name_sub.length()) {
			return false;
		}
		
		return true;
	}
	
	// 按下Enter將focus移到Go按鈕
	@Override
	public void handle(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			Go.requestFocus();
		}
	}
	
}