package main;
/*
 * 	Play class: the core class that goes over the options and initiates the correct actions
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

import player.Player;
import fight.Fight;
import movement.Explore;

public class Play {
	private Player player;
	private Explore explore;
	public boolean playing = true;
	public boolean wonGame = false;
	
	public Play(Player play, Explore exp){
		player = play;
		explore = exp;
	}

	public void Menu(){
		while(playing){
			explore.currentRoom().explored=true;
			player.reduceBuffs();
			//initiates the fight
			if (explore.currentRoom().monsterIsAlive()){
				System.out.println(explore.currentRoom().getMonster().getStrIntro());
				Fight fight = new Fight(player, explore.currentRoom().getMonster());
				int outcome = fight.fight();
				if (outcome==1){
					System.out.println("You defeated the monster!");
					Confirm.pressEnter("<Please press enter to continue>");
				}else if (outcome==0){
					System.out.println("You ran away");
					explore.gotoPreviousRoom();
				}else{
					System.out.println("GAME OVER");
					playing = false;
					return;
				}
			}
			showOptions();
		}
		if (wonGame){
			System.out.println("CONGRATULATIONS! YOU FOUND THE ANCIENT SCROLL OF CURRICULUM VITAE!");
		}

	}
	private void showOptions(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		/*
		 * Dynamic menu only shows what's applicable.
		 * 		It initially goes over every option (including looping through lists) and prints it out if it's applicable
		 * 		During this it prints a char n that starts at 'A' and adds n if the option is applicable.
		 * 		The player inputs the character he wants (char is taken by using .charAt(0) to make it more user friendly)
		 * 		n is set back to 'A'
		 * 		The menu examines the options in the same way again and adds n where applicable
		 * 		if n is the same as the user input this means this is what the player chose
		 * 		A while loop is used to allow the user to do more than one action in the menu or to catch error where nothing is chosen
		 */
		while(true){
			char n = 'A';
			System.out.println(player.toString()+"\n");
			System.out.println("Map:");
			System.out.println(explore.getMap());
			System.out.println(explore.currentRoom() + "\n");
			
			String strOptions = "Please choose an option:\n";
			strOptions += "Move:\n";
			strOptions += "\t(8) N\n";

			strOptions += "(4) W";
			
			strOptions += "\t\t(6) E\n";

			strOptions += "\t(2) S\n";
			
			strOptions += n + ") Examine inventory\n";
			n++;
			strOptions += n + ") Show stats\n";
			n++;
			if (explore.currentRoom().containerAvailable()){
				strOptions += n + ") Search " + explore.currentRoom().getContainer().getStrName() + "\n";
				n++;
			}
			if (explore.currentRoom().hasMonster()){
				if (explore.currentRoom().getMonster().getContainer()!=null){
					if(!explore.currentRoom().getMonster().getContainer().isEmpty()){
						strOptions += n + ") Search " + explore.currentRoom().getMonster().getStrName() + "\n";
						n++;
					}
				}
			}
			if(!player.getInventory().getWeapons().isEmpty()||!player.getInventory().getArmours().isEmpty()||!player.getInventory().getShields().isEmpty()||!player.getInventory().getMagicWands().isEmpty()){
				strOptions += n + ") Equip items\n";
				n++;
			}
			if (player.getInventory().getSpellbook().spellsAvailable()&&player.getInventory().getEquipped().magicWandEquipped()){
				strOptions += n + ") Use spell\n";
				n++;
			}
			if ((player.hasHealingPotions()&&player.needsHealing())||!player.getInventory().getDefensePotions().isEmpty()||!player.getInventory().getStrengthPotions().isEmpty()){
				strOptions += n + ") Use potion\n";
				n++;
			}
			System.out.println(strOptions);
			
			char charInput = '=';
			
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput = '=';
			}
			n='A';
			if (charInput=='8'){
				if(explore.tryMoveNorth()){
					Confirm.pressEnter("<Press enter to continue>");
					return;
				}
				Confirm.pressEnter("<Press enter to continue>");
			}

			if (charInput=='6'){
				if(explore.tryMoveEast()){
					Confirm.pressEnter("<Press enter to continue>");
					return;
				}
				Confirm.pressEnter("<Press enter to continue>");
			}

			if (charInput =='2'){
				if(explore.tryMoveSouth()){
					Confirm.pressEnter("<Press enter to continue>");
					return;
				}
				Confirm.pressEnter("<Press enter to continue>");
			}

			if(charInput=='4'){
				if(explore.tryMoveWest()){
					Confirm.pressEnter("<Press enter to continue>");
					return;
				}
				Confirm.pressEnter("<Press enter to continue>");
			}

			if(charInput ==n){
				player.examineInventory();
			}
			n++;
			if (charInput ==n){
				player.showStats();
			}
			n++;
			if (explore.currentRoom().containerAvailable()){
				if (charInput==n){
					if(player.inspectContainer(explore.currentRoom().getContainer())){
						playing=false;
						wonGame = true;
						return;
					}
				}
				n++;
			}
			if (explore.currentRoom().hasMonster()){
				if (explore.currentRoom().getMonster().getContainer()!=null){
					if (!explore.currentRoom().getMonster().getContainer().isEmpty()){
						if (charInput==n){
							player.inspectContainer(explore.currentRoom().getMonster().getContainer());
						}
						n++;
					}
				}
			}
			if(!player.getInventory().getWeapons().isEmpty()||!player.getInventory().getArmours().isEmpty()||!player.getInventory().getShields().isEmpty()||!player.getInventory().getMagicWands().isEmpty()){
				if (charInput==n){
					player.equipItems();
				}
				n++;
			}
			if (player.getInventory().getSpellbook().spellsAvailable()&&player.getInventory().getEquipped().magicWandEquipped()){
				if (charInput==n){
					player.spellMenu();
				}
				n++;
			}
			if ((player.hasHealingPotions()&&player.needsHealing())||!player.getInventory().getDefensePotions().isEmpty()||!player.getInventory().getStrengthPotions().isEmpty()){
				if (charInput==n){
					player.choosePotion();
				}
				n++;
			}
		}
	}
}