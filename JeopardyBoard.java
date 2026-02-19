import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JeopardyBoard extends JFrame implements ActionListener{
    List<JLabel> categoryNames = new ArrayList<>();
    List<JeopardyBoardButton> buttons = new ArrayList<>();
    GridLayout gl = new GridLayout(6, 5);
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 28;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);
    ImageIcon jpdIcon = new ImageIcon("JPD.png");

    public JeopardyBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(gl);
        this.setSize(1024, 512);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setIconImage(jpdIcon.getImage());


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
            newLabel.setBackground(backgroundColor);
            newLabel.setOpaque(true);
            newLabel.setForeground(fontColor);
            newLabel.setFont(defaultFont);
            categoryNames.add(newLabel);
        }
    }

    private void ButtonFactory(int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                JeopardyBoardButton newButton = new JeopardyBoardButton(i, j);
                newButton.setText("$" + newButton.getPointValue());
                newButton.addActionListener(this);
                newButton.setBackground(backgroundColor);
                newButton.setForeground(fontColor);
                newButton.setFont(defaultFont);
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