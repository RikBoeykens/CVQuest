package movement;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int intX, int intY){
		x = intX;
		y = intY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getStrCoordinates(){
		return x + "-" + y;
	}
	
	public void increaseX(){
		x++;
	}
	
	public void decreaseX(){
		x--;
	}
	
	public void increaseY(){
		y++;
	}
	
	public void decreaseY(){
		y--;
	}
}
