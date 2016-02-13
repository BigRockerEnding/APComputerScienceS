package org.jointheleague.stephenh.ElevenS;

/**
 * This is a class that plays the GUI version of the Elevens game.
 * See accompanying documents for a description of how Elevens is played.
 */
public class ElevenSGUIRunner {

	/**
	 * Plays the GUI version of Elevens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board board = new ElevenSBoard();
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
	}
}

