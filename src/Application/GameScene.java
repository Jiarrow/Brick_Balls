package Application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Application.CollisionChecker.CollisionType;
import Interface.ScoreAndLevel;
import Object.Ball;
import Object.Board;
import Object.Brick;
import Object.Brick.Type;
import Object.Drop;
import Object.Drop.dropType;
import Object.TextOfStart;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameScene extends Pane {
	private TextOfStart text = new TextOfStart("Chick Mouse Button to Start");
	private Board board = new Board();
	private Ball ball = new Ball(10);
	private List<Brick> bricks = Collections.synchronizedList(new ArrayList<>());
	private List<Drop> drops = Collections.synchronizedList(new ArrayList<>());
	private Timeline timeline;
	private Timeline dropsTimeline;
	private DataProperties dataProperties;
	private ScoreAndLevel scoreAndLevel = new ScoreAndLevel();
	private Level level = new Level(this);
	
	// 設定遊戲介面寬高
	public GameScene(int width, int height) {
		setWidth(width);
		setHeight(height);
	
		dataProperties = new DataProperties();
		loadGame();
		setOnMouseMoved(e -> board.onMouseMove(e));
	}


	// 載入板子、球、初始座標、初始關卡、初始動畫、設定按滑鼠鍵開始動畫
	private void loadGame() {
		getChildren().add(scoreAndLevel);
		getChildren().add(text);
		getChildren().add(board);
		getChildren().add(ball);
		initObjectLayout();
		level.load(dataProperties.getLevel());
		initTimeline();
		initDropsTimeline();
		initScoreAndLevel();
		setOnMouseClicked(e -> startTimeline());
		setOnKeyPressed(e -> shrinkBall(e));
	}

	// 各種數字資料的類別
	public class DataProperties {
		public DoubleProperty scoreProperty = new SimpleDoubleProperty(0);
		public DoubleProperty thisLevelScore = new SimpleDoubleProperty(0);
		public IntegerProperty levelProperty = new SimpleIntegerProperty(1);
		public IntegerProperty lifeProperty = new SimpleIntegerProperty(3);
		
		public DataProperties() { };
		public Double getScore() {
			return scoreProperty.get();
		}
		public void addScore(Double score) {
			scoreProperty.set(getScore() + score);
		}
		public void addThisLevelScore(Double score) {
			thisLevelScore.set(thisLevelScore.get() + score);
		}
		public int getLevel() {
			return levelProperty.get();
		}
		public void nextLevel() {
			int nowLevel = levelProperty.get();
			if (nowLevel < 3) {
				levelProperty.set(levelProperty.get() + 1);
			}
		}
	}
	
	// 初始元素座標
	private void initObjectLayout() {
		text.setLayoutX(getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
		text.setLayoutY(getHeight() * 3 / 4);
		board.setLayoutX(getWidth()/2 - board.getWidth()/2);
		board.setLayoutY(getHeight() - board.getHeight() - 20);
		ball.layoutXProperty().bind(board.layoutXProperty().add(board.getWidth()/2));
		ball.setLayoutY(getHeight() - board.getHeight() - ball.getRadius() - 20);
	}
	
	// 初始動畫
	private void initTimeline() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(2), (e) -> {
			ball.move();
			handleCollision();
			if (bricks.size() <= 0) {
				showResult(true);
			}
			if (ball.getBoundsInParent().getMinY() >= BrickBall.HEIGHT) {
				dataProperties.lifeProperty.set(dataProperties.lifeProperty.get() - 1);
				scoreAndLevel.lifeValue();
				if (dataProperties.lifeProperty.get() <= 0) {
					showResult(false);
				}
				else {
					shotAnotherBall();
				}
			}
		});
		
		timeline.getKeyFrames().add(keyFrame);
	}
	
	// 處理球與板子和磚塊的碰撞
	private void handleCollision() {
		CollisionType type = ball.getCollisionType(board);
		if (type != CollisionType.NO) {
			changeSpeed(type);
		}
		
		for (Brick brick: bricks) {
			type = ball.getCollisionType(brick);
			if (type != CollisionType.NO) {
				changeSpeed(type);
				brick.setHp(brick.getHp() - 1);
				if (brick.getHp() <= 0) {
					if (brick.getType() == Type.RED) {
						Drop drop = new Drop(dropType.SHORT);
						drop.setLayoutX(brick.getLayoutX());
						drop.setLayoutY(brick.getLayoutY());
						drops.add(drop);
						getChildren().add(drop);
					}
					if (brick.getType() == Type.YELLOW) {
						Drop drop = new Drop(dropType.LONG);
						drop.setLayoutX(brick.getLayoutX());
						drop.setLayoutY(brick.getLayoutY());
						drops.add(drop);
						getChildren().add(drop);
					}
					if (brick.getType() == Type.BLUE) {
						Drop drop = new Drop(dropType.BIG);
						drop.setLayoutX(brick.getLayoutX());
						drop.setLayoutY(brick.getLayoutY());
						drops.add(drop);
						getChildren().add(drop);
					}
					destroyObject(brick);
					dataProperties.addScore((double)brick.getScore());
					dataProperties.addThisLevelScore((double)brick.getScore());
				}
				break;
			}
		}
	}
	
	// 球改變速度
	private void changeSpeed(CollisionType type) {
		switch(type) {
			case HORIZON:
				ball.reverseSpeedX();
				break;
			case VERTICAL:
				ball.reverseSpeedY();
				break;
			case POINT:
				ball.reverseSpeedX();
				ball.reverseSpeedY();
				break;
			default:
				break;
		}
	}
	
	// 去掉被擊中的磚塊
	private void destroyObject(final Brick brick) {
		FadeTransition fade = new FadeTransition(Duration.millis(100.0D), brick);
		fade.setFromValue(1.0D);
		fade.setToValue(0.0D);
		fade.setOnFinished((event) -> getChildren().remove(brick));
		bricks.remove(brick);
		fade.play();
	}
	
	private void shotAnotherBall() {
		timeline.pause();
		ball.layoutXProperty().bind(board.layoutXProperty().add(board.getWidth()/2));
		ball.setLayoutY(getHeight() - board.getHeight() - ball.getRadius() - 20);
		ball.reset();
	}
	
	// 掉落物動畫
	private void initDropsTimeline() {
		dropsTimeline = new Timeline();
		dropsTimeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame dropskeyFrame = new KeyFrame(Duration.millis(50), e -> {
			for(Drop drop : drops) {
				drop.setLayoutY(drop.getLayoutY() + 2);
				if (drop.getBoundsInParent().intersects(board.getBoundsInParent())) {
					if (drop.getType() == dropType.SHORT) {
						if (board.getScaleX() > 0.8) {
							changeBoardScale(board.getScaleX() - 0.2);
						}
					}
					else if (drop.getType() == dropType.LONG) {
						if (board.getScaleX() < 2) {
							changeBoardScale(board.getScaleX() + 0.2);
						}
					}
					else if (drop.getType() == dropType.BIG) {
						if (ball.getRadius() < 27.2) {
							ball.setRadius(ball.getRadius() + 2);
						}
					}
					getChildren().remove(drop);
					drops.remove(drop);
					break;
				}
			}
		});
		dropsTimeline.getKeyFrames().add(dropskeyFrame);
		dropsTimeline.play();
	}
	
	// 板子的伸長與縮短
		private void changeBoardScale(double scale) {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), board);
			scaleTransition.setFromX(board.getScaleX());
			scaleTransition.setToX(scale);
			scaleTransition.setCycleCount(1);
			scaleTransition.setAutoReverse(false);
			scaleTransition.play();
		}
	
	// 按滑鼠鍵開始動畫與板子移動
	private void startTimeline() {
		getChildren().remove(text);
		setOnMouseMoved(e -> board.onMouseMove(e));
		ball.layoutXProperty().unbind();
		timeline.play();
		dropsTimeline.play();
	}
	
	// 案空白鍵縮小球
	private void shrinkBall(KeyEvent e) {
		if (e.getCode() == KeyCode.SPACE) {
			if (ball.getRadius() > 10) {
				ball.setRadius(ball.getRadius() - 2);
			}
		}
	}
	
	// 初始分數與關卡
	public void initScoreAndLevel() {
		scoreAndLevel.bindValue(dataProperties);
	}
	
	// 顯示結果
	private void showResult(boolean isWin) {
		timeline.pause();
		dropsTimeline.pause();
		
		if (isWin) {
			if (dataProperties.getLevel() < 3) {
				ChangeState.Level_Completed(false);
				
			}
			else {
				ChangeState.Level_Completed(true);
			}
		}
		else {
			ChangeState.LoseGame();
			dataProperties.scoreProperty.set(dataProperties.getScore() - dataProperties.thisLevelScore.get());
		}
		
		dataProperties.thisLevelScore.set(0.0);
		ball.reset();
		board.reset();
		initObjectLayout();
		scoreAndLevel.resetLife();
		dataProperties.lifeProperty.set(3);
		if (drops.size() >= 0) {
			for (Drop drop : drops) {
				getChildren().remove(drop);
			}
			drops.clear();
		}
	}
	
	// 重新此關卡
	public void restartGame() {
		if (bricks.size() >= 0) {
			for (Brick brick : bricks) {
				getChildren().remove(brick);
			}
			bricks.clear();
		}
		level.load(dataProperties.getLevel());
	}
	
	// 進入下一關
	public void enterNextLevel() {
		dataProperties.nextLevel();
		level.load(dataProperties.getLevel());
	}
	
	
	// 加元素進去遊戲介面
	public void addChildren(Node node) {
		getChildren().add(node);
	}
	
	// 得到磚塊列
	public List<Brick> getBricks() {
		return bricks;
	}
	
	// 得到分數關卡等資訊
	public DataProperties getData() {
		return this.dataProperties;
	}
	
}
