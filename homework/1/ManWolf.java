/**
 * file: ManWolf.java
 * author: Tyler Rimaldi
 * course: CMPT 440
 * assignment: HW 1
 * due date: March/11/2020
 * version: 1.0
 * 
 * This file contains the declaration of the abstract public class ManWolf.
 */

import java.util.*;

public abstract class ManWolf {

  // Transition Table Columns in our DFA.
  private static final int WOLF = 0;
  private static final int GOAT = 1;
  private static final int CABBAGE = 2;
  private static final int NOTHING = 3;

  // Constant map (really a set) of alphabet/column-index for our DFA to transition with.
  private static final Map<Character, Integer> ALPHABET_INDEX = 
    // Ensure that we have a constant map and that it is serializable.
    Collections.unmodifiableMap(new HashMap<Character, Integer>() {
      private static final long serialVersionUID = 1L; 
      { 
        // put(AlphabetChar, Transition Table Column Index)
        put('w', WOLF);
        put('g', GOAT);
        put('c', CABBAGE);
        put('n', NOTHING);
      }
    });

  private static final int TOTAL_STATES = 10;

  // Constant map (really a set) of states for our DFA to transition to.
  private static final Map<String, Integer> STATES =
    // Ensure that we have a constant map and that it is serializable.
    Collections.unmodifiableMap(new HashMap<String, Integer>() {
      private static final long serialVersionUID = 1L;
      { 
        // Error state
        put("qE", -1);
        for (int i = 0; i < TOTAL_STATES; i ++) {
          // All other states
          put("q"+i, i);
        }
      }
    });

  private static final int START_STATE = 0;

  // Constant set of states that our DFA accepts on.
  private static final Set<Integer> ACCEPTANCE_STATES = 
    // Ensure that we have a constant set and that it is serializable.
    Collections.unmodifiableSet(new HashSet<Integer>() {
      private static final long serialVersionUID = 1L;
      { 
        add(STATES.get("q9"));
      }
    });

  // Constant state transition table for to create our delta in the DFA.
  private static final int[][] transitionTable = new int[][] {
    //     w                 g                 c                 n                 q
    { STATES.get("qE"), STATES.get("q1"), STATES.get("qE"), STATES.get("qE") }, // 0
    { STATES.get("qE"), STATES.get("q0"), STATES.get("qE"), STATES.get("q2") }, // 1
    { STATES.get("q6"), STATES.get("qE"), STATES.get("q3"), STATES.get("qE") }, // 2
    { STATES.get("qE"), STATES.get("q4"), STATES.get("q2"), STATES.get("qE") }, // 3
    { STATES.get("q5"), STATES.get("q3"), STATES.get("qE"), STATES.get("qE") }, // 4
    { STATES.get("q4"), STATES.get("qE"), STATES.get("q7"), STATES.get("q8") }, // 5
    { STATES.get("q2"), STATES.get("q7"), STATES.get("qE"), STATES.get("qE") }, // 6
    { STATES.get("qE"), STATES.get("q6"), STATES.get("q5"), STATES.get("qE") }, // 7
    { STATES.get("qE"), STATES.get("q9"), STATES.get("qE"), STATES.get("qE") }, // 8
    { STATES.get("qE"), STATES.get("q8"), STATES.get("qE"), STATES.get("qE") }  // 9
  };

  /**
   * delta
   * 
   * Delta function takes in multiple parameters to perform state transitions.
   * 
   * @param state is the current state of the DFA to work on.
   * @param symbol is the symbol to check against the transition table.
   * @return the state newly transitioned to.
   */
  private int delta(int state, char symbol) {
    return transitionTable[state][ALPHABET_INDEX.get(symbol)];
  }

  /**
   * isCharValid
   * 
   * Check if a letter of a target string is in the DFA alphabet.
   * 
   * @param letter to check if the alphabet contains it.
   * @return whether or not the alphabet contains the letter, true or false.
   */
  private boolean isValidChar(char letter) {
    return ALPHABET_INDEX.containsKey(letter);
  }

  /**
   * process
   * 
   * Process the given string, check if it is accepted or not,
   * then print the results.
   * 
   * @param potentialSoluition the string to process.
   */
  public void process(String potentialSoluition) {
    int currentState = START_STATE;
    int i = 0;
    while (i < potentialSoluition.length() && currentState != STATES.get("qE")) { 
      if (isValidChar(potentialSoluition.charAt(i))) {
        currentState = delta(currentState, potentialSoluition.charAt(i));
        i = i + 1;
      } 
      else {
        currentState = STATES.get("qE");
      }
    }
    printDFAResults(currentState);
  }

  /**
   * isAccepted
   * 
   * Check whether or not the given string is accepted.
   * 
   * @param state the state of the DFA.
   * @return true or false as the result of
   *         proccessing the string at the given state.
   */
  public boolean isAccepted(int state) {
    return ACCEPTANCE_STATES.contains(state);
  }

  /**
   * printDFAResults
   * 
   * Print the results after processing the string.
   * Whether or no the string is accepted (a solution),
   * or is rejected (not a solution).
   * 
   * @param stateToCheck the current state of the DFA to pass to
   *                     the "isAccepted(int)" method to check
   *                     if the given string was accepted or not.
   */
  private void printDFAResults(int stateToCheck) {
    if (isAccepted(stateToCheck)) {
      System.out.println("This is a solution.");
    } 
    else {
      System.out.println("This is not a solution.");
    }
  }
}