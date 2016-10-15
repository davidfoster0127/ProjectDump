import java.util.ArrayList;


public class eWater {
	
	private int health = 15;
	private int level;
	private String type = "water";
	private String name = "Water Monster";
	private ArrayList<Abilities> moveSet = new ArrayList<Abilities>();
	private Abilities move1 = new Abilities("Pound", "normal", 4, null);
	private Abilities move2 = new Abilities("Water Gun", "water", 3, null);
	private Abilities move3 = new Abilities("Whirlpool", "water", 5, null);
	private Abilities move4 = new Abilities("Tsunami", "water", 7, null);
	
	public eWater(int pLevel){
		this.level = pLevel;
		health+=((level-1)*3);
		if (level >= 6){
			moveSet.add(move2);
			moveSet.add(move3);
			moveSet.add(move4);
		}
		else if (level >= 4){
			moveSet.add(move2);
			moveSet.add(move3);
		}
		else if (level >= 2)
			moveSet.add(move2);	
		moveSet.add(move1);
	}
	
	public String toString(){
		String details;
		details = name+
				"\nResistant to fire, water and ice type attacks."+
				"\nWeak against grass and electric type attacks.";
		return details;
		
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Abilities> getMoveSet() {
		return moveSet;
	}
	public void setAttacks(ArrayList<Abilities> moveSet) {
		this.moveSet = moveSet;
	}

}
