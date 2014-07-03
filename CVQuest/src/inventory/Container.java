package inventory;
/*
 * 	Container class: can contain different items
 */
import java.util.ArrayList;

public class Container {
	private ArrayList<Item>items = new ArrayList<Item>();
	private String strDescription;
	private String strName;
	
	public Container(String name, String description, Item...input){
		strName = name;
		strDescription = description;
		for (Item anItem:input){
			items.add(anItem);
		}
	}
	
	public String getStrName(){
		return strName;
	}
	
	public String getStrDescription(){
		if(items.isEmpty()){
			return strDescription + " It is empty.";
		}
		return strDescription;
	}
	
	public ArrayList<Item>getItems(){
		return items;
	}
	
	public boolean isEmpty(){
		return items.isEmpty();
	}
}
