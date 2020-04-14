//Create classes admin, staff, and distributor
// Make objects of those classes and then use them to call all the functions of the class
// Mention these object calls in switch statements



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;

class Menu{
  private static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    int choice;
    do{
      System.out.println("--------------------------------");
      System.out.println("Welcome to the WolfPub Database!");
      System.out.println("--------------------------------");
      System.out.println("Select your identity");
      System.out.println("1: Admin");
      System.out.println("2: Staff");
      System.out.println("3: Distributor");
      System.out.println("4: Exit");

      choice = sc.nextInt();
      int choice2;
      switch(choice) {
          case 1:
            do{
              System.out.println("Select the Task/Operation");
              System.out.println("--------------------------");
              System.out.println("1. Editing and Publishing");
              System.out.println("2. Production of a new edition or of an issue of a publication:");
              System.out.println("3. Distribution");
              System.out.println("4. Reports");
              System.out.println("5. Show tables");
              System.out.println("6. Back");
              choice2 = sc.nextInt();
              switch(choice2){
                case 1:
                    App.main(null);
                    break;
                case 2:
                    tasksOps.main(null);
                    break;
                case 3:
                    distributor.main(null);
                    break;
                case 4:
                    report.main(null);
                    break;
                case 5:
                    show.main(null);
                    break;
                case 6:
                    break;
                }
              }while(choice2<=5 && choice2 >=1);
              break;
          case 2:
          do{
            System.out.println("Select the Task/Operation");
            System.out.println("--------------------------");
            System.out.println("1. Editing and Publishing");
            System.out.println("2. Production of a new edition or of an issue of a publication:");
            System.out.println("5. Back");
            choice2 = sc.nextInt();
            switch(choice2){
              case 1:
                  App.main(null);
                  break;
              case 2:
                  tasksOps.main(null);
                  break;
              case 3:
                  break;
                }

            }while(choice2<=2 && choice2 >=1);

              break;
          case 3:
            do{
              System.out.println("Select the Task/Operation");
              System.out.println("--------------------------");
              System.out.println("4. Reports");
              System.out.println("5. Back");
              choice2 = sc.nextInt();
              switch(choice2){
                case 1:
                    report.main(null);
                    break;
                case 2:
                    break;
                }
              }while(choice2==1);
              break;
          case 4:
              break;
          default:
              throw new IllegalStateException("Unexpected value: " + choice);
      }
    }while(choice <=3 && choice >=1);


  }
}
