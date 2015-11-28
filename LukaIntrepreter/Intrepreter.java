
import java.awt.*;
import java.util.NoSuchElementException;

/**
 * Luka Virtual Machine (LVM) -- An interpreter for the Luka programming
 * language.
 *
 * @author Marcel Turcotte and Princejeet Singh Sandhu
 */
public class Interpreter {


    /**
     * Class variable. Newline symbol on this machine at run-time.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Instance variable. Defines the font for the <I>type</I> method.
     */
    private Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );

    /**
     * Instance variable. The operands stack.
     */
    private Stack<Token> operands;
    /**
     * Instance variable. The string stack.
     */
    private Stack<Token> strings;
    /**
     * Instance variable. The quotedSymbol stack.
     */
    private Stack<Token> quotedSymbol;

    /**
     * Instance variable. The Dictionary Object.
     */
    private Dictionary dictionary = new Dictionary();

    /**
     * Instance variable. A reference to a lexical analyzer (Reader).
     */
    private Reader r;

    /**
     * Instance variable. Coordinate x of the graphics state.
     */
    private int gsX;

    /**
     * Instance variable. Coordinate y of the graphics state.
     */
    private int gsY;

    /**
     * Instance variable. Color of the pen.
     */
    private Color gsColor;

    /**
     * Initializes this newly created interpreter so that the operand stack is
     * empty, the accumulator is set 0, the cursor is at (0,0), and the default
     * color is blue.
     */
    public Interpreter() {
        reset();
    }

    /**
     * Auxiliary method that resets the graphics state of this interpreter.
     */
    private void reset() {

        operands = new LinkedStack<Token>();
        quotedSymbol = new LinkedStack<Token>();
        strings = new LinkedStack<Token>();
        gsX = 0;
        gsY = 0;
        gsColor = Color.BLUE;
    }

    /**
     * Executes the input program and displays the result onto the Graphics
     * object received as an argument.
     *
     * @param program contains the source to be executed.
     * @param g the graphics context.
     */

    public void execute(String program, Graphics g){

        reset();

        r = new Reader(program);

        g.setColor(gsColor);

        while (r.hasMoreTokens()) {
            Token t = r.nextToken();
            if (t.isNumber()) {

                operands.push(t);

            }else if (t.getSymbol().charAt(0)=='/' && t.getSymbol().length()>1){

                quotedSymbol.push(new Token(t.getSymbol().substring(1)));

            }else if (t.getSymbol().charAt(0)=='\"' && t.getSymbol().length()>1 ){
                String string=t.toString();
                Token token = r.nextToken();
                while (!token.equals(new Token("\""))) {
                    string=string+ " " +token.toString();
                    token=r.nextToken();
                }
                strings.push(new Token(string.substring(1)));

            }else if (t.getSymbol().equals("def")) {

                execute_def();

            }else if (t.getSymbol().equals("set")) {
                execute_set();
            }else if (t.getSymbol().equals("add")) {

                execute_add();

            } else if (t.getSymbol().equals("sub")) {

                execute_sub();

            }else if(dictionary.contains(t.getSymbol())){
                operands.push(dictionary.get(t.getSymbol()));
            }else if (t.getSymbol().equals("mul")) {

                execute_mul();

            } else if (t.getSymbol().equals("div")) {

                execute_div();

            } else if (t.getSymbol().equals("pop")) {

                execute_pop();

            } else if (t.getSymbol().equals("clear")) {

                execute_clear();

            } else if (t.getSymbol().equals("pstack")) {

                execute_pstack();

            }else if (t.getSymbol().equals("undef")) {

                execute_undef();

            } else if (t.getSymbol().equals("moveto")) {

                execute_moveto();

            } else if (t.getSymbol().equals("lineto")) {

                execute_lineto(g);

            } else if (t.getSymbol().equals("arc")) {

                execute_arc(g);

            } else if (t.getSymbol().equals("quit")) {

                execute_quit();

            } else if (t.getSymbol().equals("rectangle")) {

                execute_rectangle(g);

            } else if (t.getSymbol().equals("circle")) {

                execute_circle(g);

            } else if (t.getSymbol().equals("color")) {

                execute_color(g);

            } else if (t.getSymbol().equals("triangle")) {

                execute_triangle(g);

            }else if (t.getSymbol().equals("type")) {

                execute_type(g);

            }else if (t.getSymbol().equals("size48")) {

                stringFont = new Font("SansSerif", Font.PLAIN, 48);

            }else if (t.getSymbol().equals("size80")) {

                stringFont = new Font("SansSerif", Font.PLAIN, 80);

            }else {

                throw new LukaSyntaxException("The symbol is not been defined in dicyionary");

            }
        }
    }

    /**
     * Removes an association from the dictionary.
     *
     * @throws LukaSyntaxException
     */

    private void execute_undef() throws LukaSyntaxException{
        Token op1 = quotedSymbol.pop();
        try{
            dictionary.remove(op1.getSymbol());
        }catch(NullPointerException e)
        {
            System.err.println(e);
            String error= dictionary+"\n"+operands+"\nLukaSyntaxException: " + op1.getSymbol() + " not found caught LukaSyntaxException";
            throw new LukaSyntaxException(error);
        }catch(NoSuchElementException e)
        {
            System.err.println(e);
            String error= dictionary+"\n"+operands+"\nLukaSyntaxException: " + op1.getSymbol() + " not found caught LukaSyntaxException";
            throw new LukaSyntaxException(error);
        }
    }

    /**
     * Set is used to assign a new value to a symbol for which an association already exists.
     *
     * @throws LukaSyntaxException
     */

    private void execute_set() throws LukaSyntaxException{
        Token op1 = quotedSymbol.pop();
        Token op2 = operands.pop();
        try{
            dictionary.replace(op1.getSymbol(), op2);
        }catch(NullPointerException e)
        {
            System.err.println(e);
            String error= dictionary+"\n"+operands+"\nLukaSyntaxException: " + op1.getSymbol() + " no found caught LukaSyntaxException";
            throw new LukaSyntaxException(error);
        }catch(NoSuchElementException e)
        {
            System.err.println(e);
            String error= dictionary+"\n"+operands+"\nLukaSyntaxException: " + op1.getSymbol() + " no found caught LukaSyntaxException";
            throw new LukaSyntaxException(error);
        }
    }

    /**
     * Changes the color of the Pen
     *
     * @param g
     */

    private void execute_color(Graphics g) {
        Token blue = operands.pop();
        Token green = operands.pop();
        Token red = operands.pop();
        g.setColor(new Color(red.getNumber(),green.getNumber(),blue.getNumber()));
    }

    /**
     * Displays the String on the Canvas
     *
     * @param g
     */

    private void execute_type(Graphics g) {
        Token content = strings.pop();
        Token y = operands.pop();
        Token x = operands.pop();
        g.setFont(stringFont);
        g.drawString(content.getSymbol(), x.getNumber(), y.getNumber());
        stringFont = new Font("SansSerif", Font.PLAIN,18);
    }

    /**
     * Displays a circle on the canvas
     *
     * @param g
     */

    private void execute_circle(Graphics g) {
        Token radius = operands.pop();
        Token y = operands.pop();
        Token x = operands.pop();
        g.fillArc(x.getNumber(),y.getNumber(),radius.getNumber(),radius.getNumber(),0,360);
    }

    /**
     * Displays a triangle on the canvas
     *
     * @param g
     */
    private void execute_triangle(Graphics g) {
        Token y3 = operands.pop();
        Token x3 = operands.pop();
        Token y2 = operands.pop();
        Token x2 = operands.pop();
        Token y1 = operands.pop();
        Token x1 = operands.pop();
        int[] x ={x1.getNumber(),x2.getNumber(),x3.getNumber()};
        int[] y ={y1.getNumber(),y2.getNumber(),y3.getNumber()};
        g.fillPolygon(x,y,3);
    }
    /**
     * Displays a rectangle on the canvas
     *
     * @param g
     */
    private void execute_rectangle(Graphics g) {
        Token height = operands.pop();
        Token width = operands.pop();
        Token y = operands.pop();
        Token x = operands.pop();
        g.fillRect(x.getNumber(), y.getNumber(), width.getNumber(), height.getNumber());
    }

    /**Def is used to create an association between a value and a symbol.
     *
     * @throws LukaSyntaxException
     */

    private void execute_def() throws LukaSyntaxException{
        Token op1 = quotedSymbol.pop();
        Token op2 = operands.pop();
        try{
            dictionary.put(op1.getSymbol(), op2);
        }catch(NullPointerException e)
        {
            System.err.println(e);
            String error= dictionary+"\n"+operands+"\nLukaSyntaxException: " + op1.getSymbol() + " not defined caught LukaSyntaxException";
            throw new LukaSyntaxException(error);
        }
    }

    /**
     * pops off the top two elements from the operands stack, adds them together and pushes back the result onto the stack.
     */
    private void execute_add(){
            Token op1 = operands.pop();
            Token op2 = operands.pop();
            Token res = new Token( op1.getNumber()+op2.getNumber());
            operands.push(res);
    }

    /**
     * pops off the top two elements from the operands stack, subtracts them together and pushes back the result onto the stack
     */
    private void execute_sub() {
        Token op1 = operands.pop();
        Token op2 = operands.pop();
        Token res = new Token(op2.getNumber() - op1.getNumber());
        operands.push(res);
    }

    /**
     * pops off the top two elements from the operands stack, multiplies them together and pushes back the result onto the stack.
     */
    private void execute_mul() {
        Token op1 = operands.pop();
        Token op2 = operands.pop();
        Token res = new Token(op1.getNumber() * op2.getNumber());
        operands.push(res);
    }

    /**
     * pops off the top two elements from the operands stack, divides them and pushes back the result onto the stack.
     */
    private void execute_div() {
        Token op1 = operands.pop();
        Token op2 = operands.pop();
        Token res = new Token(op2.getNumber() / op1.getNumber());
        operands.push(res);
    }

    /**
     * removes the top element of the stack
     */
    private void execute_pop() {
        operands.pop();
    }

    /**
     * sets the position of the pen to (x′,y′), where (x′,y′) are read from the stack
     */
    private void execute_moveto() {
        Token y = operands.pop();
        Token x = operands.pop();
        gsX = x.getNumber();
        gsY = y.getNumber();
    }

    /**
     * draws a line from (x,y) to (x′,y′), where (x,y) is the current location of the pen (which is part of the graphics state of the interpreter), and (x′,y′) are read from the stack. Once the line has been drawn, the position of the pen, (x,y), is set to (x′,y′).
     *
     * @param g
     */
    private void execute_lineto(Graphics g) {
        Token y = operands.pop();
        Token x = operands.pop();
        g.drawLine(gsX, gsY, x.getNumber(), y.getNumber());
        gsX = x.getNumber();
        gsY = y.getNumber();
    }

    /**
     * draws an arc onto the Graphics object using the method drawArc( x, y, radius, radius, startAngle, angle ), where (x,y) is the current location of the pen (which is part of the graphics state of the interpreter), and the radius, startAngle and angle are read from the stack.
     *
     * @param g
     */
    private void execute_arc(Graphics g) {
        Token a2 = operands.pop();
        Token a1 = operands.pop();
        Token r = operands.pop();
        g.drawArc(gsX, gsY, r.getNumber(), r.getNumber(), a1.getNumber(),
                a2.getNumber());
    }

    /**
     * displays the content of the stack in the Viewer’s console (JTextArea object designated by output). The representation starts with the symbol “[” representing the bottom of the stack. The content of the stack follows, starting with the bottom of the stack up to the top of the stack.
     */
    private void execute_pstack() {

        System.out.println(operands);

    }

    /**
     * removes all the elements from the operands stack.
     */
    private void execute_clear() {
        while (!operands.isEmpty()) {
            operands.pop();
        }
    }

    /**
     * exits the application (calls System.exit( 0 )).
     */
    private void execute_quit() {
        System.out.println("Bye!");
        System.exit(0);
    }

}
