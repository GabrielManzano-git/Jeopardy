import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeopardyBoard extends JFrame implements ActionListener{
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 28;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    List<JLabel> categoryNames = new ArrayList<>();
    List<JeopardyBoardButton> buttons = new ArrayList<>();
    JeopardyBoardButton currentButton = null;
    
    GridLayout gl = new GridLayout(6, 5);
    
    ImageIcon jpdIcon = new ImageIcon("JPD.png");
    JPanel questionBoard = new JPanel();
    CardLayout cl = new CardLayout();
    JPanel cardPanel = new JPanel();
    JLayeredPane layeredPane = new JLayeredPane();

    JPanel qaPanel = new JPanel();
    JPanel qPanel = new JPanel(new FlowLayout());
    JPanel aPanel = new JPanel(new FlowLayout());
    JTextArea qLabel = new JTextArea();
    JTextArea aLabel = new JTextArea();
    JButton revealAnswerButton = new JButton("Reveal Answer");
    JButton mainMenuButton = new JButton("Back to main");

    public JeopardyBoard(Scanner inputFile){
        //Create main window with a card layout
        this.add(cardPanel);
        cardPanel.setLayout(cl);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024, 582);
        //this.setResizable(false);
        this.setBackground(Color.BLACK);
        cardPanel.setBackground(Color.BLACK);
        this.setIconImage(jpdIcon.getImage());


        //Prepare the question board
        questionBoard.setLayout(gl);
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(questionBoard, null, JLayeredPane.DEFAULT_LAYER);
        cardPanel.add(layeredPane);
        cl.addLayoutComponent(layeredPane, "Question Select Board");

        //Create sample column names and buttons
        LabelFactory(5);
        for(var l : categoryNames){
            questionBoard.add(l);
        }


        ButtonFactory(5, 5);
        for(var b : buttons){
            questionBoard.add(b);
        }

        //Create area for player scoreboards
        JPanel scoreBoardPanel = new JPanel();
        scoreBoardPanel.setLayout(new FlowLayout());
        scoreBoardPanel.setBackground(backgroundColor);
        layeredPane.add(scoreBoardPanel, BorderLayout.SOUTH, JLayeredPane.PALETTE_LAYER);


        //Create sample player scoreboard
        PlayerScoreboard sampleBoard = new PlayerScoreboard("Test name", this);
        scoreBoardPanel.add(sampleBoard);

        PlayerScoreboard sampleBoard2 = new PlayerScoreboard("Test name 2", this);
        scoreBoardPanel.add(sampleBoard2);


        //Create Q/A scene
        qaPanel.setLayout(new GridLayout(2, 1, 10, 10));
        qaPanel.setBackground(Color.black);
        qPanel.setBackground(backgroundColor);
        qPanel.setLayout(new BorderLayout());
        aPanel.setLayout(new BorderLayout());
        aPanel.setBackground(backgroundColor);
        setupLabel(qLabel);
        setupLabel(aLabel);
        qaPanel.add(qPanel);
        qaPanel.add(aPanel);
        qPanel.add(qLabel);
        qPanel.add(revealAnswerButton, BorderLayout.EAST);
        revealAnswerButton.setFocusable(false);
        revealAnswerButton.addActionListener(e -> revealAnswer());
        aPanel.add(aLabel);
        aPanel.add(mainMenuButton, BorderLayout.EAST);
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(e -> {cl.show(cardPanel, "Question Select Board");});
        cardPanel.add(qaPanel);
        cl.addLayoutComponent(qaPanel, "QA Board");



        //Show board
        cl.show(cardPanel, "Question Select Board");
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

    private void setupLabel(JTextArea label){
        // Enable line wrapping
        label.setLineWrap(true);

        // Enable wrapping by word (not character) for better readability
        label.setWrapStyleWord(true);

        

        // Make it look like a JLabel
        label.setEditable(false); // Make it non-editable
        label.setOpaque(false); // Make background transparent
        label.setFocusable(false); // Prevent it from getting focus
        label.setBackground(backgroundColor); // Match JLabel background
        label.setFont(defaultFont); // Match JLabel font
        label.setForeground(Color.WHITE);
    }

    private void ButtonFactory(int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                JeopardyBoardButton newButton = new JeopardyBoardButton(i, j);
                newButton.setText("$" + newButton.getPointValue());
                newButton.addActionListener(e -> {
                    currentButton = newButton;
                    doBoardButtonClick();
                    System.out.println("You pressed a button of value: " + newButton.getPointValue());});
                newButton.setBackground(backgroundColor);
                newButton.setForeground(fontColor);
                newButton.setFont(defaultFont);
                buttons.add(newButton);
            }
        }
    } 

    public JeopardyBoardButton getCurrentButton(){
        return currentButton;
    }

    public void doBoardButtonClick(){
        setQuestionAnswer();
        cl.show(cardPanel, "QA Board");
    }

    private void setQuestionAnswer(){
        if(currentButton == null) return;
        qLabel.setText(currentButton.getQuestion().getQuestion());
        aLabel.setText("");
    }

    private void revealAnswer(){
        aLabel.setText(currentButton.getQuestion().getAnswer());
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