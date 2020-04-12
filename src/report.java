import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

public class report {
	 static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/dshah4";
	    private static Scanner in = null;
	    private static Connection connection = null;
	    private static Statement statement = null;
	    private static ResultSet result = null;

	    public static void main(String[] args) {
	        in = new Scanner(System.in);
	        initialize();
	        //Base Structure Not perfect

	        Scanner s = new Scanner(System.in);
	        int choice;
	        do {
	            System.out.println("---------------------QUERIES----------------------");
	            System.out.println("1.Generate monthly report with number and total price of copies of each publication bought per distributor in a month;  ");
	            System.out.println("2.Total Revenue of Publishing House per Month");
	            System.out.println("3.Total Costs of Publishing House per month");
	            System.out.println("4.Number of Current Distributors");
	            System.out.println("5.Get the total revenue by city");
	            System.out.println("6.Get revenue by Distributor");
	            System.out.println("7.Get revenue by location");
	            System.out.println("8.Calculate total payments to authors and editors during time period");
	            System.out.println("9.Calculate total payments to the editors and authors, per time period and per work type");
	           
	            System.out.println("\n\n Enter your choice:    ");

	            choice = s.nextInt();
	            switch(choice) {
	                case 1: try {
	                	monthlyReport();
	                }catch (SQLException e){
	                    e.printStackTrace();
	                    if (connection != null) {
	                        try {
	                            connection.rollback();
	                        } catch(SQLException excep) {
	                            excep.printStackTrace();
	                        }
	                    }
	                }
	                    
	                case 2:
	                case 3:
	                case 4:
	                case 5:
	                case 6:
	                case 7:
	                case 8:
	                case 9:
	                default:
	                    throw new IllegalStateException("Unexpected value: " + choice);
	            }
	        }while(choice<=9 && choice>=1);
	    }

	    private static void initialize() {
	        try {
	            connectToDatabase();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
	        Class.forName("org.mariadb.jdbc.Driver");

	        String user = "dshah4";
	        String password = "legionsofdoom";

	        connection = DriverManager.getConnection(jdbcURL, user, password);
	        statement = connection.createStatement();
	    }

	    public static void close(){
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (result != null) {
	            try {
	                result.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    


	    public static void monthlyReport()  throws SQLException{
	    	
	    
	    	
	    	String Query = "SELECT Distributor.Account_no, Month(Orders.order_date) Month, Year(Orders.order_date) Year, sum(oi.price) totalprice, sum(oi.quantity) totalquantity, oi.Pub_Id, Publications.title FROM Distributor NATURAL JOIN Order_for_Issues oi NATURAL JOIN Orders Inner JOIN Publications where oi.Pub_Id =Publications.Pub_Id GROUP BY Distributor.Account_no, oi.Pub_Id, Month, Year UNION SELECT Distributor.Account_no, Month(Orders.order_date) Month,Year(Orders.order_date) Year, sum(oe.price) totalprice, sum(oe.quantity) totalquantity, Publications.Pub_Id, Publications.title FROM Distributor NATURAL JOIN Order_for_Edition oe NATURAL JOIN Orders Inner JOIN Publications Inner Join Ofwhere oe.ISBN = Of.ISBN AND Of.Pub_Id = Publications.Pub_Id GROUP BY Distributor.Account_no, Publications.Pub_Id,Month, Year;";
	    	ResultSet rs = statement.executeQuery(Query);
	    	
	    	while(rs.next())
	    	{

       System.out.print("Account_no:"+rs.getString(1));
       System.out.print("\tMonth:"+rs.getString(2));
       System.out.print("\tYear:"+rs.getString(3));
       System.out.print("\ttotalprice:"+rs.getString(4));
       System.out.print("\ttotalquantity:"+rs.getString(5));
       System.out.print("\tPub_Id:"+rs.getString(6));
       System.out.print("\tDtitle:"+rs.getString(7));

   }
 }


	    public static void revenuePublishing()  throws SQLException{
	  

	    	String Query = "SELECT Month(t.claim_date) as month, Year(t.claim_date) as year, SUM(t.Amount) as revenue FROM ( SELECT amount,  claim_date, Pays.PaymentId FROM Pays INNER JOIN Payments ON Pays.PaymentId = Payments.PaymentId GROUP BY Month, Year;";

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("Month:"+rs.getString(1));
	    				System.out.print("\tYear:"+rs.getString(2));
	    				System.out.print("\tRevenue :"+rs.getString(3));

	    			} 

	    }


	    public static void costPublishing()  throws SQLException{
	    

	        
	    	String Query = "SELECT YEAR(date) as Year, MONTH(date) as Month, SUM(t.cost) AS total_expenses FROM (SELECT shipping_cost AS cost, order_date AS date FROM Orders UNION ALL SELECT amount AS cost, claim_date AS date FROM Compensate INNER JOIN Payments ON Payments.PaymentId = Compensate.PaymentId GROUP BY MONTH, YEAR;";
	    		

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("Year:"+rs.getString(1));
	    				System.out.print("\tMonth:"+rs.getString(2));
	    				System.out.print("\tTotalExpense :"+rs.getString(3));

	    			} 
	    }
	    			

	    public static void distributionNum()  throws SQLException{
	    	
	   
	    		        
	    		  String Query = "SELECT COUNT(*) AS num_distributors FROM Distributor;";
	    		    		

	    		  ResultSet rs = statement.executeQuery(Query);

	    		  while(rs.next())
	    		    {
	    	  
	    		    	System.out.print("numDistributors:"+rs.getString(1));
	    		    				

	    		    } 
	    			}


	    public static void revenueCity()  throws SQLException{
	    	

	        
	    	String Query = "SELECT city, SUM(amount) AS revenue FROM Pays ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY city;";

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("City:"+rs.getString(1));
	    				System.out.print("\tRevenue :"+rs.getString(2));

	    			} 

	    }

	    
	    public static void revenueDistributor()  throws SQLException{
	    	

	        
	    	String Query = "SELECT name, SUM(amount) AS revenue FROM Pays INNER JOIN Distributor ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY Distributor.Account_no;";
	    		

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("accountNum:"+rs.getString(1));
	    				System.out.print("\tname :"+rs.getString(2));
	    				System.out.print("\trevenue :"+rs.getString(3));

	    			} 

	    }
	    
