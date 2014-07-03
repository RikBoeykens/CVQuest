package magic;
/*
 * 	Spellbook class: this class holds all the spells the player has
 */
import java.util.ArrayList;

public class Spellbook {
	private ArrayList<HealingSpell> healingspells= new ArrayList<HealingSpell>();
	private ArrayList<AttackSpell>attackspells = new ArrayList<AttackSpell>();
	private ArrayList<ModifyingSpell>modifyingspells = new ArrayList<ModifyingSpell>();
	private FunctionSpell functionspell;
	
	public Spellbook(){
		functionspell = null;
	}
	public ArrayList<HealingSpell>getHealingSpells(){
		return healingspells;
	}
	
	public ArrayList<AttackSpell>getAttackSpells(){
		return attackspells;
	}
	
	public ArrayList<ModifyingSpell>getModifyingSpells(){
		return modifyingspells;
	}
	
	public FunctionSpell getFunctionSpell(){
		return functionspell;
	}
	
	public void setFunctionSpell(FunctionSpell spell){
		functionspell = spell;
	}
	
	public boolean functionSpellHasFunctions(){
		return functionspell!=null?functionspell.functionsAvailable():false;
	}

	public boolean spellsAvailable(){
		return !healingspells.isEmpty()||!attackspells.isEmpty()||!modifyingspells.isEmpty()||functionSpellHasFunctions();
	}
}
