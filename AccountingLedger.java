package accounting_ledger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountingLedger {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Create the variables.
    static int currentMonth;
    static int currentYear;
    static int reportInput;
    static int lastMonth;
    static int lastYear;
    static String userInput;
    static String ledgerInput;
    static String depositDescription;
    static String depositVendor;
    static String paymentDescription;
    static String paymentVendor;
    static String finalDateTime;
    static String line;
    static String input;
    static String monthString;
    static String searchVendor;
    static String vendor;
    static String transactionDescription;
    static String transactionVendor;
    static double depositAmount;
    static double paymentAmount;
    static double amount;
    static double transactionAmount;
    static boolean found;
    static boolean match;
    static LocalDate lastMonthDate;
    static LocalDate currentDate;
    static LocalDate lastYearDate;
    static LocalDate transactionDate;
    static LocalDate startDate = null;
    static LocalDate endDate = null;
    static LocalDateTime currentTime;
    static DateTimeFormatter formatDateTime;

    public static void main(String args[]) {
        // Call homeMenu method.
        homeMenu();
    }

    // Create the homeMenu method.
    public static void homeMenu() {
        while (true) {
            // Print the menu options.
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            // Ask the user what they want to do.
            System.out.print("Please select an option: ");
            userInput = scanner.nextLine();

            // Convert user input to uppercase for comparison
            userInput = userInput.toUpperCase();

            // Check the user input
            switch (userInput) {
                case "D":
                    // Call addDeposit method.
                    addDeposit();
                    break;
                case "P":
                    // Call makePayment method.
                    makePayment();
                    break;
                case "L":
                    // Call ledger method.
                    ledger();
                    break;
                case "X":
                    // Quit.
                    System.exit(0);
                default:
                    // If user entered a wrong input.
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    // Create the addDeposit method.
    public static void addDeposit() {
        // Ask user to enter their deposit information.
        System.out.println("\nPlease enter the deposit information:");

        // Ask user to enter the deposit information.
        System.out.print("Enter deposit description: ");
        depositDescription = scanner.nextLine();

        // Ask user to enter deposit vendor.
        System.out.print("Enter deposit vendor: ");
        depositVendor = scanner.nextLine();

        // Ask user to enter deposit amount.
        System.out.print("Enter deposit amount: ");
        depositAmount = scanner.nextDouble();
        scanner.nextLine();

        // Get current date and time.
        currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        finalDateTime = currentTime.format(formatDateTime);

        // Write the deposit information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write("\n" + finalDateTime + "|" + depositDescription + "|" + depositVendor + "|" + depositAmount);

            // Success messsage.
            System.out.println("\nDeposit information added to transactions.csv successfully.\n");
        // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }

        // Go back to home menu.
        homeMenu();
    }

    // Create the makePayment method.
    public static void makePayment() {
        // Ask user for the payment informaiton.
        System.out.println("\nPlease enter the payment information:");

        // Ask user to enter the payment information.
        System.out.print("Enter payment description: ");
        paymentDescription = scanner.nextLine();

        // Ask user to enter payment vendor.
        System.out.print("Enter payment vendor: ");
        paymentVendor = scanner.nextLine();

        // Ask user to enter payment amount.
        System.out.print("Enter payment amount (as negative): ");
        paymentAmount = scanner.nextDouble();
        scanner.nextLine();

        // Get current date and time.
        currentTime = LocalDateTime.now();

        // Set the format for the date and time.
        formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Format the date and time.
        finalDateTime = currentTime.format(formatDateTime);

        // Write the payment information into the csv.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write("\n" + finalDateTime + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentAmount);
            
            // Success messsage.
            System.out.println("\nPayment information added to transactions.csv successfully.\n");
        // If an error occured, print error.
        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the file: " + e.getMessage() + "\n");
        }

        // Go back to home menu.
        homeMenu();
    }

    // Create the ledger method.
    public static void ledger() {
        // Print the ledger menu options.
        System.out.println("\nA) All");
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
            System.out.print("Invalid input. Please try again: ");
            ledgerInput = scanner.nextLine();
        }
    }

    // Create ledgerAll method.
    public static void ledgerAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            line = reader.readLine();

            // If CSV is empty, print message.
            if (line == null) {
                System.out.println("No transactions found.");
                return;
            }

            // Read the lines in the CSV into a list.
            List<String> transactions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        
            // Sort transactions by date and time.
            Collections.sort(transactions, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] parts1 = o1.split("\\|");
                    String[] parts2 = o2.split("\\|");
                    LocalDateTime dateTime1 = LocalDateTime.parse(parts1[0] + "|" + parts1[1], DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss"));
                    LocalDateTime dateTime2 = LocalDateTime.parse(parts2[0] + "|" + parts2[1], DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss"));
                    return dateTime2.compareTo(dateTime1);
                }
            });

            // Print title.
            System.out.println("\nAll Transactions:");

            // Print all transactions.
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger menu.
        ledger();
    }

    // Create the ledgerDeposits method.
    public static void ledgerDeposits() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            line = reader.readLine();

            // If CSV is empty, print message.
            if (line == null) {
                System.out.println("No transactions found.");
                return;
            }

            // Print title.
            System.out.println("\nAll Deposit Transactions:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");

                // Set the amount.
                amount = Double.parseDouble(parts[4]);

                // Print all the deposits.
                if (amount > 0) {
                    System.out.println(line);
                }
            }
        // If an error occured, print error.
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger menu.
        ledger();
    }

    // Create the ledgerPayments method.
    public static void ledgerPayments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            line = reader.readLine();

            // If CSV is empty, print message.
            if (line == null) {
                System.out.println("No transactions found.");
                return;
            }

            // Print title.
            System.out.println("\nAll Payment Transactions:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");

                // Set the amount.
                amount = Double.parseDouble(parts[4]);

                // Print all the payments.
                if (amount < 0) {
                    System.out.println(line);

                    // Go back to ledger menu when done.
                    ledger();
                }
            }
        // If an error occured, print error.
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger menu.
        ledger();
    }

    // Create the ledgerReports method.
    public static void ledgerReports() {
        // Create menu for reports.
        System.out.println("\n1) Month to Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year to Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("0) Back");

        // Ask user to choose an option.
        System.out.print("Choose an option: ");
        reportInput = scanner.nextInt();
        scanner.nextLine();

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
        // If user chose 6.
        } else if (reportInput == 6) {
            // Call customSearch method.
            customSearch();
        }
        // If user chose 0.
        else if (reportInput == 0) {
            // Call ledgerReports method.
            ledgerReports();
        // If user entered a wrong input.
        } else {
            System.out.print("Invalid input. Please try again: ");
            reportInput = scanner.nextInt();
        }
    }

    // Create monthToDate method.
    public static void monthToDate() {
        // Get the current month and year.
        currentDate = LocalDate.now();
        currentMonth = currentDate.getMonthValue();
        currentYear = currentDate.getYear();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nMonth to Date Transactions:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                transactionDate = LocalDate.parse(parts[0].split("\\|")[0]);

                // Check if the date is in the current month and year.
                if (transactionDate.getMonthValue() == currentMonth && transactionDate.getYear() == currentYear) {
                    System.out.println(line);
                    found = true;
                }
            }

            // If no transactions found, print message.
            if (!found) {
                System.out.println("No transactions found for the current month.");
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }

    // Create previousMonth method.
    public static void previousMonth() {
        // Get the date for the last month.
        currentDate = LocalDate.now();
        lastMonthDate = currentDate.minusMonths(1);
        lastMonth = lastMonthDate.getMonthValue();
        lastYear = lastMonthDate.getYear();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nPrevious Month Results:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                transactionDate = LocalDate.parse(parts[0].split("\\|")[0]);

                // Check if the date is in the last month.
                if (transactionDate.getMonthValue() == lastMonth && transactionDate.getYear() == lastYear) {
                    System.out.println(line);
                    found = true;
                }
            }   

            // If no transactions found, print message.
            if (!found) {
                System.out.println("No transactions found for the last month.");
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }

    // Create yearToDate method.
    public static void yearToDate() {
        // Get the current year.
        currentYear = LocalDate.now().getYear();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nYear to Date Results:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                transactionDate = LocalDate.parse(parts[0].split("\\|")[0]);

                // Check if the date is in the current year.
                if (transactionDate.getYear() == currentYear) {
                    System.out.println(line);
                    found = true;
                }
            }

            // If no transactions found, print message.
            if (!found) {
                System.out.println("No transactions found for the current year.");
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }

    // Create previousYear method.
    public static void previousYear() {
        // Get the date for the previous year.
        currentDate = LocalDate.now();
        lastYearDate = currentDate.minusYears(1);
        lastYear = lastYearDate.getYear();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nPrevious Year Results:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                LocalDate transactionDate = LocalDate.parse(parts[0].split("\\|")[0]);

                // Check if the date is in the previous year.
                if (transactionDate.getYear() == lastYear) {
                    System.out.println(line);
                    found = true;
                }
            }

            // If no transactions found, print message.
            if (!found) {
                System.out.println("No transactions found for the previous year.");
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }

    // Create searchByVendor method.
    public static void searchByVendor() {
        // Ask the user to enter the vendor to search for.
        System.out.print("Enter the vendor name to search for: ");
        searchVendor = scanner.nextLine().trim().toLowerCase();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nSearch by Vendor Results:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                vendor = parts[3].trim().toLowerCase();

                // Check if the vendor matches the user's input.
                if (vendor.equalsIgnoreCase(searchVendor)) {
                    System.out.println(line);
                    found = true;
                }
            }

            // If no transactions found for the vendor, print message.
            if (!found) {
                System.out.println("No transactions found for the vendor: " + searchVendor);
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }

    public static void customSearch() {
        // Title.
        System.out.println("Enter search values for ledger entry properties (press Enter to skip):");

        // Ask the user for the start date.
        System.out.print("Start date (YYYY-MM-DD): ");
        String startDateInput = scanner.nextLine().trim();

        // If their was an input, set the value.
        if (!startDateInput.isEmpty()) {
            startDate = LocalDate.parse(startDateInput);
        }

        // Ask the user for the end date.
        System.out.print("End date (YYYY-MM-DD): ");
        String endDateInput = scanner.nextLine().trim();

        // If their was an input, set the value.
        if (!endDateInput.isEmpty()) {
            endDate = LocalDate.parse(endDateInput);
        }

        // Ask the user to input the description.
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        // Ask the user to input the vendor.
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim();

        // Ask the user to input the amount.
        System.out.print("Amount: ");
        String amountInput = scanner.nextLine().trim();

        // If their was an input, set the value.
        Double amount = null;
        if (!amountInput.isEmpty()) {
            amount = Double.parseDouble(amountInput);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            // Skip the first line.
            reader.readLine();

            // Set and initialize the variables.
            String line;
            found = false;

            // Print title.
            System.out.println("\nCustom Search Results:");

            // Read the lines in the CSV.
            while ((line = reader.readLine()) != null) {
                // Split the information into parts.
                String[] parts = line.split("\\|");
                transactionDate = LocalDate.parse(parts[0].split("\\|")[0]);
                transactionDescription = parts[2];
                transactionVendor = parts[3];
                transactionAmount = Double.parseDouble(parts[4]);

                // Check if the transaction matches the search criteria.
                match = true;
                if (startDate != null && transactionDate.isBefore(startDate)) {
                    match = false;
                }
                if (endDate != null && transactionDate.isAfter(endDate)) {
                    match = false;
                }
                if (!description.isEmpty() && !transactionDescription.contains(description)) {
                    match = false;
                }
                if (!vendor.isEmpty() && !transactionVendor.equalsIgnoreCase(vendor)) {
                    match = false;
                }
                if (amount != null && transactionAmount != amount) {
                    match = false;
                }

                // Print the transaction if it matches all the criterias.
                if (match) {
                    System.out.println(line);
                    found = true;
                }
            }

            // If no transactions found, print message.
            if (!found) {
                System.out.println("No transactions found matching the search criteria.");
            }
        // If an error occurred, print error.
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Go back to ledger reports menu.
        ledgerReports();
    }
}