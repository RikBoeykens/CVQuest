package magic;
/*
 * 	Spell used to attack a monster
 */
public class AttackSpell extends Spell{
	private int intDamage;
	
	public AttackSpell(String descript, int damage){
		super(descript);
		intDamage = damage;
	}
	
	public int getIntDamage(){
		return intDamage*super.getModifyingValue();
	}
	
	public String toString(){
		return getStrName() + "\t" + "attack " + getIntDamage();
	}
	
	public String getStrName(){
		return "Spell of " +super.getStrModifyingEffect()+ "monster.intHealth-=" + intDamage;
	}
}
