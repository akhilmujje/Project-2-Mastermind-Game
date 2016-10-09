/* EE422C Project 2 (Mastermind) submission by 9/22/2016
 * Devisriram Akhil Mujje
 * dam4335
 * Slip days used: <0>
 * Fall 2016
 */

package assignment2;

public class Driver {
	
	/**
	 * main
	 * runs the Mastermind program
	 * @param args checks to see argument commands whether program should be run in testing mode
	 */
	public static void main(String[] args) {
		boolean test_flag = false;

		int firstArg = 0;

		if (args.length > 0)
			firstArg = Integer.parseInt(args[0]);
		if (firstArg == 1) {
			test_flag = true;
		}
		Game g = new Game(test_flag);
		g.runGame();

	}

}
