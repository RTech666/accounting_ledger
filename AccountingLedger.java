package accounting_ledger;
import java.util.Scanner;

public class AccountingLedger {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Create the variables.
    static String userInput;

    public static void main(String args[]) {
        // Print the menu options.
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");

        // Ask the user what they want to do.
        System.out.print("Please select an option: ");
        userInput = scanner.nextLine();

        // If user chose D.
        if (userInput.equalsIgnoreCase("d")) {
            // Call addDeposit method.
            addDeposit();
        // If user chose P.
        } else if (userInput.equalsIgnoreCase("p")) {
            // Call makePayment method.
            makePayment();
        // If user chose L.
        } else if (userInput.equalsIgnoreCase("l")) {
            // Call ledger method.
            ledger();
        // If user chose X.
        } else if (userInput.equalsIgnoreCase("x")) {
            // Quit.
            System.exit(0);
        }
    }

    // Create the addDeposit method.
    public static void addDeposit() {

    }

    // Create the makePayment method.
    public static void makePayment() {

    }

    // Create the ledger method.
    public static void ledger() {

    }
}
