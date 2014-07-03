package movement;
/*
 * 	Wall class: a wall that is unpassable
 */
public class Wall extends Separation{
	Wall(String description){
		super(description);
	}
	public boolean canPass(){
		return false;
	}
	
	public String getStrMap(){
		return "##";
	}
}
