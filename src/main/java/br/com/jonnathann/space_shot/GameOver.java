package br.com.jonnathann.space_shot;

import java.awt.event.KeyEvent;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Sprite;

public class GameOver extends GameImage{
	
	private Sprite pointerGameOver = new Sprite("imgs/pointerGameOverExit.png", 3);
	private Sprite pointerGameOverExit = new Sprite("imgs/pointerGameOverExit.png", 3);
	private Keyboard keyboard;
	private int select = 0;
	
	public GameOver(String image, Keyboard keyboard) {
		super(image);
		this.keyboard = keyboard;
		this.pointerGameOver.setPosition(265, 340);
		this.pointerGameOverExit.setPosition(260, 385);
	}
	
	public void draw() {
		super.draw();
		this.pointerGameOver.draw();
		this.pointerGameOver.runAnimation();
	}
	
	public void drawGameOverExit() {
		super.draw();
		this.pointerGameOverExit.draw();
		this.pointerGameOverExit.runAnimation();
	}
	
	public int controllerGameOver() {
		
		if (keyboard.keyDown(KeyEvent.VK_W)) {
			
			
			if (pointerGameOver.y == 340) {
				pointerGameOver.y = 430;
			}
			
			else if (pointerGameOver.y == 430) {
				pointerGameOver.y = 340;
			}
			
			new Sound("audios/shoot.wav").play();
			
		}
		
		if (keyboard.keyDown(KeyEvent.VK_S)) {
							
			if (pointerGameOver.y == 340) {
				pointerGameOver.y = 430;
			}
			
			else if (pointerGameOver.y == 430) {
				pointerGameOver.y = 340;
			}
			
			new Sound("audios/shoot.wav").play();
		}
		
		if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
			
			if (pointerGameOver.y == 340) {
				select = 1;
			}
			
			else if (pointerGameOver.y == 430) {
				select = 2;
			}
			
		}
		
		return select;
		
	}
	
	public boolean controllerGameOverExit() {
		
		boolean select = false;
		if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
			
			if (pointerGameOverExit.y == 385) {
				select = true;
			}
			
		}
		
		return select;
		
	}

}
