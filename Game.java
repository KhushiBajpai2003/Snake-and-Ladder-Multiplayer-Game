import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int WINNING_POSITION = 100;
    private static final Map<Integer, Integer> snakesAndLadders = new HashMap<>();

    static {
        // Define snakes and ladders positions
        snakesAndLadders.put(16, 6);
        snakesAndLadders.put(47, 26);
        snakesAndLadders.put(49, 11);
        snakesAndLadders.put(56, 53);
        snakesAndLadders.put(62, 19);
        snakesAndLadders.put(64, 60);
        snakesAndLadders.put(87, 24);
        snakesAndLadders.put(93, 73);
        snakesAndLadders.put(95, 75);
        snakesAndLadders.put(98, 78);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Snakes and Ladders!");

        // Prompt the user to input the number of players
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Initialize player positions
        int[] playerPositions = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerPositions[i] = 0;
        }

        int currentPlayer = 0;

        while (!isAnyPlayerWinner(playerPositions)) {
            System.out.println("Player " + (currentPlayer + 1) + ", enter 'r' to roll the dice:");
            String input = scanner.nextLine();
            if ("r".equalsIgnoreCase(input.trim())) {
                int diceRoll = rollDice();
                System.out.println("You rolled a " + diceRoll + ".");
                
                // Update the position of the current player
                playerPositions[currentPlayer] += diceRoll;
                playerPositions[currentPlayer] = checkPosition(playerPositions[currentPlayer]);

                System.out.println("Player " + (currentPlayer + 1) + "'s current position is: " + playerPositions[currentPlayer]);

                if (playerPositions[currentPlayer] == WINNING_POSITION) {
                    System.out.println("Player " + (currentPlayer + 1) + " wins!");
                    break;
                }

                // Switch to the next player
                currentPlayer = (currentPlayer + 1) % numPlayers;
            } else {
                System.out.println("Invalid input. Please enter 'r' to roll the dice.");
            }
        }

        scanner.close();
    }

    private static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private static int checkPosition(int position) {
        if (snakesAndLadders.containsKey(position)) {
            int newPosition = snakesAndLadders.get(position);
            if (newPosition > position) {
                System.out.println("You climbed a ladder from position " + position + " to position " + newPosition + "!");
            } else {
                System.out.println("You were bitten by a snake from position " + position + " to position " + newPosition + "!");
            }
            return newPosition;
        }
        return position;
    }

    private static boolean isAnyPlayerWinner(int[] playerPositions) {
        for (int position : playerPositions) {
            if (position >= WINNING_POSITION) {
                return true;
            }
        }
        return false;
    }
}
