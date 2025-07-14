import java.io.*;
import java.util.*;
class Test extends Login
{
	static String RESET = "\u001B[0m";
	static String RED = "\u001B[31m";
	static String GREEN = "\u001B[32m";
	static String YELLOW = "\u001B[33m";
        static String BLUE  =" \u001B[34m";
        static String  CYAN ="\u001B[36m";
	static Register obj=new Register();
	static
	{
		System.out.println(RED+"             -----Welcome to Electricity Bill Paying System-----          "+RESET);
		System.out.println(GREEN+"Click 1 if you are existing user for login"+RESET);
		System.out.println(YELLOW+"Click 2 for Registration"+RESET);
	};
	static void input(int n)
	{
		if(n==1)
		{
			System.out.println("Enter login details");
			login("","");
		}
		else if(n==2)
		{
			System.out.println("Registration page opened");
			obj.register();
		}
		else
		{
			System.out.println("Please enter the valid input");
			n=sc.nextInt();
			input(n);
		}
	}
	public static void main(String[] args)
	{
		int n=sc.nextInt();
		input(n);
	}
}
class Register extends Login
{
	 static String BLUE  =" \u001B[34m";
	static String RESET = "\u001B[0m";
	static String name;
	static String mobno;
	private String username="";
	private String password="";
	void register()
	{
		System.out.print("Enter Customer name: ");
		name=sc.next();
		System.out.print("Enter mobile Number: ");
		mobno=sc.next();
		while(mobno.length()>10 || mobno.length()<10)
		{
			System.out.println("Enter valid mobile number:");
			mobno=sc.next();
		}
		System.out.print("Enter E-mail ID: ");
		String email=sc.next();
		System.out.print("Enter username: ");
		username=sc.next();
		System.out.print("Create new password: ");
		password=sc.next();
		System.out.print("Confirm password: ");
		String cpassword=sc.next();
		while(!(cpassword.equals(password)))
		{
			System.out.println("Please enter same password");
			System.out.print("Create new password: ");
			password=sc.next();
			System.out.print("Confirm password: ");
			cpassword=sc.next();
		}
		System.out.println(BLUE+"-----"+name+" Your Registration Successful. You are redirecting to the login page-----"+RESET);
		login(username,password);
	}
	String verify(String username,String password)
	{
		if((this.username.equals(username)) && (this.password.equals(password)))
		{
			return "-----Login Successful-----";
		}
		else
		{
			return "Login Unsuccessful. Please Enter valid credentials";
		}
	}
}
class Login extends Bill
{     
        static String  CYAN ="\u001B[36m";
        static String RESET = "\u001B[0m";
	static Scanner sc=new Scanner(System.in);
	static Bill obj=new Bill();
	static Register obj1=new Register();
	static void login(String username,String password)
	{
		System.out.print("Enter username: ");
		String username1=sc.next();
		System.out.print("Enter password: ");
		String password1=sc.next();
		String var=obj1.verify(username1,password1);
		if(var.equals("-----Login Successful-----"))
		{
			System.out.println(CYAN+var+RESET);
			obj.bill();
		}
		else
		{
			System.out.println(CYAN+var+RESET);
			obj1.register();
		}
	}
}
interface I1
{
	void verifybillno(String Billnum);
	void bill();
}
class Bill implements I1
{
	static Scanner sc=new Scanner(System.in);
        static String RESET = "\u001B[0m";
        static String RED = "\u001B[31m";	
        static String GREEN = "\u001B[32m"; 
	static double unitcost;
	static Payment obj=new Payment();
	static String month;
	static String Billno;
	static int unitsconsume;
	public void verifybillno(String Billnum)
	{
		for(int i=0;i<=Billnum.length()-1;i++)
		{
			char ch=Billnum.charAt(i);
			int a=(int)ch;
			while((a>=32 && a<=47) || (a>=58 && a<=64) || (a>=91 && a<=96) || (a>=123 && a<=127))
			{
				System.out.println(RED+"Special Characters are not allowed. Please enter valid Billno"+RESET);
				Billnum=sc.next();
			}
		}
	}
	public void bill()
	{
		System.out.println(GREEN+"Enter 1 for Central Board\nEnter 2 for Southern Board\nEnter 3 for Eastern Board"+RESET);
		int n=sc.nextInt();
		billdetails(n);
	}
	void billdetails(int n)
	{
		if(n==1)
		{
			System.out.print("Enter month: ");
			month=sc.next();
			System.out.println("Enter Bill Number: ");
			Billno=sc.next();
			verifybillno(Billno);
			while(Billno.length()!=13)
			{
				System.out.println("Enter valid bill number");
				Billno=sc.next();
			}
			System.out.println("Enter units consumed: ");	
			unitsconsume=sc.nextInt();
			billAmount(unitsconsume);
		}
		else if(n==2)
		{
			System.out.print("Enter month: ");
			month=sc.next();
			System.out.println("Enter Bill Number: ");
			Billno=sc.next();
			verifybillno(Billno);
			while(Billno.length()!=13)
			{
				System.out.println(RED+"Enter valid bill number"+RESET);
				Billno=sc.next();
			}
			System.out.println("Enter units consumed: ");	
			unitsconsume=sc.nextInt();
			billAmount1(unitsconsume);
		}
		else if(n==3)
		{
			System.out.print("Enter month: ");
			month=sc.next();
			System.out.println("Enter Bill Number: ");
			Billno=sc.next();
			verifybillno(Billno);
			while(Billno.length()!=16)
			{
				System.out.println(RED+"Enter valid bill number"+RESET);
				Billno=sc.next();
			}
			System.out.println("Enter units consumed: ");	
			unitsconsume=sc.nextInt();
			billAmount2(unitsconsume);
		}
		else
		{
			System.out.println(RED+"Please Enter valid inputs only"+RESET);
			n=sc.nextInt();
			billdetails(n);
		}		
	}
	static void billAmount(int units)
	{
		if(units<=100)
		{
			unitcost=units*1.20;
		}
		else if(units<=300)
		{
			unitcost=100*1.20+(units-100)*2;
		}
		else
		{
			unitcost=100*1.20+200*2+(units-300)*2.5;
		}
		obj.payment(unitcost);
	}
	static void billAmount1(int units)
	{
		if(units<=100)
		{
			unitcost=units*1.25;
		}
		else if(units<=300)
		{
			unitcost=100*1.25+(units-100)*2.20;
		}
		else
		{
			unitcost=100*1.25+200*2.20+(units-300)*3;
		}
		obj.payment(unitcost);
	}
	static void billAmount2(int units)
	{
		if(units<=100)
		{
			unitcost=units*1.3;
		}
		else if(units<=300)
		{
			unitcost=100*1.3+(units-100)*2;
		}
		else
		{
			unitcost=100*1.3+200*2+(units-300)*3.2;
		}
		obj.payment(unitcost);
	}
}
class Payment extends Receipt
{
	static Scanner sc=new Scanner(System.in);
       
