package inventory;
/*
 * 	Equipped class: Holds the items that are currently equipped
 */
import armsandarmour.Armour;
import armsandarmour.MagicWand;
import armsandarmour.Shield;
import armsandarmour.Weapon;


public class Equipped {
	private Armour armour;
	private Shield shield;
	private Weapon weapon;
	private MagicWand magicwand;
	
	public Equipped(){
		armour = null;
		shield = null;
		weapon = null;
		magicwand = null;
	}
	
	public Armour getArmour() {
		return armour;
	}
	public void unequipArmour(){
		armour = null;
		return;
	}

	public Shield getShield() {
		return shield;
	}

	public void unequipShield(){
		shield = null;
		return;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	
	public String getStrWeapon(){
		if (weapon!=null){
			return weapon.toString();
		}
		return "bare hands\t1-1";
	}

	
	public void setArmour(Armour newArmour){
		armour = newArmour;
	}
	
	public void setShield(Shield newShield){
		shield = newShield;
	}
	
	public void setWeapon(Weapon newWeapon){
		weapon = newWeapon;
	}
	
	public void resetMagicWand(){
		magicwand = null;
	}
	
	public MagicWand getMagicWand(){
		return magicwand;
	}
	
	public void setMagicWand(MagicWand newWand){
		magicwand = newWand;
	}
	

	
	public String toString(){
		String output = "";
		if (magicwand!=null){
			output += "\t" + magicwand.toString() + "\n";

		}else{
			output = "\tWeapon: ";
			if (weapon == null){
				output+="bare hands\n";
			}else{
				output+=weapon.toString() + "\n";
			}
			output += "\tShield: ";
			if(shield==null){
				output+="no shield equipped\n";
			}else{
				output+=shield.toString() + "\n";
			}
			output += "\tArmour: ";
			if(armour ==null){
				output+="no armour equipped\n";
			}else{
				output += armour.toString()+"\n";
			}
		}
		return output;
	}
	public boolean magicWandEquipped(){
		return (magicwand!=null);
	}
}
