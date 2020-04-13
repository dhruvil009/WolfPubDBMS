import org.mariadb.jdbc.internal.util.SqlStates;

import java.sql.*;

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
        int choice;
        do {
            System.out.println("---------------------QUERIES----------------------");
            System.out.println("1. Create New Publication");
            System.out.println("2. Update Publication");
            System.out.println("3. Assign Editor");
            System.out.println("4. See Publications You are responsible for");
            System.out.println("5. Create New Chapter");
            System.out.println("6. Delete Chapter");
            System.out.println("7. Create New Article");
            System.out.println("8. Delete Article");
            System.out.println("9. Go Back");
            System.out.println("\n\n Enter your choice.");

            choice = in.nextInt();
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
                case 2: try {
                    UpdatePublication();
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
                case 3: try {
                    AssignEditor();
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
                case 4: try {
                    ResponsiblePublicationsInfo();
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
                    case 5: try {
                        addChapter();
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
                    case 6: try {
                        removeChapter();
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
                    case 7: try {
                        addArticle();
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
                    case 8: try {
                        removeArticle();
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

                case 9: break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while(choice<=8 && choice>=1);
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
        System.out.println("Enter Pub Id");
        int pubid = in.nextInt();
        in.nextLine();
        System.out.println("Enter title");
        String title = in.nextLine();
        System.out.println("Is it a periodical?");
        Boolean type = in.nextBoolean();
        in.nextLine();
        System.out.println("Enter audience");
        String audience = in.nextLine();
        PreparedStatement statement=connection.prepareStatement("INSERT into Publications VALUES(?, ?, ?, ?);");
        statement.setInt(1,pubid);//1 specifies the first parameter in the query
        statement.setString(2, title);
        statement.setBoolean(3,type);//1 specifies the first parameter in the query
        statement.setString(4, audience);
        int i=statement.executeUpdate();
        System.out.println(i+" records inserted in Publications");

        if(type){
          System.out.println("Enter periodicity: ");
          String p = in.next();
          System.out.println("Enter type/genre: ");
          String t = in.next();
          statement=connection.prepareStatement("INSERT into Periodicals VALUES(?, ?, ?);");
          statement.setInt(1,pubid);
          statement.setString(2, p);
          statement.setString(3, t);
          i=statement.executeUpdate();
          System.out.println(i+" records inserted in  Periodicals");
        }
        else{
          System.out.println("Enter Publication Date : ");
          String date = in.next();
          statement=connection.prepareStatement("INSERT into Books VALUES(?, ?);");
          statement.setInt(1,pubid);
          statement.setDate(2, java.sql.Date.valueOf(date));
          i=statement.executeUpdate();
          System.out.println(i+" records inserted in Books");
        }

    }

    public static void UpdatePublication() throws SQLException {
        System.out.println("Enter Pub Id");
        int pubid = in.nextInt();
        in.nextLine();
        System.out.println("Is it a periodical?");
        boolean flag = in.nextBoolean();
        in.nextLine();
        System.out.println("Enter the new title");
        String title = in.nextLine();
        System.out.println("Enter the new audience");
        String audience = in.nextLine();
        PreparedStatement statement;
        statement = connection.prepareStatement("UPDATE Publications SET Title = ?, Audience = ? WHERE Pub_Id = ?");
        statement.setString(1, title);
        statement.setString(2, audience);
        statement.setInt(3, pubid);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated in  Publications");


        if(flag){
          System.out.println("Enter new periodicity: ");
          String p = in.next();
          System.out.println("Enter new type/genre: ");
          String t = in.next();
          statement=connection.prepareStatement("UPDATE Periodicals SET periodicity = ?, type = ? WHERE Pub_Id = ?");
          statement.setString(1, p);
          statement.setString(2, t);
          statement.setInt(3, pubid);
          i=statement.executeUpdate();
          System.out.println(i+" records updated in  Periodicals");
        }
        else{
          System.out.println("Enter new Publication Date : ");
          String date = in.next();
          statement=connection.prepareStatement("UPDATE Books SET Publication_Date = ? WHERE Pub_Id = ?");
          statement.setDate(1,java.sql.Date.valueOf(date));
          statement.setInt(2, pubid);
          i=statement.executeUpdate();
          System.out.println(i+" records updated in Books");
        }
    }

    public static void AssignEditor() throws SQLException {
        System.out.println("Enter Pub Id");
        int pub_id = in.nextInt();
        System.out.println("Enter Staff Id of Editor");
        int ed_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("INSERT into Has VALUES(?, ?);");
        statement.setInt(1,pub_id);//1 specifies the first parameter in the query
        statement.setInt(2, ed_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records inserted");
    }

    public static void ResponsiblePublicationsInfo() throws SQLException{
        System.out.println("Enter Staff Id");
        int s_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("SELECT * From Publications where Pub_Id in (select Pub_Id from Has where Staff_Id=?);");
        statement.setInt(1, s_id);
        result = statement.executeQuery();
        System.out.println("Pub_ID: Title : Type : Audience");
        while (result.next()) {
            System.out.println(result.getInt("Pub_Id")+"  "+ result.getString("Title") +"  "+ result.getBoolean("Type") +"  "+ result.getString("Audience"));
        }
    }

    private static void addArticle() throws SQLException{
        System.out.println("Enter the pub_id of the periodical");
        int pub_id = in.nextInt();
        System.out.println("Enter the date of issue");
        String date = in.next();
        System.out.println("Enter the article id");
        int article_id = in.nextInt();
        in.nextLine();
        System.out.println("Enter the article title");
        String article_title = in.nextLine();
        System.out.println("Enter the article text");
        String article_text = in.nextLine();
        System.out.println("Enter the article date of creation");
        String article_date = in.next();
        PreparedStatement statement=connection.prepareStatement("INSERT INTO Article VALUES(?, ?, ?, ?, ?, ?)");
        statement.setInt(1,pub_id);//1 specifies the firesultt parameter in the query
        statement.setInt(3,article_id);
        statement.setString(4,article_title);
        statement.setString(5,article_text);
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setDate(6,java.sql.Date.valueOf(article_date));
        int i=statement.executeUpdate();
        System.out.println(i+" records inserted");
    }

    private static void addChapter() throws SQLException{
        System.out.println("Enter the ISBN of the book edition");
        String ISBN = in.next();
        System.out.println("Enter the chapter id");
        int chapter_id = in.nextInt();
        in.nextLine();
        System.out.println("Enter the chapter text");
        String chapter_text = in.nextLine();
        System.out.println("Enter the chapter title");
        String chapter_title = in.nextLine();
        PreparedStatement statement=connection.prepareStatement("INSERT INTO Chapter VALUES(?, ?, ?, ?)");
        statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
        statement.setInt(2,chapter_id);
        statement.setString(3, chapter_text);
        statement.setString(4,chapter_title);
        int i=statement.executeUpdate();
        System.out.println(i+" records inserted");
    }

    private static void removeArticle() throws SQLException{

        System.out.println("Enter the pub_id of the book");
        int pub_id = in.nextInt();
        System.out.println("Enter the date of issue");
        String date = in.next();
        System.out.println("Enter the article id");
        int article_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("DELETE FROM Article WHERE Pub_Id = ? AND issue_date = ? AND Id = ?");
        statement.setInt(1,pub_id);//1 specifies the firesultt parameter in the query
        statement.setDate(2,java.sql.Date.valueOf(date));
        statement.setInt(3,article_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records removed");

    }

    private static void removeChapter() throws SQLException{

        System.out.println("Enter the ISBN of the book edition");
        String ISBN = in.next();
        System.out.println("Enter the chapter id");
        int chapter_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("DELETE FROM Chapter WHERE ISBN = ? AND chapter_Id = ?");
        statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
        statement.setInt(2,chapter_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records inserted");


    }





}
