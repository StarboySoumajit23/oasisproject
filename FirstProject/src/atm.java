
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User{
    private String userId;
    private String password;
    public User(String userId,String password)
    {
        this.userId=userId;
        this.password=password;

    }
    public String getuserId(){
        return userId;
    }
    public String getpassword(){
        return password;

    }

}
class Transaction{
    private String type;
    private double amount;
    public Transaction(String type, double amount)
    {
        this.type=type;
        this.amount=amount;

    }
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
}
class Account{
    private double balance;
    private String userId;
    private List<Transaction>transactionHistory;
    public Account(String userId)
    {
        this.userId=userId;
        this.balance=1000;
        this.transactionHistory=new ArrayList<>();
    }
    public double getBalance(){
        return balance;
    }
    public String getUserId(){
        return userId;
    }
    public List<Transaction>getTransactionHistory(){
        return transactionHistory;
    }
    public void withdraw(double amount)
    {
        if (amount>0 && amount<=balance)
        {
            balance -=amount;
            transactionHistory.add(new Transaction("Withdrawal",amount));
            System.out.println("Withdrawal successful.Remaining balance"+balance);
        }
        else{
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }
    public void deposit(double amount)
    {
        if(amount>0){
            balance +=amount;
            transactionHistory.add(new Transaction("Deposit",amount));
            System.out.println("Deposit successful.New balance :"+balance);
        }
        else{
            System.out.println("invalid deposit amount");
        }
    }
    public void transfer(Account reciptant, double amount)
    {
        if(amount>0 && amount<=balance){
            balance -=amount;
            reciptant.balance +=amount;
            transactionHistory.add(new Transaction("Transfer to"+reciptant.getUserId(),amount));
            System.out.println("Transfer successful");
        }
        else{
            System.out.println("Invalid transfer amount");
        }

    }
}
class ATMInterface{
    private User currentUser;
    private Account currentAccount;
    private Scanner scanner;
    public ATMInterface(User user,Account account)
    {
        this.currentUser=user;
        this.currentAccount=account;
        this.scanner=new Scanner(System.in);
    }
    public void displayMenu()
    {
        System.out.println("Welcome"+currentUser.getuserId()+"!");
        System.out.println("1.Check Balance");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit");
        System.out.println("4. Transfer");
        System.out.println("5.Transaction History");
        System.out.println("6.Exit");
        int choice=scanner.nextInt();
        switch(choice)
        {
            case 1:System.out.println("Balnce:"+currentAccount.getBalance());
                break;
            case 2:System.out.println("Enter withdrawal amount");
                double withdrawalAmount=scanner.nextDouble();
                currentAccount.withdraw(withdrawalAmount);
                break;
            case 3:System.out.println("Enter deposit amount");
                double depositAmount=scanner.nextDouble();
                currentAccount.deposit(depositAmount);
                break;
            case 4:System.out.println("Enter receiptant User ID");
                String recipientUserId=scanner.next();
                Account recipientAccount=new Account(recipientUserId);
                System.out.println("Enter transfer amount");
                double transferAmount=scanner.nextDouble();
                currentAccount.transfer(recipientAccount, transferAmount);
                break;
            case 5:displayTransactionHistory();
                break;
            case 6:System.out.println("Exiting.Thankyou!");
                break;
            default:System.out.println("Invalid choise");



        }
        displayMenu();


    }
    private void displayTransactionHistory()
    {
        List<Transaction>transactions=currentAccount.getTransactionHistory();
        System.out.println("Transaction History for"+ currentAccount.getUserId()+":");
        for(Transaction transaction:transactions){
            System.out.println(transaction.getType()+"-Amount:"+transaction.getAmount());
        }

    }

}


public class atm {
    public static void main(String[] args) {
        System.out.println("Hello. I am Soumajit Bhattacharjee");
        Scanner sc=new Scanner(System.in);


        System.out.println("Enter user Id");
        String userId=sc.next();
        System.out.println("Create password");
        String password=sc.next();



        User user =new User(userId,password);
        Account account=new Account(userId);
        ATMInterface atmInterface= new ATMInterface(user, account);
        atmInterface.displayMenu();


    }

}
