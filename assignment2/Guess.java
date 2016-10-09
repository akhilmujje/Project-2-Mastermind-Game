/* EE422C Project 2 (Mastermind) submission by 9/22/2016
 * Devisriram Akhil Mujje
 * dam4335
 * Slip days used: <0>
 * Fall 2016
 */


package assignment2;

public class Guess {
	private String guess;
	

	public Guess() {
		guess = "";		
	}

	/**
	 * getGuess
	 * returns the guess string inputed by Player
	 * @return the guess of the code
	 */
	public String getGuess() {
		return guess;
	}	
	
	/**
	 * addGuess
	 * Verifies whether the given guess is valid or not
	 * @param c string inputed by Player
	 * @return true if guess is valid or false if not
	 */
	public boolean addGuess(String c) {
        int false_count = 0;
               
        if(c.length() != GameConfiguration.pegNumber)
        	return false;
        
        
		for (int j = 0; j < c.length(); j++) {
			for (int i = 0; i < GameConfiguration.colors.length; i++) {
				if (GameConfiguration.colors[i].charAt(0) == (c.charAt(j)))
					break;
				else
					false_count++;				
			}
			if(false_count == GameConfiguration.colors.length)
				return false;
			false_count = 0;
		}
		guess += c;
		
		return true;
	}

}
