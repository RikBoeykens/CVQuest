package player;
/*
 * 	DefenseBuff class: adds a defense bonus for a set amount of time
 */
public class DefenseBuff extends Buff{
	private int intDefense;
	
	public DefenseBuff(String effect, int time, int defense){
		super(effect, time);
		intDefense = defense;
	}
	
	public int getIntDefense(){
		return intDefense;
	}
}
