package main;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

import game.FileSelector;

public class ModeSelector extends JFrame {
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 12;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    ModeSelector(){
        this.setSize(200, 100);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JButton makeFileButton = new JButton("Create new file");
        makeFileButton.setBackground(backgroundColor);
        makeFileButton.setFont(defaultFont);
        makeFileButton.addActionListener(e -> goToFileMaker());

        JButton playButton = new JButton("Play jeopardy");
        playButton.setBackground(backgroundColor);
        playButton.setFont(defaultFont);
        playButton.addActionListener(e -> goToGame());

        this.add(makeFileButton);
        this.add(playButton);

        this.setVisible(true);
    }

    private void goToFileMaker(){
        this.dispose();
    }
    private void goToGame(){
        new FileSelector();
        this.dispose();
    }
}
