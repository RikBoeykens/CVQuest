package fight;
/*
 * 	Fighter class: a superclass for monster and player
 */
import java.util.Random;

public abstract class Fighter {

	public int getIntDefense(){return 0;}
	public int getIntDamage(){return 0;}
	public String getStrHealth(){return"";}
	public int getIntHealth(){return 0;}
	public void subtractHealth(int damage){return;}
	public void setZeroHealth(){return;}
	
	public int calculateDamage(int min, int max){
		Random rand = new Random();
		int output = rand.nextInt(max-min+1);
		return output+min;
	}
}
