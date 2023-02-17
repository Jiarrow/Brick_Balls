package Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrickBall extends Application {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static GameScene game;
	public static Scene gameScene;
	public static Stage currentStage;
	public static Scene menuScene;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../FXML/MenuScene.fxml"));
		menuScene = new Scene(root);
		currentStage = primaryStage;
		currentStage.setTitle("Brick Balls");
		currentStage.setScene(menuScene);
		currentStage.setResizable(false);
		currentStage.show();
	}

}

