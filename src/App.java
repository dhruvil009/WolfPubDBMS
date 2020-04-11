import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;


public class App{
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
            System.out.println("1.");
            System.out.println("7. Go Back");
            System.out.println("\n\n Enter your choice.");

            choice = s.nextInt();
            switch(choice) {
                case 1: try {
                    NewPublication();
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

    public static void NewPublication() throws SQLException {
        int pubid = in.nextInt();
        String title = in.nextLine();
        Boolean type = in.nextBoolean();
        String audience = in.nextLine();

        String Query = "INSERT INTO Publications VALUES ("+ pubid +", "+title+", "+type+", "+audience+")";
        statement.executeUpdate(Query);
    }


}