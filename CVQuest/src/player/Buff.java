package player;
/*
 * 	Buff class: this is the superclass for buffs which improve a character's strength or defense for a set time
 */
public abstract class Buff {
	private String strEffect;
	public int intTime;
	
	public Buff(String effect, int time){
		strEffect = effect;
		intTime = time;
	}

	public String getStrEffect() {
		return strEffect;
	}
	
}
