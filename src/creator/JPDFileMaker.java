package creator;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class JPDFileMaker extends JFrame{
    
    int rows;
    int columns;

    File currentFile;
    String JPD_toString;

    List<JPDCategory> catList = new ArrayList<>();
    List<JPDQuestion> qList = new ArrayList<>();

    public JPDFileMaker(){
        try(Scanner s = new Scanner(currentFile)){
            s.nextLine(); //Eat JPD at beginning of file
            rows = Integer.parseInt(s.nextLine());
            columns = Integer.parseInt(s.nextLine());

        } catch(Exception e){
            rows = 5;
            columns = 5;
        }

        this.setLayout(new GridLayout(rows + 1, columns + 1, 5, 5));
        this.add(new JPDRowColumn(this, rows, columns));
        for(int i = 0; i < columns; i++){
            JPDCategory newCat = new JPDCategory(null);
            catList.add(newCat);
            this.add(newCat);
        }
        for(int i = 0; i < rows; i++){
            this.add(new JPDBlankPanel());
            for(int j = 0; j < columns; j++){
                JPDQuestion newQuestion = new JPDQuestion();
                qList.add(newQuestion);
                this.add(newQuestion);
            }
        }

        this.setSize(1536,582);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }

    private void readFile(Scanner in){
        //TODO
    }

    private void writeFile(){
        //TODO
    }

    void refreshBoard(int rows, int columns){

    }
}
