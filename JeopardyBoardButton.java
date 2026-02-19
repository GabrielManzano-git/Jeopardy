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
    }

    public int GetRow(){
        return row;
    }
    public int GetColumn(){
        return column;
    }
    public int GetPointValue(){
        return pointValue;
    }
}
