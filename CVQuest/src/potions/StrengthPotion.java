package potions;
/*
 * 	StrengthPotion class: adds a strenght buff to the player
 */
import inventory.Item;

public class StrengthPotion extends Item{
	private int intDamage;
	private String strEffect;
	private int intTime;
	
	public StrengthPotion(String name, String description, int damage, String effect, int time){
		super(name, description);
		intDamage = damage;
		strEffect = effect;
		intTime = time;
	}

	public int getIntDamage() {
		return intDamage;
	}

	public String getStrEffect() {
		return strEffect;
	}

	public int getIntTime() {
		return intTime;
	}
	
	public String toString(){
		return super.getStrName() + "\t" + intDamage + " ( " + intTime + " turns)"; 
	}
	
}
