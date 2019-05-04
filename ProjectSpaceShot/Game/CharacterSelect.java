package Game;

import java.awt.event.KeyEvent;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Sprite;
import JPlay.Window;

public class CharacterSelect{
	
	
	private GameImage characterBackground;
	private Sprite characterPoint;
	private Keyboard controller;
	private int select = 0;
	
	public CharacterSelect(Keyboard keyboard, Window windows, String characterBackground, String characterPoint, int frames) {
		
		this.characterBackground = new GameImage(characterBackground);
		this.characterPoint = new Sprite(characterPoint, frames);
		this.characterPoint.setPosition(10, 170);
		this.controller = keyboard;	
		
	}
	
	public void draw() {
		this.characterBackground.draw();
		this.characterPoint.draw();
		this.characterPoint.runAnimation();
	}
	
	public int controllerSelect() {
		if (controller.keyDown(KeyEvent.VK_D)) {
			
			
			if (characterPoint.x == 10) {
				characterPoint.x = 275;
			}
			
			else if(characterPoint.x == 275) {
				characterPoint.x = 540;
			}
			
			else {
				characterPoint.x = 10;
			}
			new Sound("audios/shoot.wav").play();
			
		}
		
		if (controller.keyDown(KeyEvent.VK_A)) {
			
			
			if (characterPoint.x == 540) {
				characterPoint.x = 275;
			}
			
			else if(characterPoint.x == 275) {
				characterPoint.x = 10;
			}
			
			else {
				characterPoint.x = 540;
			}
			
			new Sound("audios/shoot.wav").play();
			
		}
		
		if (controller.keyDown(Keyboard.ENTER_KEY)) {
			
			if (characterPoint.x == 10) {
				select = 1;
			}
			
			if (characterPoint.x == 275) {
				select = 2;
			}
			
			if (characterPoint.x == 540) {
				select = 3;
			}
			
		}
		return select;
		
	}
	

}
