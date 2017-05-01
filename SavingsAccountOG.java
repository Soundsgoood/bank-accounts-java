/**
 * A savings account that processes interest based on its own rate and the
 * minimum balance of the month.
 * @author Orion Guan
 * @version November 21st, 2016
 */
public class SavingsAccountOG extends BankAccount
{
  private double intrate;
  private double minimumBalanceOfMonth;
  
  /**
   * Creates a new savings account.
   * @param name The name of the owner of the savings account.
   * @param intrate The interest rate of the savings account.
   */
  public SavingsAccountOG(String name, double intrate)
  {
    super(name);
    this.intrate = intrate;
    minimumBalanceOfMonth = super.getBalance();
  }
  
  /**
   * Creates a savings account, based on data about an existing savings account.
   * @param name The name of the owner of the savings account.
   * @param intrate The interest rate of the savings account.
   * @param balance The current balance of the savings account.
   * @param minimumBalanceOfMonth The lowest balance the account has seen this
   * month.
   */
  public SavingsAccountOG(String name, double intrate, double balance,
    double minimumBalanceOfMonth)
  {
    super(name, balance);
    this.intrate = intrate;
    this.minimumBalanceOfMonth = minimumBalanceOfMonth;
  }

  /**
   * Withdraws money from the savings account, keeping track of the lowest
   * balance the account has seen during the month.
   * @param amount The amount to be withdrawn.
   */
  public void withdraw(double amount)
  {
    super.withdraw(amount);
    minimumBalanceOfMonth = (super.getBalance() < minimumBalanceOfMonth)
      ? super.getBalance() : minimumBalanceOfMonth;
  }
  
  /**
   * Does month end processing for the savings account, i.e. sets the current
   * balance as the lowest balance amount of the month.
   */
  public void monthEnd()
  {
    super.deposit(minimumBalanceOfMonth * intrate);
    minimumBalanceOfMonth = super.getBalance();
  }
  
  /**
   * Prints the savings account's data on a line of values in a specific format
   * for data files.
   * @return A String of information regarding the savings account's current
   * state.
   */
  public String printFormatted()
  {
    return ("S"
              + super.getAccountNumber() + " "
              + super.getBalance() + " "
              + minimumBalanceOfMonth + " "
              + intrate + " "
              + super.getName());
  }
  
  /**
   * Prints the savings account's data on a line of values in a specific format
   * for the printed text at bank opening.
   * @return A String of information regarding the savings account's current
   * state.
   */
  public String toString()
  {
    return getClass().getName() + "[" + super.getName() + ",acct#="
      + super.getAccountNumber() + ",bal=" + super.getBalance() + "][int="
      + intrate + ",minbal=" + minimumBalanceOfMonth + "]";
  }
}