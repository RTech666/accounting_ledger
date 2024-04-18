package accounting_ledger;
import java.util.Scanner;

public class AccountingLedger {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Create the variables.
    static String userInput;
    static String ledgerInput;
    static String depositDescription;
    static String depositVendor;
    static double depositAmount;
    static String paymentDescription;
    static String paymentVendor;
    static double paymentAmount;

    public static void main(String args[]) {
        homeMenu();
    }

    // Create the homeMenu method.
    public static void homeMenu() {
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
        // Ask user to enter their deposit information.
        System.out.println("Please enter the deposit information:");

        // Ask user to enter the deposit information.
        System.out.print("Enter deposit description: ");
        depositDescription = scanner.nextLine();

        // Ask user to enter deposit vendor.
        System.out.print("Enter deposit vendor: ");
        depositVendor = scanner.nextLine();

        // Ask user to enter deposit amount.
        System.out.println("Enter deposit amount: ");
        depositAmount = scanner.nextDouble();
    }

    // Create the makePayment method.
    public static void makePayment() {
        // Ask user for the payment informaiton.
        System.out.println("Please enter the deposit information:");

        // Ask user to enter the deposit information.
        System.out.print("Enter payment description: ");
        paymentDescription = scanner.nextLine();

        // Ask user to enter deposit vendor.
        System.out.print("Enter payment vendor: ");
        paymentVendor = scanner.nextLine();

        // Ask user to enter deposit amount.
        System.out.println("Enter payment amount (as negative): ");
        paymentAmount = scanner.nextDouble();
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
        if (ledgerInput.equalsIgnoreCase("a")) {
            // Call ledgerAll method.
            ledgerAll();
        // If user chose D.
        } else if (ledgerInput.equalsIgnoreCase("d")) {
            // Call ledgerDeposits method.
            ledgerDeposits();
        // If user chose P.
        } else if (ledgerInput.equalsIgnoreCase("p")) {
            // Call ledgerPayments method.
            ledgerPayments();
        // If user chose R.
        } else if (ledgerInput.equalsIgnoreCase("r")) {
            // Call ledgerReports method.
            ledgerReports();
        // If user chose H.
        } else if (ledgerInput.equalsIgnoreCase("h")) {
            // Return to home.
            homeMenu();
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
