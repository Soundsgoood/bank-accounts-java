/**
 * A checking account that keeps track of withdrawals. Charges a fee if the
 * maximum amount of withdrawals is exceeded.
 * @author Orion Guan
 * @version November 21st, 2016
 */
public class CheckingAccountOG extends BankAccount
{
  private static final int MAXIMUM_WITHDRAWALS = 3;
  private static final double EXCESS_WITHDRAWAL_FEE = 1.00;
  private int numberOfWithdrawals;
  
  /**
   * Creates a new checking account.
   * @param name The name of the owner of the checking account.
   */
  public CheckingAccountOG(String name)
  {
    super(name);
    numberOfWithdrawals = 0;
  }
  
  /**
   * Creates a checking account based on data of an existing checking account.
   * @param name The name of the owner of the checking account.
   * @param balance The current balance of the checking account.
   * @param numberOfWithdrawals The number of withdrawals made this month from
   * the checking account.
   */
  public CheckingAccountOG(String name, double balance, int numberOfWithdrawals)
  {
    super(name, balance);
    this.numberOfWithdrawals = numberOfWithdrawals;
  }
  
  /**
   * Withdraws money from the checking account, counting the number of
   * withdrawals and charging a fee if too many have been made.
   * @param amount The amount to be withdrawn.
   */
  public void withdraw(double amount)
  {
    super.withdraw(amount);
    numberOfWithdrawals++;
    if (numberOfWithdrawals > MAXIMUM_WITHDRAWALS)
    {
      super.withdraw(EXCESS_WITHDRAWAL_FEE);
    }
  }
  
  /**
   * Does month end processing for the checking account, i.e. resetting the
   * number of withdrawals made during the month.
   */
  public void monthEnd()
  {
    numberOfWithdrawals = 0;
  }
  
  /**
   * Prints the checking account's data on a line of values in a specific format
   * for data files.
   * @return A String of information regarding the checking account's current
   * state.
   */
  public String printFormatted()
  {
    return String.format("C"
              + super.getAccountNumber() + " "
              + super.getBalance() + " "
              + numberOfWithdrawals + " "
              + super.getName());
  }
  
  /**
   * Prints the checking account's data on a line of values in a specific format
   * for the printed text at bank opening.
   * @return A String of information regarding the checking account's current
   * state.
   */
  public String toString()
  {
    return getClass().getName() + "[" + super.getName() + ",acct#="
      + super.getAccountNumber() + ",bal=" + super.getBalance() + "][withs="
      + numberOfWithdrawals + "]";
  }
}