import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class App{
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/dshah4";
    // Put your oracle ID and password here

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    public static void main(String[] args) {
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
                case 1: method(conn); break;
                case 7: break;
            }
        }while(choice<=7 && choice>=1);
    }

    }
}