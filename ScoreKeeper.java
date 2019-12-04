package learningJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreKeeper {
	String Name;
	int Score = 0;
	
	/*5 Friends (let's call them a, b, c, d and e) are playing a game and need to keep track of the scores. 
	 * Each time someone scores a point, the letter of their name is typed in lowercase. If someone loses a point, 
	 * the letter of their name is typed in uppercase. Give the resulting score from highest to lowest.

	 * Input Description:
	 * A series of characters indicating who scored a point.
     * Examples:
     * abcde
	 * dbbaCEDbdAacCEAadcB

     * Output Description:
	 * The score of every player, sorted from highest to lowest.
	 * Examples: 
	 * a:1, b:1, c:1, d:1, e:1
	 * b:2, d:2, a:1, c:0, e:-2
	 */

	public static void main(String[] args) throws IOException {
		ScoreKeeper[] Players = MakePlayers();
		String s = GetScores();
		Players = EvaluateString(s, Players);
		Players = SortPlayers(Players);
		PrintResult(Players);
	}

	private static void PrintResult(ScoreKeeper[] players) {
		String s = "";
		for(int i=0; i<players.length; i++) {
			s = s + players[i].Name + ":" + players[i].Score + ", ";
		}
		s = s.substring(0, s.length()-2); //Trims of the last comma and whitespace 
		System.out.println("The Scores Are:");
		System.out.println(s);
	}

	private static String GetScores() throws IOException {
		System.out.println("Please Enter The Score String. (Lowercase: player gains point, Uppercase: player loses point");
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		String s = reader.readLine();
		return s;
	}

	private static ScoreKeeper[] SortPlayers(ScoreKeeper[] players) { //Just bubblesort. There are 5 of them! Alphabetically for ties.
		ScoreKeeper temp = new ScoreKeeper(); 
		int j = 0;
		for(int i = players.length-1; i>0; i--) {
			for(j = 0; j<i; j++) {
				if(players[j].Score<players[j+1].Score) {
					temp = players[j];
					players[j] = players[j+1];
					players[j+1] = temp;
				}
			}
		}
		return players;
	}

	private static ScoreKeeper[] EvaluateString(String s, ScoreKeeper[] players) { //Ignores characters that don't change the score.
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)=='a') players[0].Score++;
			if(s.charAt(i)=='A') players[0].Score--;
			
			if(s.charAt(i)=='b') players[1].Score++;
			if(s.charAt(i)=='B') players[1].Score--;
			
			if(s.charAt(i)=='c') players[2].Score++;
			if(s.charAt(i)=='C') players[2].Score--;
			
			if(s.charAt(i)=='d') players[3].Score++;
			if(s.charAt(i)=='D') players[3].Score--;
			
			if(s.charAt(i)=='e') players[4].Score++;
			if(s.charAt(i)=='E') players[4].Score--;
		}
		return players;
	}

	private static ScoreKeeper[] MakePlayers() {
		ScoreKeeper[] Players = new ScoreKeeper[5];
		System.out.println();
		Players[0] = new ScoreKeeper(); Players[0].Name = "Anna"; 
		Players[1] = new ScoreKeeper(); Players[1].Name = "Berta"; 
		Players[2] = new ScoreKeeper(); Players[2].Name = "Clara"; 
		Players[3] = new ScoreKeeper(); Players[3].Name = "Dora"; 
		Players[4] = new ScoreKeeper(); Players[4].Name = "Emily";
		return Players;
	}

}
