package fight;
/*
 * 	Fighter class: a superclass for monster and player
 */
import java.util.Random;

public abstract class Fighter {

	public abstract int getIntDefense();
	public abstract int getIntDamage();
	public abstract String getStrHealth();
	public abstract int getIntHealth();
	public abstract void subtractHealth(int damage);
	public abstract void setZeroHealth();
	
	public int calculateDamage(int min, int max){
		Random rand = new Random();
		int output = rand.nextInt(max-min+1);
		return output+min;
	}
}
