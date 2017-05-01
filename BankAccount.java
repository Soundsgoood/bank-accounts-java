/**
 *  A bank account has a balance that can be changed by
 *  deposits and withdrawals. Adapted from Horstmann
 *  @author Jon Beck
 *  @version 14 November 2016
 */
public abstract class BankAccount
{
    private String name;
    private double balance;
    private int accountNumber;
    private static int lastAccountNumberUsed = 100;

    /**
     * Constructs a bank account with a zero balance.
     */
    public BankAccount(String name)
    {
        this.name = name;
        balance = 0.0;
        lastAccountNumberUsed++;
        accountNumber = lastAccountNumberUsed;
    }

    /**
     * Constructs a bank account with a given balance.
     * @param initialBalance the initial balance
     */
    public BankAccount(String name, double initialBalance)
    {
        this.name = name;
        balance = initialBalance;
        lastAccountNumberUsed++;
        accountNumber = lastAccountNumberUsed;
    }

    /**
     * The last account number used is the number of the most recent
     * account created.
     * @return the number of the most recently created account
     */
    public static int getLastAccountNumberUsed()
    {
        return lastAccountNumberUsed;
    }

    /**
     * Deposits money into the account.
     * @param amount the amount of money to deposit
     */
    public void deposit(double amount)
    {
        balance += amount;
    }

    /**
     * Withdraws money from the account.
     * @param amount the amount of money to withdraw
     */
    public void withdraw(double amount)
    {
        balance -= amount;
    }

    /**
     * Gets the account balance.
     * @return the account balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Find out this account's number
     * @return the account number
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Accessor for the name on the account
     * @return the name on the account
     */
    public String getName()
    {
        return name;
    }

    /**
     * Do the month-end processing
     */
    public abstract void monthEnd();

    /**
     * Generate the string representation necessary for writing to
     * the data file
     * @return a formatted string with the account information
     */
    public abstract String printFormatted();

    /**
     * Override toString for simple reporting of the account info
     * @return a string representation of the account
     */
    public String toString()
    {
        return getClass().getName() + "[" + name + ",acct#=" + accountNumber +
            ",bal=" + balance + "]";
    }
}
