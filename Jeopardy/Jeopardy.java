import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Author : Princejeet Singh Sandhu 

/**In this application, <b>Jeopardy</b> is a specialized type of <i>JFrame</i> that
  *holds three <i>JPanel</i> objects and it also defines the events, which occur when
  *objects in different panels are clicked.
  */
public class Jeopardy extends JFrame implements ActionListener {
  
  private JeopardyButton[][] jeopardyButtons;
  /**
   * An array of buttons which stores two numbers, category and question and each button has <I>Action Listener</I> added
   * to it which assigns an event to it.
   */
  private Database database = Database.readQuestions("File.txt");
  /**
   * Object of <B>Database Class</B> which is used to access the <i>Questions</i> and <i>Responses</i> saved in the <B>Database Class</B>.
   */
  private JLabel question, answer;
  /**
   * <B>JLabel</B> object question and answer is used to display question and reponse that we get from the <I>Database</I> object for the
   * particular category and question that we get from Jeopardy Button.
   */
  private JPanel grid, header,footer;
  /**
   * Three <B>JPanel</B> objects are used in the <I>Jeopardy</I> frame. There functionality is as discussed below:
   * <ul>
   *     <li><b>header</b> is a <i>JPanel</i> object which has labels in it and each label specifies one category.</li>
   *     <li><b>grid</b> is a <i>JPanel</i> object that displays an array of JeopardyButton objects.</li>
   *     <li><B>header</B> is a <i>JPanel</i> object which displays two JLabel objects question and answer and also displays 
   *     a <i>JPanel</i> object which contains two <I>JButton</I> objects.</li>
   * </ul>
   */
  private JButton reveal, load;
  /**
   * <ul>
   *     <li><B>reveal</B> is a <i>JButton</i> object which is used to reveal the answer to the question displayed after clicking a <I>JeopardyButton</I></li>
   *     <li><B>load</B> is a <i>JButton</i> object which is used to display a <i>JOptionPane</i> which takes a <b>File Name</b> as an input.</li>
   * </ul>
   */
  private int a=-1,b=-1,amount = 100;
  /**
   * <ul>
   *     <li><b>a</b> is an <i>int</i> variable which is used to store the number of category received after pressing a <i>JeopardyButton</i>  object.</li>
   *     <li><b>b</b> is an <i>int</i> variable which is used to store the number of question received after pressing a <i>JeopardyButton</i>  object.</li>
   *     <li><b>amount</b> is an <i>int</i> variable which is assigned value of 100 and this value is passed to a <i>JeopardyButton</i>  object.</li>
   * </ul>
   */
  private int col = database.getNumCategories(), row = database.getNumQuestions();
  /**
   * <ul>
   *     <li><b>col</b> is an <i>int</i> variable which receives the value of number of categories from <i>Database</i>  object.</li>
   *     <li><b>row</b> is an <i>int</i> variable which receives the value of number of questions from <i>Database</i>  object.</li>
   * </ul>
   */
  
