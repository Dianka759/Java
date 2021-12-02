import java.util.Random;

public class BankAccount {

    // MEMBER VARIABLES
    private double checking;
    private double savings;
    private static int accountsCreated;
    private static double Total;
    private int accountNumber;

    //In the constructor, be sure to increment the account count.
    public BankAccount() {
        accountsCreated++;
        this.accountNumber = randomAccountNumber();
    } 

//  ------------------ Did it!!! ...ish-------------------------//

    private int randomAccountNumber() {
        Random rando = new Random();
        int number = rando.nextInt(999999999);
        return number;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

// ---------------------------------------------------------------------------//


    //Getter method for user's checking account balance
    public double getChecking() {
        return checking;
    }

    //Create a getter method for the user's saving account balance.
    public double getSavings() {
        return savings;
    }

    //Create a method that will allow a user to deposit money into 
    //either the checking or saving, be sure to add to total amount stored.
    public void deposit() {
        System.out.println("Please enter account type (checking or savings):");
        String accountType = System.console().readLine();

        System.out.println("Please enter deposit amount:");
        String deposit= System.console().readLine();
        int amountDeposit = Integer.parseInt(deposit);

		if (accountType.contains("checking")){
			checking += amountDeposit;
			Total += amountDeposit;
			System.out.println("Checkings deposit amount: $" + String.format("%.2f", checking));
		}
		else if(accountType.equals("savings")) {
			savings += amountDeposit;
			Total += amountDeposit;
			System.out.println("Savings deposit amount: $" + String.format("%.2f", savings));
		} else {
            
        } 
    }

    //Create a method to withdraw money from one balance. 
    //Do not allow them to withdraw money if there are insufficient funds.
    public void withdraw() {
        System.out.println("Please enter account type (checking or savings):");
        String accountType = System.console().readLine();

        System.out.println("Please enter withdrawal amount:");
        String withdrawal= System.console().readLine();
        int amountWithdrawal = Integer.parseInt(withdrawal);

        if (accountType.contains("checking")){
			checking -= amountWithdrawal;
            if (amountWithdrawal > Total){
                System.out.println("\nSorry you're too broke");
            } else {
                Total -= amountWithdrawal;
                System.out.println("Checkings withdrawal amount: $" + String.format("%.2f", checking));
            }
		} else if(accountType.equals("savings")) {
			savings -= amountWithdrawal;
            if (amountWithdrawal > Total){
                System.out.println("\nSorry you're too broke");
            } else {
                Total -= amountWithdrawal;
                System.out.println("Savings withdrawal amount: $" + String.format("%.2f", savings));
            }
        }
    }

    //Create a method to see the total money from the checking and saving.
    public void getTotal() {
		System.out.println("Checking amount: $" + checking);
		System.out.println("Savings amount: $" + savings);
		System.out.println("The total in both is: $" + (checking + savings));
    }


    // private void randomAccountNumber() {
    //     for(int i=0; i < 2; i++){
    //     //Generate random number and convert to long
    //     long randnum = Math.round(Math.random()*1000000000);
    //     //Print the random value
    //     System.out.println(randnum);   
    //     }
    //     // Random rando = new Random();
    //     // int number = rando.nextInt(999999999);
    //     // return number;
    // }
    
}