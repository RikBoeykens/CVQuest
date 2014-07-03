package armsandarmour;
/*
 * 	Shield class: an equippable class which increases the defense. It can't be equipped along with a wand.
 */
public class Shield extends ArmsandArmour{
	private int intDefense;
	
	public Shield(String name, String description, int defense){
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
