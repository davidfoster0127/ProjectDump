
public class Abilities {
	private String type;
	private int damage;
	private String effect;
	private String name;

	public Abilities(String name, String type, int damage, String effect){
		this.type = type;
		this.damage = damage;
		this.effect = effect;
		this.name = name;
	}
	
	public int attack(String targetType){
		int damageApplied = 0;
		switch (targetType){
		case ("fire"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage-1;
			break;
			case ("water"):
				damageApplied +=this.damage+2;
			break;
			case ("grass"):
				damageApplied +=this.damage-1;
			break;
			case ("ice"):
				damageApplied +=this.damage-1;
			break;
			case ("poison"):
				damageApplied +=this.damage;
			break;
			case ("rock"):
				damageApplied +=this.damage+2;
			break;
			case ("electric"):
				damageApplied +=this.damage;
			break;
			case ("bird"):
				damageApplied +=this.damage;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("water"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage-1;
			break;
			case ("water"):
				damageApplied +=this.damage-1;
			break;
			case ("grass"):
				damageApplied +=this.damage+2;
			break;
			case ("ice"):
				damageApplied +=this.damage-1;
			break;
			case ("poison"):
				damageApplied +=this.damage;
			break;
			case ("rock"):
				damageApplied +=this.damage;
			break;
			case ("electric"):
				damageApplied +=this.damage+2;
			break;
			case ("bird"):
				damageApplied +=this.damage;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("grass"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage+2;
			break;
			case ("water"):
				damageApplied +=this.damage-1;
			break;
			case ("grass"):
				damageApplied +=this.damage-1;
			break;
			case ("ice"):
				damageApplied +=this.damage-1;
			break;
			case ("poison"):
				damageApplied +=this.damage+2;
			break;
			case ("rock"):
				damageApplied +=this.damage-1;
			break;
			case ("electric"):
				damageApplied +=this.damage-1;
			break;
			case ("bird"):
				damageApplied +=this.damage+2;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("ice"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage+2;
			break;
			case ("water"):
				damageApplied +=this.damage-1;
			break;
			case ("grass"):
				damageApplied +=this.damage;
			break;
			case ("ice"):
				damageApplied +=this.damage;
			break;
			case ("poison"):
				damageApplied +=this.damage;
			break;
			case ("rock"):
				damageApplied +=this.damage+2;
			break;
			case ("electric"):
				damageApplied +=this.damage;
			break;
			case ("bird"):
				damageApplied +=this.damage;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("poison"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage;
			break;
			case ("water"):
				damageApplied +=this.damage;
			break;
			case ("grass"):
				damageApplied +=this.damage-1;
			break;
			case ("ice"):
				damageApplied +=this.damage;
			break;
			case ("poison"):
				damageApplied +=this.damage-1;
			break;
			case ("rock"):
				damageApplied +=this.damage+2;
			break;
			case ("electric"):
				damageApplied +=this.damage;
			break;
			case ("bird"):
				damageApplied +=this.damage;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("rock"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage-1;
			break;
			case ("water"):
				damageApplied +=this.damage+2;
			break;
			case ("grass"):
				damageApplied +=this.damage+2;
			break;
			case ("ice"):
				damageApplied +=this.damage+2;
			break;
			case ("poison"):
				damageApplied =0;
			break;
			case ("rock"):
				damageApplied +=this.damage-1;
			break;
			case ("electric"):
				damageApplied =0;
			break;
			case ("bird"):
				damageApplied +=this.damage-1;
			break;
			case ("normal"):
				damageApplied +=this.damage-1;
			break;
			}
		break;
		case ("electric"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage;
			break;
			case ("water"):
				damageApplied +=this.damage;
			break;
			case ("grass"):
				damageApplied +=this.damage;
			break;
			case ("ice"):
				damageApplied +=this.damage;
			break;
			case ("poison"):
				damageApplied +=this.damage;
			break;
			case ("rock"):
				damageApplied +=this.damage+2;
			break;
			case ("electric"):
				damageApplied +=this.damage-1;
			break;
			case ("bird"):
				damageApplied +=this.damage-1;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		case ("bird"):
			switch (this.type){
			case ("fire"):
				damageApplied += this.damage;
			break;
			case ("water"):
				damageApplied +=this.damage;
			break;
			case ("grass"):
				damageApplied +=this.damage-1;
			break;
			case ("ice"):
				damageApplied +=this.damage+2;
			break;
			case ("poison"):
				damageApplied +=this.damage;
			break;
			case ("rock"):
				damageApplied +=this.damage+2;
			break;
			case ("electric"):
				damageApplied +=this.damage+2;
			break;
			case ("bird"):
				damageApplied +=this.damage;
			break;
			case ("normal"):
				damageApplied +=this.damage;
			break;
			}
		break;
		
		}
		return damageApplied;
	}

	
	
	
	
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}


}
