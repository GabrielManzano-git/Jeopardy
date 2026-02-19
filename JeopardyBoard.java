import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JeopardyBoard extends JFrame implements ActionListener{
    List<JLabel> categoryNames = new ArrayList<>();
    List<JeopardyBoardButton> buttons = new ArrayList<>();
    GridLayout gl = new GridLayout(6, 5);

    public JeopardyBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(gl);
        this.setSize(1024, 512);
        this.setResizable(false);

        LabelFactory(5);
        for(var l : categoryNames){
            this.add(l);
        }

        ButtonFactory(5, 5);
        for(var b : buttons){
            this.add(b);
        }


        this.setVisible(true);
    }

    private void LabelFactory(int rows){
        for(int i = 0; i < rows; i++){
            JLabel newLabel = new JLabel("Default label: " + i);
            newLabel.setHorizontalAlignment(JLabel.CENTER);
            categoryNames.add(newLabel);
        }
    }

    private void ButtonFactory(int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                JeopardyBoardButton newButton = new JeopardyBoardButton(i, j);
                newButton.setText("" + newButton.getPointValue());
                newButton.addActionListener(this);
                buttons.add(newButton);
            }
        }
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(JeopardyBoardButton.class.isInstance(e.getSource())){
            JeopardyBoardButton button = (JeopardyBoardButton) e.getSource();
            System.out.println("You pressed a button of value: " + button.getPointValue());
        }else{
            new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }
}