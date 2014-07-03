package armsandarmour;
/*
 *	Armour class: an equippable class which increases the defense. It can't be equipped along with a wand 
 */
public class Armour extends ArmsandArmour{
	private int intDefense;
	
	public Armour(String name, String description, int defense){
		super(name, description);
		intDefense = defense;
	}
	
	public int getIntDefense(){
		return intDefense;
	}
	
	public String toString(){
		return super.getStrName() + "\t" + intDefense;
	}
}
