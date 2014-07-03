package movement;
/*
 * 	Passage class: a passage that the player can pass without any problems
 */
public class Passage extends Separation{
	Passage(String description){
		super(description);
	}
	
	public String getStrMap(){
		return "  ";
	}
}
