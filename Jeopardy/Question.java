public class Question {
  
// Author : Princejeet Singh Sandhu 
  
  /**
   * <b>Question</b> object is used to store question and the answer for that question in itself.
   */
  
  private String question, answer;
  /**
   * <ul>
   *     <li><i>String</i> object <b>question</b> is used to save question into it.</li>
   *     <li><i>String</i> object <b>answer</b> is used to save answer into it.</li>
   * </ul>
   */
  
  /**
   * 
   */
  
  public Question(String response, String question){
    
    this.question=question;
    answer=response;
    
  }
  
  /**
   * This method return <i>String</i> object <b>question.</b>
   * @return question
   *          The question saved in <b>Question</b> object.
   */
  public String getQuestion() {
    
    return question;
    
  }
  
  /**
   * This method return <i>String</i> object <b>answer.</b>
   * @return answer
   *          The response saved in <b>Question</b> object.
   */
  public String getResponse() {
    
    return answer;
    
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
