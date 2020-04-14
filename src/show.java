import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.*;

public class show {
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
							System.out.println("\n\n---------------- Main Menu ------------------");
							System.out.println("1: Show all employees");
							System.out.println("2: Show all authors");
							System.out.println("3: Show all editors");
							System.out.println("4: Show all journalists");
							System.out.println("5: Show all publications");
							System.out.println("6: Show all topics");
							System.out.println("7: Show all PubAbout");
							System.out.println("8: Show all employees associated with publications");
							System.out.println("9: Show all editors associated with pulications");
							System.out.println("10: Show all Books");
							System.out.println("11: Show all Edition");
							System.out.println("12: Show all Chapter");
							System.out.println("13: Show all WritesChapter");
							System.out.println("14: Show all Periodicals");
							System.out.println("15: Show all Issues");
							System.out.println("16: Show all Articles");
							System.out.println("17: Show all journalists associated with articles");
							System.out.println("18: Show all articles with topics");
							System.out.println("19: Show all payment details");
							System.out.println("20: Show all distributors");
							System.out.println("21: Show all payments from distributors");
							System.out.println("22: Show all payments to employees");
							System.out.println("23: Show all orders");
							System.out.println("24: Show all orders for issues");
							System.out.println("25: Show all orders for edition");
							System.out.println("26: Show all editions of books");
							System.out.println("27: Show all chapter with topics");
							System.out.println("28: Exit");


							System.out.println("\n\n Enter your choice.");

