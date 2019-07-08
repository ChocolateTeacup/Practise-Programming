package numberProblem;
import java.io.*;
import java.util.*;

//Author: ChocolateTeapot (ChocolateTeacup)

public class NumberProblem  {

	/*
	    Write a program that is able to parse simple math word problems and outputs the answer.
		For example: "What is 5 plus 13?"
		Output answer: "18".

		Handle large numbers and negative numbers.
		Start with addition and then implement subtraction, multiplication and Division.
		Examples: "What is 7 minus 5?", "What is 6 multiplied by 4?", "What is 25 divided by 5?"

		You can also add multiple operations. Since these are verbal word problems, 
		evaluate the expression from left-to-right, ignoring the typical order of operations.
		Examples: "What is 5 plus 13 plus 6?", 
		"What is 3 plus 2 multiplied by 3?" =15 (i.e. not 9)
	 */
	
	public static void main(String[] args) throws IOException {
		String Q = Question();
		List<String> W = DismantelingThe(Q);
		//System.out.println(W);
		List<Double> N = GetNumbers(W);
		List<String> O = GetOperators(W);
		double Answer = UnderstandingThe(O,N);
		if (Answer == (int)Answer) System.out.println((int) Answer);
		else System.out.println(Answer);
	}

	// Reads in the Question.
	public static String Question() throws IOException {
		  BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		String Question = reader.readLine(); 
		return Question;
	}

	/* Separates the Question into a list of words. Inspired by Hanspeter Mössenböck's 
	   "Sprechen Sie Java?" programming book. */
	public static List<String> DismantelingThe(String Q) {
		int i = 0;
		List<String> Words = new ArrayList<String>(); 
		int l = Q.length();
		while (i<l) {
			while (i < l && !Character.isLetterOrDigit(Q.charAt(i)) && Q.charAt(i) != '-') {
				i++;
			}
			int beg = i;
			while (i < l && (Character.isLetterOrDigit(Q.charAt(i)) || Q.charAt(i) == '-')) {
				i++;
			}
			if (i>beg) {
				String w = Q.substring(beg,i);
				Words.add(w);
			}
		}
		return Words;
	}
	
	//Finds the numbers in the question and creates a list containing only them.
	public static List<Double> GetNumbers(List<String> Q){
		List<Double> numbers = new ArrayList<Double>();
		int i = 0;
		while(i < Q.size()) {			
			boolean convert = NumCheck(Q.get(i));
			if (convert == true)
				numbers.add(Double.parseDouble(Q.get(i)));
			i++;
		}
		return numbers;
	}
	
	/*Finds the operators in the question and creates a list containing only them. 
	  Meant to find as quite a few permutations of . */
	public static List<String> GetOperators(List<String> Q){
		List<String> operators = new ArrayList<String>();
		int i = 0;
		while(i < Q.size()) {			
			if (Q.get(i).equalsIgnoreCase("Plus")) operators.add("plus");
			if (Q.get(i).startsWith("Add")||Q.get(i).startsWith("add")) operators.add("plus");
			if (Q.get(i).equalsIgnoreCase("Minus")) operators.add("minus");
			if (Q.get(i).startsWith("Sub")||Q.get(i).startsWith("sub")) operators.add("minus");
			if (Q.get(i).equalsIgnoreCase("Times")) operators.add("times");
			if (Q.get(i).startsWith("Mult")||Q.get(i).startsWith("mult")) operators.add("times");
			if (Q.get(i).startsWith("Div")||Q.get(i).startsWith("div")) operators.add("divide");
			i++;
		}
		return operators;
	}
	
	/*Looping over the input to get an answer in the sequence the numbers and operators
	are entered. */
	public static Double UnderstandingThe(List<String> Operate, List<Double> Num) {
		int i = 1; int j = 0;
		Double result = 0.0;
		while(i < Num.size() && j < Operate.size()) {
			if (Operate.get(j).equals("plus")){
				result = Add(Num.get(i-1),Num.get(i));
				Num.set(i, result);
				i++;
				j++;
			}
			else if (Operate.get(j).equals("minus")){
				result = Subtract(Num.get(i-1),Num.get(i));
				Num.set(i, result);
				i++;
				j++;
			}
			else if (Operate.get(j).equals("times")){
				result = Multiply(Num.get(i-1),Num.get(i));
				Num.set(i, result);
				i++;
				j++;
			}
			else if (Operate.get(j).equals("divide")){
				result = Divide(Num.get(i-1),Num.get(i));
				Num.set(i, result);
				i++;
				j++;
			}
		}
		if (Num.size()>=1) return Num.get(i-1); //Handles the case that no numbers are entered.
		else return result;
	}
	
	
	//Tests if a string is a number
	public static boolean NumCheck(String s) {
		boolean isNumber = true;
		int i = 0;
		if (s.charAt(0) == '-' && s.length()>1) i++;
		while (i<s.length()) {
			if (!Character.isDigit(s.charAt(i))) {
				isNumber = false;
				break;
			}
			i++;
		}
		return isNumber;
	}
	
	// Adds two numbers
	public static double Add(double a, double b) {
		return a + b;
	}
	
	// Multiply two numbers
	public static double Multiply(double a, double b) {
		return a * b;
	}
	
	// Subtract the second number from the first
	public static double Subtract(double a, double b) {
		return a - b;
	}
	
	// Divide the first number by the second
	public static double Divide(double a, double b) {
		return a/b;
	}
	
}
