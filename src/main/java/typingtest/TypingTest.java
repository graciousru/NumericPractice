package typingtest;

import java.util.Random;
import java.util.Scanner;
public class TypingTest
{

      public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      Random generator = new Random();

      int max_errors = 4;

      System.out.println("What is your maximum number of errors? ");
      int user_max_errors = GameManager.getUserInput(keyboard);
      // TODO: Error check response
      max_errors = user_max_errors;

      GameManager gm = new GameManager(max_errors);

      gm.printInstructions();
      GameManager.getStartSignal(keyboard);

      while (gm.isAlive())
      { 
         // start timer

         gm.testKey(keyboard, generator);

         // end timer
         // find how long for response
         // if response long then 8 sec then decrease score
      }

      gm.printResults();
      keyboard.close();
   }
}