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


public class tasksOps{
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/dshah4";

  private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
  private static Scanner sc = null;
  public static void main(String[] args) {
    try {
      Class.forName("org.mariadb.jdbc.Driver");

      String user = "dshah4";
      String passwd = "legionsofdoom";


      try {

        connection = DriverManager.getConnection(jdbcURL, user, passwd);
        statement = connection.createStatement();

        // Create the BOOKS table

        sc = new Scanner(System.in);
        int choice, choice2;


        do {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Production of a new edition or of an issue of a publication: \n");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("1. Operations on Book Editions");
            System.out.println("2. Operations on Issues of Periodicals");
            System.out.println("3. Operations on articles");
            System.out.println("4. Operations on chapters");
            System.out.println("5. Search");
            System.out.println("6. Check/set Employee payments");
            System.out.println("7. Exit");
            System.out.println("\nEnter your choice.");

            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                  do{
                      System.out.println("Actions ");
                      System.out.println("-------------------------- ");
                      System.out.println("1. Add Book Editions");
                      System.out.println("2. Update Book Editions");
                      System.out.println("3. Delete Book Editions");
                      System.out.println("4. Go back");

                      System.out.println("\nEnter your choice.");
                      choice2 = sc.nextInt();
                      sc.nextLine();
                      switch(choice2) {
                          case 1:
                              addBookEdition();
                              break;
                          case 2:
                              updateBookEdition();
                              break;
                          case 3:
                              deleteBookEdition();
                              break;
                          case 4:
                              break;
                          default:
                              throw new IllegalStateException("Unexpected value: " + choice);
                      }
                    }while(choice2<=3 && choice2>=1);
                    break;
                case 2:
                  do{
                      System.out.println("Actions ");
                      System.out.println("-------------------------- ");
                      System.out.println("1. Add issue for periodical");
                      System.out.println("2. Update issue of a Periodical");
                      System.out.println("3. Delete issue of a Periodical");
                      System.out.println("4. Go back");

                      System.out.println("\nEnter your choice.");
                      choice2 = sc.nextInt();
                      sc.nextLine();
                      switch(choice2) {
                          case 1:
                              addPeriodicIssue();
                              break;
                          case 2:
                              updatePeriodicIssue();
                              break;
                          case 3:
                              deletePeriodicIssue();
                              break;
                          case 4:
                              break;
                          default:
                              throw new IllegalStateException("Unexpected value: " + choice);
                      }
                    }while(choice2<=3 && choice2>=1);
                    break;
                case 3:
                  do{
                    System.out.println("Actions ");
                    System.out.println("-------------------------- ");
                    System.out.println("1. Add article to issue");
                    System.out.println("2. Set article title");
                    System.out.println("3. Set article date");
                    System.out.println("4. Set article text");
                    System.out.println("5. Add journalist to article ");
                    System.out.println("6. Add topic to article ");
                    System.out.println("7. Remove topic from article ");
                    System.out.println("8. Remove article");
                    System.out.println("9. Go back");


                    System.out.println("\nEnter your choice.");
                    choice2 = sc.nextInt();
                    sc.nextLine();
                    switch(choice2) {
                        case 1:
                            addArticle();
                            break;
                        case 2:
                            setArticleTitle();
                            break;
                        case 3:
                            setArticleDate();
                            break;
                        case 4:
                            setTextOfArticle();
                            break;
                        case 5:
                            addJournalistToArticle();
                            break;
                        case 6:
                            addTopicToArticle();
                            break;
                        case 7:
                            removeTopicFromArticle();
                            break;
                        case 8:
                            removeArticle();
                            break;
                        case 9:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);
                    }
                  }while(choice2<=8 && choice2>=1);
                    break;
                case 4:
                  do{
                  System.out.println("Actions ");
                  System.out.println("-------------------------- ");
                  System.out.println("1. Add chapter to book");
                  System.out.println("2. Set chapter title");
                  System.out.println("3. Set chapter text");
                  System.out.println("4. Add author to chapter ");
                  System.out.println("5. Add topic to chapter ");
                  System.out.println("6. Remove topic from chapter ");
                  System.out.println("7. Remove chapter");
                  System.out.println("8. Go back");


                  System.out.println("\nEnter your choice.");
                  choice2 = sc.nextInt();
                  sc.nextLine();
                  switch(choice2) {
                      case 1:
                          addChapter();
                          break;
                      case 2:
                          setChapterTitle();
                          break;
                      case 3:
                          setChapterText();
                      case 4:
                          addAuthorToChapter();
                          break;
                      case 5:
                          addTopicToChapter();
                          break;
                      case 6:
                          removeTopicFromChapter();
                          break;
                      case 7:
                          removeChapter();
                          break;
                      case 8:
                          break;
                      default:
                          throw new IllegalStateException("Unexpected value: " + choice);

                  }
                }while(choice2<=7 && choice2>=1);
                  break;

                case 5:
                  do{
                    System.out.println("Actions ");
                    System.out.println("-------------------------- ");
                    System.out.println("1. Find books by topic");
                    System.out.println("2. Find books by author");
                    System.out.println("3. Find books by date");
                    System.out.println("4. Find articles by topic");
                    System.out.println("5. Find articles by date ");
                    System.out.println("6. Find articles by journalist ");
                    System.out.println("7. Go back");


                    System.out.println("\nEnter your choice.");
                    choice2 = sc.nextInt();
                    sc.nextLine();
                    switch(choice2) {
                        case 1:
                            System.out.println("\n-------");
                            findBooksByTopic();
                            break;
                        case 2:
                            FindBookbyAuthor();
                            break;
                        case 3:
                            FindBooksbyDate();
                            break;
                        case 4:
                            findArticlesByTopic();
                            break;
                        case 5:
                            FindArticlesbyDate();
                            break;
                        case 6:
                            FindArticlesbyJournalist();
                            break;
                        case 7:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);

                    }
                  }while(choice2<=6 && choice2>=1);
                    break;
                case 6:
                  do{
                    System.out.println("Actions ");
                    System.out.println("-------------------------- ");
                    System.out.println("1. Set Employee salary ");
                    System.out.println("2. Check claim date ");
                    System.out.println("3. Go back");
                    choice2 = sc.nextInt();
                    sc.nextLine();
                    switch(choice2) {
                        case 1:
                            setPayment();
                            break;
                        case 2:
                            checkClaimDate();
                            break;
                        case 3:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);
                      }
                    }while(choice2<=2 && choice2>=1);
                      break;
                case 7:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while(choice<=6 && choice>=1);






            } finally {
                close(result);
                close(statement);
                close(connection);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
      }

