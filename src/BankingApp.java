import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApp {

    //scanner to read input
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        //creating bank object
        Bank bank = new Bank();
        System.out.println("Welcome to Banking App");

        //creating menu
        while (true) {

            System.out.println("\n ---- Menu ----");
            System.out.println("1. Add Account");
            System.out.println("2. Get Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");

            //taking input from user
            int choice = sc.nextInt();

            //switch case for menu
            switch (choice) {
                //case 1 for adding account
                case 1:
                    System.out.println("Enter the Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    bank.addAccount(name);
                    break;

                //case 2 for getting accounts
                case 2:
                    bank.getAccounts();
                    break;

                //case 3 for depositing money
                case 3:
                    System.out.println("Enter the Account no: ");
                    int accountNumber = sc.nextInt();
                    Account account = bank.findAccount(accountNumber);

                    if (account != null) {
                        System.out.println("Enter the Amount to Deposit");
                        double depositAmount = sc.nextInt();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                    //case 4 for withdrawing money
                case 4:
                    System.out.println("Enter the Account no: ");
                    int withdrawAccountNumber = sc.nextInt();

                    Account withdrawAccount = bank.findAccount(withdrawAccountNumber);

                    if (withdrawAccount != null) {
                        System.out.println("Enter the Amount to Withdraw");
                        double withdrawAmount = sc.nextInt();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                    //case 5 for viewing transaction history
                case 5:
                    System.out.println("Enter the Account no: ");
                    sc.nextLine();
                    int accountNumToView = sc.nextInt();
                    Account accountToView = bank.findAccount(accountNumToView);

                    if (accountToView != null) {
                        System.out.println("\nAccount Details:  " + "\n" + "\n      Name: " + accountToView.getName() + "\n      Account No: " + accountToView.getAccountNumber() + "\n      Balance: " + accountToView.getBalance() + "\n");
                        accountToView.viewTransactionHistory();
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                    //case 6 for exiting the program
                case 6:
                    System.out.println("Thank you for using Banking App");
                    System.exit(0);
                    sc.close();
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}

// account class
class Account {

    private int accountNumber;
    private String name;
    private double balance;

    private static int getAccountNumberCounter = 10001;

    private List<String> transactionHistory = new ArrayList<>();

    public Account(String name) {
        this.accountNumber = getAccountNumberCounter++;
        this.name = name;
        this.balance = 0;
        recordTransaction("Account created with initial balance: Rs.0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + "\nName: " + name + "\nBalance: " + balance;
    }


    //deposit method
    public void deposit(double depositAmount) {


        if (depositAmount > 0) {
            balance += depositAmount;
            recordTransaction("Deposited: Rs." + depositAmount + " | New Balance: Rs." + balance);
            System.out.println("Amount " + depositAmount + " deposited successfully");

        } else {
            System.out.println("Invalid deposit amount");
        }

    }

    //withdraw method
    public void withdraw(double withdrawAmount) {

        if (balance > 0 && balance > withdrawAmount) {
            balance -= withdrawAmount;
            recordTransaction("Withdrawn: Rs." + withdrawAmount + " | New Balance: Rs." + balance);
            System.out.println("Amount " + withdrawAmount + " Withdrawn successfully");

        } else {
            System.out.println("Insufficient balance");
        }


    }

    //record transaction method
    public void recordTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    //view transaction history method
    public void viewTransactionHistory() {
        System.out.println("\nTransaction History for Account No: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

//bank class
class Bank {

    private List<Account> accounts = new ArrayList<>();

    //add account method
    public void addAccount(String name) {
        Account account = new Account(name);
        accounts.add(account);

        System.out.println("Account added successfully with Account number: " + account.getAccountNumber());

    }

    //get accounts method
    public void getAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
                System.out.println("------------------------");
            }
        }
    }


    //find account method
    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Account not found");
        return null;
    }

}





