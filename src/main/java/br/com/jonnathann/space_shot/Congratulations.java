package br.com.jonnathann.space_shot;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sprite;

public class Congratulations extends GameImage{
	
	private Sprite pointerCong = new Sprite("imgs/point.png", 3);
	private Keyboard keyboard;
	private GameImage cong;
	
	public Congratulations(String image, String cong, Keyboard keyboard) {
		super(image);
		this.pointerCong.setPosition(273, 430);
		this.keyboard = keyboard;
		this.cong = new GameImage(cong);
		this.cong.setPosition(600, 170);
	}
	
	public void congratulationsDraw() {
		super.draw();
		cong.draw();
		this.pointerCong.draw();
		this.pointerCong.runAnimation();
	}
	
	public boolean controllerCongratulations() {
		
		boolean select = false;
		if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
			select = true;
		}
		
		return select;
	}
}
