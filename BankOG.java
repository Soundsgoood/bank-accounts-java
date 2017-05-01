import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A bank that holds and processes bank accounts. Allows for the creation and
 * modification of accounts, monthly processing, and reading/writing account
 * information from a data file.
 * @author Orion Guan
 * @version November 21st, 2016
 */
public class BankOG
{
  private static final String DATA_FILE_NAME = "bank.dat";
  private ArrayList<BankAccount> accountList;

  /**
   * Opens a bank, importing accounts from information in the bank's data file
   * if there are any.
   */
  public BankOG()
  {
    File dataFile = new File(DATA_FILE_NAME);
    accountList = new ArrayList<BankAccount>();
    importAccountData(dataFile);
  }

  /**
   * Imports account data for checking and savings accounts.
   * @param dataFile The file where all the account data is stored in a specific
   * format.
   */
  public void importAccountData(File dataFile)
  {
    try (Scanner in = new Scanner(dataFile, "US-ASCII"))
    {
      while (in.hasNextLine())
      {
        String readLine = in.nextLine();
        Scanner inLine = new Scanner(readLine);
        inLine.useDelimiter(" ");
        if (readLine.charAt(0) == 'C')
        {
          int readAccountNumber = Integer.parseInt(inLine.next().substring(1,3));
          double readBalance = inLine.nextDouble();
          int readNumberOfWithdrawals = inLine.nextInt();
          String readName = "";
          while (inLine.hasNext())
          {
            readName += inLine.next();
            readName += " ";
          }
          BankAccount checkingAccount = new CheckingAccountOG(readName,
            readBalance, readNumberOfWithdrawals);
          accountList.add(checkingAccount);
          System.out.println("[Read] " + checkingAccount);
        }
        else if (readLine.charAt(0) == 'S')
        {
          int readAccountNumber = Integer.parseInt(inLine.next().substring(1,3));
          double readBalance = inLine.nextDouble();
          double readMinimumBalanceOfMonth = inLine.nextDouble();
          double readIntrate = inLine.nextDouble();
          String readName = "";
          while (inLine.hasNext())
          {
            readName += inLine.next();
            readName += " ";
          }
          BankAccount savingsAccount = new SavingsAccountOG(readName,
            readIntrate, readBalance, readMinimumBalanceOfMonth);
          accountList.add(savingsAccount);
          System.out.println("[Read] " + savingsAccount);
        }
        else
        {
          System.out.println("Bank data file lines must begin with C or S.");
        }
        inLine.close();
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("input file not found");
    }
  }
  
  /**
   * Creates a new checking account.
   * @param name The name String associated with the owner of the account.
   * @return The account number.
   */
  public int newAccount(String name)
  {
    BankAccount checkingAccount = new CheckingAccountOG(name);
    accountList.add(checkingAccount);
    return checkingAccount.getAccountNumber();
  }
  
  /**
   * Creates a new savings account.
   * @param dataFile The file where all the account data is stored in a specific
   * format.
   * @param intrate The monthly interest rate associated with the account, in
   * decimal form.
   * @return The account number.
   */
  public int newAccount(String name, double intrate)
  {
    BankAccount savingsAccount = new SavingsAccountOG(name, intrate);
    accountList.add(savingsAccount);
    return savingsAccount.getAccountNumber();
  }
  
  /**
   * Prompts via Scanner the account number to be accessed, and returns that
   * account.
   * @return The bank account associated with a valid used bank account number
   * entered. Otherwise returns null.
   */
  public BankAccount validate()
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter account number: ");
    int queryNumber = Integer.parseInt(in.nextLine());

    BankAccount validatedAccount = null;
    for (BankAccount account : accountList)
    {
      if (account.getAccountNumber() == queryNumber)
      {
        validatedAccount = account;
      }
    }
    return validatedAccount;
  }
  
  /**
   * Processes all accounts in the bank with their monthly activity, specified
   * per type of account.
   */
  public void processEndMonth()
  {
    for (BankAccount account : accountList)
    {
      account.monthEnd();
    }
  }
  
  /**
   * Ends the current session with the bank's data. Writes all the account
   * information to the bank's data file.
   */
  public void close()
  {
    File dataFile = new File(DATA_FILE_NAME);
    try (PrintWriter out = new PrintWriter(dataFile, "US-ASCII"))
    {
      for (BankAccount account : accountList)
      {
        System.out.println("Saved " + account);
        out.println(account.printFormatted());
      }
      System.out.println("Bank data saved to file");
    }
    catch (FileNotFoundException e)
    {
      System.out.println("output file not found");
    }
    catch (UnsupportedEncodingException e)
    {
      System.out.println("encoding not supported");
    }
  }
}