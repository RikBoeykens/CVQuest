package magic;
/*
 * 	HealingSpell class: Heals the player
 */
public class HealingSpell extends Spell{
	private int intHeal;
	
	public HealingSpell(String descript, int heal){
		super(descript);
		intHeal = heal;
	}
	
	public int getIntHeal(){
		return super.getModifyingValue()*intHeal;
	}
	
	public String toString(){
		return getStrName() + "\t" + "heal " + getIntHeal();
	}
	
	public String getStrName(){
		return "Spell of " +super.getStrModifyingEffect()+ "player.intHealth+=" + intHeal;
	}
}
