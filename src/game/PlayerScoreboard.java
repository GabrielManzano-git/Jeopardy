package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerScoreboard extends JPanel{
    int score = 0;
    String name;
    JTextField textField;
    JeopardyBoard board;

    public PlayerScoreboard(String name, JeopardyBoard board){
        super();

        this.board = board;
        this.setBackground(Color.lightGray);

        this.setPreferredSize(new Dimension(160, 70));
        this.setLayout(new FlowLayout());

        //Create team name
        this.name = name;
        JLabel nameLabel = new JLabel(name);
        this.add(nameLabel);
        nameLabel.setVerticalAlignment(JLabel.TOP);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        //Create point counter
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(50, 20));;
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setText("" + score);
        textField.addActionListener(e -> changePoints());
        this.add(textField);


        //Create add/subtract point buttons
        JButton addButton = new JButton("add");
        JButton subtractButton = new JButton("sub");
        this.add(addButton);
        this.add(subtractButton);
        addButton.setFocusable(false);
        subtractButton.setFocusable(false);
        addButton.addActionListener(e -> {changePoints(); addPoints();});
        subtractButton.addActionListener(e -> {changePoints(); subtractPoints();});
    }

    private void changePoints(){
        score = Integer.parseInt(textField.getText());
    }

    private void addPoints(){
        JeopardyBoardButton currQuestion = board.getCurrentButton();
        if(currQuestion == null) return;
        int currQuestionPoints = currQuestion.getPointValue();

        score += currQuestionPoints;
        textField.setText("" + score);
    }

    private void subtractPoints(){
        JeopardyBoardButton currQuestion = board.getCurrentButton();
        if(currQuestion == null) return;
        int currQuestionPoints = currQuestion.getPointValue();

        score -= currQuestionPoints;
        textField.setText("" + score);
    }
}