       static String RESET = "\u001B[0m";
       static String RED = "\u001B[31m";
       static String GREEN = "\u001B[32m";
       static String BLUE  =" \u001B[34m";
	void payment(double amount)
	{	
		String cardno;
		String cvv;
		String pin;
		String expirydate;
		int e=((int)(Math.random()*100000));
		int otp;
		System.out.println("Bill amount is: "+amount+" Excludes taxes");
		System.out.println(BLUE+"-----Payment Options-----"+RESET);
		System.out.println(GREEN+"Enter 1 for UPI\n2 for Credit Card\n3 for Debit Card"+RESET);
		int n=sc.nextInt();
		if(n==1)
		{
			System.out.print("Enter UPI ID: ");
			String upiid=sc.next();
			System.out.println("Enter pin: ");
			pin=sc.next();
			verifypin(pin);
		}
		else if(n==2)
		{
			System.out.println("Enter Card Number: ");
			cardno=sc.next();
			while(cardno.length()>16)
			{
				System.out.println(RED+"Invalid card number. Enter valid card number: "+RESET);
				cardno=sc.next();
			}
			System.out.println("Enter CVV: ");
			cvv=sc.next();
			while(cvv.length()>3)
			{
				System.out.println(RED+"Enter valid cvv number: "+RESET);
				cvv=sc.next();
			}
			System.out.println("Enter expiry date: ");
			expirydate=sc.next();
			System.out.println("Enter pin: ");
			pin=sc.next();
			verifypin(pin);	
			System.out.println("OTP: "+e);
			System.out.println("Enter OTP: ");
			otp=sc.nextInt();
			while(otp!=e)
			{
				System.out.println(RED+"Invalid OTP. Please Enter valid OTP"+RESET);
				otp=sc.nextInt();
			}
		}
		else if(n==3)
		{
			System.out.println("Enter Card Number: ");
			cardno=sc.next();
			while(cardno.length()>16)
			{
				System.out.println(RED+"Invalid card number. Enter valid card number: "+RESET);
				cardno=sc.next();
			}
			System.out.println("Enter CVV: ");
			cvv=sc.next();
			while(cvv.length()>3)
			{
				System.out.println(RED+"Enter valid cvv number: "+RESET);
				cvv=sc.next();
			}
			System.out.println("Enter expiry date: ");
			expirydate=sc.next();
			System.out.println("Enter pin: ");
			pin=sc.next();
			verifypin(pin);
			System.out.println("OTP: "+e);
			System.out.println("Enter OTP: ");
			otp=sc.nextInt();
			while(otp!=e)
			{
				System.out.println(RED+"Invalid OTP. Please Enter valid OTP"+RESET);
				otp=sc.nextInt();
			}
		}
		receipt(amount);
	}
	static void verifypin(String pinno)
	{
		while(pinno.length()!=4)
		{
			System.out.println(RED+"Invalid pin number.\nEnter valid pin"+RESET);
			pinno=sc.next();
		}
	}
}
class Receipt extends Register
{
	static String GREEN = "\u001B[32m";
        static String BLUE  =" \u001B[34m";
        static String RESET = "\u001B[0m";
        static Bill obj=new Bill();
	static void receipt(double amount1)
	{
		double amount2=amount1+(amount1*0.18);
		System.out.println(GREEN+"-----Receipt Details-----"+RESET);
		System.out.println("Customer Name: "+name);
		System.out.println("Mobile Number: "+mobno);
		System.out.println("Bill Number: "+obj.Billno);
		System.out.println("Month: "+obj.month);
		System.out.println("Units Consumed: "+obj.unitsconsume);
		System.out.println("Bill Amount (Excluding taxes): "+amount1);		 
		System.out.println("Total Bill (including taxes): ");
		System.out.printf("%2f",amount2);
		System.out.println();
		System.out.println(BLUE+"Payment Status: Success"+RESET);
		System.out.println(GREEN+"-----Thank You-----"+RESET);
	}
}