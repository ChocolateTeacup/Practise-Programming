package learningJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Given a person's allergy score, determine whether or not they're allergic to a given item, and their full list of allergies.
 * An allergy test produces a single numeric score which contains the information about all the allergies the person has 
 * (that they were tested for).
 * The list of items (and their value) that were tested are:
 * eggs (1)
 * peanuts (2)
 * shellfish (4)
 * strawberries (8)
 * tomatoes (16)
 * chocolate (32)
 * pollen (64)
 * cats (128)
 */

public class AllergyScore {
	
	public static void main(String[] args) throws IOException {
		int score = GetScore();
		int ceil = GetCeil(score); 
		String allergies = EvaluateScore(score,ceil);
		System.out.println("All allergies: " + allergies);
		TestItem(allergies);
	}

	private static void TestItem(String allergies) throws IOException {
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Please enter the item you want to know if the patient is allergic to:");
		String r = reader.readLine();
		r.toLowerCase();
		if(allergies.contains(r)) System.out.println("The patient is allergic to " + r + ".");
		else System.out.println("The patient is not allergic to " + r + ".");
	}

	private static String EvaluateScore(int score, int ceil) {
		String allergies = "";
		while(ceil>0) {
			if(score-ceil>=0) {
				score = score - ceil;
				if(ceil==128) allergies = allergies + "cats, ";
				if(ceil==64) allergies = allergies + "pollen, ";
				if(ceil==32) allergies = allergies + "chocolate, ";
				if(ceil==16) allergies = allergies + "tomatoes, ";
				if(ceil==8) allergies = allergies + "strawberries, ";
				if(ceil==4) allergies = allergies + "shellfish, ";
				if(ceil==2) allergies = allergies + "peanuts, ";
				if(ceil==1) allergies = allergies + "eggs, ";
			}
			ceil = (int)ceil/2;
		}
		if(allergies.length()==0) allergies = "No Allergies";
			else allergies = allergies.substring(0, allergies.length()-2);
		return allergies;
	}

	private static int GetCeil(int score) { //This sets the maximum size the score can be, telling us where to start comparing.
		int ceil = 1;
		while(ceil<score) {
			ceil = ceil*2;
		}
		return ceil;
	}

	private static int GetScore() throws IOException {
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Please enter the allergy score: (Integer Number)");
		String r = reader.readLine();
		if(CheckInput(r)) {
			return Integer.parseInt(r);
		}
		else return 0;
	}
	
	private static boolean CheckInput(String numb) {
		try {
			Integer.parseInt(numb);
			return true;
		}
		catch(NumberFormatException nfe) {
			System.out.println("Only integer numbers please.");
			return false;
		}
	}
}
