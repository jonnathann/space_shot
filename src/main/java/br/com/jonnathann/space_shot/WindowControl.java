package br.com.jonnathann.space_shot;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sprite;

public class WindowControl extends GameImage{
	
	private Sprite pointerControl = new Sprite("imgs/point.png", 3);
	private Keyboard keyboard;
	
	public WindowControl(String image, Keyboard keyborad) {
		super(image);
		this.pointerControl.setPosition(273, 430);
		this.keyboard = keyborad;
	}
	
	public void windowControlDraw() {
		super.draw();
		this.pointerControl.draw();
		this.pointerControl.runAnimation();
	}
	
	public boolean controllerWindowControl() {
		
		boolean select = false;
		if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
			select = true;
		}
		
		return select;
	}
	
	

}
