package Game;

import java.awt.event.KeyEvent;

import JPlay.Keyboard;
import JPlay.Sound;

public class ControllerAirplane {
	
	private Airplane airplane;
	private boolean fire;
	public ControllerAirplane(Airplane airplane) {
		
		this.airplane = airplane;
		fire = false;
	}
	
	public void controllerMove(Keyboard controller) {
		
		
		if (controller.keyDown(KeyEvent.VK_W) ) {
			airplane.moveAirplaneUp();
		}
		
		if (controller.keyDown(KeyEvent.VK_S) ) {
			airplane.moveAirplaneDown();
		}
		
		if (controller.keyDown(KeyEvent.VK_A) ) {
			airplane.moveAirplaneLeft();
		}
		
		if (controller.keyDown(KeyEvent.VK_D) ) {
			airplane.moveAirplaneRight();
		}
		
	}
	
	public void controllerShot(Keyboard controller) {
		
		if (controller.keyDown(Keyboard.SPACE_KEY) ) {
			
			new Sound("audios/shoot.wav").play();
			fire = true;
		}
		
		if (airplane.getShot().y <= 0) {
			fire = false;
			airplane.getShot().x = airplane.x + 21;
			airplane.getShot().y = airplane.y + 30;
			
		}
		
		if (fire == true) {
			
			airplane.getShot().y -= 10;		
			
		}
		
	}
	
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	
}
