package player;
/*
 * 	Player class: this class holds the methods that affect the player
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

import armsandarmour.*;
import potions.*;
import fight.Fighter;
import magic.*;
import main.Confirm;
import inventory.*;

public class Player extends Fighter{
	public String strName;
	public int x;
	public int y;
	private final int intMAXHealth = 20;
	private int intHealth;
	private Inventory inventory;
	private ArrayList<StrengthBuff>extraStrength = new ArrayList<StrengthBuff>();
	private ArrayList<DefenseBuff>extraDefense = new ArrayList<DefenseBuff>();
	
	public Player(String name){
		intHealth= 10;
		strName = name;
		x=0;
		y=0;
		inventory = new Inventory();
	}
	
	public int getIntDefense(){
		return getIntBasicDefense()+getIntExtraDefense();
	}
	
	public int getIntBasicDefense(){
		int output = 0;
		if (inventory.getEquipped().getArmour()!=null){
			output += inventory.getEquipped().getArmour().getIntDefense();
		}
		if (inventory.getEquipped().getShield()!=null){
			output += inventory.getEquipped().getShield().getIntDefense();
		}
		return output;
	}
	
	public int getIntExtraDefense(){
		int output = 0;
		for (DefenseBuff defense:extraDefense){
			output +=defense.getIntDefense();
		}
		return output;
	}
	
	public int getIntDamage(){
		return getBasicDamage()+getExtraDamage();
	}
	
	public int getBasicDamage(){
		if (inventory.getEquipped().magicWandEquipped()){
			return 0;
		}
		if (inventory.getEquipped().getWeapon()!=null){
			Weapon equipped = inventory.getEquipped().getWeapon();
			
			return super.calculateDamage(equipped.getIntMinDamage(), equipped.getIntMaxDamage());
		}
		return 1;
	}
	
	public int getExtraDamage(){
		int output =0;
		for (StrengthBuff strength:extraStrength){
			output += strength.getIntDamage();
		}
		return output;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public int getIntHealth(){
		return intHealth;
	}
	
	public void subtractHealth(int damage){
		intHealth-=damage;
		return;
	}
	
	public void setZeroHealth(){
		intHealth = 0;
		return;
	}
	
	public String getStrHealth(){
		String output = "";
		for (int i = 0; i<intHealth;i++){
			output += "|";
		}
		return output;
	}
	
	public String toString(){
		return strName + "\t" + getStrHealth();
	}
	//Shows current stats along with buffs shown as current effects
	public void showStats(){
		String output = toString() + "\n===================\n";
		output += "Strength:\t";
		if (inventory.getEquipped().magicWandEquipped()){
			output += "0";
		}else if (inventory.getEquipped().getWeapon()!=null){
			Weapon equipped = inventory.getEquipped().getWeapon();
			output += equipped.getIntMinDamage() + " - " + equipped.getIntMaxDamage();
		}else{
			output += "1";
		}
		if (!extraStrength.isEmpty()){
			output += " (+" + getExtraDamage() + ")";
		}
		output += "\n-----\n";
		
		output += "Defense:\t";
		output += getIntBasicDefense() + "";
		if (!extraDefense.isEmpty()){
			output +=  " (+" + getIntExtraDefense() + ")";
		}

		if (!extraStrength.isEmpty()||!extraDefense.isEmpty()){
			output += "\n============\n";
			output += "Current effects:\n----------------\n";
		}
		for (StrengthBuff buff: extraStrength){
			output += buff.getStrEffect() + "(" + buff.intTime + " turns left)\n";
		}
		for (DefenseBuff buff:extraDefense){
			output += buff.getStrEffect() + "(" + buff.intTime + " turns left)\n";
		}
		System.out.println(output);
		Confirm.pressEnter("<Please press enter to continue>");
		
	}
	//heals the player with input of an integer. If input will exceed max health only int health becomes max health
	public void healPlayer(int intHeal){
		int formerHealth = intHealth;
		intHealth = (intHealth+intHeal>intMAXHealth)?intMAXHealth:intHealth+intHeal;
		System.out.println("Healed " + (intHealth-formerHealth) + " points. Current Health: " + getStrHealth());
		Confirm.pressEnter("<Please press enter to continue>");
	}
	//allows you to choose a spell and do specific things to it
	public void spellMenu(){
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
			String strOptions = "Please choose a spell\n";
			strOptions += "Healing Spells\n";
			if (inventory.getSpellbook().getHealingSpells().isEmpty()){
				strOptions += "\tNone\n";
			}
			for (HealingSpell spell:inventory.getSpellbook().getHealingSpells()){
				strOptions += "\t" + n + ") " + spell.toString() + "\n";

				n++;
			}
			strOptions += "Attack Spells\n";
			if (inventory.getSpellbook().getAttackSpells().isEmpty()){
				strOptions += "\tNone\n";
			}
			for (AttackSpell spell: inventory.getSpellbook().getAttackSpells()){
				strOptions += "\t" + n +") " +  spell.toString() + "\n";
				n++;
			}
			strOptions += "Modifying Spells\n";
			if (inventory.getSpellbook().getModifyingSpells().isEmpty()){
				strOptions += "\tNone\n";
			}
			for (ModifyingSpell spell: inventory.getSpellbook().getModifyingSpells()){
				strOptions += "\t" + n + ") " + spell.toString() + "\n";
				n++;
			}
			if (inventory.getSpellbook().getFunctionSpell()!=null){
				strOptions += "Function Spells:\n";
				strOptions += "\t" + n + ") " + inventory.getSpellbook().getFunctionSpell().getStrName() + "\n";
				n++;
			}
			strOptions+= n + ") Exit\n";
			
			System.out.print(strOptions);
		
			char charInput = '=';
			
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
		
			n='A';
		
			for (HealingSpell spell:inventory.getSpellbook().getHealingSpells()){
				if (charInput==n){
					healingSpellOptions(spell);
				}
				n++;
			}
			for (AttackSpell spell: inventory.getSpellbook().getAttackSpells()){
				if(charInput==n){
					attackSpellOptions(spell);
				}
				n++;
			}
			int i =0;
			while (i<inventory.getSpellbook().getModifyingSpells().size()){
				if (charInput==n){
					if(modifyingSpellOptions(inventory.getSpellbook().getModifyingSpells().get(i))){
						inventory.getSpellbook().getModifyingSpells().remove(i);
					}
				}
				i++;
				n++;
			}
			if (inventory.getSpellbook().getFunctionSpell()!=null){
				if(charInput==n){
					functionSpellOptions();
				}
				n++;
			}
			if (charInput ==n){
				return;
			}
		}
	}
	//uses a healing spell to heal
	public void healingSpell(HealingSpell spell){
		healPlayer(spell.getIntHeal());
	}
	//chooses a healing spell
	public void healingSpellOptions(HealingSpell spell){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while(true){	
			char n = 'A';
			String strOptions = "Please choose an option for " + spell.getStrName() + "\n";
		
			if (needsHealing()){
				strOptions += n + ") Heal " + spell.getIntHeal() + " points\n";
				n++;
			}
			if (spell.hasModifier()){
				strOptions += n + ") Unequip modifier?\n";
				n++;
			}
			strOptions += n + ") Exit\n";
			n++;
			
			System.out.print(strOptions);
		
			char charInput = '=';
		
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
		
			n='A';
		
			if (needsHealing()){
				if (charInput ==n){
					healingSpell(spell);
				}
				n++;
			}
			if (spell.hasModifier()){
				if(charInput==n){
					inventory.getSpellbook().getModifyingSpells().add(unequipModifier(spell));
				}
				n++;
			}
			if (charInput ==n){
				return;
			}
		}

	}
	//a recursive function used to find the last modifier (as modifiers can also have modifiers) and equips it to that
	public void equipModifier(Spell spell, ModifyingSpell modifier){
		if (spell.hasModifier()){
			equipModifier(spell.getModifyingSpell(), modifier);
		}else{
			System.out.println("Equipping " + modifier.getStrName() + " to " + spell.getStrName());
			spell.setModifyingSpell(modifier);
			Confirm.pressEnter("<Please press enter to continue>");
		}
	}
	//a recursive function which finds the last modifier and returns it
	public ModifyingSpell unequipModifier(Spell spell){
		if (spell.getModifyingSpell().hasModifier()){
			return unequipModifier(spell.getModifyingSpell());
		}
		ModifyingSpell found = spell.getModifyingSpell();
		spell.setModifyingSpell(null);
		System.out.println("Unequipping " + found.getStrName());
		return found;
	}
	//method to equip modifying spells, it returns true if equipped so the spell can be removed from the arraylist
	public boolean modifyingSpellOptions(ModifyingSpell spell){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while(true){
			char n = 'A';
			String strOptions = "Please choose an option for " + spell.getStrName() + "\n";
		
			for (HealingSpell healingspell: inventory.getSpellbook().getHealingSpells()){
				strOptions += n + ") Use on " + healingspell.toString() + "\n";
				n++;
			}
			for (AttackSpell attackspell:inventory.getSpellbook().getAttackSpells()){
				strOptions += n + ") Use on " + attackspell.toString() + "\n";
				n++;
			}
			if (inventory.getSpellbook().getFunctionSpell()!=null){
				strOptions += n + ") Use on " + inventory.getSpellbook().getFunctionSpell().getStrName() + "\n";
				n++;
			}
		
			strOptions += n + ") Exit\n";

			System.out.print(strOptions);
			
			char charInput = '=';
			
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
			n = 'A';
			
			for (HealingSpell healingspell: inventory.getSpellbook().getHealingSpells()){
				if(n==charInput){
					equipModifier(healingspell, spell);
					return true;
				}
				n++;
			}
			for (AttackSpell attackspell:inventory.getSpellbook().getAttackSpells()){
				if(n==charInput){
					equipModifier(attackspell, spell);
					return true;
				}
				n++;
			}
			if (inventory.getSpellbook().getFunctionSpell()!=null){
				if (n==charInput){
					equipModifier(inventory.getSpellbook().getFunctionSpell(), spell);
					return true;
				}
				n++;
			}
			if (n==charInput){
				return false;
			}
		}
	}
	
	public void attackSpellOptions(AttackSpell spell){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while(true){
		
			char n = 'A';
			String strOptions = "Please choose an option for " + spell.getStrName() + "\n";
		
			if (spell.hasModifier()){
				strOptions += n + ") Unequip modifier?\n";
				n++;
			}
			strOptions += n + ") Exit\n";
			n++;
			
			System.out.print(strOptions);
		
			char charInput = '=';
		
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
		
			n='A';
		
			if (spell.hasModifier()){
				if(charInput==n){
					inventory.getSpellbook().getModifyingSpells().add(unequipModifier(spell));
				}
				n++;
			}
			if (charInput ==n){
				return;
			}
		}
	}
	//options for function spell. This spell can hold other spells so it's quite powerful
	public void functionSpellOptions(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while(true){
			FunctionSpell spell = inventory.getSpellbook().getFunctionSpell();
			char n = 'A';
			String strOptions = "Please choose an option for " + spell.getStrName() + "\n";

			if (!spell.getHealingSpells().isEmpty()&&needsHealing()){
				strOptions +=  n + ") Use function\n";
				n++;
			}
			
			if (spell.functionsAvailable()){
				strOptions+= n + ") Show all functions\n";
				n++;
			}
			strOptions += n + ") Change name\n";
			n++;
			
			strOptions += n + ") Add function\n";
			n++;

			strOptions += n + ") Exit\n";
			
			System.out.print(strOptions);
			
			char charInput = '=';
			
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
			n = 'A';
			
			if (!spell.getHealingSpells().isEmpty()&&needsHealing()){
				if (n==charInput){
					System.out.println("Only able to heal");
					healPlayer(spell.getIntHeal());
				}
				n++;
			}
			
			if (spell.functionsAvailable()){
				if (n==charInput){
					System.out.print(spell.getStrFunctions());
				}
				n++;
			}
			if (n==charInput){
				System.out.println("Please enter name");
				String newName = "";
				
				try {
					newName = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				spell.setStrFunctionName(newName);
				System.out.println("Name changed");
			}
			n++;
			if (n==charInput){
				chooseSpellforFunction();
			}
			n++;
			if (n==charInput){
				return;
			}
			
		}
	}
	//this method shows all the spells that are not in the method yet
	public void chooseSpellforFunction(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		FunctionSpell functionspell = inventory.getSpellbook().getFunctionSpell();
		while(true){
			char n = 'A';
			String strOptions = "Please choose a spell to equip\n";
			
			for (HealingSpell spell:inventory.getSpellbook().getHealingSpells()){
				if (!functionspell.healingSpellEquipped(spell)){
					strOptions += n + ") " + spell.toString() + "\n";
					n++;
				}
			}
			for (AttackSpell spell:inventory.getSpellbook().getAttackSpells()){
				if (!functionspell.attackSpellEquipped(spell)){
					strOptions += n + ") " + spell.toString() + "\n";
					n++;
				}
			}
			strOptions += n + ") Exit\n";
			
			System.out.print(strOptions);
			
			char charInput = '=';
			
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
			
			n = 'A';
			
			for (HealingSpell spell:inventory.getSpellbook().getHealingSpells()){
				if (!functionspell.healingSpellEquipped(spell)){
					if (n==charInput){
						functionspell.addHealingSpell(spell);
					}
					n++;
				}
			}
			for (AttackSpell spell:inventory.getSpellbook().getAttackSpells()){
				if (!functionspell.attackSpellEquipped(spell)){
					if (n==charInput){
						functionspell.addAttackSpell(spell);
					}
					n++;
				}
			}
			if (n==charInput){
				return;
			}
			
		}
	}
	
	public void choosePotion(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		
		char n = 'A';
		String strOptions = "Please choose a potion:\n";
		for (HealingPotion potion: inventory.getHealingPotions()){
			strOptions += n + ") " + potion.getStrName() + ": " + potion.getStrDescription() + "\n";
			n++;
		}
		for (StrengthPotion potion: inventory.getStrengthPotions()){
			strOptions += n + ") " + potion.getStrName() + ": " + potion.getStrDescription() + "\n";
			n++;
		}
		for (DefensePotion potion: inventory.getDefensePotions()){
			strOptions += n + ") " + potion.getStrName() + ": " + potion.getStrDescription() + "\n";
			n++;
		}
		strOptions += n+ ") Exit\n";
		System.out.print(strOptions);
		
		char charInput = '=';
		
		try {
			charInput = reader.readLine().toUpperCase().charAt(0);
		} catch (Exception e) {
			charInput ='=';
		}
		n='A';
		int i = 0;
		while (i<inventory.getHealingPotions().size()){
			if (charInput==n){
				healingPotion(inventory.getHealingPotions().get(i));
				inventory.getHealingPotions().remove(i);
			}
			i++;
			n++;
		}
		i = 0;
		while (i<inventory.getStrengthPotions().size()){
			if (charInput==n){
				strengthPotion(inventory.getStrengthPotions().get(i));
				inventory.getStrengthPotions().remove(i);
			}
			i++;
			n++;
		}
		i = 0;
		while (i<inventory.getDefensePotions().size()){
			if (charInput==n){
				defensePotion(inventory.getDefensePotions().get(i));
				inventory.getDefensePotions().remove(i);
			}
			i++;
			n++;
		}
		if (charInput==n){
			
		}
	}
	
	public void healingPotion(HealingPotion potion){
		healPlayer(potion.getIntHeal());
	}
	
	public void strengthPotion(StrengthPotion potion){
		System.out.println("Using " + potion.getStrName());
		extraStrength.add(new StrengthBuff(potion.getStrEffect(), potion.getIntTime(), potion.getIntDamage()));
	}
	
	public void defensePotion(DefensePotion potion){
		System.out.println("Using " + potion.getStrName());
		extraDefense.add(new DefenseBuff(potion.getStrEffect(), potion.getIntTime(), potion.getIntDefense()));
	}
	//this method shows all available weapons and allows to equip them
	public void equipItems(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while (!inventory.getWeapons().isEmpty()||!inventory.getArmours().isEmpty()||!inventory.getShields().isEmpty()||!inventory.getMagicWands().isEmpty()){
			System.out.println("Currently equipped:\n");
			System.out.println(inventory.getEquipped());
			
			char n = 'A';
			String strOptions = "Please choose what to equip\n";
			if (!inventory.getWeapons().isEmpty()){
				strOptions+="Weapons\n";
			}
			for (Weapon weapon:inventory.getWeapons()){
				strOptions += "\t" + n + ") " + weapon.toString() + "\n";
				n++;
			}
			if (!inventory.getMagicWands().isEmpty()){
				strOptions+="Magic Wands\n";
			}
			for (MagicWand magicwand:inventory.getMagicWands()){
				strOptions += "\t" + n + ") " + magicwand.toString() + "\n";
				n++;
			}
			if (!inventory.getArmours().isEmpty()){
				strOptions+="Armour\n";
			}
			for (Armour armour:inventory.getArmours()){
				strOptions += "\t" +n + ") " + armour.toString() + "\n";
				n++;
			}
			if (!inventory.getShields().isEmpty()){
				strOptions+="Shields\n";
			}
			for (Shield shield:inventory.getShields()){
				strOptions += "\t" +n + ") " + shield.toString() + "\n";
				n++;
			}
			strOptions+= n + ") Exit\n";
			
			System.out.print(strOptions);
			
			char charInput = '=';
			try{
				charInput = reader.readLine().toUpperCase().charAt(0);
			}catch (Exception e) {
				charInput ='=';
			}
			n = 'A';
			int i = 0;
			while (i<inventory.getWeapons().size()){
				if(charInput ==n){
					if (equipWeapon(inventory.getWeapons().get(i))){
						inventory.getWeapons().remove(i);
					}
				}
				i++;
				n++;
			}
			i = 0;
			while (i<inventory.getMagicWands().size()){
				if(charInput ==n){
					if (equipMagicWand(inventory.getMagicWands().get(i))){
						inventory.getMagicWands().remove(i);
					}
				}
				i++;
				n++;
			}
			i = 0;
			while (i<inventory.getArmours().size()){
				if(charInput ==n){
					if (equipArmour(inventory.getArmours().get(i))){
						inventory.getArmours().remove(i);
					}
				}
				i++;
				n++;
			}
			i = 0;
			while (i<inventory.getShields().size()){
				if(charInput ==n){
					if (equipShield(inventory.getShields().get(i))){
						inventory.getShields().remove(i);
					}
				}
				i++;
				n++;
			}
			if (charInput ==n){
				return;
			}
			
		
		}
	}
	//equips a weapon. If a weapon already exists it put it in the weapons arraylist
	//if a magic wand is equipped it asks for permission to unequip it
	public boolean equipWeapon(Weapon newWeapon){
		if (unequipMagicWand()){
			if (inventory.getEquipped().getWeapon()!=null){
				inventory.getWeapons().add(inventory.getEquipped().getWeapon());
			}
			inventory.getEquipped().setWeapon(newWeapon);
			System.out.println("Equipped " + newWeapon.getStrName());
			Confirm.pressEnter("<Press enter to continue>");
			return true;
		}
		return false;
	}
	
	public boolean equipShield(Shield newShield){
		if (unequipMagicWand()){
			if (inventory.getEquipped().getShield()!=null){
				inventory.getShields().add(inventory.getEquipped().getShield());
			}
			inventory.getEquipped().setShield(newShield);
			System.out.println("Equipped " + newShield.getStrName());
			Confirm.pressEnter("<Press enter to continue>");
			return true;
		}
		return false;
	}
	
	public boolean equipArmour(Armour newArmour){
		if (unequipMagicWand()){
			if (inventory.getEquipped().getArmour()!=null){
				inventory.getArmours().add(inventory.getEquipped().getArmour());
			}
			inventory.getEquipped().setArmour(newArmour);
			System.out.println("Equipped " + newArmour.getStrName());
			Confirm.pressEnter("<Press enter to continue>");
			return true;
		}
		return false;
	}
	
	public boolean equipMagicWand(MagicWand newWand){
		boolean hasWeapon = inventory.getEquipped().getWeapon()!=null;
		boolean hasArmour = inventory.getEquipped().getArmour()!=null;
		boolean hasShield = inventory.getEquipped().getShield()!=null;
		if(hasArmour||hasWeapon||hasShield){
			if (Confirm.getConfirm("Doing this will unequip any weapons, shields or armour.")){
				if (hasWeapon){
					inventory.getWeapons().add(inventory.getEquipped().getWeapon());
				}
				if (hasArmour){
					inventory.getArmours().add(inventory.getEquipped().getArmour());
				}
				if (hasShield){
					inventory.getShields().add(inventory.getEquipped().getShield());
				}
				inventory.getEquipped().setArmour(null);
				inventory.getEquipped().setWeapon(null);
				inventory.getEquipped().setShield(null);
				
				inventory.getEquipped().setMagicWand(newWand);
				System.out.println("Equipped " + newWand.getStrName());
				Confirm.pressEnter("<Press enter to continue>");
				return true;
			}
			return false;
			
		}
		inventory.getEquipped().setMagicWand(newWand);
		System.out.println("Equipped " + newWand.getStrName());
		Confirm.pressEnter("<Press enter to continue>");
		return true;
	}
	//asks for permission to unequip the magic wand
	public boolean unequipMagicWand(){
		if(inventory.getEquipped().magicWandEquipped()){
			if (Confirm.getConfirm("Doing this will unequip the magic wand")){
				inventory.getMagicWands().add(inventory.getEquipped().getMagicWand());
				inventory.getEquipped().setMagicWand(null);
				return true;
			}
			return false;
		}
		return true;
	}
	//this function reduces the time in the buffs. If time is 0 it deletes them
	public void reduceBuffs(){
		int n = 0;
		while (n<extraStrength.size()){
			extraStrength.get(n).intTime--;
			if (extraStrength.get(n).intTime<=0){
				System.out.println(extraStrength.get(n).getStrEffect() + " wears off.");
				Confirm.pressEnter("<Please press enter to continue>");
				extraStrength.remove(n);
			}
			n++;
		}
		n = 0;
		while (n<extraDefense.size()){
			extraDefense.get(n).intTime--;
			if (extraDefense.get(n).intTime<=0){
				System.out.println(extraDefense.get(n).getStrEffect() + " wears off.");
				Confirm.pressEnter("<Please press enter to continue>");
				extraDefense.remove(n);
			}
			n++;
		}
	}

	public boolean hasHealingPotions(){
		return !inventory.getHealingPotions().isEmpty();
	}
	
	public boolean hasHealingSpells(){
		return inventory.getEquipped().magicWandEquipped()&&(!inventory.getSpellbook().getHealingSpells().isEmpty());
	}
	
	public boolean needsHealing(){
		return intHealth<intMAXHealth;
	}
	//this shows all the available nets and allows to choose one
	public Net chooseNet(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while (true){
			char n = 'A';
			String strOutput = "Please choose a net\n";
			for (Net net:inventory.getNets()){
				strOutput += n + ") " + net.getStrName() + "\n";
				n++;
			}
			System.out.print(strOutput);
		
			char charInput = '=';
		
			try {
				charInput = reader.readLine().toUpperCase().charAt(0);
			} catch (Exception e) {
				charInput ='=';
			}
			n='A';
			for (Net net:inventory.getNets()){
				if (charInput ==n){
					return net;
				}
				n++;
			}
			System.out.println("Incorrect input");
		}
	}
	//method to print out the inventory and allow the player to do specific things to it
	public void examineInventory(){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader (input);
		
		System.out.println(inventory);
		
		if (Confirm.getConfirm("Examine items?")){
			while(true){
				char n = 'A';
				String strOptions = "Please choose an option\n";
				if (inventory.getEquipped().getMagicWand()!=null){
					strOptions += n + ") " + inventory.getEquipped().getMagicWand().getStrName() + "(equipped) \n";
					n++;
				}
				if (inventory.getEquipped().getWeapon()!=null){
					strOptions += n + ") " + inventory.getEquipped().getWeapon().getStrName() + "(equipped) \n";
					n++;
				}
				if (inventory.getEquipped().getShield()!=null){
					strOptions += n + ") " + inventory.getEquipped().getShield().getStrName() + "(equipped) \n";
					n++;
				}
				if (inventory.getEquipped().getArmour()!=null){
					strOptions += n + ") " + inventory.getEquipped().getArmour().getStrName() + "(equipped) \n";
					n++;
				}
				for (Weapon current:inventory.getWeapons()){
					strOptions += n+ ") " + current.getStrName() + "\n";
					n++;
				}
				for (MagicWand current:inventory.getMagicWands()){
					strOptions += n+ ") " + current.getStrName() + "\n";
					n++;
				}
				for (Armour current:inventory.getArmours()){
					strOptions += n+ ") " + current.getStrName() + "\n";
					n++;
				}
				for (Shield current:inventory.getShields()){
					strOptions += n+ ") " + current.getStrName() + "\n";
					n++;
				}
				for (Net net:inventory.getNets()){
					strOptions += n+ ") " + net.getStrName() + "\n";
					n++;
				}
				for (HealingPotion potion:inventory.getHealingPotions()){
					strOptions += n + ") " + potion.getStrName() + "\n";
					n++;
				}
				for (StrengthPotion potion:inventory.getStrengthPotions()){
					strOptions += n + ") " + potion.getStrName() + "\n";
					n++;
				}
				for (DefensePotion potion:inventory.getDefensePotions()){
					strOptions += n + ") " + potion.getStrName() + "\n";
					n++;
				}
				for (Key key: inventory.getKeychain()){
					strOptions += n + ") " + key.getStrName() + "\n";
					n++;
				}
				for (HealingSpell spell: inventory.getSpellbook().getHealingSpells()){
					strOptions += n + ") " + spell.getStrName() + "\n";
					n++;
				}
				for (AttackSpell spell: inventory.getSpellbook().getAttackSpells()){
					strOptions += n + ") " + spell.getStrName() + "\n";
					n++;
				}
				for (ModifyingSpell spell:inventory.getSpellbook().getModifyingSpells()){
					strOptions += n + ") " + spell.getStrName() + "\n";
					n++;
				}
				if (inventory.getSpellbook().getFunctionSpell()!=null){
					strOptions += n + ") " + inventory.getSpellbook().getFunctionSpell().getStrName();
					n++;
				}
				for (Note note:inventory.getNotes()){
					strOptions += n + ") " + note.getStrName() + "\n";
					n++;
				}
				strOptions += n + ") Exit\n";
				System.out.print(strOptions);
				char charInput = '=';
				try {
					charInput = reader.readLine().toUpperCase().charAt(0);
				} catch (Exception e) {
					charInput ='=';
				}
				n = 'A';
				if (inventory.getEquipped().getMagicWand()!=null){
					if (charInput ==n){
						System.out.println(inventory.getEquipped().getMagicWand().getStrName() + ": " + inventory.getEquipped().getMagicWand().getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				if (inventory.getEquipped().getWeapon()!=null){
					if (charInput ==n){
						System.out.println(inventory.getEquipped().getWeapon().getStrName() + ": " + inventory.getEquipped().getWeapon().getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				if (inventory.getEquipped().getShield()!=null){
					if (charInput ==n){
						System.out.println(inventory.getEquipped().getShield().getStrName() + ": " +  inventory.getEquipped().getShield().getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				if (inventory.getEquipped().getArmour()!=null){
					if (charInput ==n){
						System.out.println(inventory.getEquipped().getArmour().getStrName() + ": " + inventory.getEquipped().getArmour().getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				int i = 0;
				while (i<inventory.getWeapons().size()){
					Weapon current = inventory.getWeapons().get(i);
					if (charInput ==n){
						System.out.println(current.getStrDescription());
						if (Confirm.getConfirm("Equip " + current.getStrName())){
							if (equipWeapon(current)){
								inventory.getWeapons().remove(i);
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				i = 0;
				while (i<inventory.getMagicWands().size()){
					MagicWand current = inventory.getMagicWands().get(i);
					if (charInput ==n){
						System.out.println(current.getStrDescription());
						if (Confirm.getConfirm("Equip " + current.getStrName())){
							if (equipMagicWand(current)){
								inventory.getMagicWands().remove(i);
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				i = 0;
				while (i<inventory.getArmours().size()){
					Armour current = inventory.getArmours().get(i);
					if (charInput ==n){
						System.out.println(current.getStrDescription());
						if (Confirm.getConfirm("Equip " + current.getStrName())){
							if (equipArmour(current)){
								inventory.getArmours().remove(i);
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				i = 0;
				while (i<inventory.getShields().size()){
					Shield current = inventory.getShields().get(i);
					if (charInput ==n){
						System.out.println(current.getStrDescription());
						if (Confirm.getConfirm("Equip " + current.getStrName())){
							if (equipShield(current)){
								inventory.getShields().remove(i);
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				for (Net net:inventory.getNets()){
					if (charInput ==n){
						System.out.println(net.getStrName() + ": " + net.getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				i = 0;
				while (i<inventory.getHealingPotions().size()){
					HealingPotion currentPotion = inventory.getHealingPotions().get(i);
					if (charInput ==n){
						System.out.println(currentPotion.getStrDescription());
						if (needsHealing()){
							if (Confirm.getConfirm("Use " + currentPotion.getStrName())){
								healingPotion(currentPotion);
								inventory.getHealingPotions().remove(i);
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				i = 0;
				while (i<inventory.getStrengthPotions().size()){
					StrengthPotion currentPotion = inventory.getStrengthPotions().get(i);
					if (charInput ==n){
						System.out.println(currentPotion.getStrDescription());
						if (Confirm.getConfirm("Use " + currentPotion.getStrName())){
							strengthPotion(currentPotion);
							inventory.getStrengthPotions().remove(i);
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}
				i = 0;
				while (i<inventory.getDefensePotions().size()){
					DefensePotion currentPotion = inventory.getDefensePotions().get(i);
					if (charInput ==n){
						System.out.println(currentPotion.getStrDescription());
						if (Confirm.getConfirm("Use " + currentPotion.getStrName())){
							defensePotion(currentPotion);
							inventory.getDefensePotions().remove(i);
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					i++;
					n++;
				}

				for (Key key: inventory.getKeychain()){
					if (charInput ==n){
						System.out.println(key.getStrName() + ": " + key.getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				for (HealingSpell spell: inventory.getSpellbook().getHealingSpells()){
					if (charInput ==n){
						System.out.println(spell.getStrName() + " " + spell.getStrDescription());
						if (hasHealingSpells()&&needsHealing()){
							if (Confirm.getConfirm("Use spell?")){
								healPlayer(spell.getIntHeal());
							}
						}
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				for (AttackSpell spell: inventory.getSpellbook().getAttackSpells()){
					if (charInput ==n){
						System.out.println(spell.getStrName() + ": " +spell.getStrDescription());
						Confirm.pressEnter("<Press enter to continue>");
					}
					n++;
				}
				i =0;
				while (i< inventory.getSpellbook().getModifyingSpells().size()){
					if(charInput==n){
						ModifyingSpell spell = inventory.getSpellbook().getModifyingSpells().get(i);
						System.out.println(spell.getStrName() + ": " + spell.getStrDescription());
						if(inventory.getEquipped().magicWandEquipped()){
							if (Confirm.getConfirm("Use spell?")){
								if (modifyingSpellOptions(spell)){
									inventory.getSpellbook().getModifyingSpells().remove(i);
								}
							}
						}
					}
					n++;
					i++;
				}
				if (inventory.getSpellbook().getFunctionSpell()!=null){
					if (charInput ==n){
						inventory.getSpellbook().getFunctionSpell().getStrDescription();
					}
					n++;
				}
				for (Note note:inventory.getNotes()){
					if (charInput ==n){
						System.out.println(note.getStrDescription());
						if (Confirm.getConfirm("Read note?")){
							note.readNote();
						}
					}
					n++;
				}
				if (charInput==n){
					return;
				}
			}
		}
	}
	//inspects the container and allows the player to take things and add to the inventory
	public boolean inspectContainer(Container container){		
		if (container.isEmpty()){
			System.out.println(container.getStrDescription()+ " is empty");
			return false;
		}
		System.out.println ("You examine the " + container.getStrName());
		ListIterator<Item>iterator = container.getItems().listIterator();

		while (iterator.hasNext()){
			Item currentItem = iterator.next();
			System.out.println("\n" +currentItem.getStrName()+ ": " + currentItem.getStrDescription());

			if (Confirm.getConfirm("Take " + currentItem.getStrName() + "?")){
				//the program determines what to do with the item by checking it's class
				if (currentItem.getClass().isInstance(new Key("","", ""))){
					System.out.println("Key " + currentItem.getStrName() + " added.");
					inventory.getKeychain().add((Key)currentItem);
				
				}else if(currentItem.getClass().isInstance(new HealingPotion("","",0))){
					if (needsHealing()){
						if(Confirm.getConfirm("Use " + currentItem.getStrName() + " ?")){
							healingPotion((HealingPotion)currentItem);
						}else{
							System.out.println(currentItem.getStrName() + " added.");
							inventory.getHealingPotions().add((HealingPotion)currentItem);
						}
					}
					else{
						System.out.println("Healing potion " + currentItem.getStrName() + " added.");
						inventory.getHealingPotions().add((HealingPotion)currentItem);
					}
				
				}else if(currentItem.getClass().isInstance(new StrengthPotion("","",0, "", 0))){
					if(Confirm.getConfirm("Use "+ currentItem.getStrName() +" ?")){
						strengthPotion((StrengthPotion)currentItem);
					}else{
						System.out.println(currentItem.getStrName() + " added.");
						inventory.getStrengthPotions().add((StrengthPotion)currentItem);
					}
				
				}else if(currentItem.getClass().isInstance(new DefensePotion("","",0, "", 0))){
					if(Confirm.getConfirm("Use "+ currentItem.getStrName() +" ?")){
						defensePotion((DefensePotion)currentItem);
					}else{
						System.out.println(currentItem.getStrName() + " added.");
						inventory.getDefensePotions().add((DefensePotion)currentItem);
					}
				
				}else if(currentItem.getClass().isInstance(new HealingSpell("",0))){
					System.out.println(currentItem.getStrName() + " added.");
					inventory.getSpellbook().getHealingSpells().add((HealingSpell)currentItem);
				
				}else if(currentItem.getClass().isInstance(new AttackSpell("", 0))){
					System.out.println(currentItem.getStrName() + " added");
					inventory.getSpellbook().getAttackSpells().add((AttackSpell)currentItem);
				
				}else if(currentItem.getClass().isInstance(new ModifyingSpell("", 0))){
					System.out.println(currentItem.getStrName() + " added.");
					inventory.getSpellbook().getModifyingSpells().add((ModifyingSpell)currentItem);

				}else if(currentItem.getClass().isInstance(new FunctionSpell(""))){
					System.out.println(currentItem.getStrName() + " added");
					inventory.getSpellbook().setFunctionSpell((FunctionSpell)currentItem);
				
				}else if(currentItem.getClass().isInstance(new Note("","",""))){
					if (Confirm.getConfirm("Read note?")){
						((Note)currentItem).readNote();
					}
					inventory.getNotes().add((Note)currentItem);
					System.out.println(currentItem.getStrName() + " added");
				
				}else if(currentItem.getClass().isInstance(new Net("", "", 0))){
					inventory.getNets().add((Net)currentItem);
					System.out.println(currentItem.getStrName() + " added");
				
				}else if (currentItem.getClass().isInstance(new Weapon("","",0,0))){
					if (Confirm.getConfirm("Equip " + currentItem.getStrName() + "?")){
						if(!equipWeapon((Weapon) currentItem)){
							System.out.println(currentItem.getStrName() + " added to inventory.");
							inventory.getWeapons().add((Weapon)currentItem);
						}
					}else{
					System.out.println(currentItem.getStrName() + " added to inventory.");
					inventory.getWeapons().add((Weapon)currentItem);
					}
				
				}else if (currentItem.getClass().isInstance(new MagicWand("",""))){
					if (Confirm.getConfirm("Equip " + currentItem.getStrName() + "?")){
						if(!equipMagicWand((MagicWand) currentItem)){
							System.out.println(currentItem.getStrName() + " added to inventory.");
							inventory.getMagicWands().add((MagicWand)currentItem);
						}
					}else{
					System.out.println(currentItem.getStrName() + " added to inventory.");
					inventory.getMagicWands().add((MagicWand)currentItem);
					}
				
				}else if (currentItem.getClass().isInstance(new Armour("","",0))){
					if (Confirm.getConfirm("Equip " + currentItem.getStrName() + "?")){
						if(!equipArmour((Armour) currentItem)){
							System.out.println(currentItem.getStrName() + " added to inventory.");
							inventory.getArmours().add((Armour)currentItem);
						}
					}else{
					System.out.println(currentItem.getStrName() + " added to inventory.");
					inventory.getArmours().add((Armour)currentItem);
					}
				
				}else if (currentItem.getClass().isInstance(new Shield("","",0))){
					if (Confirm.getConfirm("Equip " + currentItem.getStrName() + "?")){
						if(!equipShield((Shield) currentItem)){
							System.out.println(currentItem.getStrName() + " added to inventory.");
							inventory.getShields().add((Shield)currentItem);
						}
					}else{
					System.out.println(currentItem.getStrName() + " added to inventory.");
					inventory.getShields().add((Shield)currentItem);
					}
				}else if (currentItem.getClass().isInstance(new WonItem())){
					return true; 
				}else{
					System.out.println(currentItem.getStrName() + " added.");
					inventory.getRest().add(currentItem);
				}
				iterator.remove();
			}
		}
		return false;
	}
}
