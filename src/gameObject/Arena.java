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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Arena extends Application { 
	public final static int SIZE = 800;
	public final int MAX_SCORE = 10;
	private Vehicule player;
	private GameObject objectif;
	private Text text ;
	Pane root;
	
	private Pane createPane() {
		root = new Pane();
		root.setPrefSize(SIZE,  SIZE);
		objectif = new GameObject(new Circle(15,15,15, Color.YELLOW));
		moveObj(objectif);
		root.getChildren().add(objectif.getView());
		Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
    		10.0, 20.0
    		,20.0, 10.0
    		,0.0, 0.0	            
             });
		player = new Vehicule(new Rectangle(40,15, Color.BLUE));
		player.getView().setTranslateX(400);
		player.getView().setTranslateY(400);
		root.getChildren().add(player.getView());
		
		//Code d'un Score board qui ne s'affiche pas pour le moment 
//		text = new Text();
//		text.setFont(new Font("ARIAL", 30));
//		text.setStyle("-fx-font-weight: bold;");
//		text.setFill(Color.WHITE);
//		text.setText("Score : " +player.getScore());
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
		stage.setTitle("Game"); 
				
		root.getChildren().add(text);
		
		stage.getScene().setOnKeyPressed(e -> {
			if( e.getCode() == KeyCode.UP) {
				player.thrust();
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
		if(player.collide(objectif)){
			moveObj(objectif);
			player.setScore(player.getScore()+1);
		}
		
		if(player.getScore() >= 50) {
			//implementer Pop up victoire
		}
		player.update();
	}
	
	private void moveObj(GameObject obj) {
		Random r = new Random();
		double x = 1 + (600)*r.nextDouble();
		double y = 1 + (600)*r.nextDouble();
		obj.getView().setTranslateX(x);
		obj.getView().setTranslateY(y);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