  private static void addBookEdition() throws SQLException{
    try{
      System.out.println("Enter the 13 digit ISBN");
      String ISBN = sc.next();
      System.out.println("Enter the edition number");
      int edition_no = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("INSERT INTO Edition VALUES(?,?)");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(2, edition_no);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void addPeriodicIssue() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the book");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the title of the issue");
      String title = sc.next();
      PreparedStatement statement=connection.prepareStatement("INSERT INTO Issues VALUES(?, ?, ?)");
      statement.setString(3,title);//1 specifies the firesultt parameter in the query
      statement.setDate(2, java.sql.Date.valueOf(date));
      statement.setInt(1, pub_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void updateBookEdition() throws SQLException{
    try{
      System.out.println("Enter the 13 digit ISBN of the book");
      String ISBN = sc.next();
      System.out.println("Enter the new edition number");
      int edition_no = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("UPDATE Edition SET edition_no = ? WHERE ISBN = ?");
      statement.setString(2,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(1, edition_no);
      int i=statement.executeUpdate();
      System.out.println(i+" records updated");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void updatePeriodicIssue() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the book");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the new title");
      String title = sc.next();
      PreparedStatement statement=connection.prepareStatement("UPDATE Issues SET Title = ? WHERE issue_date = ? AND Pub_Id = ?");
      statement.setString(1,title);//1 specifies the firesultt parameter in the query
      statement.setDate(2, java.sql.Date.valueOf(date));
      statement.setInt(3, pub_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records updated");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void deleteBookEdition() throws SQLException{
    try{
      System.out.println("Enter the 13 digit ISBN of the book");
      String ISBN = sc.next();
      PreparedStatement statement=connection.prepareStatement("DELETE FROM Edition WHERE ISBN = ?");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      int i=statement.executeUpdate();
      System.out.println(i+" records updated");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void deletePeriodicIssue() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the period");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      PreparedStatement statement=connection.prepareStatement("DELETE FROM Issues WHERE issue_date = ? AND Pub_Id = ?");
      statement.setDate(1, java.sql.Date.valueOf(date));
      statement.setInt(2, pub_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records updated");

    }
    catch (SQLException e) {
      System.out.println("The issue can't be deleted since it's a part of an order or has articles associated with it.");
    }
  }

  private static void addArticle() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the periodical");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the article title");
      String article_title = sc.nextLine();
      System.out.println("Enter the article text");
      String article_text = sc.nextLine();
      System.out.println("Enter the article date of creation");
      String article_date = sc.next();
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
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void addChapter() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the chapter text");
      String chapter_text = sc.nextLine();
      System.out.println("Enter the chapter title");
      String chapter_title = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("INSERT INTO Chapter VALUES(?, ?, ?, ?)");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(2,chapter_id);
      statement.setString(3, chapter_text);
      statement.setString(4,chapter_title);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setChapterTitle() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the new chapter title");
      String chapter_title = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("UPDATE Chapter SET title = ? WHERE ISBN = ? AND chapter_Id = ? ");
      statement.setString(1,chapter_title);
      statement.setString(2,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(3,chapter_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setArticleTitle() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the book");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the new article title");
      String article_title = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("UPDATE Article SET Title = ? WHERE Pub_Id = ? AND issue_date = ? AND Id = ?");
      statement.setInt(2,pub_id);//1 specifies the firesultt parameter in the query
      statement.setDate(3,java.sql.Date.valueOf(date));
      statement.setInt(4,article_id);
      statement.setString(1,article_title);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void removeArticle() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the book");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("DELETE FROM Article WHERE Pub_Id = ? AND issue_date = ? AND Id = ?");
      statement.setInt(1,pub_id);//1 specifies the firesultt parameter in the query
      statement.setDate(2,java.sql.Date.valueOf(date));
      statement.setInt(3,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records removed");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void removeChapter() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("DELETE FROM Chapter WHERE ISBN = ? AND chapter_Id = ?");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(2,chapter_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      System.out.println("The chapter is currently associated with an author");
    }
  }

  private static void addAuthorToChapter() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the staff id of the author");
      int staff_id = sc.nextInt();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("INSERT INTO WritesChapter VALUES(?, ?, ?)");
      statement.setString(2,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(3,chapter_id);
      statement.setInt(1,staff_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void addJournalistToArticle() throws SQLException{
    try{
      System.out.println("Enter the staff id of the journalist");
      int staff_id = sc.nextInt();
      System.out.println("Enter the pub_id of the periodical");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("INSERT INTO WritesArticle VALUES(?, ?, ?, ?)");

      statement.setInt(1,staff_id);
      statement.setInt(2,pub_id);
      statement.setDate(3,java.sql.Date.valueOf(date));
      statement.setInt(4,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void addTopicToArticle() throws SQLException{
    try{
      System.out.println("Enter the topic(has to be in the topic table)");
      String topic = sc.nextLine();
      System.out.println("Enter the pub_id of the periodical");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("insert into ArticleAbout values(?, ? ,?, ?)");
      statement.setString(1,topic);
      statement.setInt(2,pub_id);
      statement.setDate(3,java.sql.Date.valueOf(date));
      statement.setInt(4,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void removeTopicFromArticle() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the periodical");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("DELETE FROM ArticleAbout WHERE Pub_Id = ? AND issue_date = ? AND article_Id = ?");
      statement.setInt(1,pub_id);
      statement.setDate(2,java.sql.Date.valueOf(date));
      statement.setInt(3,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void addTopicToChapter() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      sc.nextLine();
      System.out.println("Enter the topic(has to be in the topic table)");
      String topic = sc.nextLine();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("insert into ChapterAbout values(?, ?, ?)");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(2,chapter_id);
      statement.setString(3,topic);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void removeTopicFromChapter() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("delete from ChapterAbout where ISBN = ? AND chapter_Id = ?");
      statement.setString(1,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(2,chapter_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setArticleDate() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the periodical");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      System.out.println("Enter the date of creation to be set");
      String article_date = sc.next();

      PreparedStatement statement=connection.prepareStatement("UPDATE Article SET date_of_creation = ? WHERE Pub_Id = ? AND issue_date = ? AND Id = ?");
      statement.setDate(1,java.sql.Date.valueOf(article_date));
      statement.setInt(2,pub_id);
      statement.setDate(3,java.sql.Date.valueOf(date));
      statement.setInt(4,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setTextOfArticle() throws SQLException{
    try{
      System.out.println("Enter the pub_id of the book");
      int pub_id = sc.nextInt();
      System.out.println("Enter the date of issue");
      String date = sc.next();
      System.out.println("Enter the article id");
      int article_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the article text to be set");
      String article_text = sc.nextLine();

      PreparedStatement statement=connection.prepareStatement("UPDATE Article SET text = ? WHERE Pub_Id = ? AND issue_date = ? AND Id = ?");
      statement.setString(1,article_text);
      statement.setInt(2,pub_id);
      statement.setDate(3,java.sql.Date.valueOf(date));
      statement.setInt(4,article_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setChapterText() throws SQLException{
    try{
      System.out.println("Enter the ISBN of the book edition");
      String ISBN = sc.next();
      System.out.println("Enter the chapter id");
      int chapter_id = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the new chapter text");
      String chapter_text = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("UPDATE Chapter SET text = ? WHERE ISBN = ? AND chapter_Id = ?");
      statement.setString(1,chapter_text);
      statement.setString(2,ISBN);//1 specifies the firesultt parameter in the query
      statement.setInt(3,chapter_id);
      int i=statement.executeUpdate();
      System.out.println(i+" records inserted");

    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }


  private static void findBooksByTopic() throws SQLException{
    try{
      System.out.println("Enter the topic to search books");
      String topic = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("select Title from Publications NATURAL JOIN PubAbout where PubAbout.Name = ? AND Publications.Type = 0");
      statement.setString(1,topic);
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void findArticlesByTopic() throws SQLException{
    try{
      System.out.println("Enter the topic to search articles");
      String topic = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement(" select Title from Article NATURAL JOIN ArticleAbout WHERE ArticleAbout.article_Id = Article.Id and ArticleAbout.Name = ?");
      statement.setString(1,topic);
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void FindBooksbyDate() throws SQLException{
    try{
      System.out.println("Enter the date to search books by");
      String date = sc.next();
      PreparedStatement statement=connection.prepareStatement(" select Title from Publications NATURAL JOIN Books WHERE Books.Publication_Date = ?");
      statement.setDate(1,java.sql.Date.valueOf(date));
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void FindArticlesbyDate() throws SQLException{
    try{
      System.out.println("Enter the date to search articles by");
      String date = sc.next();
      PreparedStatement statement=connection.prepareStatement(" select Title from Article where date_of_creation = ?");
      statement.setDate(1,java.sql.Date.valueOf(date));
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void FindArticlesbyJournalist() throws SQLException{
    try{
      System.out.println("Enter the name of the jounrnalist");
      String name = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement(" select Article.Title from Article natural join WritesArticle natural join Journalists natural join Employee where Employee.name LIKE ?");
      statement.setString(1,name);
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Article.Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void FindBookbyAuthor() throws SQLException{
    try{
      System.out.println("Enter the name of the author");
      String name = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("select Title from Publications natural join Invites natural join Authors natural join Employee where Name = ? and Publications.type = 0");
      statement.setString(1,name);
      result = statement.executeQuery();

      while (result.next()) {
          String title = result.getString("Title");
          System.out.println(title);
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void setPayment() throws SQLException{
    try{

      System.out.println("Enter the staff id of the employee");
      int staff_id = sc.nextInt();
      System.out.println("Enter the salary to be set");
      int salary = sc.nextInt();
      PreparedStatement statement=connection.prepareStatement("UPDATE Employee SET Salary = ? WHERE Staff_Id = ?");
      statement.setInt(1, salary);
      statement.setInt(2, staff_id);
      int i=statement.executeUpdate();
      if (i==0){
        System.out.println("No such employee exists");
      }
      else
        System.out.println(i+" records updated");


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void checkClaimDate() throws SQLException{
    try{
      System.out.println("Enter the name of the author");
      String name = sc.nextLine();
      PreparedStatement statement=connection.prepareStatement("select date, claim_date from Employee NATURAL JOIN Compensate NATURAL JOIN Payments where Employee.Name = ?");
      statement.setString(1,name);
      result = statement.executeQuery();
      System.out.println("Payment date"+"\t"+ "Claimed date");
      while (result.next()) {
        java.sql.Date dbSqlDate = result.getDate("date");
        java.sql.Date dbSqlClaimDate = result.getDate("claim_date");
        System.out.println(dbSqlDate + "\t" +dbSqlClaimDate );
      }


    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }






  static void close(Connection conn) {
      if(conn != null) {
          try { conn.close(); } catch(Throwable whatever) {}
      }
  }

  static void close(Statement st) {
      if(st != null) {
          try { st.close(); } catch(Throwable whatever) {}
      }
  }

  static void close(ResultSet rs) {
      if(rs != null) {
          try { rs.close(); } catch(Throwable whatever) {}
      }
  }
}
