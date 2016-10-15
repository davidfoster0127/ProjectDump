import java.util.ArrayList;


public class pGrass {
	
	private int health = 20;
	private int level = 1;
	private String name = "Undecided";
	private String type = "grass";
	private ArrayList<Abilities> moveSet = new ArrayList<Abilities>();
	private int runCount = 5;
	private Abilities move1 = new Abilities("Cut", "normal", 5, null);
	private Abilities move2 = new Abilities("Grass Blades", "grass", 4, null);
	private Abilities move3 = new Abilities("Razor Leaf", "grass", 6, null);
	private Abilities move4 = new Abilities("Wild Growth", "grass", 8, null);
	
	public pGrass(String name){
		this.name = name;
		moveSet.add(move1);
	}
	
	public void levelUp(){
		level++;
		health+=4;
		if (level == 2)
			moveSet.add(move2);
		if (level == 4)
			moveSet.add(move3);
		if (level == 6)
			moveSet.add(move4);		
	}
	
	public void run(){
			runCount--;
		}
	
	public String toString(){
		String details;
		details = "Name: "+name+
				"\nType: "+type+
				"\nLevel: "+level+
				"\nHealth: "+health+
				"\nCurrent Moves: "+moveSet+     //may need tweaking to get to display the way i want
				"\nResistant to water, grass, rock and electric type attacks."+
				"\nWeak against fire, ice, poison and bird type attacks.";
		return details;
		
	}
	
	public int getRunCount() {
		return runCount;
	}
	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Abilities> getMoveSet() {
		return moveSet;
	}
	public void setAttacks(ArrayList<Abilities> moveSet) {
		this.moveSet = moveSet;
	}

}
