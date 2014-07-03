package movement;
/*
 * 	Door class: A door which is initially locked but can be opened with a key
 */
import inventory.*;

public class Door extends Separation{
	private boolean locked;
	private String unlockCode;
	
	Door(String description, String unlock){
		super(description);
		unlockCode = unlock;
		locked = true;
	}
	
	public boolean canPass(){
		return !locked;
	}
	
	public boolean unLock(Key key){
		if (key.getStrCode().equals(unlockCode)){
			locked = false;
			return true;
		}
		return false;
	}
	
	public String getStrMap(){
		return locked?"@@":"  ";
	}
}
