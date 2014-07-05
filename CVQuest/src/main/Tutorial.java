package main;

public class Tutorial {
	public Tutorial(){
		
	}
	public void showTutorial(){

		System.out.println("THE MAP");
		System.out.println("======######======");
		System.out.println("======@@    ======");
		System.out.println("======##  ##======");
		System.out.println("########  ##======");
		System.out.println("#!      ()  ======");
		System.out.println("############======");
		System.out.println();
		System.out.println("(): this is you");
		System.out.println("##: this is a wall");
		System.out.println("@@: this is a locked door");
		System.out.println("==: this part of the map is undiscovered");
		System.out.println("! : this means there is an unopened chest");
		Confirm.pressEnter("<Please press enter to continue>");
		
		System.out.println("MOVEMENT");
		System.out.println("\t(8) N");
		System.out.println("(4) W\t\t(6) E");
		System.out.println("\t(2) S");
		System.out.println("Press 8 to go North");
		System.out.println("Press 4 to go West");
		System.out.println("Press 6 to go East");
		System.out.println("Press 2 to go South");
		Confirm.pressEnter("<Please press enter to continue>");
		
		System.out.println("OPTIONS");
		System.out.println("Please choose an option");
		System.out.println("A) Examine inventory");
		System.out.println("B) Show stats");
		System.out.println("C) Equip items");
		System.out.println("D) Use potion");
		System.out.println("\nThe options will vary depending on what's possible.");
		Confirm.pressEnter("<Please press enter to continue>");
		
		System.out.println("Get ready to start adventuring!");
		Confirm.pressEnter("<Please press enter to continue>");
		
	}
}
