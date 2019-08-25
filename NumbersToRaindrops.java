package raindropNumbers;
import java.io.*;

/*
This programming challenge is again from exercism.io.

The program should be able to take a number as an input and convert it as follows:

    if the number has 3 as a factor, output "Pling".
    if the number has 5 as a factor, output "Plang".
    if the number has 7 as a factor, output "Plong".
    if the number does not have 3,5 or 7 as a factor, just pass the number's digits straight through.

Examples:
28's factors are 1, 2, 4, 7, 14, 28 - in raindrop-speak, this would be a simple "Plong".
30's factors are 1, 2, 3, 5, 6, 10, 15 - in raindrop-speak, this would be a "PlingPlang".

 */

public class NumbersToRaindrops {
	
	public static void main(String[] args) throws IOException {
		String number = EnterNumber();
		if(CheckInput(number)) {
			int n = Integer.parseInt(number);
			System.out.println(PlingPlangPlong(n));
		}
	}

	private static String EnterNumber() throws IOException {
		System.out.println("Please enter an integer number.");
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		String Number = reader.readLine();
		return Number;
		}
	
	private static boolean CheckInput(String numb) {
		try {
			Integer.parseInt(numb);
			return true;
		}
		catch(NumberFormatException nfe) {
			System.out.println("I'm afraid that's not an integer.");
			return false;
		}
	}
		
	private static String PlingPlangPlong(int n) {
		String Raindrops = "";
		if(IsFactor(n,3)) Raindrops = Raindrops + "Pling";
		if(IsFactor(n,5)) Raindrops = Raindrops + "Plang";
		if(IsFactor(n,7)) Raindrops = Raindrops + "Plong";
		if(Raindrops.equals("")) Raindrops = String.valueOf(n);
		return Raindrops;
	}
	
	private static boolean IsFactor(int n, int fac) {
		return (n%fac==0);
	}
	
	}