package movement;
/*
 * 	Separation class: the superclass for what's connecting rooms, be it walls, passages or doors
 */
import inventory.*;

public abstract class Separation {
	private String strDescription;
	
	Separation(String description){
		strDescription = description;
	}
	public boolean canPass(){
		return true;
	}
	
	public boolean unLock(Key key){
		return false;
	}
	
	public String getDescription(){
		return strDescription;
	}
	
	public String getStrMap(){
		return "##";
	}
}
