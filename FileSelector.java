import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelector extends JFrame{

    JFileChooser fileChooser;
    JButton openFileChooserButton;
    int fileChooserResult;

    public FileSelector(){
        openFileChooserButton = new JButton("Select File");
        openFileChooserButton.addActionListener(e -> openFile());
        openFileChooserButton.setFocusable(false);
        openFileChooserButton.setPreferredSize(new Dimension(100, 50));
        fileChooser = new JFileChooser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(100, 100);
        this.add(openFileChooserButton);

        this.setVisible(true);
    }

    private void openFile(){
        fileChooserResult = fileChooser.showOpenDialog(null);
        if(fileChooserResult == 1) return;

        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

        try(Scanner s = new Scanner(file)){
            new JeopardyBoard(s);
            this.dispose();
        } catch(Exception e){

        }
    }
}
