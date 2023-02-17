package Object;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextOfStart extends Text {
	
	public TextOfStart(String text) {
		super(text);
		this.setFill(Color.PURPLE);
		this.setFont(Font.font("Calluna", FontWeight.BOLD, FontPosture.REGULAR, 30));
		
	}
}
