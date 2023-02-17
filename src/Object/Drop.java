package Object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Drop extends ImageView {
	public enum dropType {
		SHORT, LONG, BIG
	}
	private dropType type;
	public Drop(dropType type) {
		this.type = type;
		if (type == dropType.SHORT) {
			Image image = new Image(getClass().getResourceAsStream("../Image/getShorter.png"), 40, 30, false, false);
			this.setImage(image);
		}
		else if (type == dropType.LONG) {
			Image image = new Image(getClass().getResourceAsStream("../Image/getLonger.png"), 40, 30, false, false);
			this.setImage(image);
		}
		else if (type == dropType.BIG) {
			Image image = new Image(getClass().getResourceAsStream("../Image/Big.jpg"), 40, 30, false, false);
			this.setImage(image);
		}
	}

	public dropType getType() {
		return type;
	}
}
