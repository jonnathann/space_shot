package Game;

public class Stage {
	
	private Scenario scenario_1;
	private Scenario scenario_2;
	
	public Stage(String image) {
		
		scenario_1 = new Scenario(image);
		scenario_2 = new Scenario(image);
		scenario_2.y = -570;
		
	}
	
	public void update() {
		
		//Paralax
		scenario_1.draw();
		scenario_2.draw();
		
		if (scenario_2.y == 0) {
			scenario_1.y = -570;
			
		}
		
		if (scenario_1.y == 0) {
			scenario_2.y = -570;
		}
		
		scenario_1.y+=1.5;
		scenario_2.y+=1.5;
	}
}
