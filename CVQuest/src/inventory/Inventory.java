package inventory;

/*
 *	Inventory class: This holds all the items which are held by the player
 */
import java.util.ArrayList;

import armsandarmour.*;
import potions.*;
import magic.*;

public class Inventory {
	private Equipped equipped = new Equipped();
	private ArrayList<Weapon>weapons = new ArrayList<Weapon>();
	private ArrayList<MagicWand>magicwands = new ArrayList<MagicWand>();
	private ArrayList<Armour>armours = new ArrayList<Armour>();
	private ArrayList<Shield>shields = new ArrayList<Shield>();
	private Spellbook spellbook = new Spellbook();
	private Potions potions = new Potions();
	private ArrayList<Key>keychain = new ArrayList<Key>();
	private ArrayList<Note>notes = new ArrayList<Note>();
	private ArrayList<Net>nets = new ArrayList<Net>();
	private ArrayList<Item>rest = new ArrayList<Item>();
	
	public Inventory(){
	
	}
	public ArrayList<Key>getKeychain(){
		return keychain;
	}
	
	public ArrayList<HealingPotion> getHealingPotions(){
		return potions.getHealingpotions();
	}
	
	public ArrayList<DefensePotion> getDefensePotions(){
		return potions.getDefensepotions();
	}
	
	public ArrayList<StrengthPotion> getStrengthPotions(){
		return potions.getStrengthpotions();
	}
	public ArrayList<Weapon> getWeapons(){
		return weapons;
	}
	public ArrayList<MagicWand> getMagicWands(){
		return magicwands;
	}

	public ArrayList<Armour> getArmours(){
		return armours;
	}

	public ArrayList<Shield> getShields(){
		return shields;
	}
	
	public ArrayList<Item>getRest(){
		return rest;
	}

	public Equipped getEquipped(){
		return equipped;
	}
	
	public Spellbook getSpellbook(){
		return spellbook;
	}
	
	public ArrayList<Note>getNotes(){
		return notes;
	}
	
	public ArrayList<Net>getNets(){
		return nets;
	}
	
	public String toString(){
		String output = "EQUIPPED\n=========\n";
		output += equipped.toString() + "\n";
		output += "NOT EQUIPPED\n==============\n";
		output += "Arms and armour:\n";
		if(weapons.isEmpty()&&magicwands.isEmpty()&&armours.isEmpty()&&shields.isEmpty()){
			output+="\tNone\n";
		}else{
			if (!weapons.isEmpty()){
				output += "\tWeapons:\n";
				for (Weapon weapon: weapons){
					output += "\t\t" + weapon.toString() + "\n";
				}
			}
			if (!magicwands.isEmpty()){
				output += "\tMagic Wands:\n";
				for (MagicWand magicwand: magicwands){
					output += "\t\t" + magicwand.toString() + "\n";
				}
			}
			if (!armours.isEmpty()){
				output += "\tArmour:\n";
				for (Armour armour: armours){
					output += "\t\t" + armour.toString() + "\n";
				}
			}
			if (!shields.isEmpty()){
				output += "\tShields:\n";
				for (Shield shield: shields){
					output += "\t\t" + shield.toString() + "\n";
				}
			}
			
		}
		output += "Nets:\n";
		if(nets.isEmpty()){
			output+="\tNone\n";
		}else{
			for (Net net:nets){
				output += "\t" + net.getStrName() + "\n";
			}
		}
		output += "Potions:\n";
		if(potions.isEmpty()){
			output+="\tNone\n";
		}else{
			for (HealingPotion potion: potions.getHealingpotions()){
				output+="\t"+ potion.toString() + "\n";
			}
			for (DefensePotion potion: potions.getDefensepotions()){
				output +="\t" + potion.toString() + "\n";
			}
			for (StrengthPotion potion: potions.getStrengthpotions()){
				output +="\t" + potion.toString() + "\n";
			}
		}
		output+="Keys:\n";
		if(keychain.isEmpty()){
			output+="\tNone\n";
		}else{
			for (Key aKey: keychain){
				output += "\t"+ aKey.getStrName() + "\n";
			}
		}
		output += "Spells:\n";
		if (spellbook.getHealingSpells().isEmpty()&&spellbook.getAttackSpells().isEmpty()){
			output += "\tNone\n";
		}else{
			for (HealingSpell spell:spellbook.getHealingSpells()){
				output +="\t" + spell.getStrName() + "\n";
			}
			for (AttackSpell spell:spellbook.getAttackSpells()){
				output +="\t" + spell.getStrName() + "\n";
			}
			for (ModifyingSpell spell:spellbook.getModifyingSpells()){
				output += "\t" + spell.getStrName() + "\n";
			}
			if (spellbook.getFunctionSpell()!=null){
				output += "\t" + spellbook.getFunctionSpell().getStrName() + "\n";
			}
		}
		output += "Notes:\n";
		if (notes.isEmpty()){
			output += "\tNone\n";
		}else{
			for (Note current: notes){
				output += "\t" + current.getStrName() + "\n";
			}
		}
		return output;
	}
}
