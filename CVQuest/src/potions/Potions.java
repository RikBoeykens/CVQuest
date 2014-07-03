package potions;
/*
 * 	Potions class: Holds all the potions the player has. Taught by Severus Snape
 */
import java.util.ArrayList;

public class Potions {
	private ArrayList<HealingPotion>healingpotions = new ArrayList<HealingPotion>();
	private ArrayList<DefensePotion>defensepotions = new ArrayList<DefensePotion>();
	private ArrayList<StrengthPotion>strengthpotions = new ArrayList<StrengthPotion>();
	
	public Potions(){
		
	}

	public ArrayList<HealingPotion> getHealingpotions() {
		return healingpotions;
	}

	public ArrayList<DefensePotion> getDefensepotions() {
		return defensepotions;
	}

	public ArrayList<StrengthPotion> getStrengthpotions() {
		return strengthpotions;
	}
	
	public boolean isEmpty(){
		return healingpotions.isEmpty()&&defensepotions.isEmpty()&&strengthpotions.isEmpty();
	}
}
