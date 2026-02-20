package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TeamCreator extends JFrame {
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 12;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    JButton addTeamButton;
    JButton finishButton;
    JTextField teamNameField;

    public TeamCreator(Scanner s){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(300, 120);
        this.setResizable(false);
        this.setBackground(backgroundColor);

        JeopardyBoard jb = new JeopardyBoard(s);

        teamNameField = new JTextField();
        teamNameField.setToolTipText("Enter team name...");
        teamNameField.setPreferredSize(new Dimension(200, 30));
        teamNameField.addActionListener(e ->  {jb.addPlayer(teamNameField.getText()); teamNameField.setText("");});

        addTeamButton = new JButton("Add team");
        addTeamButton.addActionListener(e -> {jb.addPlayer(teamNameField.getText()); teamNameField.setText("");});
        addTeamButton.setFocusable(false);
        addTeamButton.setBackground(backgroundColor);
        addTeamButton.setForeground(Color.white);
        addTeamButton.setFont(defaultFont);

        finishButton = new JButton("Done");
        finishButton.addActionListener(e -> {jb.start(); this.dispose();});
        finishButton.setFocusable(false);
        finishButton.setBackground(backgroundColor);
        finishButton.setForeground(Color.white);
        addTeamButton.setFont(defaultFont);

        this.add(teamNameField);
        this.add(addTeamButton);
        this.add(finishButton);
        

        this.setVisible(true);
    }
    
}
