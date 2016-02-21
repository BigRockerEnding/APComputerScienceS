package org.jointheleague.stephenh.MagPieS;

import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * 
 * @author Laurie White
 * @version April 2012
 */
public class MagPieSRunner {

	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args) {
		// MagPieS maggie = new MagPieS();
		MagPieSX maggie = new MagPieSX();

		System.out.println(maggie.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();

		while (!statement.equals("Bye")) {
			System.out.println(maggie.getResponse(statement.toLowerCase()));
			statement = in.nextLine();
		}
	}

}
