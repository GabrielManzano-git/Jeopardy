import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JeopardyBoard extends JFrame implements ActionListener{
    List<JeopardyBoardButton> buttons = new ArrayList<>();

    public JeopardyBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6, 5));
        this.setSize(1024, 512);
        this.setResizable(false);
        this.setVisible(true);

        ButtonFactory(5, 5);
        for(var b : buttons){
            this.add(b);
        }
    }

    private void ButtonFactory(int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                JeopardyBoardButton newButton = new JeopardyBoardButton(i, j);
                newButton.setText("" + newButton.GetPointValue());
                buttons.add(newButton);
            }
        }
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}