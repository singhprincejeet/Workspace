import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

// Author 1 Princejeet Singh Sandhu

/**
 * <b>Database</b> object is used to save name of categories, question and responses in a <b>Question</b>
 * array and also the number of categories and questions.
 *
 * It also has a method which read all of the above mentioned things from a file.
 */

/**
 * 
 */

public class Database {
  
  private int m,n;
  /**
   * <ul>
   *     <li><b>m</b> is an <i>int</i> variable which is used to store the number of categories.</li>
   *     <li><b>n</b> is an <i>int</i> variable which is used to store the number of questions.</li>
   * </ul>
   */
  private String[] categories;
  /**
   * An array of <i>String</i> objects used to store name of categories.
   */
  private Question[][] questions;
  /**
   * A multi-dimensional array of <i>Question</i> objects used to store name of questions.
   */
  private static String[][] ques, ans;
  /**
   * A multi-dimensional array of <i>String</i> objects used to save questions and answers from file.
   */
  
  private Database(int m, int n) {
    
    this.m = m;
    this.n = n;
    categories = new String[m];
    questions = new Question[m][n];
    
  }
  
  /**
   * This method is used to read a file whose name is received as a formal parameter and everything
   * read from file is saved in a database which is then returned to from where the method was called.
   * @param name
   *          name of the file with extension
   * @return database
   *          database in which everything from the file is saved.
   */
  
  public static Database readQuestions(String name){
    
    Scanner scanner;// This scanner object is used to read data from file.
    
    try {
      scanner = new Scanner(new File(name));
    } catch (FileNotFoundException e) {
      scanner=null;
    }
    
    
    int a=-1,b=-1;// These int variables are used to save the number of categories and questions respectively.
    
    Database database = null;
    
    while (scanner.hasNextInt()){
      try {
        a = scanner.nextInt();
      }
      catch (InputMismatchException e){
        return null;
      }
      try {
        b = scanner.nextInt();
      }
      catch (InputMismatchException e){
        return null;
      }
    }
    
    if(a>0 && b>0) {
      database = new Database(a, b);
    }
    int count=0;
    
    while (scanner.hasNextLine()){
      
      String rt = scanner.nextLine();
      if(a>0 && b>0) {
        String[] c = new String[a];// This String array is used to save the categories.
        for (int i = 0; i < a && scanner.hasNextLine(); i++) {
          c[i] = scanner.nextLine();
          database.setCategory(i, c[i]);
          count++;
        }
      }
      
      if (count!=a){
        return null;
      }
      
      if(a>0 && b>0) {
        String[][] ques = new String[a][b];// This multi-dimensional String array is used to save the questions.
        String[][] ans = new String[a][b];// This multi-dimensional String array is used to save the answers.
        for (int i = 0; i < a && scanner.hasNextLine(); i++) {
          for (int j = 0; j < b && scanner.hasNextLine(); j++) {
            try {
              ans[i][j] = scanner.nextLine();
              ques[i][j] = scanner.nextLine();
              Question question = new Question(ans[i][j], ques[i][j]);
              database.setQuestions(i, j, question);
            }
            catch (NoSuchElementException e){
              return null;
            }
          }
        }
      }
    }
    
    return database;
    
  }
  
  /**
   * This method returns the <i>String</i> object from category[].
   * @param index
   *          The position of the particular <i>String</i> object in the array.
   * @return
   *          The <i>String</i> object at the provided index.
   */
  public String getCategory(int index){
    
    return categories[index];
    
  }
  
  /**
   * This method inserts the <i>String</i> object in the category[].
   * @param index
   *          The position to add the <i>String</i> object in the array.
   * @param category
   *          The <i>String</i> object to be added at the provided index.
   */
  public void setCategory(int index, String category) {
    
    categories[index] = category;
    
  }
  
  /**
   * This method returns the <i>Question</i> object from questions[][].
   * @param category
   *          The position of the column in which the <i>Question</i> object is in the array.
   * @param index
   *          The position of the row in which the <i>Question</i> object is in the array.
   * @return
   *          The <i>Question</i> object at the provided index.
   */
  public Question getQuestions(int category, int index) {
    
    return questions[category][index];
    
  }
  /**
   * This method inserts the <i>Question</i> object in questions[][].
   * @param category
   *          The position of the column in which the <i>Question</i> object is added in the array.
   * @param index
   *          The position of the row in which the <i>Question</i> object is added in the array.
   * @param question
   *          The <i>Question</i> object to be added at the provided position.
   */
  public void setQuestions(int category, int index, Question question) {
    
    this.questions[category][index] = question;
    
  }
  
  /**
   * Returns the number of categories.
   *
   * @return m
   *         the number of categories.
   */
  public int getNumCategories() {
    
    return m;
    
  }
  
  /**
   * Returns the number of questions.
   *
   * @return n
   *         the number of questions.
   */
  public int getNumQuestions() {
    
    return n;
    
  }
  
  /**
   * Java programs start by executing the main method. Here, this main method displays the information of the students.
   *
   * @param args
   *            the command line arguments
   */
  
  public static void main(String[] args){
    StudentInfo.display();
  }
  
}
