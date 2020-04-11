import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println("---------------------QUERIES----------------------");
            System.out.println("1: Add a distributor");
            System.out.println("\n\n Enter your choice.");

            choice = s.nextInt();
            switch(choice) {
                case 1: try {
                    newDistributor();
                }catch (SQLException e){
                    if (connection != null) {
                        try {
                            connection.rollback();
                        } catch(SQLException excep) {
                            excep.printStackTrace();
                        }
                    }
                }
                case 2: try {
                    showDistributor();
                }catch (SQLException e){
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
        String Query = "SELECT * FROM Distributor;";
        result = statement.executeQuery(Query);
        while (result.next()) {
          int account_no = result.getInt(1);
          char type = result.getString(2);
          String name = result.getString(3);
          String phone_no = result.getString(4);
          String contact_person = result.getString(5);
          String location = result.getString(6);
          double balance = result.getDouble(7);
          String city = result.getString(8);
            System.out.println(dist +
                               "\t" + account_no +
                               "\t" + type +
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
        System.out.println("Enter Account_no");
        int account_no = in.nextInt();
        System.out.println("Enter type: ('W' | 'B' | 'L')");
        char type = in.next().charAt(0);
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
        System.out.println("Enter city: ");
        String city = in.nextLine();

        String Query = "INSERT INTO Distributor VALUES (" +account_no+ ","
         +type+ "," +name+ "," +phone_no+ "," +contact_person+ ","
         +location+ "," +balance+ "," +city + ")";
        statement.executeUpdate(Query);



    }


}
