import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileSelector extends JFrame{
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 12;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    JFileChooser fileChooser;
    JButton openFileChooserButton;
    int fileChooserResult;

    public FileSelector(){
        openFileChooserButton = new JButton("Select File");
        openFileChooserButton.addActionListener(e -> openFile());
        openFileChooserButton.setFocusable(false);
        openFileChooserButton.setPreferredSize(new Dimension(100, 50));
        openFileChooserButton.setBackground(backgroundColor);
        openFileChooserButton.setForeground(Color.white);
        openFileChooserButton.setFont(defaultFont);
        fileChooser = new JFileChooser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(100, 100);
        this.setResizable(false);
        this.add(openFileChooserButton);

        this.setVisible(true);
    }

    private void openFile(){
        fileChooserResult = fileChooser.showOpenDialog(null);
        if(fileChooserResult == 1) return;

        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

        try(Scanner s = new Scanner(file)){
            if("JPD".equals(s.nextLine())){
                new TeamCreator(s);
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(null, "You tried to open an invalid file", "Invalid File", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "That file could not be opened", "Invalid File", JOptionPane.ERROR_MESSAGE);
        }
    }
}
