package gameObject;

import javafx.scene.Node;

public class Vehicule extends GameObject{
		
	protected double direction;
	protected Point speed;
	
	private int turnit = 5;
	private double thrustit = 0.5;
	
	
	public Vehicule(Node view) {
		super(view);
		this.coord = new Point(0, 0);
		this.direction = 0;
		this.speed = new Point(0, 0);
	}

	public void clock() {
		direction = direction-turnit;
		view.setRotate(direction);
		System.out.println(direction);
	}
	
	public void antiClock() {
		direction = direction+turnit;
		view.setRotate(direction);
		System.out.println(direction);
	}
	
	public void thrust() {
		speed.x = speed.getX() + thrustit*Math.cos(direction);
		speed.y = speed.getY() + thrustit*Math.sin(direction);
	}
	
	public void pull() {
		speed.x = speed.getX()-thrustit*Math.cos(direction);
		speed.y = speed.getY()-thrustit*Math.sin(direction);
	}
	
	public void update() {
		if(coord.x <0) {
			coord.x = 600-coord.x;
		}
		if(coord.y <0){
			coord.y = 600-coord.y;
		}
		coord.x = (coord.x + speed.getX())%600;
		coord.y = (coord.y + speed.getY())%600; 
		
		view.setTranslateX(coord.x);
		view.setTranslateY(coord.y);
	}


	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public Point getSpeed() {
		return speed;
	}

	public void setSpeed(Point speed) {
		this.speed = speed;
	}

	
	
}
