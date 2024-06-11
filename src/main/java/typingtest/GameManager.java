package typingtest;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private int score;
    private int errors;
    private boolean alive;
    private final int[] row;

   
    private final long MAX_RESPONSE_TIME;
    private final int MAX_ERRORS;
   
    public GameManager(int max_errors) {
        this.MAX_ERRORS = max_errors;
        this.MAX_RESPONSE_TIME = 8;
        this.alive = true;
        this.row = new int[10];
    }
    public void printInstructions() {
        System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
        System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
        System.out.println("If you fail to enter the correct number " + this.MAX_ERRORS + " times in a row, you lose. Try to get the highest score!");
        System.out.println("You have " + this.MAX_RESPONSE_TIME+ " seconds to type something and press Enter! Good Luck!");
        System.out.println("======================================================================================================");
    }

    public static void getStartSignal(Scanner keyboard) {
        boolean wait = true;
        while(wait)
        {
            System.out.println("Are you ready? Enter \"Y\" to continue.");
            
            String input = keyboard.nextLine();

            wait = !input.equalsIgnoreCase("y");

        }

    }
    public static int getUserInput(Scanner keyboard) {
        int response = keyboard.nextInt();
        keyboard.nextLine();
        
        return response;
    }

    public void printResults() {
        System.out.println("Your correctly entered: " + this.score + " numbers!");
        for(int i = 0; i < this.row.length; i++)
        {
            // use float and percentage instead
            System.out.println("Your accuracy for the number " + i + ": " + this.row[i]);
        }
    }
    public void incrementScore() {
        this.score++; 
    }

    public void incrementErrors() {
        this.errors++;
    }

    public void decrementScore(){
        this.score--;
    }
    
    public void updateKey(boolean correct, int answer) {
        if (correct) {
            this.row[answer]++;
        } 
        else {
            this.row[answer]--;
        }
    }


    public boolean isAlive() {
        return this.alive;
    }

    
    public void testKey(Scanner keyboard, Random generator) {
        int answer = generator.nextInt(10);
        System.out.println("The number is: " + answer);
        long startTime = System.currentTimeMillis(); // starts time 
        int response = GameManager.getUserInput(keyboard);
        long endTime = System.currentTimeMillis();// end time
        
        long responseTime = endTime - startTime;


        if (response == answer & responseTime <= 8000) // correct on time
        {
            this.incrementScore();
            this.updateKey(true, answer);
        }
        else if(response == answer & responseTime >= 8000) // correct not on time
        {
            this.decrementScore();
            System.out.println("Took too long to respond! Minus one point.");
        }
        else // wrong answer time doesn't matter
        {
            this.incrementErrors();
            this.updateKey(false, answer);
        }

        // TODO: ensure max_errors is > 1
        this.alive  = this.errors < this.MAX_ERRORS;
    }
}
