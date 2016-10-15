import java.util.ArrayList;


public class pFire {
	
	private int health = 16;
	private int level = 1;
	private String name = "Undecided";
	private String type = "fire";
	private ArrayList<Abilities> moveSet = new ArrayList<Abilities>();
	private int runCount = 5;
	private Abilities move1 = new Abilities("Body Slam", "normal", 5, null);
	private Abilities move2 = new Abilities("Flame Thrower", "fire", 4, null);
	private Abilities move3 = new Abilities("Flame Wheel", "fire", 6, null);
	private Abilities move4 = new Abilities("Volcano", "fire", 8, null);
	
	public pFire(String name){
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
				"\nResistant to fire, grass, and ice type attacks."+
				"\nWeak against water and rock type attacks.";
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
