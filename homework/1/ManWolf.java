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

  private static final int WOLF = 0;
  private static final int GOAT = 1;
  private static final int CABBAGE = 2;
  private static final int NOTHING = 3;
  private static final Set<Character> ALPHABET = 
    Collections.unmodifiableSet(new HashSet<Character>() {{ 
      add('w');
      add('g');
      add('c');
      add('n');
  }});
  private static final Map<Character, Integer> OPTIONS = 
    Collections.unmodifiableMap(new HashMap<Character, Integer>() {{ 
      put('w', WOLF);
      put('g', GOAT);
      put('c', CABBAGE);
      put('n', NOTHING);
  }});
  private static final Map<String, Integer> STATES = 
    Collections.unmodifiableMap(new HashMap<String, Integer>() {{ 
      put("qE", -1);
      for (int i = 0; i < 10; i ++) {
        put("q"+i, i);
      }
  }});
  private static final Set<Integer> ACCEPTANCE_STATES = 
    Collections.unmodifiableSet(new HashSet<Integer>() {{ 
      add(9);
  }});
  private final int START_STATE = 0;
  private int currentState = START_STATE;
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
   * Delta function takes in multiple parameters to perform state transitions.
   * 
   * @param state is the current state of the DFA to work on.
   * @param symbol is the symbol to check against the transition table.
   * @return modified state.
   */
  private int delta(int state, char symbol) {
    return transitionTable[state][OPTIONS.get(symbol)];
  }

  /**
   * Check if a letter of a target string is in the DFA alphabet.
   * 
   * @param letter to check if the alphabet contains it.
   * @return whether or not the alphabet contains the letter, true or false.
   */
  private boolean isValidChar(char letter) {
    return ALPHABET.contains(letter);
  }

  /**
   * Process the given string, check if it is accepted or not,
   * then print the results.
   * 
   * @param potentialSoluition the string to process.
   */
  public void process(String potentialSoluition) {
    int i = 0;
    while (i < potentialSoluition.length() && currentState != STATES.get("qE")) { 
      if (isValidChar(potentialSoluition.charAt(i))) {
        currentState = delta(currentState, potentialSoluition.charAt(i));
        i = i + 1;
      } else {
        currentState = STATES.get("qE");
      }
    }
    printDFAResults();
  }

  /**
   * Check whether or not the given string is accepeted.
   * 
   * @return the result of proccessing the string, true or false
   */
  public boolean accepted() {
    return ACCEPTANCE_STATES.contains(currentState);
  }

  /**
   * Print the results after processing the string.
   * Whether or no the string is accepted (a solution),
   * or is rejected (not a solution).
   */
  private void printDFAResults() {
    if (accepted()) {
      System.out.println("This is a solution.");
    } else {
      System.out.println("This is not a solution.");
    }
  }

  /**
   * Reset the DFA to the start state.
   */
  public void resetDFA() {
    currentState = START_STATE;
  }
}