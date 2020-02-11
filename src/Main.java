import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	private static FileHelper<String> fileHelper = new FileHelper<>("src/wordseasy.txt", new WordLineConverter());


	public static void main(String[] args) {
	
//		Path path1 = Paths.get("src/wordseasy.txt");
//		if (Files.notExists(path1)) {
//			try {
//				Files.createFile(path1);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		Path path2 = Paths.get("src/stats.txt");
		if (Files.notExists(path2)) {
			try {
				Files.createFile(path2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		Scanner scanner = new Scanner(System.in);
		

		Player player = createPlayer();
		
	
		
	do {
		ArrayList<Character> guessed = new ArrayList<>();
		String word = randWord(fileHelper);
		WordValid wv = new WordValid(word);
		wv.setDifficulty();
		
		do {
			playGame(player, wv, guessed);
		} while (!wv.win() && !wv.lose());

		if (wv.win()) {
			System.out.println("You won!");
			player.win();
		} else if (wv.lose()) {	
			System.out.println("You lost");
			player.lose();
			System.out.println(word);
		}
	}while(cont());
	player.addStat();
	
	
	FileHelper<Player> fileHelper2 = new FileHelper<>("src/stats.txt", new StatLineConverter());
	displayStat(fileHelper2);
	
	System.out.println("Good game");
	
	}

	public static void playGame(Player player, WordValid word, ArrayList<Character> list) {
		
		Scanner scan = new Scanner(System.in);
		word.showUnder();
		System.out.println(word.getChances());
		char letter = Validator.getChar(scan, "Guess a letter: ");
		if (word.valid(letter)) {
			System.out.println("Hit!");
		} else {
			System.out.println("Miss!");
			list.add(letter);
			
			// System.out.println(word.getMisses());
		}
		System.out.println(list);

	}
	
	public static String randWord(FileHelper filehelper) {
		List<String> words = filehelper.readAll();
		int rand = (int)(Math.random()*words.size());
		String word = words.get(rand);
		return word;
		
	}
	
	public static boolean cont() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		String cont = Validator.getStringMatchingRegex(scan, "Do you want to go again?[y/n]", "[y,n]"); 
		if (cont.equals("y")) {
			return true;
		}else {
			return false;
		}		
		
	}
	
	public static Player createPlayer() {
		Scanner scanner = new Scanner(System.in);
		String n = Validator.getString(scanner, "Enter Player name: ");
		Player p = new Player(n);
		return p;
	}
	
	public static void displayStat(FileHelper fileHelper) { //location of filehelper tho
		List<Player> players = fileHelper.readAll();  
		
		Collections.sort(players, Player.WIN_ORDER);	
		
		System.out.printf("%10s %10s %20s\n", "Wins", "Losses", "User");
		for (Player player : players) {
			System.out.printf("%10d %10d %20s\n", player.getWins(), player.getLoses(), player.getName());
		}
		System.out.println();
	}
	

}
