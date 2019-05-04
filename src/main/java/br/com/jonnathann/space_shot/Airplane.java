package br.com.jonnathann.space_shot;

import JPlay.Sprite;

public class Airplane extends Sprite{
	
	private Shot shot;
	private Sprite turbine;
	private static final int VELOCITY = 1;
	
	
	
	public Airplane(String image_air, int frames_air, String image_shot, int frames_shot, String image_tur, int frames_tur) {
		
		super(image_air, frames_air);
		super.setPosition(400, 400);
		this.shot = new Shot(image_shot, frames_shot);
		this.shot.setPosition(super.x + 21, super.y + 30);
		this.turbine = new Turbine(image_tur, frames_tur);
		this.turbine.setPosition(super.x - 11, super.x + 75);
		
	}
	
	public void setPositionAirplane(int x, int y) {
		
		shot.setPosition(x, y);
		super.setPosition(x, y);
		
	}
	
	public void moveAirplaneUp() {
		
		shot.y -= VELOCITY;
		super.y -= VELOCITY;
		turbine.y -= VELOCITY;
	}
	
	public void moveAirplaneDown() {
		
		if (super.y < 540) {
			shot.y +=VELOCITY;
			super.y += VELOCITY;
			turbine.y += VELOCITY;
		}
			
	}
	
	public void moveAirplaneLeft() {
		
		if (super.x > 0) {
			
			shot.x -= VELOCITY;
			super.x -= VELOCITY;
			turbine.x -= VELOCITY;
			
		}
	}
	
	public void moveAirplaneRight() {
		
		if (super.x < 750) {
			shot.x += VELOCITY;
			super.x += VELOCITY;
			turbine.x += VELOCITY;
		}
		
	}
	
	public Shot getShot() {
		return shot;
	}
	
	public Sprite getTurbine() {
		return turbine;
	}
}
