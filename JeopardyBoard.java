import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    JLabel qLabel = new JLabel();
    JLabel aLabel = new JLabel();
    JButton revealAnswerButton = new JButton("Reveal Answer");
    JButton mainMenuButton = new JButton("Back to main");

    public JeopardyBoard(){
        //Create main window with a card layout
        this.add(cardPanel);
        cardPanel.setLayout(cl);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024, 582);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        cardPanel.setBackground(Color.BLACK);
        this.setIconImage(jpdIcon.getImage());


        //Prepare the question board
        questionBoard.setLayout(gl);
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(questionBoard, null, JLayeredPane.DEFAULT_LAYER);
        cardPanel.add(layeredPane);

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
        qaPanel.setLayout(new BorderLayout(0, 0));
        qaPanel.setBackground(Color.black);
        qPanel.setPreferredSize(new Dimension(100, 286));
        qPanel.setBackground(backgroundColor);
        aPanel.setPreferredSize(new Dimension(100, 286));
        aPanel.setBackground(backgroundColor);
        qLabel.setFont(defaultFont);
        qLabel.setBackground(backgroundColor);
        qLabel.setForeground(Color.WHITE);
        aLabel.setFont(defaultFont);
        aLabel.setBackground(backgroundColor);
        aLabel.setForeground(Color.WHITE);
        qaPanel.add(qPanel, BorderLayout.NORTH);
        qaPanel.add(aPanel, BorderLayout.SOUTH);
        qPanel.add(qLabel);
        qPanel.add(revealAnswerButton);
        revealAnswerButton.setFocusable(false);
        revealAnswerButton.addActionListener(e -> revealAnswer());
        aPanel.add(aLabel);
        aPanel.add(mainMenuButton);
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(e -> {cl.first(cardPanel);});
        cardPanel.add(qaPanel);



        //Show board
        cl.first(cardPanel);
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
        cl.last(cardPanel);
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