							choice = s.nextInt();
							switch(choice) {
									case 1: try {                               // Add a distributor
											showEmployee();
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
											showAuthors();
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
											showEditors();
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
											showJournalists();
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
											showPublications();
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
											showTopic();
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
											showPubAbout();
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
											showInvites();
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
									case 9: try {                               // Update a distributor
											showHas();
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
									case 10: try {                               // Update a distributor
											showBooks();
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
									case 11: try {                               // Update a distributor
											showEdition();
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
									case 12: try {                               // Update a distributor
											showChapter();
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
									case 13: try {                               // Update a distributor
											showWritesChapter();
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
									case 14: try {                               // Update a distributor
											showPeriodicals();
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
									case 15: try {                               // Update a distributor
											showIssues();
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
									case 16: try {                               // Update a distributor
											showArticles();
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
									case 17: try {                               // Update a distributor
											showWritesArticle();
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
									case 18: try {                               // Update a distributor
											showArticleAbout();
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
									case 19: try {                               // Update a distributor
											ShowPayment();
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
									case 20: try {                               // Update a distributor
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
									case 21: try {                               // Update a distributor
											ShowPay();
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
									case 22: try {                               // Update a distributor
											ShowCompensate();
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
									case 23: try {                               // Update a distributor
											showOrders();
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
									case 24: try {                               // Update a distributor
											showOrdersforissues();
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
									case 25: try {                               // Update a distributor
											showOrdersforedition();
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
									case 26: try {                               // Update a distributor
											ShowOf();
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
									case 27: try {                               // Update a distributor
											ShowChapterabout();
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
									case 28:
										break;
									default:
											throw new IllegalStateException("Unexpected value: " + choice);
							}
					}while(choice <= 27 && choice >= 1);
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


	    public static void showEmployee() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement employee = null;
	        employee = connection.prepareStatement("SELECT * FROM Employee");
	        result = employee.executeQuery();
	        System.out.println( "Staff Id" +
            "\t" + "Salary" +
            "\t" + "Name" +
            "\t" + "Employment Type" );
	        while (result.next()) {
	          System.out.println();
	          int staffId = result.getInt(1);
	          Double salary = result.getDouble(2);
	          String name = result.getString(3);
	          int employmenttype = result.getInt(4);

	          System.out.println(
	                               staffId +
	                               "\t" + salary +
	                               "\t" + name +
	                               "\t" + employmenttype);

	        }
	    }

	    public static void showAuthors() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement author = null;
	        author = connection.prepareStatement("SELECT * FROM Authors");
	        result = author.executeQuery();
	        //if (!result.next())
	        //  System.out.println("Distributor is empty.");
	        System.out.println("staff Id");
	        while (result.next()) {
	          System.out.println();
	          int staffId = result.getInt(1);

	          System.out.println(
	                               staffId);

	        }
	    }

	    public static void showEditors() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement editor = null;
	        editor = connection.prepareStatement("SELECT * FROM Editors");
	        result = editor.executeQuery();
	        //if (!result.next())
	        //  System.out.println("Distributor is empty.");
	        System.out.println("staff Id");
	        while (result.next()) {
	          System.out.println();
	          int staffId = result.getInt(1);

	          System.out.println(
	                               staffId);

	        }
	    }

	    public static void showJournalists() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement journalist = null;
	        journalist = connection.prepareStatement("SELECT * FROM Journalists");
	        result = journalist.executeQuery();
	        //if (!result.next())
	        //  System.out.println("Distributor is empty.");
	        System.out.println("staff Id");
	        while (result.next()) {
	          System.out.println();
	          int staffId = result.getInt(1);

	          System.out.println(
	                               staffId);

	        }
	    }

	    public static void showPublications() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement pub = null;
	        pub = connection.prepareStatement("SELECT * FROM Publications");
	        result = pub.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Title" +
            "\t" + "Type" +
            "\t" + "Audience" );
	        while (result.next()) {
	          System.out.println();
	          int pubId = result.getInt(1);
	          String title = result.getString(2);
	          int type = result.getInt(3);
	          String audience = result.getString(4);
	          System.out.println(
	                               pubId +
	                               "\t" + title +
	                               "\t" + type +
	                               "\t" + audience);

	        }
	    }

	    public static void showTopic() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement topic = null;
	        topic = connection.prepareStatement("SELECT * FROM Topic");
	        result = topic.executeQuery();
	        System.out.println("Name");
	        while (result.next()) {
	          System.out.println();
	          int name = result.getInt(1);

	          System.out.println(
	                               name);

	        }
	    }

	    public static void showPubAbout() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement pub = null;
	        pub = connection.prepareStatement("SELECT * FROM PubAbout");
	        result = pub.executeQuery();
	        System.out.println( "Name" +
            "\t" + "Pub Id" );
	        while (result.next()) {
	          System.out.println();
	          String name= result.getString(1);
	          int pubId = result.getInt(2);
	          System.out.println(
	                               name +
	                               "\t" + pubId);

	        }
	    }

	    public static void showInvites() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement inv = null;
	        inv = connection.prepareStatement("SELECT * FROM Invites");
	        result = inv.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Staff Id" );
	        while (result.next()) {
	          System.out.println();
	          int pubId= result.getInt(1);
	          int staffId = result.getInt(2);
	          System.out.println(
	                               pubId +
	                               "\t" +staffId);

	        }
	    }

	    public static void showHas() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement has = null;
	        has = connection.prepareStatement("SELECT * FROM Has");
	        result = has.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Staff Id" );
	        while (result.next()) {
	          System.out.println();
	          int pubId= result.getInt(1);
	          int staffId = result.getInt(2);
	          System.out.println(
	                               pubId +
	                               "\t" +staffId);

	        }
	    }

	    public static void showBooks() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement book = null;
	        book = connection.prepareStatement("SELECT * FROM Books");
	        result = book.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Publication_Date" );
	        while (result.next()) {
	          System.out.println();
	          int pubId= result.getInt(1);
	          String date = result.getString(2);
	          System.out.println(
	                               pubId +
	                               "\t" +date);

	        }
	    }

	    public static void showEdition() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement edition = null;
	        edition = connection.prepareStatement("SELECT * FROM Edition");
	        result = edition.executeQuery();
	        System.out.println( "ISBN" +
            "\t" + "Edition No" );
	        while (result.next()) {
	          System.out.println();
	          String isbn = result.getString(1);
	          int num= result.getInt(2);

	          System.out.println(
	                               isbn +
	                               "\t" +num);

	        }
	    }

	    public static void showChapter() throws SQLException {
	        PreparedStatement chapter = null;
	        chapter = connection.prepareStatement("SELECT * FROM Chapter");
	        result = chapter.executeQuery();
	        System.out.println( "ISBN" +
            "\t" + "Chapter_Id" +
            "\t" + "Text" +
            "\t" + "Title" );
	        while (result.next()) {
	          System.out.println();
	          String isbn = result.getString(1);
	          int chapterid = result.getInt(2);
	          String text = result.getString(3);
	          String title = result.getString(4);

	          System.out.println(
	                                isbn +
	                               "\t" + chapterid +
	                               "\t" + text +
	                               "\t" + title);

	        }
	    }

	    public static void showWritesChapter() throws SQLException {
	        PreparedStatement write = null;
	        write = connection.prepareStatement("SELECT * FROM WritesChapter");
	        result = write.executeQuery();
	        System.out.println( "Staff Id" +
            "\t" + "ISBN" +
            "\t" + "Chapter Id" );
	        while (result.next()) {
	          System.out.println();
	          int staffid = result.getInt(1);
	          String isbn = result.getString(2);
	          int chapterid = result.getInt(3);

	          System.out.println(
	                                staffid +
	                               "\t" + isbn +
	                               "\t" + chapterid);

	        }
	    }

	    public static void showPeriodicals() throws SQLException {
	        PreparedStatement periodical = null;
	        periodical = connection.prepareStatement("SELECT * FROM Periodicals");
	        result = periodical.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Periodicity" +
            "\t" + "Type" );
	        while (result.next()) {
	          System.out.println();
	          int pubid = result.getInt(1);
	          String periodicity = result.getString(2);
	          String type = result.getString(3);

	          System.out.println(
	                                pubid +
	                               "\t" + periodicity +
	                               "\t" + type);

	        }
	    }

	    public static void showIssues() throws SQLException {
	        PreparedStatement issue = null;
	        issue = connection.prepareStatement("SELECT * FROM Issues");
	        result = issue.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Issue Date" +
            "\t" + "Title" );
	        while (result.next()) {
	          System.out.println();
	          int pubid = result.getInt(1);
	          String date = result.getString(2);
	          String title = result.getString(3);

	          System.out.println(
	                                pubid +
	                               "\t" + date +
	                               "\t" + title);

	        }
	    }

	    public static void showArticles() throws SQLException {
	        PreparedStatement chapter = null;
	        chapter = connection.prepareStatement("SELECT * FROM Article");
	        result = chapter.executeQuery();
	        System.out.println( "Pub Id" +
            "\t" + "Issue date" +
            "\t" + "Id" +
            "\t" + "Title" +
            "\t" + "Text" +
            "\t" + "date of creation" );
	        while (result.next()) {
	          System.out.println();
	          int pubid = result.getInt(1);
	          String issuedate = result.getString(2);
	          int id = result.getInt(3);
	          String title = result.getString(4);
	          String text = result.getString(5);
	          String datecreate = result.getString(6);

	          System.out.println(
	                                pubid +
	                               "\t" + issuedate +
	                               "\t" + id +
	                               "\t" + title+
	                               "\t" + text+
	                               "\t" + datecreate);


	        }
	    }

	    public static void showWritesArticle() throws SQLException {
	        PreparedStatement write = null;
	        write = connection.prepareStatement("SELECT * FROM WritesArticle");
	        result = write.executeQuery();
	        System.out.println( "Staff Id" +
            "\t" + "Pub Id" +
            "\t" + "Issue date" +
            "\t" + "Article Id" );
	        while (result.next()) {
	          System.out.println();
	          int staffid = result.getInt(1);
	          int pubid = result.getInt(2);
	          String date = result.getString(3);
	          int id = result.getInt(4);

	          System.out.println(
	                                staffid +
	                               "\t" + pubid +
	                               "\t" + date +
	                               "\t" + id );

	        }
	    }

	    public static void showArticleAbout() throws SQLException {
	        PreparedStatement article = null;
	        article = connection.prepareStatement("SELECT * FROM ArticleAbout");
	        result = article.executeQuery();
	        System.out.println( "Name" +
            "\t" + "Pub Id" +
            "\t" + "Issue date" +
            "\t" + "Article Id" );
	        while (result.next()) {
	          System.out.println();
	          String name = result.getString(1);
	          int pubid = result.getInt(2);
	          String date = result.getString(3);
	          int id = result.getInt(4);

	          System.out.println(
	                                name +
	                               "\t" + pubid +
	                               "\t" + date +
	                               "\t" + id );

	        }
	    }


	    public static void ShowPayment() throws SQLException {
	        PreparedStatement payment = null;
	        payment = connection.prepareStatement("SELECT * FROM Payments");
	        result = payment.executeQuery();
	        System.out.println( "Payment Id" +
            "\t" + "Amount" +
            "\t" + "date" +
            "\t" + "Type" +
            "\t" + "claim date" );
	        while (result.next()) {
	          System.out.println();
	          int pid = result.getInt(1);
	          Double amount= result.getDouble(2);
	          String date = result.getString(3);
	          int type = result.getInt(4);
	          String claimdate = result.getString(5);

	          System.out.println(
	                                pid +
	                               "\t" + amount +
	                               "\t" + date +
	                               "\t" + type +
	                               "\t" + claimdate);

	        }
	    }

	    public static void showDistributor() throws SQLException {
	        //String Query = "SELECT * FROM Distributor;";
	        PreparedStatement showAllDistributors = null;
	        showAllDistributors = connection.prepareStatement("SELECT * FROM Distributor;");
	        result = showAllDistributors.executeQuery();

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

	    public static void ShowPay() throws SQLException {
	        PreparedStatement pay = null;
	        pay = connection.prepareStatement("SELECT * FROM Pays");
	        result = pay.executeQuery();
	        System.out.println( "Payment Id" +
            "\t" + "Account no" );
	        while (result.next()) {
	          System.out.println();
	          int pid = result.getInt(1);
	          int accountno= result.getInt(2);

	          System.out.println(
	                                pid +
	                               "\t" + accountno);

	        }
	    }

	    public static void ShowCompensate() throws SQLException {
	        PreparedStatement compenste = null;
	        compenste = connection.prepareStatement("SELECT * FROM Compensate");
	        result = compenste.executeQuery();
	        System.out.println( "Payment Id" +
            "\t" + "Staff Id" );
	        while (result.next()) {
	          System.out.println();
	          int pid = result.getInt(1);
	          int staffid= result.getInt(2);

	          System.out.println(
	                                pid +
	                               "\t" + staffid);

	        }
	    }

	    public static void showOrders() throws SQLException {

	        PreparedStatement orders = null;
	        orders = connection.prepareStatement("SELECT * FROM Orders;");
	        result = orders.executeQuery();
	        System.out.println( "Order Id" +
	                "\t" + "order date" +
	                "\t" + "delivery status" +
	                "\t" + "shipping cost" );
	        while (result.next()) {
	          System.out.println();
	          int orderid = result.getInt(1);
	          String date = result.getString(2);
	          int status= result.getInt(3);
	          double cost = result.getDouble(4);
	          System.out.println(
	                               orderid +
	                               "\t" + date +
	                               "\t" + status +
	                               "\t" + cost);
	        }
	    }


	    public static void showOrdersforissues() throws SQLException {

	        PreparedStatement orders = null;
	        orders = connection.prepareStatement("SELECT * FROM Order_for_Issues;");
	        result = orders.executeQuery();
	        System.out.println( "Account no" +
	                "\t" + "pub Id" +
	                "\t" + "Issue date" +
	                "\t" + "order Id" +
	                "\t" + "Price" +
	                "\t" + "quantity");
	        while (result.next()) {
	          System.out.println();
	          int accountno = result.getInt(1);
	          int pubid = result.getInt(2);
	          String issuedate = result.getString(3);
	          int orderid = result.getInt(4);
	          double price = result.getDouble(5);
	          int quantity = result.getInt(6);
	          System.out.println(
	                               accountno +
	                               "\t" + pubid +
	                               "\t" + issuedate +
	                               "\t" + orderid +
	                               "\t" + price+
	                               "\t" + quantity
	                               );
	        }
	    }

	    public static void showOrdersforedition() throws SQLException {

	        PreparedStatement orders = null;
	        orders = connection.prepareStatement("SELECT * FROM Order_for_Edition;");
	        result = orders.executeQuery();
	        System.out.println( "Account no" +
	                "\t" + "ISBN" +
	                "\t" + "order Id" +
	                "\t" + "Price" +
	                "\t" + "quantity");
	        while (result.next()) {
	          System.out.println();
	          int accountno = result.getInt(1);
	          String isbn = result.getString(2);
	          int orderid = result.getInt(3);
	          double price = result.getDouble(4);
	          int quantity = result.getInt(5);
	          System.out.println(
	                               accountno +
	                               "\t" + isbn+
	                               "\t" + orderid +
	                               "\t" + price+
	                               "\t" + quantity
	                               );
	        }
	    }

	    public static void ShowOf() throws SQLException {
	        PreparedStatement of = null;
	        of = connection.prepareStatement("SELECT * FROM Of");
	        result = of.executeQuery();
	        System.out.println( "ISBN" +
            "\t" + "Pub Id" );
	        while (result.next()) {
	          System.out.println();
	          String isbn = result.getString(1);
	          int pubid = result.getInt(2);

	          System.out.println(
	                                isbn +
	                               "\t" + pubid);

	        }
	    }

	    public static void ShowChapterabout() throws SQLException {
	        PreparedStatement chapter = null;
	        chapter = connection.prepareStatement("SELECT * FROM ChapterAbout");
	        result = chapter.executeQuery();
	        System.out.println( "ISBN" +
            "\t" + "Chapter Id" +
            "\t" + "Topic" );
	        while (result.next()) {
	          System.out.println();
	          String isbn = result.getString(1);
	          int chapterid = result.getInt(2);
	          String topic = result.getString(3);

	          System.out.println(
	                                isbn +
	                               "\t" + chapterid +
	                               "\t" + topic );

	        }
	    }

}
