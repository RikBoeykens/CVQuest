package magic;
/*
 * 	Spell class: the superclass for all spells
 */
import inventory.Item;

public abstract class Spell extends Item{
	private ModifyingSpell modifier = null;
	
	public Spell(String descript){
		super("Spell", descript);
	}
	
	public ModifyingSpell getModifyingSpell(){
		return modifier;
	}

	public void setModifyingSpell(ModifyingSpell spell){
		modifier = spell;
	}
	
	public boolean hasModifier(){
		return modifier!=null;
	}
	
	public String getStrModifyingEffect(){
		return hasModifier()?modifier.getStrEffect():"";
	}
	
	public int getModifyingValue(){
		return hasModifier()?modifier.getIntMultiplier():1;
	}
	
	public String toString(){
		return super.getStrName();
	}
	
}
