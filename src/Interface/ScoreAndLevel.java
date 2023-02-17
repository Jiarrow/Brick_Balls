package Interface;

import Application.GameScene.DataProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ScoreAndLevel extends AnchorPane implements FXMLLayoutLoader {
	@FXML
	private Label Label_score;
	@FXML
	private Label Label_level;
	@FXML
	private ImageView life1;
	@FXML
	private ImageView life2;
	@FXML
	private ImageView life3;
	
	private DoubleProperty score = new SimpleDoubleProperty();
	private IntegerProperty level = new SimpleIntegerProperty();
	private IntegerProperty life = new SimpleIntegerProperty();
	
	
	public ScoreAndLevel() {
		loadXML(this, "ScoreAndLevelScene");
		
	}

	
	public void bindValue(DataProperties data) {
		score.bind(data.scoreProperty);
		score.addListener((observable, oldValue, newValue) -> {
			Label_score.setText("" + newValue.intValue());
		});
		
		level.bind(data.levelProperty);
		level.addListener((observable, oldValue, newValue) -> {
			Label_level.setText("" + newValue.intValue());
		});
	
		life.bind(data.lifeProperty);
	}

	public void lifeValue() {
		if (life.get() == 2) {
			life3.setVisible(false);
		}
		else if (life.get() == 1) {
			life2.setVisible(false);
		}
		else if (life.get() == 0) {
			life1.setVisible(false);
		}
	}

	public void resetLife() {
		life1.setVisible(true);
		life2.setVisible(true);
		life3.setVisible(true);
	}
}
