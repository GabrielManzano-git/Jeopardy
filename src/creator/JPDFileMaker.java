package creator;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JPDFileMaker extends JFrame{
    
    int rows;
    int columns;

    File currentFile;
    String JPD_toString;

    GridLayout gl;

    List<JPDCategory> catList = new ArrayList<>();
    List<List<JPDQuestion>> qListList = new ArrayList<>();

    JFileChooser fileChooser = new JFileChooser();

    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("Files");
    JMenuItem openFile = new JMenuItem("Open File");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem saveAs = new JMenuItem("Save As");
    JMenuItem newFile = new JMenuItem("New");

    public JPDFileMaker(File inFile){
        this.currentFile = inFile;

        readFile();

        menuBar.add(fileMenu);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAs);
        fileMenu.add(newFile);
        openFile.addActionListener(e -> doOpenFile());
        saveFile.addActionListener(e -> doSaveFile());
        saveAs.addActionListener(e -> doSaveAs());
        newFile.addActionListener(e -> doNewFile());

        this.setJMenuBar(menuBar);

        this.setSize(1536,582);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public JPDFileMaker(){
        rows = 5;
        columns = 5;

        gl = new GridLayout(rows + 1, columns + 1, 5, 5);
        this.setLayout(gl);
        this.add(new JPDRowColumn(this, rows, columns));
        for(int i = 0; i < columns; i++){
            JPDCategory newCat = new JPDCategory(null);
            catList.add(newCat);
            this.add(newCat);
        }
        for(int i = 0; i < rows; i++){
            this.add(new JPDBlankPanel());
            List<JPDQuestion> nList = new ArrayList<>();
            qListList.add(nList);
            for(int j = 0; j < columns; j++){
                JPDQuestion newQuestion = new JPDQuestion();
                nList.add(newQuestion);
                this.add(newQuestion);
            }
        }

        menuBar.add(fileMenu);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAs);
        fileMenu.add(newFile);
        openFile.addActionListener(e -> doOpenFile());
        saveFile.addActionListener(e -> doSaveFile());
        saveAs.addActionListener(e -> doSaveAs());
        newFile.addActionListener(e -> doNewFile());

        this.setJMenuBar(menuBar);

        this.setSize(1536,582);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private void doOpenFile(){
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "No file selected", "File error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        currentFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        new JPDFileMaker(currentFile);
        this.dispose();
    }

    private void doSaveFile(){
        if(currentFile == null) doSaveAs();
        writeFile();
    }

    private void doSaveAs(){
        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "No destination selected", "File error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        currentFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        doSaveFile();
    }

    private void doNewFile(){
        new JPDFileMaker();
        this.dispose();
    }

    private void readFile(){
        try(Scanner in = new Scanner(currentFile)){
            if(!in.nextLine().equals("JPD")){
                JOptionPane.showMessageDialog(null, "Bad file", "File error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            columns = Integer.parseInt(in.nextLine());
            rows = Integer.parseInt(in.nextLine());
            gl = new GridLayout(rows + 1, columns + 1, 5, 5);
            this.setLayout(gl);

            this.add(new JPDRowColumn(this, rows, columns));

            catList.clear();
            qListList.clear();

            for(int i = 0; i < columns; i++){
                JPDCategory newCat = new JPDCategory(in.nextLine());
                catList.add(newCat);
                this.add(newCat);
            }

            for(int i = 0; i < rows; i++){
                this.add(new JPDBlankPanel());
                List<JPDQuestion> newList = new ArrayList<>();
                qListList.add(newList);
                for(int j = 0; j < columns; j++){
                    int value = Integer.parseInt(in.nextLine());
                    String question = in.nextLine();
                    String answer = in.nextLine();
                    JPDQuestion newQuestion = new JPDQuestion(question, answer, value);
                    newList.add(newQuestion);
                    this.add(newQuestion);
                }
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "File could not be opened", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void writeFile(){
        try (PrintWriter out = new PrintWriter(new FileWriter(currentFile))) {
            out.println("JPD");
            out.println(columns);
            out.println(rows);
            for(var c : catList){
                out.println(c.getCategoryString());
            }
            for(var r : qListList){
                for(var q : r){
                    out.println(q.getValue());
                    out.println(q.getQuestion());
                    out.println(q.getAnswer());
                }
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File could not be opened", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void refreshBoard(int rows, int columns){

    }
}
