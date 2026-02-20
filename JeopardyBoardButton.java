import javax.swing.JButton;

public class JeopardyBoardButton extends JButton{
    private int row;
    private int column;
    private int pointValue;
    private JeopardyQuestion question;
    private boolean isAnswered = false;
    public JeopardyBoardButton(int row, int column){
        this(row, column, (row + 1) * 100);
    }

    public JeopardyBoardButton(int row, int column, int pointValue){
        this(row, column, pointValue, "Default Question blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", "Default Answer");
    }

    public JeopardyBoardButton(int row, int column, int pointValue, String question, String answer){
        super();
        this.row = row;
        this.column = column;
        this.pointValue = pointValue;
        this.question = new JeopardyQuestion(question, answer);
        this.setFocusable(false);
    }


    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int getPointValue(){
        return pointValue;
    }
    public JeopardyQuestion getQuestion(){
        return question;
    }
    public boolean getAnswered(){
        return isAnswered;
    }
}
