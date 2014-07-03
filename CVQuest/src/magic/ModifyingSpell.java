package magic;
/*
 * 	ModifyingSpell class: multiplies the effects of any spell
 */
public class ModifyingSpell extends Spell{
	private int intMultiplier;
	private String strEffect;
	
	public ModifyingSpell(String descript, int multiplier){
		super(descript);
		intMultiplier = multiplier;
		strEffect = "for(int i = 0;i<"+multiplier+";i++)";
	}

	public int getIntMultiplier() {
		return intMultiplier*super.getModifyingValue();
	}
	
	public String getStrEffect(){
		return super.getStrModifyingEffect() + strEffect;
	}
	
	public String getStrName(){
		return "Spell of " + getStrEffect();
	}
	
	public String toString(){
		return getStrName() + "\t" + "x" +intMultiplier;
	}
}
