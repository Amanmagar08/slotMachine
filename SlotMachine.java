import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
    public static void main (String[] args) {
        // Slot Machine
        Scanner scanner = new Scanner(System.in);

        // Variables
        String[] symbols = {"🍋", "🎁", "❤️", "🔔", "🔥", "🎂", "🥂"};
        int balance = 100;
        int bet;
        int payout;
        String keepPlaying = " ";

        // WELCOME TO THE SLOT MACHINE MESSAGE
        System.out.println("****************");
        System.out.println("  Slot Machine  ");
        System.out.println("****************");
        System.out.print("    SYMBOLS:    ");
        System.out.println(String.join(" | ", symbols));
        System.out.println();

        // DO WHILE BALANCE > 0 AND KEEP PLAYING IS TRUE
        while (balance > 0 && !keepPlaying.equals("no")) {
            System.out.println("\n\nYour balance is: $" + balance);
            System.out.print("Enter the bet amount: ");
            bet = scanner.nextInt();

            // If invalid bet amount is received
            if (bet > balance) {
                System.out.println("You cannot bet more than your balance.");
                continue;
            }
            if (bet <= 0) {
                System.out.println("Your bet needs to be greater than 0.");
                continue;
            }

            // Generate random output
            System.out.println("Spinning...");
            String[] output = spin(symbols);
            System.out.println(String.join(" | ", output));

            // Calculate payout and print the result accordingly
            payout = payOut(output, bet);
            balance += payout;
            System.out.println((payout > 0) ? "You won: $" + payout: "You lost: $" + bet );


            // If balance is already 0 donot continue further
            if (balance == 0){
                System.out.println("You ran out of money.");
                break;
            }

            // Prompt the user asking if they want to continue playing
            System.out.println("Do you want to keep playing: ");
            System.out.print("no to stop playing: ");
            keepPlaying = scanner.next().toLowerCase();

        }

        // BYE BYE MESSAGE
        System.out.println("\nWe hope to see you again.");

        scanner.close();
    }

    // METHOD TO SPIN
    public static String[] spin(String[] symbols) {
        Random random = new Random();

        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            result[i] = symbols[random.nextInt(symbols.length)];
        }

        return result;
    }

    // METHOD TO CALCULATE THE PAYOUT
    public static int payOut (String[] result, int bet) {
        if (result[0].equals(result[1]) && result[1].equals(result[2])) {
            return bet * 10;
        } else if (result[0].equals(result[1]) || result[1].equals(result[2]) || result[0].equals(result[2])) {
            return bet * 2;
        }else {
            return -bet;
        }
    }


}
