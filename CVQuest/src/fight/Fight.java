package fight;
/*
 * Fight class: the class in which the fight happens. First the monster does damage, then the player can choose options for his turn
 */
import inventory.Net;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import magic.*;
import main.Confirm;
import player.Player;

public class Fight {
	private Player player;
	private Monster monster;
	
	public Fight(Player pl, Monster mon){
		player = pl;
		monster = mon;
	}
	public int fight(){
		Confirm confirm = new Confirm();
		confirm.pressEnter("<Please press enter to continue>");
		while (true){
		//while loop runs while neither is dead or until "run away"
			//print info
			System.out.println("======== FIGHT ==========");
			int outcome = monsterTurn();
			if (outcome==-1){
				return -1;
			}

			confirm.pressEnter("<Press enter to continue>");
			outcome = playerTurn();
			if (outcome>=0){
				return outcome;
			}
			player.reduceBuffs();
			confirm.pressEnter("<Press enter to continue>");
		}
	}
	private int monsterTurn(){
		System.out.println("--- Turn: " +monster.getStrName());
		System.out.println(player.toString());
		System.out.println("VS.");
		System.out.println(monster.toString());
		System.out.println(monster.getStrName() + "'s turn to attack");
		if (monster.trapped()){
			System.out.println(monster.getStrName() + " is trapped in a net. It tries to get out.");
			monster.subtractTrapped(monster.getIntDamage());
			if (monster.trapped()){
				System.out.println("It fails to get loose");
			}else{
				System.out.println("It gets loose");
			}
		}else{
			int damageDone = doDamage(player, monster.getIntDamage());
			System.out.println(monster.getStrName() + " does " + (damageDone + player.getIntDefense()) + " damage.");
			if(player.getIntDefense()>0){
				System.out.println("Your defense absorbs " + player.getIntDefense() + " leaving " + damageDone + ".");
			}
			if (player.getIntHealth()<=0){
				return -1;
			}
		}
		return 0;
	}
	
