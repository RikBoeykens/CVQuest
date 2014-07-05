package main;
/*
 * 	Start class: asks the player name and starts the game
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import player.Player;
import movement.*;

public class Start {

	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		
		System.out.print("Welcome to CV Quest! You'll be going on a search for the lost Scroll of Curriculum Vitae.\n"
				+ "But first: what is your name? ");
		
		String strName = "";
		
		try {
			strName = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		Player pl = new Player(strName);
		Explore explore = new Explore(pl);
		
		Play game = new Play (pl, explore);
		
		if (Confirm.getConfirm("Show tutorial?")){
			Tutorial tut = new Tutorial();
			tut.showTutorial();
		}
		
		game.Menu();

	}

}
