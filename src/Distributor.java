import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.*;

public class Distributor{
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/mtrawic";
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
            System.out.println("\n\n---------------- Main Menu ------------------");
            System.out.println("1: Add a distributor");
            System.out.println("2: Show all distributors");
            System.out.println("3: Delete distributor");
            System.out.println("4: Update a distributor");
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
                    break;
                case 7: break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while(choice<=7 && choice>=1);
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

        String user = "mtrawic";
        String password = "aplus";

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
    * A menu guides you through adding a new Distributor to the Distributors table.
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
    * Guides the user through updating the values in a row.
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

    }


}