  /**
   * 
   */
  
  
  public Jeopardy() {
    
    super("Jeopardy");
    header = new JPanel();
    header.setBackground(Color.yellow);
    header.setLayout(new GridLayout(1, col));
    for (int i = 0; i<col; i++ ) {
      header.add(new JLabel(database.getCategory(i),SwingConstants.CENTER));
    }
    add(header, BorderLayout.NORTH);
    grid = new JPanel();
    grid.setLayout(new GridLayout(row, col));
    jeopardyButtons = new JeopardyButton[col][row];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        jeopardyButtons[j][i] = new JeopardyButton(this, j , i , amount);
        jeopardyButtons[j][i].addActionListener(this);
        grid.add(jeopardyButtons[j][i]);
      }
      amount += 100;
    }
    add(grid, BorderLayout.CENTER);
    footer = new JPanel();
    footer.setLayout(new GridLayout(3, 1));
    question = new JLabel();
    footer.add(question);
    answer = new JLabel();
    footer.add(answer);
    JPanel buttons = new JPanel();
    reveal = new JButton("Reveal");
    reveal.setBackground(Color.cyan);
    reveal.addActionListener(this);
    load = new JButton("Load");
    load.setBackground(Color.cyan);
    load.addActionListener(this);
    footer.setBackground(Color.white);
    buttons.add(reveal);
    buttons.add(load);
    buttons.setBackground(Color.white);
    footer.add(buttons);
    add(footer, BorderLayout.SOUTH);
    
  }
  
  /**
   * This method is implemented as part of the contract specified by
   * ActionListener. When the user clicks the reset button, it performs the various functions assigned to different objects.
   * Written below are the functions that objects perform when they are clicked.
   * <ul>
   *     <li>When a <b>JeopardyButton</b> object is pressed, its label is changed to "-", displays the question and also the number of category and number of question is saved from the object.</li>
   *     <li>When <b>reveal</b> JButton is pressed, it uses the number of category and number of question from <i>JeopardyButton</i> object to display the answer.</li>
   *     <li>When <b>load</b> JButton is pressed, it display a <i>JOptionPane</i> which takes a <b>File Name</b> as an input and then reset the <i>JFrame</i>.</li>
   * </ul>
   *
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  
  public void actionPerformed(ActionEvent e) {
    
    if(e.getSource() instanceof JeopardyButton){
      JeopardyButton button = (JeopardyButton) e.getSource();
      a=button.getCategory();
      b=button.getQuestion();
      question.setText("<HTML>" + database.getQuestions(a, b).getQuestion()+"</HTML>");
      button.setText("-");
      button.setBackground(Color.RED);
    }
    else if (e.getSource() == reveal) {
      if (a == -1 && b == -1) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Click on a button first");
      } else {
        answer.setText("<HTML>"+database.getQuestions(a,b).getResponse()+"</HTML>");
      }
    }
    else if (e.getSource() == load){
      JFrame frame = new JFrame();
      String db;
      boolean error;
      try {
        error = true;
        db = JOptionPane.showInputDialog(frame, "Name of file to be uploaded");
        reset(db);
      }
      catch(Exception E) {
        JOptionPane.showMessageDialog(null, "Sorry, the input is invalid, try again", "Wrong Input", JOptionPane.PLAIN_MESSAGE);
        error = false;
      }
    }
    
  }
  
  /**
   * Re-initializes all the <i>JPanel</i> objects of the frame using new file assigned from the <i>JOptionPane</i>.
   */
  
  private void reset(String db) {
    remove(grid);
    remove(header);
    pack();
    database = Database.readQuestions(db);
    int cat = database.getNumCategories();
    int ques = database.getNumQuestions();
    header = new JPanel();
    header.setBackground(Color.yellow);
    header.setLayout(new GridLayout(1, col));
    for (int i = 0; i<cat; i++ ) {
      header.add(new JLabel(database.getCategory(i),SwingConstants.CENTER));
    }
    grid=new JPanel();
    grid.setLayout(new GridLayout(ques,cat));
    jeopardyButtons = new JeopardyButton[cat][ques];
    amount=100;
    for (int i = 0; i < ques; i++) {
      for (int j = 0; j < cat; j++) {
        jeopardyButtons[j][i] = new JeopardyButton(this, j , i , amount);
        jeopardyButtons[j][i].addActionListener(this);
        grid.add(jeopardyButtons[j][i]);
      }
      amount += 100;
    }
    add(header,BorderLayout.NORTH);
    add(grid, BorderLayout.CENTER);
    setSize(new Dimension(500,300));
  }
  
  /**
   * Java programs start by executing the main method. Here, this main method
   * creates the main window of the application.
   *
   * @param args
   *            the command line arguments
   */
  public static void main(String[] args) {
    Jeopardy jeopardy = new Jeopardy();
    jeopardy.setVisible(true);
    jeopardy.setSize(new Dimension(500,300));
    jeopardy.setDefaultCloseOperation(Jeopardy.EXIT_ON_CLOSE);
    
  }
  
}


