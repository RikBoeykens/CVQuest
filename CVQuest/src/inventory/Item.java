package inventory;
/*
 *	Item class: a superclass for everything that can be picked up and stored away
 */
public abstract class Item {
	private String strName;
	private String strDescription;
	
	public Item(String name, String descript){
		strName = name;
		strDescription = descript;
	}
	
	public String getStrName(){
		return strName;
	}
	
	public String getStrDescription(){
		return strDescription;
	}
	
}