	    public static void revenueLocation()  throws SQLException{
	    	

	        
	    	String Query = "SELECT  location,  SUM(amount) AS revenue FROM Pays INNER JOIN Distributor ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY location;";
	    		

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("Location:"+rs.getString(1));
	    				System.out.print("\tRevenue :"+rs.getString(2));

	    			} 

	    }
	    
	    public static void personnelPayments()  throws SQLException{
	    	
	
	    	System.out.println("Enter the date of beginning");
	        String begindate = in.next();
	        System.out.println("Enter the date of ending");
	        String enddate = in.next();

	    	
	    	String Query = "SELECT SUM(Amount) AS payments FROM Compensate NATURAL JOIN Payments WHERE date IN (SELECT date FROM Payments WHERE date" + begindate+ "AND" +enddate+ ");";
	    		

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("payment:"+rs.getString(1));


	    			} 

	       }
	    
	    
	    public static void pubPayments()  throws SQLException{
	    	
	    	System.out.println("Enter the date of beginning");
	        String begindate = in.next();
	        System.out.println("Enter the date of ending");
	        String enddate = in.next();

	        
	    	String Query = " SELECT 'Book' AS type, SUM(amount )AS total_payments INNER JOIN Employee ON Authors.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= " + begindate+ " AND claim_date <= " + enddate+ " UNION ALL SELECT 'Editorial' AS type, SUM(amount) AS total_payments FROM Editors INNER JOIN Employee ON Editors.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= " + begindate+ " AND claim_date <= " + enddate+ " UNION ALL SELECT 'Article' AS type, SUM(amount) AS total_payments FROM Journalists INNER JOIN Employee ON Journalists.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= " + begindate+ "AND claim_date <= " +enddate+ ";";

	    	ResultSet rs = statement.executeQuery(Query);

	    			while(rs.next())
	    			{

	    				System.out.print("type:"+rs.getString(1));
	    				System.out.print("totalPayment:"+rs.getString(2));


	    			} 

	       }

	
}
