package inventory;
/*
 * 	Note class: an item which can hold text.
 */
import main.Confirm;

public class Note extends Item{
	private String strContents;
	
	public Note(String name, String description, String contents){
		super(name, description);
		strContents = contents;
	}

	public String getStrContents() {
		return strContents;
	}
	
	public void readNote(){
		System.out.println(strContents);
		Confirm.pressEnter("<Press enter to continue>");
	}
	
}
