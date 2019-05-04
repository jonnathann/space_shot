package br.com.jonnathann.space_shot;

import JPlay.GameImage;
import JPlay.Window;

public class InitLogo{
	
	private GameImage backgroundEmpty;
	private GameImage logo;
	private Window window;
	
	public InitLogo(Window window) {
		this.window = window;
		this.logo1("imgs/windowEmptyLogo.png", "imgs/javaLogo.png");
		
		
	}
	
	private void logo2(String background, String logo) {
		
		this.backgroundEmpty = new GameImage(background);
		this.logo = new GameImage(logo);
		this.logo.setPosition(250, 100);
		this.logo.height = 0;
		boolean execute = true;
		
		int count = 0;
		while (execute) {
			
			this.backgroundEmpty.draw();
			this.logo.draw();
			
			if (count < 331) {
				this.logo.height = count;
				
				if (count == 330) {
				
					this.initMenu(this.window);
					
				}
				count++;
			}
			this.window.display();
				
			
		}
		
	}
	
	
	private void logo1(String background, String logo) {
		
		this.backgroundEmpty = new GameImage(background);
		this.logo = new GameImage(logo);
		this.logo.setPosition(250, 100);
		this.logo.height = 0;
		boolean execute = true;
		
		int count = 0;
		while (execute) {
			
			this.backgroundEmpty.draw();
			this.logo.draw();
			
			if (count < 433) {
				this.logo.height = count;
				
				if (count == 432) {
				
					this.logo2("imgs/windowEmptyLogoBlack.png", "imgs/faizaoLogo.png");
					
				}
				count++;
			}
			this.window.display();
				
			
		}
	}
	
	private void initMenu(Window window) {
		Menu menu = new Menu("imgs/initialMenu.png", "imgs/point.png", 3, window);
		boolean execute = true;
		while (execute) {
			
			menu.controllerMenu();
			window.display();	
		}
	}

}
