import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Player {
	private static FileHelper<Player> fileHelper2 = new FileHelper<>("src/stats.txt", new StatLineConverter());
	String name;
	int wins;
	int loses;
	int avgGuesses; 
	int averageWins;
	static final Comparator<Player> WIN_ORDER = new Comparator<Player>() {

		@Override
		public int compare(Player player1, Player player2) {
			if (player1.getAvgWins() > player2.getAvgWins()) {
				return -1;
				
			}else if(player1.getAvgWins() < player2.getAvgWins()){
					return 1;
			}
			return 0;
		}
		
	};
	
	
	public Player(String name) {
		this.name = name;
		this.wins = 0;
		this.loses = 0;
		// Validate difficulty entry

	}
	
	public Player(int wins, int losses, String name, int avgGuess, int avgWin) {
		this.name = name;
		this.wins = wins;
		this.loses = losses;
		this.avgGuesses = avgGuess;
		this.averageWins = avgWin;
		// Validate difficulty entry

	}
	
	public void avgWins() {
		double avg = wins/((double)wins+(double)loses);
		averageWins = (int)(avg*100);
	}
	
	public int getAvgWins() {
		return averageWins;
	}
	
	public void setAvgGuess(int avgGuess) {
		this.avgGuesses = avgGuess;
	}
	
	public int getAvgGuess() {
		return avgGuesses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void win() {
		
		this.wins += 1;
	}

	public int getLoses() {
		return loses;
	}

	public void lose() {
		this.loses += 1;
	}
	
	public void addStat() {
		fileHelper2.append(this);
	}
	
	

}
