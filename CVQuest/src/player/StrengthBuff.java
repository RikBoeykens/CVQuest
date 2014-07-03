package player;
/*
 * 	StrengthBuff class: adds a damage bonus for a set amount of time
 */
public class StrengthBuff extends Buff{
	public int intDamage;
	
	public StrengthBuff(String effect, int time, int damage){
		super(effect, time);
		intDamage = damage;
	}
	
	public int getIntDamage(){
		return intDamage;
	}
}
