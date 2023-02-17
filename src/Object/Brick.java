package Object;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
	private int hp;
	private int score;
	private Type type;
	public enum Type {
		RED, BLUE, YELLOW, GENERAL
	}
	
	public Brick(Type type, int hp) {
		this.hp = hp;
		this.score = 15;
		this.type = type;
		setWidth(70);
		setHeight(30);
		setFill(Color.rgb(248, 243, 209));
		Light.Distant light = new Light.Distant();
		light.setElevation(120);
		Lighting lighting = new Lighting(light);
		setEffect(lighting);
		if (type != Type.GENERAL) {
			setAdditionStyle();
		}
	}

	private void setAdditionStyle() {
		switch(type) {
			case RED:
				setStyle(getStyle() + "-fx-fill: red ");
				break;
			case BLUE:
				setStyle(getStyle() + "-fx-fill: blue");
				break;
			case YELLOW:
				setStyle(getStyle() + "-fx-fill: yellow");
				break;
			default:
				break;
		}
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Type getType() {
		return this.type;
	}

}
