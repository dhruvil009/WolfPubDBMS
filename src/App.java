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
            System.out.println("1.");
            System.out.println("7. Go Back");
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
                    NewChapter();
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
                    DeleteChapter();
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
                    NewArticle();
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
                    DeleteArticle();
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
        System.out.println("Enter title");
        String title = in.nextLine();
        title = in.nextLine();
        System.out.println(title);
        System.out.println("Enter type");
        Boolean type = in.nextBoolean();
        System.out.println("Enter audience");
        String audience = in.nextLine();
        audience = in.nextLine();

        PreparedStatement statement=connection.prepareStatement("INSERT into Publications VALUES(?, ?, ?, ?);");
        statement.setInt(1,pubid);//1 specifies the first parameter in the query
        statement.setString(2, title);
        statement.setBoolean(3,type);//1 specifies the first parameter in the query
        statement.setString(4, audience);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");

        if(!type){
            System.out.println("Enter Publication Date : ");
            String date = in.next();
            statement=connection.prepareStatement("INSERT into Books VALUES(?, ?);");
            statement.setInt(1,pubid);
            statement.setDate(2, java.sql.Date.valueOf(date));
            i=statement.executeUpdate();
            System.out.println(i+" records updated");
        }
        else{
            System.out.println("Enter Periodicity : ");
            String p = in.next();
            System.out.println("Enter Type : ");
            String t = in.next();
            statement=connection.prepareStatement("INSERT into Periodicals VALUES(?, ?, ?);");
            statement.setInt(1,pubid);
            statement.setString(2, p);
            statement.setString(3, t);
            i=statement.executeUpdate();
            System.out.println(i+" records updated");
        }


        System.out.println("Done");
    }

    public static void UpdatePublication() throws SQLException {
        System.out.println("Title: 1 Audience: 2");
        int ch = in.nextInt();
        if(ch == 1){
            System.out.println("Enter title");
            String title = in.nextLine();
            System.out.println("Enter Pub Id");
            int pub_id = in.nextInt();
            PreparedStatement statement=connection.prepareStatement("UPDATE Publications SET Title = ? WHERE Pub_Id = ?");
            statement.setString(1,title);//1 specifies the first parameter in the query
            statement.setInt(2, pub_id);
            int i=statement.executeUpdate();
            System.out.println(i+" records updated");
        }
        else if(ch == 2){
            System.out.println("Enter Audience");
            String audience = in.nextLine();
            System.out.println("Enter Pub Id");
            int pub_id = in.nextInt();
            PreparedStatement statement=connection.prepareStatement("UPDATE Publications SET Audience = ? WHERE Pub_Id = ?");
            statement.setString(1,audience);//1 specifies the first parameter in the query
            statement.setInt(2, pub_id);
            int i=statement.executeUpdate();
            System.out.println(i+" records updated");
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
        System.out.println(i+" records updated");
    }

    public static void ResponsiblePublicationsInfo() throws SQLException{
        System.out.println("Enter Staff Id");
        int s_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("SELECT * From Publications where Pub_Id in (Select Pub_Id from Has where Staff_Id=?);");
        statement.setInt(1, s_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");
    }

    public static void NewChapter() throws SQLException{
        System.out.println("Enter ISBN");
        String isbn = in.nextLine();
        isbn = in.nextLine();
        System.out.println("Enter Chapter Id");
        int c_id = in.nextInt();
        System.out.println("Enter Text");
        String text = in.nextLine();
        text = in.nextLine();
        System.out.println("Enter Title");
        String title = in.nextLine();
        PreparedStatement statement=connection.prepareStatement("INSERT INTO Chapter VALUES(?, ?, ?, ?);\n");
        statement.setString(1,isbn);//1 specifies the first parameter in the query
        statement.setInt(2, c_id);
        statement.setString(3, text);//1 specifies the first parameter in the query
        statement.setString(4, title);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");
    }

    public static void DeleteChapter() throws SQLException{
        System.out.println("Enter ISBN");
        String isbn = in.nextLine();
        System.out.println("Enter Chapter Id");
        int c_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("DELETE FROM Chapter WHERE ISBN = ? AND chapter_Id = ?;");
        statement.setString(1,isbn);//1 specifies the first parameter in the query
        statement.setInt(2, c_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");
    }

    public static void NewArticle() throws SQLException{
        System.out.println("Enter Pub Id");
        int pub_id = in.nextInt();
        System.out.println("Enter the date of issue");
        String date = in.next();
        System.out.println("Enter Article Id");
        int a_id = in.nextInt();
        System.out.println("Enter Text");
        String text = in.nextLine();
        System.out.println("Enter Title");
        String title = in.nextLine();
        System.out.println("Enter date of creation");
        String dateofcreation = in.nextLine();
        PreparedStatement statement=connection.prepareStatement("INSERT INTO Article VALUES(?, ?, ?, ?, ?, ?);");
        statement.setInt(1,pub_id);//1 specifies the first parameter in the query
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setInt(3, a_id);
        statement.setString(5, text);//1 specifies the first parameter in the query
        statement.setString(4, title);
        statement.setDate(6, java.sql.Date.valueOf(dateofcreation));
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");
    }

    public static void DeleteArticle() throws SQLException{
        System.out.println("Enter Pub Id");
        int pub_id = in.nextInt();
        System.out.println("Enter the date of issue");
        String date = in.next();
        System.out.println("Enter Article Id");
        int a_id = in.nextInt();
        PreparedStatement statement=connection.prepareStatement("DELETE FROM Article WHERE Pub_Id = Pub_Id = ? AND issue_date = ? AND Id = ?;");
        statement.setInt(1,pub_id);//1 specifies the first parameter in the query
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setInt(3, a_id);
        int i=statement.executeUpdate();
        System.out.println(i+" records updated");
    }
}