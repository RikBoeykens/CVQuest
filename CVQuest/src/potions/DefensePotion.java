package potions;
/*
 * 	DefensePotion class: adds a defense buff for the player for a set amount of time
 */
import inventory.Item;

public class DefensePotion extends Item{
	private int intDefense;
	private int intTime;
	private String strEffect;
	
	public DefensePotion(String name, String description, int defense, String effect, int time){
		super(name, description);
		intDefense = defense;
		strEffect = effect;
		intTime = time;
	}

	public int getIntDefense() {
		return intDefense;
	}

	public String getStrEffect() {
		return strEffect;
	}

	public int getIntTime() {
		return intTime;
	}
	public String toString(){
		return super.getStrName() + "\t" + intDefense + " ( " + intTime + " turns)"; 
	}
	
}
