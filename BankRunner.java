import java.util.Scanner;

/**
 * Run the bank.
 * @author Jon Beck
 * @version 14 November 2016
 */
public class BankRunner
{
    private static final Scanner in = new Scanner(System.in);
    private static final BankOG bank = new BankOG();

    public static void main(String[] args)
    {
        boolean open = true;

        while (open)
        {
            System.out.println("\nC)reate account");
            System.out.println("D)eposit");
            System.out.println("W)ithdraw");
            System.out.println("M)onth end");
            System.out.println("Q)uit");
            System.out.print("Choice: ");
            String input = in.nextLine();
            if (input.substring(0, 1).equalsIgnoreCase("C"))
            {
                createAccount();
            }
            else if (input.substring(0, 1).equalsIgnoreCase("D"))
            {
                doDeposit();
            }
            else if( input.substring(0, 1).equalsIgnoreCase("W"))
            {
                doWithdrawal();
            }
            else if( input.substring(0, 1).equalsIgnoreCase("M"))
            {
                bank.processEndMonth();
            }
            else if( input.substring(0, 1).equalsIgnoreCase("Q"))
            {
                System.out.println("Bank is now closed");
                bank.close();
                open = false;
            }
            else
            {
                System.out.println("Invalid input");
            }
        }
    }

    public static void createAccount()
    {
        System.out.print("Account name: ");
        String name = in.nextLine();
        System.out.print("C)hecking or S)avings: ");
        String type = in.nextLine().substring(0, 1);
        if (type.equalsIgnoreCase("C"))
        {
            int newAccountNumber = bank.newAccount(name);
            System.out.println("Created account " + newAccountNumber);
        }
        else if ( type.equalsIgnoreCase("S"))
        {
            System.out.print("Interest rate: ");
            double intrate = in.nextDouble();
            in.nextLine();
            int newAccountNumber = bank.newAccount(name, intrate);
            System.out.println("Created account " + newAccountNumber);
        }
        else
        {
            System.out.println("Invalid account type specified");
        }
    }

    public static void doDeposit()
    {
        BankAccount account = bank.validate();
        if (account != null)
        {
            System.out.print("Enter deposit amount: ");
            String amountString = in.nextLine();
            double amount = 0.0;
            try
            {
                amount = Double.parseDouble(amountString);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid amount specified");
                return;
            }
            account.deposit(amount);
        }
        else
        {
            System.out.println("Invalid credentials supplied");
        }
    }

    public static void doWithdrawal()
    {
        BankAccount account = bank.validate();
        if (account != null)
        {
            System.out.print("Enter withdrawal amount: ");
            String amountString = in.nextLine();
            double amount = 0.0;
            try
            {
                amount = Double.parseDouble(amountString);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid amount specified");
                return;
            }
            if (account.getBalance() >= amount)
            {
                account.withdraw(amount);
            }
            else
            {
                System.out.println("Withdrawal not allowed");
            }
        }
        else
        {
            System.out.println("Invalid credentials supplied");
        }
    }
}