	private int playerTurn(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		System.out.println("--- Turn: " +player.strName);
		System.out.println(player.toString());
		System.out.println("VS.");
		System.out.println(monster.toString());

		System.out.println("Your turn to attack");
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
			char n= 'A';
			String strOptions = "Please choose an option\n";
			if(!player.getInventory().getEquipped().magicWandEquipped()){
				strOptions += n + ") Attack using " + player.getInventory().getEquipped().getStrWeapon() + "\n";
				n++;
			}
			if (!player.getInventory().getNets().isEmpty()){
				strOptions += n + ") Throw net\n";
				n++;
			}
			if (player.getInventory().getEquipped().magicWandEquipped()&&player.getInventory().getSpellbook().spellsAvailable()){
				strOptions += n + ") Use a spell\n";
				n++;
			}
			if ((player.hasHealingPotions()&&player.needsHealing())||!player.getInventory().getDefensePotions().isEmpty()||!player.getInventory().getStrengthPotions().isEmpty()){
				strOptions += n + ") Use potion\n";
				n++;
			}
			if(!player.getInventory().getWeapons().isEmpty()||!player.getInventory().getArmours().isEmpty()||!player.getInventory().getShields().isEmpty()||!player.getInventory().getMagicWands().isEmpty()){
				strOptions += n + ") Equip a new weapon\n";
				n++;
			}
			strOptions += n + ") Show stats\n";
			n++;
			strOptions += n + ") Run away";
			
			System.out.println(strOptions);
			char charInput = '=';
			try{
				charInput = reader.readLine().toUpperCase().charAt(0);
			}catch (Exception e) {
				charInput = '=';
			}
			
			n='A';
			if(!player.getInventory().getEquipped().magicWandEquipped()){
				if (n==charInput){
					int damageDone = doDamage(monster, player.getDamage());
					System.out.println("You do " + (damageDone+monster.getIntDefense()) + " damage.");
					if (monster.getIntDefense()>0){
						System.out.println("The monster's defense absorbs " + monster.getIntDefense() + " leaving " + damageDone + ".");
					}
					if (monster.getIntHealth()<=0){
						return 1;
					}
					return -1;
				}
				n++;
			}
			if (!player.getInventory().getNets().isEmpty()){
				if(n==charInput){
					Net toThrow;
					if (player.getInventory().getNets().size()==1){
						toThrow = player.getInventory().getNets().get(0);
						player.getInventory().getNets().remove(0);
					}else{
						toThrow = player.chooseNet();
					}
					monster.addTrapped(toThrow.getNet());
					System.out.println(monster.getStrName() + " is trapped in the net.");
					return -1;
				}
				n++;
			}
			if (player.getInventory().getEquipped().magicWandEquipped()&&player.getInventory().getSpellbook().spellsAvailable()){
				if (n==charInput){
					if(spellMenu()){
						if (monster.getIntHealth()<=0){
							return 1;
						}
						return -1;
					}
				}
				n++;
			}
			if ((player.hasHealingPotions()&&player.needsHealing())||!player.getInventory().getDefensePotions().isEmpty()||!player.getInventory().getStrengthPotions().isEmpty()){
				if (n==charInput){
					player.choosePotion();
				}
				n++;
			}
			if(!player.getInventory().getWeapons().isEmpty()||!player.getInventory().getArmours().isEmpty()||!player.getInventory().getShields().isEmpty()||!player.getInventory().getMagicWands().isEmpty()){
				if (n==charInput){
					player.equipItems();
				}
				n++;
			}
			if (n==charInput){
				player.showStats();
			}
			n++;
			
			if (n==charInput){
				return 0;
			}
		}
	}
	//function that subtracts damage from fighter (not less than zero)
	public int doDamage(Fighter fighter, int damage){
		damage = damage<fighter.getIntDefense()?0:damage-fighter.getIntDefense();
		int prevHealth = fighter.getIntHealth();
		if (fighter.getIntHealth()<damage){
			fighter.setZeroHealth();
		}else{
			fighter.subtractHealth(damage);;
		}
		return prevHealth - fighter.getIntHealth();
	}
	public boolean spellMenu(){
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
		while (true){
			char n = 'A';
			String strOutput = "Please choose a spell\n";
			for (AttackSpell spell:player.getInventory().getSpellbook().getAttackSpells()){
				strOutput += n + ") Use " + spell.toString() + "\n";
				n++;
			}
			for (HealingSpell spell:player.getInventory().getSpellbook().getHealingSpells()){
				strOutput += n + ") Use " + spell.toString() + "\n";
				n++;
			}
			if (player.getInventory().getSpellbook().functionSpellHasFunctions()){
				strOutput += n + ") Use " + player.getInventory().getSpellbook().getFunctionSpell().getStrName() + "\n";
				n++;
			}
			strOutput += n + ") Exit \n";
			System.out.print(strOutput);
			char charInput = '=';
		
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput = '=';
			}
		
			n= 'A';
			for (AttackSpell spell:player.getInventory().getSpellbook().getAttackSpells()){
				if (charInput==n){
					int damageDone = doDamage(monster, spell.getIntDamage());
					System.out.println("You do " + (damageDone+monster.getIntDefense()) + " damage.");
					if (monster.getIntDefense()>0){
						System.out.println("The monster's defense absorbs " + monster.getIntDefense() + " leaving " + damageDone + ".");
					}
					return true;
				}
				n++;
			}
			for (HealingSpell spell:player.getInventory().getSpellbook().getHealingSpells()){
				if(charInput==n){
					player.healingSpell(spell);
					return true;
				}
				n++;
			}
			if (player.getInventory().getSpellbook().functionSpellHasFunctions()){
				if (charInput ==n){
					FunctionSpell spell = player.getInventory().getSpellbook().getFunctionSpell();
					player.healPlayer(spell.getIntHeal());
					int damageDone = doDamage(monster, spell.getIntDamage());
					System.out.println("You do " + (damageDone+monster.getIntDefense()) + " damage.");
					if (monster.getIntDefense()>0){
						System.out.println("The monster's defense absorbs " + monster.getIntDefense() + " leaving " + damageDone + ".");
					}
					return true;
				}
				n++;
			}
			if (charInput==n){
				return false;
			}
		}
		
	}
}
