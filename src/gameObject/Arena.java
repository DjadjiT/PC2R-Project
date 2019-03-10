package gameObject;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Arena extends Application { 
	protected final int SIZE = 600;
	private Node gameView;
	private Vehicule player;
	private ArrayList<GameObject> objectifs = new ArrayList<>();
	
	Pane root;
	
	private Pane createPane() {
		root = new Pane();
		root.setPrefSize(SIZE,  SIZE);
		 Polygon polygon = new Polygon();
	        polygon.getPoints().addAll(new Double[]{
	            0.0, 0.0,
	            20.0, 10.0,
	            10.0, 20.0 });
		player = new Vehicule(polygon);
		player.getView().setTranslateX(400);
		player.getView().setTranslateY(400);
		root.getChildren().add(player.getView());
		
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				onUpdate();
				
			}

		};
		timer.start();
		
		return root;
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(createPane()));
		
		stage.getScene().setOnKeyPressed(e -> {
			if( e.getCode() == KeyCode.UP) {
				player.thrust();
			}
			else if( e.getCode() == KeyCode.DOWN) {
				player.pull();
			}
			
			else if( e.getCode() == KeyCode.LEFT) {
				player.clock();
			}
			
			else if( e.getCode() == KeyCode.RIGHT) {
				player.antiClock();
			}
			
		});
		stage.show();
	}
	
	private void onUpdate() {
		for(GameObject obj : objectifs) {
			if(player.collide(obj)) {
				obj.setVisible(false);
			}
		}
		
		for(GameObject obj : objectifs) {
			if(!obj.isVisible) {
				objectifs.remove(obj);
				root.getChildren().remove(obj.getView());
			}
		}
		
		player.update();		
		if(Math.random() < 0.015) {
			addGameObject(new GameObject(new Circle(15,15,15, Color.YELLOW)));
		}
	}
	
	private void addGameObject(GameObject obj) {
		Random r = new Random();
		objectifs.add(obj);
		double x = 1 + (600)*r.nextDouble();
		double y = 1 + (600)*r.nextDouble();
		obj.getView().setTranslateX(x);
		obj.getView().setTranslateY(y);
		root.getChildren().add(obj.getView());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
