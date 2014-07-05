package movement;
/*
 * 	Explore class: the main class to move the player around
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import armsandarmour.Armour;
import armsandarmour.MagicWand;
import armsandarmour.Shield;
import armsandarmour.Weapon;
import player.Player;
import potions.*;
import fight.Monster;
import inventory.*;
import magic.*;
import main.Confirm;

public class Explore {
	private Player player;
	private Map map;
	private ArrayList<int[]>roomsvisited= new ArrayList<int[]>();
	
	public Explore(Player p){
		player = p;
		//initializes the map with all the contents
		map = new Map(
			new Room(0, 0, "This is the starting room. You should try to find a way to open the door and go east.",
					new Wall(""), new Door("", "1"),
					new Wall(""), new Wall(""),
						new Container("small chest", "A small chest stands in the corner. It's marked 'contains key'.",
							new Key("iron key", "a small iron key", "1")),
								null),
			new Room(1, 0, "If you go north you'll encounter a monster. Go east first to pick up some weapons. Make sure to equip them.",
					new Passage(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room(2, 0, "An armoury.",
					new Wall(""), new Wall(""),
					new Wall(""), new Passage(""),
						new Container("weapon coffer", "There is a weapon coffer that might have some useful items.",
							new Weapon("Small Dagger", "A simple dagger that does a small amount of damage. Make sure to equip it.", 1, 3),
							new Shield("Wooden Shield", "A small shield that will absorb damage. Make sure to equip it", 1)),
								null),
			new Room(3, 0, "This might have once been a classroom.",
					new Passage(""), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								null),
			new Room(4, 0, "A musty old room.",
					new Passage(""), new Wall(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Ork", "As you enter, an Ork attacks you", "The Ork is dead", 5, 1, 2, 0, 
										new Container("the Ork's bag", "The Ork had a bag",
												new Key("bronze key", "a bronze key", "2"),
												new Weapon("Spear", "a big spear", 2, 5),
												new HealingPotion("simple healing potion", "a simple healing potion", 5)))),
			new Room (5, 0, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								null),
			new Room (6, 0, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (7, 0, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Unicorn", "You see a beautiful unicorn. As you admire this majestic creature it turns to you and attacks you", "The Unicorn is no more", 7, 3, 15, 1,
										new Container("pouch", "The Troll had a pouch",
												new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
												new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
												new Net(".NET", "You can use this to trap your enemies", 10)))),
			new Room (8, 0, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (9, 0, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Troll", "A troll lumbers at you and attacks you", "The troll is defeated", 12, 2, 10, 4,
										new Container("pouch", "The Troll had a pouch",
												new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
												new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
												new Net(".NET", "You can use this to trap your enemies", 10)))),
			new Room (10, 0, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (11, 0, "Looks like this is where the cutting edge magic happens.",
					new Passage(""), new Wall(""),
					new Wall(""), new Passage(""),
						new Container("desk", "There is a writing desk in the room.",
							new FunctionSpell("Use this spell to combine other spells together so they can be used in one turn")),
								null),
			new Room (0, 1, "",
					new Door("", "2"), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								null),
			new Room (1, 1, "A laboratory with lots of weird looking objects.",
					new Wall(""), new Passage(""),
					new Passage(""), new Passage(""),
						null,
								new Monster("Goblin", "When you come into the room a Goblin attacks you.", "The goblin lies defeated.", 5, 1, 2, 0, 
									new Container("pouch", "It has a pouch.", 
										new HealingPotion("Minor healing potion", "You can use this potion to heal from damage", 3),
										new StrengthPotion("Weak strength potion", "You can use this potion to temporarily increase your strength", 3, "weak strength potion", 6),
										new DefensePotion("Minor potion of defense", "You can use this potion to temporarily increase your defense", 2, "minor potion of defense", 5)))),
			new Room (2, 1, "A damp corridor",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (3, 1, "Pieces of twine are strewn all over the room.",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
						new Container("bag", "A bag lies discarded in a corner", 
							new Net("simple net", "this can be used to trap a monster", 5)),
								null),
			new Room (4, 1, "Someone's sleeping quarters",
					new Wall(""), new Wall(""),
					new Passage(""), new Wall(""),
						new Container("Chest", "A chest stands in the corner", 
							new HealingPotion("Minor healing potion", "You can use this potion to heal from damage", 3),
							new AttackSpell("This spell can be used to fight a monster. You need a wand to use it",4),
							new Armour("chainmail armour", "simple armour that offers a bit of protection", 2),
							new Net("simple net", "this can be used to trap a monster", 5)),
								null),
			new Room (5, 1, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room (6, 1, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Wall(""),
						new Container("Treasure chest", "In the middle of the room stands a large chest",
							new AttackSpell("Use this spell to attack monsters", 7),
							new HealingSpell("Use this spell to heal", 7),
							new ModifyingSpell("Use this spell to improve other spells", 5),
							new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
							new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
							new StrengthPotion("Good potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 9),
							new StrengthPotion("Good potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 9),
							new Net(".NET", "You can use this to trap your enemies", 10),
							new Net(".NET", "You can use this to trap your enemies", 10)),
								null),
			new Room (7, 1, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Door("", "4"),
						null,
								null),
			new Room (8, 1, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Vampire", "There's a vampire. He's attacking you","The vampire has turned into a little pile of ash", 15, 3, 12, 1,
									null)),
			new Room (9, 1, "",
					new Passage(""), new Wall(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (10, 1, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								new Monster("Giant Lizard", "A Giant Lizard attacks you", "The Giant Lizard is defeated", 9, 1, 7, 7,
									new Container("pouch", "The Giant Lizard had a pouch",
											new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
											new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
											new Net(".NET", "You can use this to trap your enemies", 10),
											new StrengthPotion("Good potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 9)))),
			new Room (11, 1, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (0, 2, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room (1, 2, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Wall(""),
						new Container("bookshelf", "There is a bookshelf in the room.",
							new ModifyingSpell("You can use this to increase the effectiveness of other spells", 5)),
								null),
			new Room (2, 2, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Goblin", "As you enter the room an Ork attacks you", "The Ork is defeated", 7, 2, 6, 2,
									new Container("pouch", "You notice the Ork has a pouch",
											new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
											new StrengthPotion("Good potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 9),
											new Net(".NET", "You can use this to trap your enemies", 10)))),
			new Room (3, 2, "",
					new Passage(""), new Wall(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (4, 2, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Wall(""),
						new Container("pedestal", "There is a pedestal with what looks like an ornate scroll on it", new WonItem()),
								null),
			new Room (5, 2, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
						null,
								new Monster("Dragon", "A huge dragon attacks you", "The dragon is defeated", 20, 3, 12, 6, null)),
			new Room (6, 2, "",
					new Passage(""), new Wall(""),
					new Wall(""), new Wall(""),
						new Container("armour cabinet", "An ornate Armour cabinet stands in the middle of the room",
							new Armour("Armour of XML", "This is really good armour", 4),
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
							new StrengthPotion("Potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 10)),
								null),
			new Room (7, 2, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room (8, 2, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Wall(""),
						new Container("wooden chest", "There is a wooden chest in the room.", 
							new StrengthPotion("Potion of Strength", "This potion will increase your strength", 5, "Potion of Strength", 10)),
								new Monster("Sphinx", "As you enter the room you see a sphinx. It has no time for riddling and attacks you", "The sphinx is defeated",12, 2, 10, 4, null)),
			new Room (9, 2, "",
					new Wall(""), new Passage(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (10, 2, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (11, 2, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Passage(""),
						new Container("small chest", "In the room is a small wooden chest", 
							new Note("small note","it's a small note", "Make your way North past the one-eyed man to find the key to the Golden room full of treasures"),
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9)),
								null),
			new Room (0, 3, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Wall(""),
						new Container("small chest", "A small chest stands in the corner.",
							new HealingPotion("simple healing potion", "a simple healing potion", 5)),
								new Monster("Giant Spider", "A Giant Spider attacks you", "The Giant Spider is dead", 5, 1, 5, 1, null)),
			new Room (1, 3, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								null),
			new Room (2, 3, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								new Monster("Ork", "As you enter the room a Goblin attacks you","The defeated Ork lies on the ground.", 7, 2, 5, 2,
										new Container("the Ork", "You notice a bag next to it.",
											new Weapon("Sword of C sharpness", "A very sharp sword", 2, 10),
											new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
											new Net (".NET", "You can use this to trap a monster", 10),
											new DefensePotion("Potion of Defense", "You can use this potion to temporarily increase your defense", 6,"Potion of Defense", 9)))),
			new Room(3, 3, "",
					new Wall(""), new Passage(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (4, 3, "",
					new Passage(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room(5, 3, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room (6, 3, "",
					new Passage(""), new Passage(""),
					new Passage(""), new Passage(""),
						new Container("large chest", "There is a large chest on the side of the room",
							new ModifyingSpell("You can use this spell to increase the effects of another spell", 2),
							new HealingPotion("Good Potion of Healing","This potion will heal you", 10),
							new Net(".NET", "Use this to trap a monster", 10)),
								new Monster("Chimera", "A chimera attacs you as you enter the room", "The chimera is defeated", 10, 1, 8, 3, null)),
			new Room (7, 3, "",
					new Wall(""), new Door("", "3"),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room(8, 3, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (9, 3, "",
					new Passage(""), new Wall(""),
					new Wall(""), new Wall(""),
						new Container("chest", "A chest stands in the corner.", 
							new Key("silver key", "a gleaming silver key", "3"),
							new Shield("Shield of SQL", "a really good shield", 4),
							new Net(".NET", "Use this to trap a monster", 10)),
								null),
			new Room (10, 3, "",
					new Passage(""), new Wall(""),
					new Wall(""), new Wall(""),
						new Container("small chest", "A small chest stands in the corner.", 
							new Key("golden key", "an ornate golden key", "4"),
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9)),
								null),
			new Room(11, 3, "",
					new Passage(""), new Wall(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room(0, 4, "A room that exudes magic.",
					new Wall(""), new Passage(""),
					new Passage(""), new Wall(""),
						new Container("chest", "There is a chest with a little glow around it. It looks magical",
							new HealingSpell("A simple healing spell that can be used to heal. You need to have a wand equipped to use it", 3),
							new MagicWand("Wand of Java", "You can use this wand to perform magic. You can't wear any armour or shield while using it."),
							new StrengthPotion("Potion of Strength", "A decent Strength potion", 5, "Potion of decent Strength", 6)),
								null),
			new Room (1, 4, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (2, 4, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Wall(""),
						new Container ("armour cabiner", "in the middle of the room stands an armour cabinet", 
							new Armour("Plate armour", "decent armour", 3)),
								null),
			new Room (3, 4, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Wall(""),
						null,
								null),
			new Room (4, 4, "",
					new Wall(""), new Passage(""),
					new Passage(""), new Passage(""),
						null,
								null),
			new Room (5, 4, "",
					new Wall(""), new Wall(""),
					new Wall(""), new Passage(""),
					new Container("bookshelf", "There is a bookshelf in the room.",
							new AttackSpell("You can use this to damage monsters", 6)),
								null),
			new Room (6, 4, "",
					new Wall(""), new Passage(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room(7, 4, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						null,
								null),
			new Room(8, 4, "",
					new Wall(""), new Passage(""),
					new Wall(""), new Passage(""),
						new Container("grave", "You inspect the skeleton's grave",
							new DefensePotion("Potion of Defense", "This potion will increase your defense", 6,"Potion of Defense", 9),
							new HealingPotion("Potion of Healing", "You can use this potion to heal", 10)),
								new Monster("Skeleton", "As you enter the room a skeleton rises from it's grave. It attacks you", "The skeleton is just a pile of bones now", 7, 1, 6, 4,
										null)),
			new Room (9, 4, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
					new Container("bookshelf", "There is a bookshelf in the room.",
							new HealingSpell("You can use this to heal yourself", 6)),
								null),
			new Room (10, 4, "",
					new Wall(""), new Passage(""),
					new Passage(""), new Wall(""),
						null,
								null),
			new Room (11, 4, "",
					new Wall(""), new Wall(""),
					new Passage(""), new Passage(""),
						null,
								new Monster("Cyclops", "A cyclops comes at you and attacks", "The cyclops is defeated", 14, 1, 9, 2,
									new Container("pouch", "The cyclops had a pouch",
											new HealingPotion("Potion of Healing", "You can use this potion to heal", 10),
											new Net(".NET", "Use this to trap a monster", 10))))
				
				);
		roomsvisited.add(new int[]{0,0});
	}
	
	
	public Room currentRoom(){
		return map.getRoom(player.x, player.y);
	}
	
	public boolean tryMoveNorth(){
		if(map.roomExists(player.x, player.y+1)){
			if (currentRoom().getNorth().getClass().isInstance(new Wall(""))){
				System.out.println("There is a wall in the way");
			}else{
				Room nextRoom = map.getRoom(player.x, player.y+1);
				if (tryPassBetweenRooms(currentRoom().getNorth(),nextRoom.getSouth())){
					player.y++;
					System.out.println("Moving North");
					roomsvisited.add(new int[] {player.x, player.y});
					return true;
				}
			}
		}
		System.out.println("Unable to move there");
		return false;	
	}
	
	public boolean tryMoveEast(){
		if(map.roomExists(player.x+1, player.y)){
			if (currentRoom().getEast().getClass().isInstance(new Wall(""))){
				System.out.println("There is a wall in the way");
			}
			else{
				Room nextRoom = map.getRoom(player.x+1, player.y);
				if (tryPassBetweenRooms(currentRoom().getEast(), nextRoom.getWest())){
					player.x++;
					System.out.println("Moving East");
					roomsvisited.add(new int[] {player.x, player.y});
					return true;
				}
			}
		}
		System.out.println("Unable to move there");
		return false;
		
	}
	
	public boolean tryMoveSouth(){
		if(map.roomExists(player.x, player.y-1)){
			if (currentRoom().getSouth().getClass().isInstance(new Wall(""))){
				System.out.println("There is a wall in the way");
			}else{
				Room nextRoom = map.getRoom(player.x, player.y-1);
				if (tryPassBetweenRooms(currentRoom().getSouth(),nextRoom.getNorth())){
					player.y--;
					System.out.println("Moving South");
					roomsvisited.add(new int[] {player.x, player.y});
					return true;
				}
			}
		}
		System.out.println("Unable to move there");
		return false;
	}
	
	public boolean tryMoveWest(){
		if(map.roomExists(player.x-1, player.y)){
			if (currentRoom().getWest().getClass().isInstance(new Wall(""))){
				System.out.println("There is a wall in the way");
			}
			else{
				Room nextRoom = map.getRoom(player.x-1, player.y);
				if (tryPassBetweenRooms(currentRoom().getWest(), nextRoom.getEast())){
					player.x--;
					System.out.println("Moving West");
					roomsvisited.add(new int[] {player.x, player.y});
					return true;
				}
			}
		}
		System.out.println("Unable to move there");
		return false;
	}
	
	public boolean tryPassBetweenRooms(Separation first, Separation second){
		return tryPassSeparation(first)&&tryPassSeparation(second);
	}
	private boolean tryPassSeparation(Separation aSeparation){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		if (aSeparation.canPass()){
			return true;
		}else{
			//this means there is a locked door
			System.out.println("The door is locked");
			while (!player.getInventory().getKeychain().isEmpty()){
				char n = 'A';
				String strOptions = "Please choose a key to try:\n";
				for (Key currentKey:player.getInventory().getKeychain()){
					strOptions += n + ") " + currentKey.getStrName() + "\n";
					n++;
				}
				strOptions += n + ") Exit\n";
				System.out.print(strOptions);
				
				char charInput = '=';
				
				try {
					charInput = reader.readLine().toUpperCase().charAt(0);
				} catch (Exception e) {
					charInput = '=';
				}
				n = 'A';
				for (Key currentKey:player.getInventory().getKeychain()){
					if (n==charInput){
						System.out.println("Trying to use " + currentKey.getStrName());
						if (aSeparation.unLock(currentKey)){
							System.out.println("Door unlocked");
							Confirm.pressEnter("<Press enter to continue>");
							return true;
						}else{
							System.out.println("The key does not fit");
						}
					}
					n++;
				}
				if (n==charInput){
					return false;
				}
			}
			return false;
		}
	}
	public String getMap(){
		//find highest value (should be at the end)
		int highestX = map.highestX();
		int highestY = map.highestY();
		String strOutput = "";
		//loop to start with highest Y value and count down
		for (int j = highestY;j>=0;j--){
			//first loop through X for North
			for (int i=0; i<=highestX;i++){
				if (map.roomExists(i, j)){
					if (map.getRoom(i, j).explored){
						strOutput += "##" + map.getRoom(i, j).getNorth().getStrMap() + "##";
					}else{
						strOutput += "======";
					}
				}else{
					strOutput += "======";
				}
			}
			strOutput += "\n";
			//second loop through X for middle
			for (int i = 0; i<=highestX;i++){
				if (map.roomExists(i, j)){
					Room currentRoom = map.getRoom(i, j);
					if (currentRoom.explored){
						strOutput += currentRoom.getWest().getStrMap();
						
						if(player.x==i&&player.y==j){
							strOutput +="()";
						}else{
							if (currentRoom.containerUnopened()){
								strOutput += "! ";
							}else{
								strOutput += "  ";
							}
						}
						
						strOutput += currentRoom.getEast().getStrMap();
						
					}else{
						strOutput += "======";
					}
				}else{
					strOutput += "======";
				}
			}
			strOutput += "\n";
			//third loop to print south
			for (int i=0; i<=highestX;i++){
				if (map.roomExists(i, j)){
					if (map.getRoom(i, j).explored){
						strOutput += "##" + map.getRoom(i, j).getSouth().getStrMap() + "##";
					}else{
						strOutput += "======";
					}
				}else{
					strOutput += "======";
				}
			}
			strOutput += "\n";
		}
		return strOutput;
	}
	public void gotoPreviousRoom(){
		int[]previousroom = roomsvisited.get(roomsvisited.size()-2);
		player.x=previousroom[0];
		player.y=previousroom[1];
	}

}
