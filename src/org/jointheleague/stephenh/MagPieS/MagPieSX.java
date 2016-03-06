package org.jointheleague.stephenh.MagPieS;

import java.util.HashMap;
import java.util.Map;

/**
 * A program to carry on conversations with a human user. This version:
 * <ul>
 * <li>Uses advanced search for keywords</li>
 * <li>Will transform statements as well as react to keywords</li>
 * </ul>
 * 
 * @author Laurie White
 * @version April 2012
 *
 */
public class MagPieSX {
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		Map<String, String> responses = new HashMap<>();
		responses.put("no", "Why so negative?");
		responses.put("mother", "Tell me more about your family.");
		responses.put("father", "Tell me more about your family.");
		responses.put("sister", "Tell me more about your family.");
		responses.put("brother", "Tell me more about your family.");
		responses.put("mrs.", "She sounds like a good person");
		responses.put("mr.", "He sounds like a good person");
		responses.put("doctor who", "Doctor Who? I love Doctor Who!");
		responses.put("kirby", "Are you talking about that pink puffball?");
		String response = "";
		if (statement.length() == 0) {
			response = "Say something, please.";
		}

		for (String key : responses.keySet()) {
			if (findKeyword(statement, key, 0) >= 0) {
				response = responses.get(key);
			}
			
		}
		
		if (response.length() > 0) return response;
		// Responses which require transformations
		else if (findKeyword(statement, "say", 0) >= 0) {
			response = transformSayStatement(statement);
		}
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
		} else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement);
		}

		else {
			// Look for a two word (you <something> me)
			// pattern
			int psnY = findKeyword(statement, "you", 0);
			int psnI = findKeyword(statement, "I", 0);

			if (psnY >= 0 && findKeyword(statement, "me", psnY) >= 0) {
				response = transformYouMeStatement(statement);
			} else if (psnI >= 0 && findKeyword(statement, "you", psnI) >= 0) {
				response = transformIYouStatement(statement);
			} else {
				response = getRandomResponse();
			}
		}
		return response;
	}

	private String transformSayStatement(String statement) {
			statement = statement.trim();
			String lastChar = statement.substring(statement
					.length() - 1);
			if (lastChar.equals(".")) {
				statement = statement.substring(0, statement
						.length() - 1);
			}
			int psn = findKeyword(statement, "say", 0);
			String restOfStatement = statement.substring(psn + 3).trim();
			return "Okay! " + restOfStatement;
	}

	private String transformIWantStatement(String statement) {
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword(statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into "What would it mean to <something>?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "you <something> me" and transform it into "What makes you think that I <something> you?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfYou = findKeyword(statement, "you", 0);
		int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}

	private String transformIYouStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfI = findKeyword(statement, "I", 0);
		int psnOfYou = findKeyword(statement, "you", psnOfI + 1);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method will check that the given goal is
	 * not a substring of a longer string (so, for example, "I know" does not contain "no").
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		// The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		// Refinement--make sure the goal isn't part of a word
		while (psn >= 0) {
			// Find the string of length 1 before and after the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}

			// If before and after aren't letters, we've found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method will check that the given goal is
	 * not a substring of a longer string (so, for example, "I know" does not contain "no"). The search begins at the
	 * beginning of the string.
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		}

		return response;
	}

}
