package movement;
/*
 * 	Map class: has all the information on the rooms on the map
 */

import java.util.HashMap;

public class Map {
	private HashMap<String, Room> map = new HashMap<String, Room>();
	private int intHighestX;
	private int intHighestY;
	
	Map(Room...rooms){
		intHighestX = 0;
		intHighestY = 0;
		for (Room aRoom:rooms){
			map.put(aRoom.getCoordinates().getStrCoordinates(), aRoom);
			intHighestX = Math.max(intHighestX, aRoom.getCoordinates().getX());
			intHighestY = Math.max(intHighestY, aRoom.getCoordinates().getY());
		}
	}
	public boolean roomExists(Coordinates coordinates){
		return map.containsKey(coordinates.getStrCoordinates());
	}
	public Room getRoom(Coordinates coordinates){
		return map.get(coordinates.getStrCoordinates());
	}
	public HashMap<String, Room>getMap(){
		return map;
	}
	
	public int highestX(){
		return intHighestX;
	}
	public int highestY(){
		return intHighestY;
	}
}
