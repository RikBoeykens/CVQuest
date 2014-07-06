package movement;
/*
 * 	Room class: this can hold a monster or container
 */
import inventory.*;
import fight.Monster;

public class Room {
	private Coordinates coordinates;
	private String strDescription;
	public boolean explored;
	private Container container;
	private Monster monster;
	private Separation north;
	private Separation east;
	private Separation south;
	private Separation west;
	

	public Room(int intX, int intY, String descript, Separation n, Separation e, Separation s, Separation w, Container c, Monster m){
		coordinates = new Coordinates(intX, intY);
		strDescription = descript;
		north = n;
		east = e;
		south = s;
		west = w;
		explored = false;
		container = c;
		monster = m;
	}

	public Coordinates getCoordinates(){
		return coordinates;
	}
	
	public void onArrival(){
		explored = true;
	}

	public Container getContainer(){
		return container;
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	public boolean hasMonster(){
		return (monster!=null);
	}
	
	public boolean monsterIsAlive(){
		if (hasMonster()){
			return monster.isAlive();
		}
		return false;
	}
	
	public Separation getNorth() {
		return north;
	}

	public Separation getEast() {
		return east;
	}

	public Separation getSouth() {
		return south;
	}

	public Separation getWest() {
		return west;
	}
	public boolean containerAvailable(){
		if(container!=null){
			return !container.isEmpty();
		}
		return false;
	}
	
	public boolean containerUnopened(){
		if(container!=null){
			if (!container.isEmpty()){
				return true;
			}
		}
		if(monster!=null){
			if(monster.getContainer()!=null){
				return !monster.getContainer().isEmpty();
			}
		}
		return false;
	}
	
	public String toString(){
		String output = strDescription;
		if (container!=null){
			output+=" " + container.getStrDescription();
		}
		if (hasMonster()&&!monster.isAlive()){
			output +=" " + monster.getStrDeathMonster();
		}
		return output;
	}
}
