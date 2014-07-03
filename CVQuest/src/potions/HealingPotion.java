package potions;
/*
 * 	HealingPotion: heals the player
 */
import inventory.Item;

public class HealingPotion extends Item{
	private int intHeal;
	
	public HealingPotion(String name, String description, int heal){
		super(name, description);
		intHeal = heal;
	}
	
	public int getIntHeal(){
		return intHeal;
	}
	
	public String toString(){
		return super.getStrName() + "\t" + intHeal;
	}
}
