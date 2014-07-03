package magic;
/*
 * 	FunctionSpell class: this class adds functions together so they can be used together in one turn
 */
import java.util.ArrayList;

public class FunctionSpell extends Spell{
	private ArrayList<HealingSpell>healingspells = new ArrayList<HealingSpell>();
	private ArrayList<AttackSpell>attackspells = new ArrayList<AttackSpell>();
	private String strFunctionName = "-empty-";
	
	public FunctionSpell(String description){
		super(description);
	}
	
	public String getStrName(){
		return "Spell of " + super.getStrModifyingEffect() + "Function " + strFunctionName + " ()";
	}
	
	public void setStrFunctionName(String name){
		strFunctionName = name;
	}
	
	public ArrayList<HealingSpell>getHealingSpells(){
		return healingspells;
	}
	
	public ArrayList<AttackSpell>getAttackSpells(){
		return attackspells;
	}
	
	public void addHealingSpell(HealingSpell spell){
		healingspells.add(spell);
	}
	
	public void addAttackSpell(AttackSpell spell){
		attackspells.add(spell);
	}
	
	public boolean healingSpellEquipped(HealingSpell spell){
		for (HealingSpell healingspell: healingspells){
			if (healingspell.equals(spell)){
				return true;
			}
		}
		return false;
	}
	
	public boolean attackSpellEquipped(AttackSpell spell){
		for (AttackSpell attackspell: attackspells){
			if (attackspell.equals(spell)){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean functionsAvailable(){
		return !healingspells.isEmpty()||!attackspells.isEmpty();
	}
	
	public int getIntHeal(){
		int intOutput = 0;
		for (HealingSpell spell: healingspells){
			intOutput+=spell.getIntHeal();
		}
		intOutput *=super.getModifyingValue();
		return intOutput;
	}
	public int getIntDamage(){
		int intOutput = 0;
		for (AttackSpell spell: attackspells){
			intOutput += spell.getIntDamage();
		}
		intOutput *=super.getModifyingValue();
		return intOutput;
	}
	public String getStrFunctions(){
		String output = "Available functions\n";
		for (HealingSpell spell: healingspells){
			output += "\t" + spell.toString() + "\n";
		}
		output += "\n";
		for (AttackSpell spell: attackspells){
			output += "\t" + spell.toString() + "\n";
		}
		return output;
	}
}
