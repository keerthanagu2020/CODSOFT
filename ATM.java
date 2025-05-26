package internship;
import java.util.InputMismatchException;
import java.util.Scanner;


class BankAccount {
    private double balance; 

    
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative. Setting to 0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; 
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) { 
            balance -= amount; 
            System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            return true;
        } else {
            System.out.println("Insufficient balance for this withdrawal.");
            return false;
        }
    }

    
    public double getBalance() {
        return balance;
    }
}


public class ATM {
    private BankAccount userAccount;
    private Scanner scanner; 

    
    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in); 
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

   
    public void processOption(int option) {
        switch (option) {
            case 1:
                checkBalance(); 
                break;
            case 2:
                deposit(); 
                break;
            case 3:
                withdraw(); 
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again."); 
        }
    }

    
    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        try {
            double amount = scanner.nextDouble(); 
            userAccount.deposit(amount); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); 
        }
    }

   
    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        try {
            double amount = scanner.nextDouble(); 
            userAccount.withdraw(amount); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); 
        }
    }

    
    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", userAccount.getBalance());
    }

    
    public static void main(String[] args) {
       
        BankAccount myAccount = new BankAccount(1000.00);
      
        ATM atm = new ATM(myAccount);

        int choice;
        do {
            atm.displayMenu(); 
            try {
                choice = atm.scanner.nextInt(); 
                atm.processOption(choice); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 01 and 04.");
                atm.scanner.next();
                choice = 0; 
            }
        } while (choice != 4); 

        atm.scanner.close(); 
    }
}
