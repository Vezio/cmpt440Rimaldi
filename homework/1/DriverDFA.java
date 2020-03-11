/**
 * file: DriverDFA.java
 * author: Tyler Rimaldi
 * course: CMPT 440
 * assignment: HW 1
 * due date: March/11/2020
 * version: 1.0
 * 
 * This file contains the declaration of the DriverDFA public class that extends the 
 * public abstract class Manwolf.
 */
public class DriverDFA extends ManWolf {

  /**
   * Lets call the super class so we can
   * extend ManWolf's methods.
   */
  public DriverDFA() {
    super();
  }

  /**
   * Check whether or not a user given String
   * is solution to the ManWolf DFA.
   * 
   * @param args the string to process in our DFA
   */
  public static void main(String[] args) {
    DriverDFA dfa = new DriverDFA();
    try {
      if (args.length > 1) {
        throw new IndexOutOfBoundsException("\nYou provided too many Strings. Please provide only one String.");
      } 
      else {
        dfa.process(args[0]);
      }
    } 
    catch (IndexOutOfBoundsException e) {
        System.err.println("Caught IndexOutOfBoundsException: " +  e.getMessage());                  
    } 
    finally { 
      if (args.length < 0) {
        System.out.println("Please enter an appropriate sequence of strings.");
      } 
    }
  }
}