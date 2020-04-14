import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
							System.out.println("10. Back");

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
	                    break;
	                case 2:try {
	                	revenuePublishing();
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
	                break;
	                case 3: try {
	                	costPublishing();
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
	                break;

	                case 4: try {
	                	distributionNum();
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
	                break;

	                case 5:try {
	                	revenueCity();
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
	                break;

	                case 6:try {
	                	revenueDistributor();
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
	                break;

	                case 7:try {
	                	revenueLocation();
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
	                break;

	                case 8:try {
	                	personnelPayments();
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
	                break;
	                case 9:try {
	                	pubPayments();
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
	                break;
									case 10:
										break;

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



// shows the reports of the orders of each month of the distributors
	    public static void monthlyReport()  throws SQLException{


	    	PreparedStatement monthlyrep = null;
	    	monthlyrep = connection.prepareStatement("SELECT Distributor.Account_no, Month(Orders.order_date) Month, Year(Orders.order_date) Year, sum(oi.price) totalprice, sum(oi.quantity) totalquantity, oi.Pub_Id, Publications.title FROM Distributor NATURAL JOIN Order_for_Issues oi NATURAL JOIN Orders Inner JOIN Publications where oi.Pub_Id =Publications.Pub_Id GROUP BY Distributor.Account_no, oi.Pub_Id, Month, Year UNION SELECT Distributor.Account_no, Month(Orders.order_date) Month,Year(Orders.order_date) Year, sum(oe.price) totalprice, sum(oe.quantity) totalquantity, Publications.Pub_Id, Publications.title FROM Distributor NATURAL JOIN Order_for_Edition oe NATURAL JOIN Orders Inner JOIN Publications Inner Join Of where oe.ISBN = Of.ISBN AND Of.Pub_Id = Publications.Pub_Id GROUP BY Distributor.Account_no, Publications.Pub_Id,Month, Year ORDER BY Account_no;");
	    	result = monthlyrep.executeQuery();
	    	System.out.println(
                    "Account_no" +
                    "\t" + "Month" +
                    "\t" + "Year" +
                    "\t" + "total price" +
                    "\t" + "quantity" +
                    "\t" + "Pub Id" +
                    "\t" + "title");
	    	while(result.next())
	    	{
	    		 System.out.println();
		          int Account_no = result.getInt(1);
		          int month = result.getInt(2);
		          int year = result.getInt(3);
		          Double totalPrice = result.getDouble(4);
		          int quantity = result.getInt(5);
		          int PubId = result.getInt(6);
		          String title = result.getString(7);
		          System.out.println(
                          Account_no +
                          "\t\t" + month +
                          "\t" + year +
                          "\t" + totalPrice +
                          "\t\t" + quantity +
                          "\t\t" + PubId +
                          "\t" + title );

				}


   }

//shows the revenue of the publication house in each month

	    public static void revenuePublishing()  throws SQLException{

	    	PreparedStatement revenuep = null;

	    	revenuep = connection.prepareStatement("SELECT Month(t.claim_date) as month, Year(t.claim_date) as year, SUM(t.Amount) as revenue FROM (SELECT amount,  claim_date, Pays.PaymentId FROM Pays INNER JOIN Payments ON Pays.PaymentId = Payments.PaymentId) t GROUP BY Month, Year;");

	        result = revenuep.executeQuery();

	        System.out.println(
                    "Month" +
                    "\t" + "Year" +
                    "\t" + "Revenue" );


	    				while (result.next()) {
		    		          System.out.println();
		    		          int Month = result.getInt(1);
		    		          int Year = result.getInt(2);
		    		          double Revenue = result.getDouble(3);

	    				 System.out.println(
	                               Month +
	                               "\t" + Year +
	                               "\t" + Revenue );
	    				}



	    }

// shows the costs of the publication house in each month

	    public static void costPublishing()  throws SQLException{


	    	PreparedStatement costp = null;
	    	costp = connection.prepareStatement("SELECT YEAR(date) as Year, MONTH(date) as Month, SUM(t.cost) AS total_expenses FROM( SELECT shipping_cost AS cost, order_date AS date FROM Orders UNION ALL SELECT amount AS cost, claim_date AS date FROM Compensate INNER JOIN Payments ON Payments.PaymentId = Compensate.PaymentId) t GROUP BY MONTH, YEAR;");

	    	result = costp.executeQuery();
	    	System.out.println(
	    			"year" +
                    "\t" + "Month" +
                    "\t" + "Expense" );

	    			while(result.next()){

	    				   System.out.println();
		    		          int year = result.getInt(1);
		    		          int month = result.getInt(2);
		    		          double totalExpense = result.getDouble(3);


	    				 System.out.println(
	                               year +
	                               "\t" + month +
	                               "\t" + totalExpense );


	    			}
	    }

// calculate the number of the distributors in the database
	    public static void distributionNum()  throws SQLException{


	  	    	PreparedStatement distnum = null;
		    	distnum = connection.prepareStatement("SELECT COUNT(*) AS num_distributors FROM Distributor;");

		    	result = distnum.executeQuery();
		    	System.out.println("Number of Distributors:");

		    	while(result.next()){

 				   System.out.println();
	    		          int num = result.getInt(1);



 				 System.out.println(num );


 			}
	    			}

//shows the distributor revenues in each city
	    public static void revenueCity()  throws SQLException{

	    	PreparedStatement cityrev = null;


	    	cityrev = connection.prepareStatement("SELECT city, SUM(amount) AS revenue FROM Pays INNER JOIN Distributor ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY city;");
	    	result = cityrev.executeQuery();


	    	System.out.println(
	    			"City" +
                    "\t" + "Revenue" );

	    	while(result.next()){

				   System.out.println();
 		          String city = result.getString(1);
 		          Double Revenue = result.getDouble(2);


				 System.out.println(
                        city +
                        "\t" + Revenue );


			}


	    }

	    // shows each distributor revenue
	    public static void revenueDistributor()  throws SQLException{


	    	PreparedStatement distrev = null;


	    	distrev = connection.prepareStatement("SELECT Distributor.Account_no, name, SUM(amount) AS revenue FROM Pays INNER JOIN Distributor ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY Distributor.Account_no;");
	    	result = distrev.executeQuery();


	    	System.out.println(
	    			"Account_no" +
                    "\t" + "name" +
                    "\t" + "revenue");

	    	while(result.next()){

				   System.out.println();
 		          int accountNo = result.getInt(1);
 		          String name = result.getString(2);
 		          Double Revenue = result.getDouble(3);


				 System.out.println(
                        accountNo +
                        "\t" + name+
                        "\t" + Revenue );


			}



	    }
	    // shows the distributors revenue in each location
	    public static void revenueLocation()  throws SQLException{


	    	PreparedStatement locrev = null;


	    	locrev = connection.prepareStatement("SELECT  location,  SUM(amount) AS revenue FROM Pays INNER JOIN Distributor ON Pays.Account_no  = Distributor.Account_no INNER JOIN Payments ON Pays.PaymentId=Payments.PaymentId GROUP BY location;");
	    	result = locrev.executeQuery();


	    	System.out.println(
	    			"Location" +
                    "\t" + "Revenue" );

	    	while(result.next()){

				   System.out.println();
 		          String location = result.getString(1);
 		          Double Revenue = result.getDouble(2);


				 System.out.println(
                        location +
                        "\t" + Revenue );


			}


	    }



	    // shows the payments of the authors, editor, and journalists in a given period
	    public static void personnelPayments()  throws SQLException{


	    	System.out.println("Enter the date of beginning (i.e 2020-01-01):  ");
	        String begindate = in.next();
	        System.out.println("Enter the date of ending (i.e 2020-01-01):  ");
	        String enddate = in.next();


	        PreparedStatement ppayment = null;
	        ppayment= connection.prepareStatement("SELECT SUM(Amount) AS payments FROM Compensate NATURAL JOIN Payments WHERE date IN (SELECT date FROM Payments WHERE date > ? AND date < ?);" );
	        ppayment.setDate(1, java.sql.Date.valueOf(begindate));
	        ppayment.setDate(2, java.sql.Date.valueOf(enddate));

	    	result = ppayment.executeQuery();
	    	System.out.println("Total payment to personnel:");

	    	while(result.next()){

				   System.out.println();
    		          Double pay = result.getDouble(1);



				 System.out.println(pay);


			}


	       }

	    //shows the payments made for each publication type
	    public static void pubPayments()  throws SQLException{




				System.out.println("Enter the date of beginning (i.e 2020-01-01):  ");
	        String begindate = in.next();
	        System.out.println("Enter the date of ending (i.e 2020-01-01):  ");
	        String enddate = in.next();


	        PreparedStatement pubpayment = null;
	        pubpayment= connection.prepareStatement("SELECT 'Book' AS type, SUM(amount )AS total_payments FROM Authors INNER JOIN Employee ON Authors.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= ? AND claim_date <= ? UNION ALL SELECT 'Editorial' AS type, SUM(amount) AS total_payments FROM Editors INNER JOIN Employee ON Editors.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= ? AND claim_date <= ? UNION ALL SELECT 'Article' AS type, SUM(amount) AS total_payments FROM Journalists INNER JOIN Employee ON Journalists.Staff_Id = Employee.Staff_Id INNER JOIN Compensate ON Employee.Staff_Id = Compensate.Staff_Id INNER JOIN Payments ON Compensate.PaymentId = Payments.PaymentId WHERE claim_date >= ? AND claim_date <= ?" );
	        pubpayment.setDate(1, java.sql.Date.valueOf(begindate));
	        pubpayment.setDate(2, java.sql.Date.valueOf(enddate));
	        pubpayment.setDate(3, java.sql.Date.valueOf(begindate));
	        pubpayment.setDate(4, java.sql.Date.valueOf(enddate));
	        pubpayment.setDate(5, java.sql.Date.valueOf(begindate));
	        pubpayment.setDate(6, java.sql.Date.valueOf(enddate));

	    	result = pubpayment.executeQuery();
	    	System.out.println("type" +
                    "\t" +"Total payments for publications:");

	    	while(result.next()){
	    		  System.out.println();
	    		  String type = result.getString(1);
		          Double pay = result.getDouble(2);
				  System.out.println( type + "\t" + pay);



			}




	       }


}
