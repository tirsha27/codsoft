import java.util.Scanner;
import java.util.Random;

public class guessinggame {

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Random rand = new Random();
        int number = rand.nextInt(100) + 1;  // Random number between 1 and 100
        int maxAttempts = 10;
        int attempts = 0;

        System.out.println("I've picked a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < number) {
                System.out.println("Too low!");
            } else if (guess > number) {
                System.out.println("Too high!");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                return;
            }
        }
        System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + number + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int rounds = 0;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            playGame();
            rounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("You played " + rounds + " round(s). Thanks for playing!");
        scanner.close();
    }
}
