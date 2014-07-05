package main;
/*
 * 	Confirm class: this class is a help for the text based interface. It can print a y/n question or a <Press enter to continue>
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Confirm {
	
	public Confirm(){
	}
	//this method asks for validation of a yes-no question and outputs true/false based on this
	public static boolean getConfirm(String strMessage){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		System.out.println(strMessage + " y/n");
		char charInput = '-';
		try{
			charInput = reader.readLine().toLowerCase().charAt(0);
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			charInput = 'n';
		}
		return (charInput=='y');
		
	}
	
	public static void pressEnter(String strMessage){
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		System.out.println(strMessage);
		try {
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
