/**
 * file: Driver.java
 * author: Tyler Rimaldi
 * course: CMPT 440
 * assignment: Project
 * due date: May/15/2020
 * version: 1.0
 * 
 * This file contains the file handling abilities,
 * command line parsing and lexical analysis initializer.
 */

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import lexer.Lexer;
  
public class Driver { 

  /**
   * showUsage
   * 
   * Explains the program functionality and the required arguments
   * neccessary for the program to run.
   */
  public static void showUsage() {
    System.out.println(
      "Usage: "
    );

    System.out.println(
      "This program will run a lexer on a text file containing code pertaining to the \r\n" +
      "grammar PDF found in cmpt440Rimaldi/project/writeup/code/grammar/grammar.pdf \r\n" +
      "If the code found in the file is valid, it will output success, otherwise it \r\n" +
      "will output the errors in which occured.\r\n"
    );

    System.out.println(
      "Program Arguments (Note. can only be used one at a time): "
    );

    System.out.println(
      "-f    | Specify the file location of the text file to load into the program. \r\n" +
      "-h    | Display the usage of this program."
    );
  }

  /**
   * readArguments
   * 
   * Simple commandline argument parsing--very simple for this project, which could
   * be further expanded and fleshed out if this project continued into the future.
   * 
   * @param args
   * @return filename
   */
  public static String readArguments(String[] args) {
    String retArg = "";
    if (args[0].equals("-f")) {
      if (args[1].length() > 0) {
        retArg = args[1];
      }
    } else if (args[0].equals("-h")) {
      System.out.println("----------------------------------------------------------------------------");
      showUsage();
      System.out.println("----------------------------------------------------------------------------");
      System.exit(0);
    } else {
      System.out.println("----------------------------------------------------------------------------");
      System.err.println("Command Argument Error Occured, please follow the below instructions. \r\n");
      showUsage();
      System.out.println("----------------------------------------------------------------------------");
      System.exit(1);
    }
    return retArg;
  }

  /**
   * readRawCode
   * 
   * Read in a text file containing code that is to be run against our lexer.
   * Return the code once read.
   * 
   * @param filePath The path to the text file of code to read.
   * @return code to lex.
   * @throws FileNotFoundException
   */
  public static String readRawProgram(String filePath) throws FileNotFoundException {
    File file = new File(filePath); 
    Scanner sc = new Scanner(file); 
    sc.useDelimiter("\\Z"); 
    String code = sc.next();
    sc.close();
    return code;
  }

  /**
   * main
   * 
   * Execute the program--run lex on some given text file of code.
   * 
   * @param args Commandline arguments to parse
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException { 
    // Parse commandline arguments--in this case we only have
    // one so just take it back as a String.
    String parseArguments = readArguments(args);
    String program = readRawProgram(parseArguments);
    Lexer lexer = new Lexer(program);
    lexer.start();
  } 
} 