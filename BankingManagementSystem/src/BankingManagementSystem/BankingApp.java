package BankingManagementSystem;

import java.sql.*;
import java.util.Scanner;

import java.util.Scanner;

import static java.lang.Class.forName;

public class BankingApp {
	// telling our application where is our database and user name and password to access the database
	private static final String url = "jdbc:mysql://localhost:3306/banking_system";
	private static final String username = "root";
	private static final String password = "root";
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 try{
			 //connecting with sql driver with respect to your database
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        }catch (ClassNotFoundException e){
	            System.out.println(e.getMessage());
	        }
	        try{
	        	//making connection between database and application
	            Connection connection = DriverManager.getConnection(url, username, password);
	            // Scanner class is used to take input form the user in console
	            Scanner scanner =  new Scanner(System.in);
	            //making the objects of other classes and using their function
	            User user = new User(connection, scanner);
	            Accounts accounts = new Accounts(connection, scanner);
	            AccountManager accountManager = new AccountManager(connection, scanner);
	            
	            // making variables for extract the value form the user
	            String email;
	            long account_number;
	            
	            // taking input form the user until user exit
	            while(true){
	                System.out.println("*** WELCOME TO BANKING SYSTEM ***");
	                System.out.println();
	                System.out.println("1. Register");
	                System.out.println("2. Login");
	                System.out.println("3. Exit");
	                System.out.println("Enter your choice: ");
	                int choice1 = scanner.nextInt();
	                // Now user can choose the what he want to do from above function then execute accordingly 
	                switch (choice1){
	                    case 1:
	                    	// in User class there is a method name register
	                        user.register();
	                        break;
	                    case 2:
	                    	// in user class there is a method name login which return email
	                        email = user.login();
	                        // if it get email from function it shows successful!
	                        if(email!=null)
	                        {
	                            System.out.println();
	                            System.out.println("User Logged In!");
	                            // if user email is exist or link to account . it tell user to make a account
	                            if(!accounts.account_exist(email))
	                            {
	                                System.out.println();
	                                System.out.println("1. Open a new Bank Account");
	                                System.out.println("2. Exit");
	                                if(scanner.nextInt() == 1) {
	                                    account_number = accounts.open_account(email);
	                                    System.out.println("Account Created Successfully");
	                                    System.out.println("Your Account Number is: " + account_number);
	                                }else{
	                                    break;
	                                }

	                            }
	                            // if your having an account already then it shows account number and other function user can perform
	                            account_number = accounts.getAccount_number(email);
	                            int choice2 = 0;
	                            while (choice2 != 5) {
	                                System.out.println();
	                                System.out.println("1. Debit Money");
	                                System.out.println("2. Credit Money");
	                                System.out.println("3. Transfer Money");
	                                System.out.println("4. Check Balance");
	                                System.out.println("5. Log Out");
	                                System.out.println("Enter your choice: ");
	                                // user can select any one function at a time
	                                choice2 = scanner.nextInt();
	                                //accountManager class having some functions to manage their account
	                                switch (choice2) {
	                                    case 1:
	                                        accountManager.debit_money(account_number);
	                                        break;
	                                    case 2:
	                                        accountManager.credit_money(account_number);
	                                        break;
	                                    case 3:
	                                        accountManager.transfer_money(account_number);
	                                        break;
	                                    case 4:
	                                        accountManager.getBalance(account_number);
	                                        break;
	                                    case 5:
	                                        break;
	                                    default:
	                                        System.out.println("Enter Valid Choice!");
	                                        break;
	                                }
	                            }

	                        }
	                        // if email not exist or your password wrong
	                        else{
	                            System.out.println("Incorrect Email or Password!");
	                        }
	                    case 3:
	                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
	                        System.out.println("Exiting System!");
	                        return;
	                    default:
	                        System.out.println("Enter Valid Choice");
	                        break;
	                }
	            }
	        }catch (SQLException e){
	            e.printStackTrace();
	        }

	}

}
