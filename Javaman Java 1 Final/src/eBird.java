import java.util.ArrayList;


public class eBird {
	
	private int health = 15;
	private int level;
	private String type = "bird";
	private String name = "Bird Monster";
	private ArrayList<Abilities> moveSet = new ArrayList<Abilities>();
	private Abilities move1 = new Abilities("Swipe", "normal", 4, null);
	private Abilities move2 = new Abilities("Peck", "bird", 3, null);
	private Abilities move3 = new Abilities("Wing Attack", "bird", 5, null);
	private Abilities move4 = new Abilities("Feather Fury", "bird", 7, null);
	
	public eBird(int pLevel){
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
				"\nResistant to grass type attacks."+
				"\nWeak against ice, rock and electric type attacks.";
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
