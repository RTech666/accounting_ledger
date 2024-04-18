package accounting_ledger;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    static int reportInput;
    static LocalDateTime currentTime;
    static DateTimeFormatter formatDateTime;
    static String finalDateTime;

    public static void main(String args[]) {
        // Call homeMenu method.
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

        // Get current date and time.
        currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        finalDateTime = currentTime.format(formatDateTime);

        // Write the deposit information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(finalDateTime + "|" + depositDescription + "|" + depositVendor + "|" + depositAmount + "\n");

            // Success messsage.
            System.out.println("Deposit information added to transactions.csv successfully.");
        // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Create the makePayment method.
    public static void makePayment() {
        // Ask user for the payment informaiton.
        System.out.println("Please enter the payment information:");

        // Ask user to enter the payment information.
        System.out.print("Enter payment description: ");
        paymentDescription = scanner.nextLine();

        // Ask user to enter payment vendor.
        System.out.print("Enter payment vendor: ");
        paymentVendor = scanner.nextLine();

        // Ask user to enter payment amount.
        System.out.println("Enter payment amount (as negative): ");
        paymentAmount = scanner.nextDouble();

        // Get current date and time.
        currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        finalDateTime = currentTime.format(formatDateTime);

        // Write the payment information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(finalDateTime + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentAmount + "\n");
            
            // Success messsage.
            System.out.println("Payment information added to transactions.csv successfully.");
        // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
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
        // Create menu for reports.
        System.out.println("1) Month to Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year to Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");

        // Ask user to choose an option.
        System.out.print("Choose an option: ");
        reportInput = scanner.nextInt();

        // If user chose 1.
        if (reportInput == 1) {
            // Call monthToDate method.
            monthToDate();
        // If user chose 2.
        } else if (reportInput == 2) {
            // Call previousMonth method.
            previousMonth();
        // If user chose 3.
        } else if (reportInput == 3) {
            // Call yearToDate method.
            yearToDate();
        // If user chose 4.
        } else if (reportInput == 4) {
            // Call previousYear method.
            previousYear();
        // If user chose 5.
        } else if (reportInput == 5) {
            // Call searchByVendor method.
            searchByVendor();
        // If user chose 0.
        } else if (reportInput == 0) {
            // Call ledgerReports method.
            ledgerReports();
        // If user entered a wrong input.
        } else {
            System.out.println("Invalid input. Please try again.");
            reportInput = scanner.nextInt();
        }
    }

    // Create monthToDate method.
    public static void monthToDate() {

    }

    // Create previousMonth method.
    public static void previousMonth() {
        
    }

    // Create yearToDate method.
    public static void yearToDate() {

    }

    // Create previousYear method.
    public static void previousYear() {

    }

    // Create searchByVendor method.
    public static void searchByVendor() {

    }
}
