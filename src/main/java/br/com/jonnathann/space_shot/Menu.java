package br.com.jonnathann.space_shot;

import java.awt.event.KeyEvent;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Sprite;
import JPlay.Time;
import JPlay.Window;

public class Menu extends GameImage{
	
	private Keyboard keyboard;
	private Sprite pointer = new Sprite("imgs/point.png", 3);
	private Window windows;
	private Game game;
	private WindowStage windowStage;
	private Time time;
	public static Sound sound;
	public static int countStage = -1;
	public String airplaneName;
	private CharacterSelect characterSelect;
	public static String SELECT_AIRPLANE;
	
		
	public Menu(String backgound, String pointer, int frames, Window windows) {
		
		super(backgound);
		this.keyboard = windows.getKeyboard();
		this.pointer = new Sprite(pointer, frames);
		this.pointer.setPosition(275, 280);
		this.windows = windows;
		this.windowStage = new WindowStage("imgs/stage_1.png");
		
		
		keyboard.setBehavior(KeyEvent.VK_S, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(KeyEvent.VK_W, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.ENTER_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
				
		sound = new Sound("audios/title.wav");
		sound.play();
		sound.setRepeat(true);
				
	}
	
	public void controllerMenu() {
		
		super.draw();
		pointer.draw();
		pointer.runAnimation();
		
		if (keyboard.keyDown(KeyEvent.VK_W)) {
			
			
			if (pointer.y == 280) {
				pointer.y = 460;
			}
			
			else if (pointer.y == 370) {
				pointer.y = 280;
			}
			
			else if (pointer.y == 460) {
				pointer.y = 370;
			}
			
			else {
				
				pointer.y = 280;
			}
			new Sound("audios/shoot.wav").play();
			
		}
		
		if (keyboard.keyDown(KeyEvent.VK_S)) {
							
			if (pointer.y == 280) {
				pointer.y = 370;
			}
			
			else if (pointer.y == 370) {
				pointer.y = 460;
			}
			
			else {
				pointer.y = 280;
			}
			new Sound("audios/shoot.wav").play();
		}
		
		if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
			if (pointer.y == 280) {
				
				//Game execute
				this.windowSelectAirplane();
								
				
			}
			
			if (pointer.y == 370) {
				//Control
				this.windowsControl();
				
			}
			
			if (pointer.y == 460) {
				
				//Exit
				windows.exit();
			}
			
		}
		
	}
	
	private void gameExecute(String selectAirplane) {
		
		boolean execute = true;
		
		this.game = new Game(selectAirplane, windows, keyboard, "imgs/background_1.png", "imgs/enemies_1.png", "imgs/stage_1_clear.png");
		
		keyboard.setBehavior(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(KeyEvent.VK_A, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(KeyEvent.VK_D, Keyboard.DETECT_EVERY_PRESS);
		
		
		while (execute) {
			
			this.game.update();
			windows.display();
		}
	}
	
	private void windowStageInitialExecute(String selectAirplane) {
		boolean execute = true;
		this.time = new Time(100, 100, true);
		sound.stop();
		sound = new Sound("audios/explosao.wav");
		sound.play();
		while (execute) {
			windowStage.draw();
			
			if (this.time.getSecond() == 5) {
				sound.stop();
				sound = new Sound("audios/stage_1.wav");
				sound.play();
				sound.setRepeat(true);
				this.gameExecute(selectAirplane);				
			}

			windows.display();
		}
	}
	
	private void windowSelectAirplane() {
		
		this.keyboard.setBehavior(KeyEvent.VK_D, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(KeyEvent.VK_A, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(Keyboard.ENTER_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);	
		sound.stop();
		sound = new Sound("audios/selectAirplanes.wav");
		sound.play();
		sound.setRepeat(true);
		
		boolean execute = true;
		this.characterSelect = new CharacterSelect(this.keyboard, this.windows, "imgs/characterSelection1.png", "imgs/characterPoint.png", 3);		
		
		while (execute) {
			
			this.characterSelect.draw();
			
			if (this.characterSelect.controllerSelect() == 1) {
				SELECT_AIRPLANE = "imgs/cong1.png";
				windowStageInitialExecute("imgs/nave1.png");
				
			}
			
			else if (this.characterSelect.controllerSelect() == 2) {
				SELECT_AIRPLANE = "imgs/cong2.png";
				windowStageInitialExecute("imgs/nave2.png");
				
			}
			
			else if (this.characterSelect.controllerSelect() == 3) {
				SELECT_AIRPLANE = "imgs/cong3.png";
				windowStageInitialExecute("imgs/nave3.png");
			}
			
			windows.display();
		}
		
	}
	
	private void windowsControl() {
		WindowControl windowControl = new WindowControl("imgs/windowControl.png", this.keyboard);
		sound.stop();
		sound = new Sound("audios/controlSettings.wav");
		sound.play();
		sound.setRepeat(true);
		boolean execute = true;
		while (execute) {
			
			windowControl.windowControlDraw();
			
			if (windowControl.controllerWindowControl() == true) {
				Game.initMenu(windows);
			}
			this.windows.display();
		}
	}
	
}
