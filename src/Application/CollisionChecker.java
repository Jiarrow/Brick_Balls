package Application;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionChecker {
	public enum CollisionType {
		POINT, HORIZON, VERTICAL, NO
	}
	
	private Node object;
	public CollisionChecker(Node object) {
		this.object = object;
	}
	
	public CollisionType withRectangle(Rectangle rectangle) {
		if (object instanceof Circle) {
			return rectAndCirTest(rectangle);
		}
		else if (object instanceof Rectangle) {
			return rectangleTest(rectangle);
		}
		else {
			return CollisionType.NO;
		}
	}

	private CollisionType rectAndCirTest(Rectangle rectangle) {
		Bounds objBounds = object.getBoundsInParent();
		double radius = objBounds.getWidth() / 2;
		double centerX = objBounds.getMinX() + objBounds.getWidth() / 2;
		double centerY = objBounds.getMinY() + objBounds.getHeight() / 2;
		Bounds rectBounds = rectangle.getBoundsInParent();
		
		if ((rectBounds.getMinX() - centerX) * (rectBounds.getMaxX() - centerX) > 0 &&
				(rectBounds.getMinY() - centerY) * (rectBounds.getMaxY() - centerY) > 0) {
			
			Double distance1 = square(rectBounds.getMinX() - centerX)
					+ square(rectBounds.getMinY() - centerY);
			Double distance2 = square(rectBounds.getMinX() - centerX)
					+ square(rectBounds.getMaxY() - centerY);
			Double distance3 = square(rectBounds.getMaxX() - centerX)
					+ square(rectBounds.getMinY() - centerY);
			Double distance4 = square(rectBounds.getMaxX() - centerX)
					+ square(rectBounds.getMaxY() - centerY);
			Double distance = square(radius);
			if (distance1 <= distance || distance2 <= distance ||
					distance3 <= distance || distance4 <= distance) {
				return CollisionType.POINT;
			}
		}
		
		return rectangleTest(rectangle);
	}

	private CollisionType rectangleTest(Rectangle rectangle) {
		Bounds objBounds = object.getBoundsInParent();
		double centerX = objBounds.getMinX() + objBounds.getWidth() / 2;
//		double centerY = objBounds.getMinY() + objBounds.getHeight() / 2;
		Bounds rectBounds = rectangle.getBoundsInParent();
		
		if (rectBounds.getMinX() <= objBounds.getMaxX() &&
				objBounds.getMinX() <= rectBounds.getMaxX() &&
				rectBounds.getMinY() <= objBounds.getMaxY() &&
				objBounds.getMinY() <= rectBounds.getMaxY()) {
			if (centerX >= rectBounds.getMinX() && centerX <= rectBounds.getMaxX()) {
				return CollisionType.VERTICAL;
			}
			else {
				return CollisionType.HORIZON;
			}
		}
	
		return CollisionType.NO;
	}
	
	
	
	private double square(double num) {
		return Math.pow(num, 2);
	}
}
