package br.com.jonnathann.space_shot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Sprite;
import JPlay.Time;
import JPlay.Window;

public class Game {
	
	private Stage stage;
	private Airplane airplane;
	private ControllerAirplane ctrl;
	private Keyboard keyboard;
	private Window window;
	private boolean effectsExplosionEnemies = false;
	private boolean effectsExplosionAirplane = false;
	private ExplosionEffects exp_enemies = new ExplosionEffects("imgs/explosion_t.png", 9);
	private ExplosionEffects exp_airplane = new ExplosionEffects("imgs/explosion_t.png", 9);
	private ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	private float VELOCITY = (float)1.0;
	private WindowStage windowStageClear  = new WindowStage("imgs/stage_1_clear.png");
	private Time time;
	private WindowStage windowStage;
	private String airplaneName;	
	
	private Sprite life;
	private int countLife = 0;
	
	private int setCurrFrame = 3;
		
	public static int SCORE = 0;
	public static int All_SCORES = 0;
	
	private int[] positionEnemiesX = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700};
	private int[] positionEnemiesY = {-100, -150, -200, -250, -300, -350, -400, -450, -500, -550, -600};
	
		
	
	private final String[] STAGES = {"stage_2.png", "stage_3.png", "stage_4.png", "stage_5.png"};
	private final String[] STAGESCLEARES = {"stage_2_clear.png", "stage_3_clear.png", "stage_4_clear.png", "stage_5_clear.png"};
	private final String[] ENEMIES = {"enemies_2.png", "enemies_3.png", "enemies_4.png", "enemies_5.png"};
	private final String[] BACKGROUNDS = {"background_2.png", "background_3.png", "background_4.png", "background_5.png"};
	private final String[] SOUNDSTAGES = {"stage_2.wav", "stage_3.wav", "stage_4.wav", "stage_5.wav"};
	
	static int CREDITS = 5;
	
	public Game(String airplaneName, Window window, Keyboard keyboard, String background, String enemies, String stclear) {
		
		stage = new Stage(background);
		this.airplane = new Airplane(airplaneName, 1, "imgs/fire.png", 1, "imgs/turbine.png", 4);
		ctrl = new ControllerAirplane(this.airplane);
		this.keyboard = keyboard;
		this.window = window;
		createEnemies(enemies, 3, positionEnemiesX.length);
		this.windowStageClear  = new WindowStage(stclear);
		this.airplaneName = airplaneName;
		this.life = new Sprite("imgs/life.png", 5);
		this.life.setPosition(40, 40);
		
	}
	
	public void update() {
		
		//Update game
		stage.update();
		
		if (airplane.x > -100) {
			
			airplane.getShot().draw();
			airplane.getShot().runAnimation();
			airplane.getTurbine().draw();
			airplane.getTurbine().runAnimation();
			airplane.draw();
			ctrl.controllerMove(keyboard);
			ctrl.controllerShot(keyboard);
			
		}
		
		//Enemies
		updateEnemies();
		Font font = new Font("Courier new", Font.BOLD, 20);
		this.window.drawText("SCORE:" +SCORE, 350, 40, Color.WHITE, font);
		this.window.drawText("CREDITS: "+CREDITS, 350, 560, Color.WHITE, font);
		
		this.window.drawText("STAGE-"+(Menu.countStage + 2), 100, 560, Color.WHITE, font);
		
	}
	
	private void createEnemies(String image, int frames, int number_enemies) {
		
		for (int i = 0; i < number_enemies; i++) {
			
			Random random = new Random();
			Enemies enemy = new Enemies(image, 3);
			enemy.y = positionEnemiesY[random.nextInt(positionEnemiesY.length)];
			enemies.add(enemy);	
		}
	}
	
	private void updateEnemies() {
		
					
		for (int i = 0; i < enemies.size(); i++) {
				
			enemies.get(i).draw();
			enemies.get(i).x = positionEnemiesX[i];
			enemies.get(i).y += VELOCITY;
			
			if (setCurrFrame == 1) {
				enemies.get(i).setTimeChangeFrame(-1);
				enemies.get(i).runAnimation();
				this.life.draw();
				Font font = new Font("Courier new", Font.BOLD, 20);
				this.window.drawText("BOSS", 95, 90, Color.WHITE, font);
				
				
			}
				
		}
		
		
		if (effectsExplosionEnemies == true) {
			exp_enemies.draw();
			
			if (exp_enemies.getCurrFrame() < 8) {
				exp_enemies.runAnimation();
				
			}
			else {
				effectsExplosionEnemies = false;
				exp_enemies = new ExplosionEffects("imgs/explosion_t.png", 9);
			}
		}
		
		if (effectsExplosionAirplane == true) {
			exp_airplane.draw();
			
			if (exp_airplane.getCurrFrame() == 7) {
				
				//Window Game Over
				CREDITS -= 1;
				this.windowGameOver();
				
			}
			
			
			else if (exp_enemies.getCurrFrame() < 7) {
				exp_airplane.runAnimation();
				
			}
			else {
				effectsExplosionAirplane = false;
				exp_airplane = new ExplosionEffects("imgs/explosion_t.png", 9);
			}
		}
				
		for (int i = 0; i < enemies.size(); i++) {
			if (airplane.getShot().collided(enemies.get(i))) {
				
				exp_enemies.x = enemies.get(i).x;
				exp_enemies.y = enemies.get(i).y;
				new Sound("audios/explosao.wav").play();
				ctrl.setFire(false);
				Random random = new Random();
				
				effectsExplosionEnemies = true;
				
				enemies.get(i).x = positionEnemiesX[random.nextInt(positionEnemiesX.length)];
				enemies.get(i).y = positionEnemiesY[random.nextInt(positionEnemiesY.length)];
				
				if (setCurrFrame == 3) {
					enemies.get(i).setCurrFrame(random.nextInt(setCurrFrame));
				}
				
				airplane.getShot().x = airplane.x + 21;
				airplane.getShot().y = airplane.y + 30;
				
				SCORE += 100;
				
				if (SCORE == 5000) {
					VELOCITY = (float) 2.0;
					Menu.sound.stop();
					Menu.sound = new Sound("audios/boss.wav");
					Menu.sound.play();
					this.setCurrFrame = 1;
					
				}
				
				if (SCORE >= (5000 + (1000 * this.countLife))&& this.life.getCurrFrame() <= 5) {
					this.life.setCurrFrame(this.countLife);
					this.countLife++;
					
					
				}
				
				else if (this.life.getCurrFrame() == 5) {
					this.windowStageClear();
				}
				
			}
			
			else if (airplane.collided(enemies.get(i))) {
				
				effectsExplosionAirplane = true;
				exp_airplane.x = airplane.x;
				exp_airplane.y = airplane.y;
				//airplane.x = -100;
				
				
			}
			
			if (enemies.get(i).y >= 600) {
				Random random = new Random();				
				enemies.get(i).x = positionEnemiesX[random.nextInt(positionEnemiesX.length)];
				enemies.get(i).y = positionEnemiesY[random.nextInt(positionEnemiesY.length)];
				
			}
			
		}
		
	}
	
	public void windowStageClear() {
		boolean execute = true;
		Menu.sound.stop();
		this.time = new Time(100, 100, true);
		Menu.sound = new Sound("audios/stage_clear.wav");
		Menu.sound.play();
		
		
		while (execute) {
			
			//AQUI
			windowStageClear.draw();
			if (this.time.getSecond() == 5) {
				
				
				All_SCORES += SCORE;
				SCORE = 0;
				Menu.countStage += 1;
				
				if (Menu.countStage > 3) {
					this.windowsCongratulations();
				}
				this.windowUpdateStageExecute("imgs/"+BACKGROUNDS[Menu.countStage], "imgs/"+ENEMIES[Menu.countStage], "imgs/"+STAGESCLEARES[Menu.countStage], "audios/"+SOUNDSTAGES[Menu.countStage], "imgs/"+STAGES[Menu.countStage]);
				
			}
			
			window.display();
		}
	}
	
	private void windowUpdateStageExecute(String updateBackground, String updateEnemies, String updateStageClear, String updateSound, String windowStage) {
		boolean execute = true;
		this.time = new Time(100, 100, true);
		Menu.sound.stop();
		Menu.sound = new Sound("audios/explosao.wav");
		Menu.sound.play();
		
		this.windowStage = new WindowStage(windowStage);
		
		while (execute) {
			this.windowStage.draw();
			if (this.time.getSecond() == 5) {
				Menu.sound.stop();
				Menu.sound = new Sound(updateSound);
				Menu.sound.play();
				Menu.sound.setRepeat(true);
				
				this.updateGame(updateBackground, updateEnemies, updateStageClear);
			}

			window.display();
		}
	}
	
	private void updateGame(String updateBackground, String updateEnemies, String updateStageClear) {
		this.keyboard.setBehavior(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);
		this.keyboard.setBehavior(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		Game new_game = new Game(this.airplaneName, this.window, this.keyboard, updateBackground, updateEnemies, updateStageClear);
		boolean execute = true;
		
		while (execute) {
			
			new_game.update();
			
			window.display();
			
		}
	}
	
	private void windowGameOver() {
		this.keyboard.setBehavior(KeyEvent.VK_W, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(KeyEvent.VK_S, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		GameOver gameOver = null;
		if (CREDITS > 0) {
			gameOver = new GameOver("imgs/gameOver.png", this.keyboard);
		}
		
		else {
			gameOver = new GameOver("imgs/gameOverExit.png", this.keyboard);
		}
		
		boolean execute = true;
		
		Menu.sound.stop();
		Menu.sound = new Sound("audios/gameOver.wav");
		Menu.sound.play();
		while (execute) {
			
			if (CREDITS > 0) {
				
				gameOver.draw();
				
				if (gameOver.controllerGameOver() == 2) {
					SCORE = 0;
					CREDITS = 5;
					Menu.countStage = -1;
					initMenu(window);
				}
				
				else if (gameOver.controllerGameOver() == 1) {
					
					if (Menu.countStage == -1) {
						
						Menu.countStage = -1;
						SCORE = 0;					
						this.windowUpdateStageExecute("imgs/background_1.png", "imgs/enemies_1.png", "imgs/stage_1_clear.png",  "audios/stage_1.wav", "imgs/stage_1.png");
						
					}
					
					else {
						
						SCORE = 0;
						this.windowUpdateStageExecute("imgs/"+BACKGROUNDS[Menu.countStage], "imgs/"+ENEMIES[Menu.countStage], "imgs/"+STAGESCLEARES[Menu.countStage], "audios/"+SOUNDSTAGES[Menu.countStage], "imgs/"+STAGES[Menu.countStage]);
					}
				}
				
			}
			
			else {
				
				gameOver.drawGameOverExit();
				
				if (gameOver.controllerGameOverExit()) {
					CREDITS = 5;
					SCORE = 0;
					Menu.countStage = -1;
					initMenu(window);
				}
			}
			Font font = new Font("Courier new", Font.BOLD, 20);
			this.window.drawText("Credits: "+CREDITS, 340, 560, Color.WHITE, font);
			this.window.display();
		}
	}
	
	public static void initMenu(Window window) {
		Menu.sound.stop();
		Menu menu = new Menu("imgs/initialMenu.png", "imgs/point.png", 3, window);
		boolean execute = true;
		while (execute) {
			
			menu.controllerMenu();
			window.display();	
		}
	}
	
	private void windowsCongratulations() {
		Menu.sound.stop();
		Menu.sound = new Sound("audios/congratulations.wav");
		Menu.sound.play();
		Menu.sound.setRepeat(true);
		Congratulations cg = new Congratulations("imgs/congratulations.png", Menu.SELECT_AIRPLANE, this.keyboard);
		boolean execute = true;
		while (execute) {
			
			cg.congratulationsDraw();
			if (cg.controllerCongratulations()) {
				Menu.countStage = -1;
				All_SCORES = 0;
				initMenu(window);
			}
			
			Font font = new Font("Courier new", Font.BOLD, 40);
			this.window.drawText(""+All_SCORES, 220, 245, Color.WHITE, font);
			this.window.drawText(""+CREDITS, 220, 308, Color.WHITE, font);
			this.window.display();
		}
	}
}
