import javax.swing.JButton;

public class JeopardyBoardButton extends JButton{
    private int row;
    private int column;
    private int pointValue;
    public JeopardyBoardButton(int row, int column){
        this(row, column, (row + 1) * 100);
    }

    public JeopardyBoardButton(int row, int column, int pointValue){
        super();
        this.row = row;
        this.column = column;
        this.pointValue = pointValue;
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
}
