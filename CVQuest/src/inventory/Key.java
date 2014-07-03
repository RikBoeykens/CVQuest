package inventory;
/*
 * 	Key class: can be used to open doors if the code corresponds to the code on the door
 */
public class Key extends Item{
	private String strCode;
	
	public Key(String name, String description, String code){
		super (name, description);
		strCode = code;
	}

	public String getStrCode() {
		return strCode;
	}
	
}
