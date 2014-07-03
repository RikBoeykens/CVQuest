package armsandarmour;
/*
 * 	Weapon class: a weapon can do damage to a monster. Each weapon has stats for min and max damage
 */
public class Weapon extends ArmsandArmour{
	private int intMinDamage;
	private int intMaxDamage;
	
	public Weapon (String name, String description, int min, int max){
		super(name, description);
		intMinDamage = min;
		intMaxDamage = max;
	}
	
	public int getIntMinDamage(){
		return intMinDamage;
	}
	
	public int getIntMaxDamage(){
		return intMaxDamage;
	}
	
	public String toString(){
		return super.getStrName() + "\t" + intMinDamage + " - " + intMaxDamage;
	}
}
