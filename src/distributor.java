import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.*;

public class distributor{
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
            System.out.println("\n\n---------------- Distributor Menu ------------------");
            System.out.println("1: Sign up as a distributor");
            System.out.println("2: Show all distributors");
            System.out.println("3: Delete my distributor account");
            System.out.println("4: Update my distributor account");
            System.out.println("5: Place an order");
            System.out.println("6: Update balance on receipt of payment");
            System.out.println("7: Billing");
            System.out.println("8: Send payment");
            System.out.println("9: Back");

            System.out.println("\n\n Enter your choice.");

            choice = s.nextInt();
            switch(choice) {
                case 1: try {                               // Add a distributor
                    newDistributor();
                    break;
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
                case 2: try {                               // Show all the distributors
                    showDistributor();
                    break;
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
                case 3: try {                               // Delete a distributor
                    deleteDistributor();
                    break;
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
                case 4: try {                               // Update a distributor
                    updateDistributor();
                    break;
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
                case 5: try {                               // Update a distributor
                    placeOrder();
                    break;
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
                case 6: try {                               // Update a distributor
                    updateBalance();
                    break;
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
                case 7: try {                               // Update a distributor
                    billDistributor();
                    break;
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
                case 8: try {                               // Update a distributor
                    receivePayment();
                    break;
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
                case 9:
                  break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while(choice <= 8 && choice >= 1);
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

    /*
    * shows all the rows in the Distributor table.
    * column names: Account_no, type, name, phone_no, contact_person, location, balance, city)
    */
    public static void showDistributor() throws SQLException {
        //String Query = "SELECT * FROM Distributor;";
        PreparedStatement showAllDistributors = null;
        showAllDistributors = connection.prepareStatement("SELECT * FROM Distributor;");
        result = showAllDistributors.executeQuery();
        //if (!result.next())
        //  System.out.println("Distributor is empty.");
        while (result.next()) {
          System.out.println();
          int account_no = result.getInt(1);
          String type = result.getString(2);
          String name = result.getString(3);
          String phone_no = result.getString(4);
          String contact_person = result.getString(5);
          String location = result.getString(6);
          double balance = result.getDouble(7);
          String city = result.getString(8);
          System.out.println(
                               account_no +
                               "\t" + type +
                               "\t" + name +
                               "\t" + phone_no +
                               "\t" + contact_person +
                               "\t" + location +
                               "\t" + balance +
                               "\t" + city);
        }
    }

    /*
    * A menu guides an admin through adding a new Distributor to the Distributors table.
    * The column names are Account_no, type, name, phone_no, contact_person, location, balance, city
    */
    public static void newDistributor() throws SQLException {
        // Distributor(Account_no, type, name, phone_no, contact_person, location, balance, city)
        try {
            System.out.println("Enter Account_no");
            int account_no = in.nextInt();
            in.nextLine();
            System.out.println("Enter type: ('W' | 'B' | 'L')");
            String type = "" + in.nextLine().charAt(0);
            System.out.println("Enter name of distributor: ");
            String name = in.nextLine();
            System.out.println("Enter phone_no: i.e. 555-555-5555");
            String phone_no = in.nextLine();
            System.out.println("Enter contact_person name: ");
            String contact_person = in.nextLine();
            System.out.println("Enter location: ");
            String location = in.nextLine();
            System.out.println("Enter balance: ");
            double balance = in.nextDouble();
            in.nextLine();
            System.out.println("Enter city: ");
            String city = in.nextLine();


            PreparedStatement pstmt = null;
            pstmt = connection.prepareStatement("INSERT INTO Distributor VALUES (" +account_no+ ",\""
             +type+ "\",\"" +name+ "\",\"" +phone_no+ "\",\"" +contact_person+ "\",\""
             +location+ "\"," +balance+ ",\"" +city + "\")");

            pstmt.executeUpdate();
      }
      catch( InputMismatchException e) {
          e.printStackTrace();
          System.out.println("Returning to main menu!");
          return;
      }
    }

    /*
    * Prompts the user to delete a distributor by giving the account number.
    */
    public static void deleteDistributor() throws SQLException {
        System.out.println("Enter Account_no of Distributor to delete:");
        int account_no = in.nextInt();
        in.nextLine();

        PreparedStatement pstmt = null;
        pstmt = connection.prepareStatement("DELETE FROM Distributor WHERE Account_no = ? ;");
        pstmt.setInt(1, account_no);
        pstmt.executeUpdate();


    }

    /*
    * Guides the admin through updating the values in a row. In order for a distributor
    * to be able to update himself need some sort of protection against updating balance.
    */
    public static void updateDistributor() throws SQLException {
        System.out.println("Enter Account_no of Distributor to update:");
        int account_no = in.nextInt();
        in.nextLine();
        // Show the user the values of given accout number
        PreparedStatement showDistributorStmt = null;
        showDistributorStmt = connection.prepareStatement("SELECT * FROM Distributor WHERE Account_no = ?;");
        showDistributorStmt.setInt(1, account_no);
        result = showDistributorStmt.executeQuery();
        //if (!result.next())
        //  System.out.println("Distributor is empty.");
        String type = "Dummy";
        String name = "Dummy";
        String phone_no = "Dummy";
        String contact_person = "Dummy";
        String location = "Dummy";
        double balance = 0;
        String city = "Dummy";
        PreparedStatement update = null;
        boolean foundAccount = false;
        while (result.next()) {
            foundAccount = true;
            System.out.println();
            account_no = result.getInt(1);
            type = result.getString(2);
            name = result.getString(3);
            phone_no = result.getString(4);
            contact_person = result.getString(5);
            location = result.getString(6);
            balance = result.getDouble(7);
            city = result.getString(8);
        }
        if (!foundAccount) {
          System.out.println("Couldn't find account: " + account_no);
          return;
        }
        int choice;
        do {
            System.out.println("\n\n---------------- Update Menu ------------------");
            // Account_no, type, name, phone_no, contact_person, location, balance, city
            System.out.println( "Acnt_no \t type \t name \t phone_no \t contact \t location \t balance \t city");
            System.out.println( account_no + "\t\t" + type + "\t" + name +  "\t\t" + phone_no +  "\t\t" + contact_person +
                                 "\t\t" + location +  "\t\t" + balance +  "\t" + city);
            System.out.println("1: Update type");
            System.out.println("2: Update name");
            System.out.println("3: Update phone number");
            System.out.println("4: Update contact person");
            System.out.println("5: Update location");
            System.out.println("6: Update balance");
            System.out.println("7: Update city");
            System.out.println("8: Exit");
            System.out.println("\n\n Enter your choice.");
            /*
            System.out.println("Enter city: ");
            String city = in.nextLine();
            */
            update = connection.prepareStatement
    ("UPDATE Distributor SET type = ?, name = ?, phone_no = ?, contact_person = ?, location = ?, balance = ?, city = ? WHERE Account_no = ?");
            update.setString(1, type);
            update.setString(2, name);
            update.setString(3, phone_no);
            update.setString(4, contact_person);
            update.setString(5, type);
            update.setDouble(6, balance);
            update.setString(7, type);
            update.setInt(8, account_no);
            choice = in.nextInt();
            in.nextLine();
            switch(choice) {
                case 1:
                  System.out.println("Enter type: ('W' | 'B' | 'L')");
                  type = "" + in.nextLine().charAt(0);
                  update.setString(1, type);
                  update.executeUpdate();
                  break;
                case 2:
                  System.out.println("Enter name of distributor: ");
                  name = in.nextLine();
                  update.setString(2, name);
                  update.executeUpdate();
                  break;
                case 3:
                  System.out.println("Enter phone_no: i.e. 555-555-5555");
                  phone_no = in.nextLine();
                  update.setString(3, phone_no);
                  update.executeUpdate();
                  break;
                case 4:
                  System.out.println("Enter contact_person name: ");
                  contact_person = in.nextLine();
                  update.setString(4, contact_person);
                  update.executeUpdate();
                  break;
                case 5:
                  System.out.println("Enter location: ");
                  location = in.nextLine();
                  update.setString(5, type);
                  update.executeUpdate();
                  break;
                case 6:
                  System.out.println("Enter balance: ");
                  balance = in.nextDouble();
                  in.nextLine();
                  update.setDouble(6, balance);
                  update.executeUpdate();
                  break;
                case 7:
                  System.out.println("Enter city: ");
                  city = in.nextLine();
                  update.setString(7, type);
                  update.executeUpdate();
                  break;
                case 8:
                  choice = 8;
                  break;
              }
            } while (choice != 8);

    } // End updateDistributor()

    /*
    * placeOrder() Guides a distributor through making an order.
    */
    public static void placeOrder() throws SQLException {
        // Ideally this part will be figured out based on the user.
        System.out.println("Which distributor is this order for? (Enter the Account_no)");
        int account_no = in.nextInt();
        in.nextLine();

        // Create an order
        // Need order_id, order_date, delivery_status, shipping_cost
        System.out.println("Enter order_id:");
        int order_id = in.nextInt();
        in.nextLine();

        System.out.println("Enter date for order: (Use yyyy-mm-dd format)");
        String order_date = in.nextLine();

        System.out.println("Enter shipping cost: ");
        double shipping_cost = in.nextDouble();
        in.nextLine();

        // Declare the statements for editing an order
       PreparedStatement newOrderStmt = null;
       PreparedStatement addEditionStmt = null;
       PreparedStatement removeEditionStmt = null;
       PreparedStatement addIssueStmt = null;
       PreparedStatement removeIssueStmt = null;

        // Set up a transaction for creating an order. Don't let
        // halfway completed an orders be submitted to the db.
        // Also allow the user to cancel the order.
        try {
            connection.setAutoCommit(false);

            newOrderStmt = connection.prepareStatement("INSERT INTO  Orders VALUES(?, ?, ?, ?);");
            newOrderStmt.setInt(1, order_id);
            newOrderStmt.setDate(2, java.sql.Date.valueOf(order_date));
            newOrderStmt.setInt(3, 0);
            newOrderStmt.setDouble(4, shipping_cost);
            newOrderStmt.executeUpdate();


            addEditionStmt = connection.prepareStatement(
                "INSERT INTO Order_for_Edition VALUES(?, ?, ?, ?, ?);");
            addEditionStmt.setInt(1, account_no);
            String isbn = "";
            addEditionStmt.setInt(3, order_id);
            double price = 0;
            int quantity = 0;

            removeEditionStmt = connection.prepareStatement(
            "DELETE FROM Order_for_Edition WHERE Account_no = ? AND ISBN = ? AND order_id = ?"
            );

            addIssueStmt = connection.prepareStatement(
                "INSERT INTO Order_for_Issues VALUES(?, ?, ?, ?, ?, ?);");
            addIssueStmt.setInt(1, account_no);
            int Pub_Id = 0;
            String issue_date = "";
            addIssueStmt.setInt(4, order_id);
            price = 0;
            quantity = 0;

            removeIssueStmt = connection.prepareStatement(
            "DELETE FROM Order_for_Issues WHERE Account_no = ? AND Pub_Id = ? AND issue_date = ? AND order_id = ?"
            );

            // Let the Admin or Distributor add/remove Editions or Issues to the order as long as they please.
            int choice;
            do {
                System.out.println("\n\n---------------- Order Menu ------------------");
                System.out.println("1: Add edition to order");
                System.out.println("2: Remove edition from order");
                System.out.println("3: Add issue to order");
                System.out.println("4: Remove issue from order");
                System.out.println("5: Submit order and exit");
                System.out.println("6: Cancel order and exit");

                choice = in.nextInt();
                in.nextLine();
                switch(choice) {
                  case 1:                                   // Add edition
                    System.out.println("Enter ISBN");
                    isbn = in.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter total price: ");
                    price = in.nextDouble();
                    in.nextLine();
                    addEditionStmt.setString(2, isbn);
                    addEditionStmt.setDouble(4, price);
                    addEditionStmt.setInt(5, quantity);
                    addEditionStmt.executeUpdate();
                    break;

                  case 2:                                 // Remove edition
                    System.out.println("Enter ISBN");
                    isbn = in.nextLine();
                    removeEditionStmt.setInt(1, account_no);
                    removeEditionStmt.setString(2, isbn);
                    removeEditionStmt.setInt(3, order_id);
                    removeEditionStmt.executeUpdate();
                    break;

                  case 3:                                   // Add issue
                    System.out.println("Enter Pub_Id");
                    Pub_Id = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter issue_date: (Use format yyyy-mm-dd)");
                    issue_date = in.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter total price: ");
                    price = in.nextDouble();
                    in.nextLine();
                    addIssueStmt.setInt(2, Pub_Id);
                    addIssueStmt.setDate(3, java.sql.Date.valueOf(issue_date));
                    addIssueStmt.setInt(4, order_id);
                    addIssueStmt.setDouble(5, price);
                    addIssueStmt.setInt(6, quantity);
                    addIssueStmt.executeUpdate();
                    break;

                  case 4:                                 // Remove issue
                    System.out.println("Enter Pub_Id");
                    Pub_Id = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter issue_date: (Use format yyyy-mm-dd)");
                    issue_date = in.nextLine();
                    removeIssueStmt.setInt(1, account_no);
                    removeIssueStmt.setInt(2, Pub_Id);
                    removeIssueStmt.setDate(3, java.sql.Date.valueOf(issue_date));
                    removeIssueStmt.setInt(4, order_id);
                    removeIssueStmt.executeUpdate();
                    break;
                  case 5:                    // Order is complete so commit it to DB
                    connection.commit();
                    System.out.println("Order submitted!");
                    break;
                  case 6:                 // User wants to cancel order so rollback
                                          // to state of DB before order began.
                    connection.rollback();
                    System.out.println("Order cancelled!");
                    break;
                }


            } while (choice < 5);
        } catch (SQLException e ) {
            e.printStackTrace();
            if (connection != null) {   // If something goes wrong perform a rollback.
                try {
                    System.err.print("Order is cancelled. Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
          } finally {                     // Close all PreparedStatement once finished.
            if (newOrderStmt != null) {
                newOrderStmt.close();
            }
            if (addEditionStmt != null) {
                addEditionStmt.close();
            }
            if (removeEditionStmt != null) {
                removeEditionStmt.close();
            }
            if (addIssueStmt != null) {
                addIssueStmt.close();
            }
            if (addIssueStmt != null) {
                addIssueStmt.close();
            }
            connection.setAutoCommit(true);
          }

    }


    /*
    * Let an admin update the balance of a distributor.
    */
    public static void updateBalance() throws SQLException {

        System.out.println("Which distributor are you updating? (Enter the Account_no)");
        int account_no = in.nextInt();
        in.nextLine();

        PreparedStatement currentBalStmt = null;
        currentBalStmt = connection.prepareStatement("SELECT balance FROM Distributor WHERE Account_no = ?;");
        currentBalStmt.setInt(1, account_no);
        result = currentBalStmt.executeQuery();
        double bal = 0;
        boolean notEmpty = false;
        while (result.next()) {
          bal = result.getDouble(1);
          notEmpty = true;
        }
        if (!notEmpty) {
          System.out.println("Couldn't find that distributor!");
          return;
        }
        System.out.println("Current balance is: " + bal);
        System.out.println("What would you like the new balance to be?");
        double newBalance = in.nextDouble();
        in.nextLine();
        PreparedStatement setBalanceStmt = null;
        // ("UPDATE Distributor SET type = ?, name = ?, phone_no = ?, contact_person = ?, location = ?, balance = ?, city = ? WHERE Account_no = ?");
        setBalanceStmt = connection.prepareStatement("UPDATE Distributor SET balance = ? WHERE Account_no = ?;");
        setBalanceStmt.setDouble(1, newBalance);
        setBalanceStmt.setInt(2, account_no);
        setBalanceStmt.executeUpdate();
        System.out.println("Balance updated successfully.");

    }

    /*
    * Let an admin calculate the bill for an order.
    */
    public static void billDistributor() throws SQLException {
      System.out.println("Which order are you billing? (Enter the order_id)");
      int order_id = in.nextInt();
      in.nextLine();
      PreparedStatement billStmt = null;
      billStmt = connection.prepareStatement("SELECT Account_no, name, order_id, SUM(price) + MAX(shipping_cost) AS bill_amount " +
      " FROM (  SELECT Distributor.Account_no AS Account_no, name, Orders.order_id, price, shipping_cost " +
          " FROM Order_for_Edition  INNER JOIN Distributor ON Order_for_Edition.Account_no = Distributor.Account_no " +
              "INNER JOIN Orders ON Orders.order_id = Order_for_Edition.order_id   WHERE Orders.order_id = ? " +
          " UNION ALL  SELECT Distributor.Account_no AS Account_no, name, Orders.order_id, price, shipping_cost " +
          "FROM Order_for_Issues INNER JOIN Distributor on Order_for_Issues.Account_no = Distributor.Account_no " +
          "INNER JOIN Orders ON Orders.order_id = Order_for_Issues.order_id   WHERE Orders.order_id = ?  ) t");
      billStmt.setInt(1, order_id);
      billStmt.setInt(2, order_id);
      result = billStmt.executeQuery();
      double bill = 0;
      boolean notEmpty = false;
      int account_no = 0;
      while (result.next()) {
        account_no = result.getInt(1);
        bill = result.getDouble(4);
        notEmpty = true;
      }
      if (!notEmpty) {
        System.out.println("There is no order with " + order_id + " id.");
        return;
      }

      System.out.println("Account_no " + account_no + " owes " + bill + " for order" + order_id + ".");
      System.out.println("To change their balance use option 3 or 6 in main menu. ");

    }

    /*
    * Let an admin enter receipt of payment
    */
    public static void receivePayment() throws SQLException {
      System.out.println("Which distributor is the payment from? (Enter Account_no)");
      int account_no = in.nextInt();
      in.nextLine();

      System.out.println("What amount?");
      double amount = in.nextDouble();
      in.nextLine();

      PreparedStatement enterPayStmnt = null;
      PreparedStatement enterIntoPayTableStmnt = null;
      PreparedStatement getDistBalance = null;
      PreparedStatement updateBalance = null;

      // Come up with a PaymentId that isn't already in db.
      Random rand = new Random();
      int paymentId = 1 + rand.nextInt(2100000000);
      java.util.Date utilDate = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

      // Receive Payment, and update the balance of distibutor as one transaction.
      try {
          connection.setAutoCommit(false);
          // Insert something into payments table (PaymentId, Amount, date, type, claim_date)
          enterPayStmnt = connection.prepareStatement("INSERT INTO Payments VALUES(?, ?, ?, ?, ?)");
          enterPayStmnt.setInt(1, paymentId);
          enterPayStmnt.setDouble(2, amount);
          enterPayStmnt.setDate(3, sqlDate);
          enterPayStmnt.setInt(4, 1);         // I'm guessing 1 signals incoming payments?
          enterPayStmnt.setDate(5, sqlDate);

          // Enter a row in the Pays table.
          enterIntoPayTableStmnt = connection.prepareStatement("INSERT INTO Pays VALUES(?, ?)");
          enterIntoPayTableStmnt.setInt(1, paymentId);
          enterIntoPayTableStmnt.setInt(2, account_no);

          // Update the distributor's balance
          // First find out what the balance is and then decrease it by the recieved payment.
          getDistBalance = connection.prepareStatement("SELECT balance FROM Distributor WHERE Account_no = ?;");
          getDistBalance.setInt(1, account_no);

          updateBalance = connection.prepareStatement("UPDATE Distributor SET balance = ? WHERE Account_no = ?");

          enterPayStmnt.executeUpdate();
          enterIntoPayTableStmnt.executeUpdate();
          result = getDistBalance.executeQuery();
          result.next();
          double currBalance = result.getDouble(1);
          System.out.println("Current balance: " + currBalance);
          currBalance -= amount;
          System.out.println("Current balance after subtraction: " + currBalance);
          updateBalance.setDouble(1, currBalance);
          updateBalance.setInt(2, account_no);
          updateBalance.executeUpdate();
          connection.commit();
      } catch (SQLException e ) {   // If something goes wrong then rollback.
        e.printStackTrace();
        if (connection != null) {
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
      } finally {           // Close the PreparedStatement and turn auto commit back on.
        if (enterPayStmnt != null) {
            enterPayStmnt.close();
        }
        if (enterIntoPayTableStmnt != null) {
            enterIntoPayTableStmnt.close();
        }
        if (getDistBalance != null) {
            enterPayStmnt.close();
        }
        if (enterIntoPayTableStmnt != null) {
            updateBalance.close();
        }
        connection.setAutoCommit(true);
      }

    }



}
