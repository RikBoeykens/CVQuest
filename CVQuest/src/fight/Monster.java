package fight;
/*
 * 	Monster class: contains information on the monster including a container class for dropped items when it dies
 */
import inventory.Container;

public class Monster extends Fighter{
	private String strName;
	private int intHealth;
	private int intMinAttack;
	private int intMaxAttack;
	private int intDefense;
	private Container container;
	private String strIntro;
	private String strDeathMonster;
	private int intTrapped = 0;
	
	public Monster(String name, String intro, String death, int health, int min, int max, int defense, Container contain){
		strName = name;
		strIntro = intro;
		strDeathMonster = death;
		intHealth = health;
		intMinAttack = min;
		intMaxAttack = max;
		intDefense = defense;
		container = contain;
	}

	public String getStrName() {
		return strName;
	}

	public String getStrIntro(){
		return strIntro;
	}
	
	public String getStrDeathMonster(){
		return strDeathMonster;
	}
	
	public int getIntHealth(){
		return intHealth;
	}
	
	public void subtractHealth(int damage){
		intHealth-=damage;
		return; 
	}
	
	public void setZeroHealth(){
		intHealth = 0;
		return;
	}
	
	public int getIntDamage() {
		return super.calculateDamage(intMinAttack, intMaxAttack);
	}
	
	public int getIntDefense(){
		return intDefense;
	}
	public boolean trapped(){
		return intTrapped>0;
	}
	
	public void addTrapped(int newNet){
		intTrapped += newNet;
	}
	
	public void subtractTrapped(int damage){
		if (intTrapped-damage<0){
			intTrapped = 0;
		}else{
			intTrapped -= damage;
		}
	}

	public String getStrHealth(){
		String output = "";
		for (int i = 0; i<intHealth;i++){
			output += "|";
		}
		return output;
	}
	
	public boolean isAlive(){
		return (intHealth>0);
	}
	
	public Container getContainer() {
		return container;
	}
	
	public String toString(){
		return strName + "\t" + getStrHealth();
	}
}
