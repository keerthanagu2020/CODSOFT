package keerthana;

import java.util.Random;
import java.util.Scanner;

public class Randomnumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lowerLimit = 1, upperLimit = 100, maxAttempts = 5, roundsWon = 0, roundsPlayed = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Game!");

        do {
            roundsPlayed++;
            int randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            System.out.println("\nRound " + roundsPlayed + ": Guess a number between " + lowerLimit + " and " + upperLimit + ". You have " + maxAttempts + " attempts.");

            boolean guessedCorrectly = false;
            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                System.out.print("Enter your guess: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.next();
                    continue;
                }
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congrats! You guessed it in " + attempts + " attempt(s)!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                }
                System.out.println(userGuess < randomNumber ? "Too low!" : "Too high!");
                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) System.out.println("Out of attempts! The correct number was " + randomNumber);
            System.out.println("Score: " + roundsWon + "/" + roundsPlayed);
            System.out.print("Play again? (y/n): ");
            playAgain = scanner.next().equalsIgnoreCase("y");

        } while (playAgain);

        System.out.println("Thanks for playing! Final score: " + roundsWon + "/" + roundsPlayed);
        scanner.close();
    }
}