package accounting_ledger;
import java.util.Scanner;

public class AccountingLedger {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Create the variables.
    static String userInput;
    static String ledgerInput;

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
        // If user entered a wrong input.
        } else {
            System.out.println("Invalid input. Please try again.");
            userInput = scanner.nextLine();
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
        // Print the ledger menu options.
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");

        // Ask the user what they want to do.
        System.out.print("Please select an option: ");
        ledgerInput = scanner.nextLine();

        // If user chose A.
        if (userInput.equalsIgnoreCase("a")) {
            // Call ledgerAll method.
            ledgerAll();
        // If user chose D.
        } else if (userInput.equalsIgnoreCase("d")) {
            // Call ledgerDeposits method.
            ledgerDeposits();
        // If user chose P.
        } else if (userInput.equalsIgnoreCase("p")) {
            // Call ledgerPayments method.
            ledgerPayments();
        // If user chose R.
        } else if (userInput.equalsIgnoreCase("r")) {
            // Call ledgerReports method.
            ledgerReports();
        // If user chose H.
        } else if (userInput.equalsIgnoreCase("h")) {
            // Return to home.
            return;
        // If user entered a wrong input.
        } else {
            System.out.println("Invalid input. Please try again.");
            ledgerInput = scanner.nextLine();
        }
    }

    // Create ledgerAll method.
    public static void ledgerAll() {

    }

    // Create the ledgerDeposits method.
    public static void ledgerDeposits() {

    }

    // Create the ledgerPayments method.
    public static void ledgerPayments() {

    }

    // Create the ledgerReports method.
    public static void ledgerReports() {
        
    }
}
