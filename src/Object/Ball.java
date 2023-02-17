package Object;
import Application.BrickBall;
import Application.CollisionChecker;
import Application.CollisionChecker.CollisionType;
import javafx.geometry.Bounds;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Circle {
	private CollisionChecker collisionChecker;
	private double speedX = 0.8, speedY = -0.8;
	
	public Ball(int radius) {
        setRadius(radius);
        setFill(Color.rgb(30, 144, 255));
        setEffect(new Lighting());
        collisionChecker = new CollisionChecker(this);
        
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
 
	public double getSpeedX() {
		return speedX;
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
 
	public double getSpeedY() {
		return speedY;
	}	

	public void move() {
		Bounds bounds = getBoundsInParent();
		
		if (bounds.getMinX() <= 0 || bounds.getMaxX() >= BrickBall.WIDTH) {
			speedX = -speedX;
		}
		if (bounds.getMinY() <= 0 ) {
			speedY = -speedY;
		}
		
		setLayoutX(getLayoutX() + speedX);
		setLayoutY(getLayoutY() + speedY);
	}
	
	public void reverseSpeedX() {
		speedX = -speedX;
	}
	
	public void reverseSpeedY() {
		speedY = -speedY;
	}

//	public void decreaseSpeedX() {
//		if (speedX >= 0) {
//			speedX -= 0.2;
//		}
//		else {
//			speedX += 0.2;
//		}
//	}
//	
//	public void increaseSpeedX() {
//		if (speedX >= 0) {
//			speedX += 0.2;
//		}
//		else {
//			speedX -= 0.2;
//		}
//	}
//	
//	public void decreaseSpeedY() {
//		if (speedY >= 0) {
//			speedY -= 0.2;
//		}
//		else {
//			speedY += 0.2;
//		}
//	}
//	
//	public void increaseSpeedY() {
//		if (speedY >= 0) {
//			speedY += 0.2;
//		}
//		else {
//			speedY -= -0.2;
//		}
//	}
	
	public CollisionType getCollisionType(Rectangle rectangle) {
		return collisionChecker.withRectangle(rectangle);
	}
	
	public void reset() {
		this.speedX = 0.8;
		this.speedY = -0.8;
		this.setRadius(10);
	}
}
