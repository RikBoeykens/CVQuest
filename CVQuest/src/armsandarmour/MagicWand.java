package armsandarmour;
/*
 * 	MagicWand class: a magic wand which gives allows the user to use spells. It can't be equipped with other arms and armour
 */
public class MagicWand extends ArmsandArmour{
	public MagicWand(String name, String description){
		super(name, description);
	}
	
	public String toString(){
		return super.getStrName();
	}
}
