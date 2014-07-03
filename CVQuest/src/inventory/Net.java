package inventory;
/*
 * 	Net class: A net can be thrown at a monster. The monster will get trapped and has to do damage to the net until it's gone
 */
public class Net extends Item{
	private int intNet;
	
	public Net(String name, String description, int net){
		super(name, description);
		intNet = net;
	}
	public int getNet(){
		return intNet;
	}
}
