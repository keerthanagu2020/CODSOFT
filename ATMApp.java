package keerthana;
import java.util.Scanner;

import smpl.ATM;
import smpl.BankAccount;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
} else {
            this.balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        return true;
}

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return false;
        }
        balance -= amount;
        return true;
}
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
boolean exit = false;

        while (!exit) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
case 1:
                    performWithdraw();
                    break;
case 2:
                    performDeposit();
                    break;
case 3:
                    checkBalance();
                    break;
case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
break;
                default:
                    System.out.println("Invalid option. Please choose again.");
}
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Please choose an option:");
        System.out.println("1 - Withdraw");
System.out.println("2 - Deposit");
        System.out.println("3 - Check Balance");
        System.out.println("4 - Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
System.out.println("Invalid input. Please enter a number between 1 and 4.");
            System.out.print("Enter your choice: ");
            scanner.next(); 
        }
return scanner.nextInt();
    }

    private void performWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getPositiveDoubleInput();
        if (account.withdraw(amount)) {
System.out.printf("Withdrawal successful. New balance: $%.2f%n", account.getBalance());
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getPositiveDoubleInput();
if (account.deposit(amount)) {
            System.out.printf("Deposit successful. New balance: $%.2f%n", account.getBalance());
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }

private double getPositiveDoubleInput() {
        while (true) {
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
if (value > 0) {
                    return value;
                } else {
System.out.print("Amount must be positive. Please enter again: ");
                }
            } else {
System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next();
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
       
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}


