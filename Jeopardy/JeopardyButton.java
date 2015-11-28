import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;

// Author : Princejeet Singh Sandhu 

/**
 * In the application <b>Jeopardy</b>, a <b>JeopardyButton</b> is a specialized type of
 * <b>JButton</b> when clicked provides the user with number of category and question.
 */

public class JeopardyButton extends JButton {
  
  private int category, question;
  /**
   * <ul>
   *     <li><b>category</b> is an <i>int</i> variable which is used to store the number of category.</li>
   *     <li><b>question</b> is an <i>int</i> variable which is used to store the number of question.</li>
   * </ul>
   */
  
  /**
   * 
   */
  
  public JeopardyButton(ActionListener listener,int category, int question , int amount){
    
    super("$ "+amount);
    this.setBackground(Color.cyan);
    this.category=category;
    this.question=question;
  }
  
  /**
   * Returns the category number of the <i>JeopardyButton</i>.
   *
   * @return category
   *         the category number
   */  
  public int getCategory() {
    
    return category;
    
  }
  
  /**
   * Returns the question number of the <i>JeopardyButton</i>.
   *
   * @return question
   *         the question number
   */  
  public int getQuestion() {
    
    return question;
    
  }
  
}
