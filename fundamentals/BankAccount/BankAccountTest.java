public class BankAccountTest {
	public static void main(String[] args) {

        BankAccount bank = new BankAccount();
        BankAccount bank1 = new BankAccount();

        System.out.println("\n----- Deposit -----");
        bank.deposit();
        System.out.println("\n----- Withdrawal -----");
        bank.withdraw();
        System.out.println("\n----- Total in each account -----");
        // bank.getChecking();
        // bank.getSavings();
        bank.getTotal();
        System.out.println("\n----- Random account number -----");
        System.out.println("Account number: " + bank.getAccountNumber());
        System.out.println("Account 2 number: " + bank1.getAccountNumber() + "\n");
    }
}