/* EE422C Project 2 (Mastermind) submission by 9/22/2016
 * Devisriram Akhil Mujje
 * dam4335
 * Slip days used: <0>
 * Fall 2016
 */

package assignment2;

import java.util.Scanner;

public class Game {

	private String code;
	private Guess[] guess_array;
	private Feedback[] fb;
	private Scanner kbd;
	private boolean testing_mode;	

	/*
	 * Game
	 * Constructs a Game object and runs it
	 * in either testing mode or non-testing mode
	 * @param flag boolean representing whether program should be run in testing mode or not
	 */
	public Game(boolean flag) {
		if (flag == true)
			testing_mode = true;
		else
			testing_mode = false;		
		code = "";
		kbd = new Scanner(System.in);
	}

	/*
	 * displayGreeting
	 * Outputs game greetings and instructions onto system console	 *  
	 */
	public void displayGreeting() {

		System.out.println("Welcome to Mastermind. Here are the rules. ");
		System.out.println("");
		System.out.println("This is a text version of the classic board game Mastermind. ");
		System.out.println("The computer will think of a secret code. The code consists of 4 ");
		System.out.println("colored pegs. ");
		System.out.println("The pegs MUST be one of six colors: blue, green, orange, purple, ");
		System.out.println("red, or yellow. A color may appear more than once in the code. You ");
		System.out.println("try to guess what colored pegs are in the code and what order they ");
		System.out.println("are in. After you make a valid guess the result (feedback) will be ");
		System.out.println("displayed. ");
		System.out.println("The result consists of a black peg for each peg you have guessed ");
		System.out.println("exactly correct (color and position) in your guess. For each peg in ");
		System.out.println("the guess that is the correct color, but is out of position, you get ");
		System.out.println("a white peg. For each peg that is fully incorrect, you get no ");
		System.out.println("feedback. ");
		System.out.println("Only the first letter of the color is displayed. B for Blue, R for ");
		System.out.println("Red, and so forth. ");
		System.out.println("When entering guesses you only need to enter the first character of ");
		System.out.println("each color as a capital letter. ");
		System.out.println();
		System.out.println("You have 12 guesses to figure out the secret code or you lose the ");
		System.out.println("game. Are you ready to play? (Y/N): ");

	}
	
	/*
	 * runGame
	 * Runs the game Mastermind
	 */
	public void runGame() {
		
		
		boolean keepPlaying = true;
		boolean invalid = false;
		//boolean history = false;
		int guess_count = 0;
		int num_guesses = GameConfiguration.guessNumber;
		int num_pegs = GameConfiguration.pegNumber;
		
		guess_array = new Guess[num_guesses];
		fb = new Feedback[num_pegs];

		displayGreeting();

		while (keepPlaying) {			

			if (kbd.nextLine().toUpperCase().equals("Y")) {
				
				num_guesses = GameConfiguration.guessNumber;
				num_pegs = GameConfiguration.pegNumber;
				code = "";
				guess_count = 0;
				System.out.print("Generating secret code ....    ");
				code += SecretCodeGenerator.getInstance().getNewSecretCode();

				if (testing_mode == true) {
					System.out.println("for this example the secret code is  ");
					System.out.println(code + ")");					
				}
				else
					System.out.println();
				int counter = num_guesses;

				// Guess and Feedback array declaration and initialization
				guess_array = new Guess[num_guesses];
				for (int i = 0; i < num_guesses; i++) {
					guess_array[i] = new Guess();
				}

				fb = new Feedback[num_guesses];
				for (int i = 0; i < num_guesses; i++) {
					fb[i] = new Feedback();
				}

				while (counter > 0) {

					if (invalid == false) {
						if (counter == 1)
							System.out.println("You have " + counter + " guess left.");
						else
							System.out.println("You have " + counter + " guesses left.");
					}
					System.out.println("What is your next guess?");
					System.out.println("Type in the characters for your guess and press enter.");
					System.out.println("Enter guess: ");

					int index = num_guesses - counter;
					String s = kbd.nextLine();

					if (s.toUpperCase().equals("HISTORY")) {
						
						if (guess_count == 0)
							System.out.println("");
						else {

							for (int i = 0; i < guess_count; i++) {
								System.out.println(guess_array[i].getGuess() + "\t\t" + fb[i].getBlack() + "B_"
										+ fb[i].getWhite() + "W");
							}

						}						
						System.out.println();
						continue;
					}

					if (guess_array[index].addGuess(s) == false) {
						System.out.println(s + " -> INVALID GUESS");
						System.out.println();
						counter++;
						invalid = true;
					} else {
						invalid = false;
						guess_count++;
						String p = guess_array[index].getGuess() + " -> Result: "
								+ fb[index].getFeedback(guess_array[index], code);						
						if (fb[index].getBlack() == num_pegs) {
							p += " - You win!!";
							counter = -1;
							code = "";

						}
						System.out.println(p);
					}

					counter--;				
					

				}
				
				if (counter == 0)
					System.out.println("(Sorry, you are out of guesses. You lose, boo-hoo.)");
				System.out.println("Are you ready for another game (Y/N): ");
			} else {
				keepPlaying = false;
			}

		}

		kbd.close();

	}

}
