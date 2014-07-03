package movement;
/*
 * 	Map class: has all the information on the rooms on the map
 */
import java.util.ArrayList;

public class Map {
	private ArrayList<Room> map = new ArrayList<Room>();
	
	Map(Room...rooms){
		for (Room aRoom:rooms){
			map.add(aRoom);
		}
	}
	public boolean roomExists(int x, int y){
		for (Room room: map){
			if (room.getX()==x&&room.getY()==y){
				return true;
			}
		}
		return false;
	}
	public Room getRoom(int x, int y){
		for (Room room: map){
			if (room.getX()==x&&room.getY()==y){
				return room;
			}
		}
		return null;
	}
	public ArrayList<Room>getMap(){
		return map;
	}
	public int highestX(){
		int output = 0;
		for (Room rooms:map){
			if (rooms.getX()>output){
				output = rooms.getX();
			}
		}
		return output;
	}
	public int highestY(){
		int output = 0;
		for (Room rooms:map){
			if (rooms.getY()>output){
				output = rooms.getY();
			}
		}
		return output;
	}
}
