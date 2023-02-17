package Object;

import Application.BrickBall;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Rectangle {
	
	public Board() {
		setWidth(68);
		setHeight(20);
		setFill(Color.rgb(31, 210, 255));
		setEffect(new Lighting());
		
	}
	
	public void onMouseMove(MouseEvent e) {
		if (e.getX() >= getWidth()/2 && e.getX() <= BrickBall.WIDTH - getWidth()/2) {
			setLayoutX(e.getX() - getWidth()/2);
		}
	}
	
	public void reset() {
		setScaleX(1);
	}

}
