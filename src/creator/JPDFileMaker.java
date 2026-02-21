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

    JPDRowColumn rowCol = new JPDRowColumn(this, 5, 5);
    List<JPDCategory> catList = new ArrayList<>();
    List<JPDBlankPanel> blankList = new ArrayList<>();
    List<List<JPDQuestion>> qListList = new ArrayList<>();

    JFileChooser fileChooser = new JFileChooser();

    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("Files");
    JMenuItem openFile = new JMenuItem("Open File");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem saveAs = new JMenuItem("Save As");
    JMenuItem newFile = new JMenuItem("New");

    public JPDFileMaker(){
        rows = 5;
        columns = 5;

        gl = new GridLayout(rows + 1, columns + 1, 5, 5);
        this.setLayout(gl);
        this.add(rowCol);
        for(int i = 0; i < columns; i++){
            JPDCategory newCat = new JPDCategory(null);
            catList.add(newCat);
            this.add(newCat);
        }
        for(int i = 0; i < rows; i++){
            JPDBlankPanel newPanel = new JPDBlankPanel();
            blankList.add(newPanel);
            this.add(newPanel);
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
        clearBoard();
        readFile();
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
        rows = 5;
        columns = 5;
        rowCol.setRows(rows);
        rowCol.setCols(columns);

        clearBoard();

        gl = new GridLayout(rows + 1, columns + 1, 5, 5);
        this.setLayout(gl);

        for(int i = 0; i < columns; i++){
            JPDCategory newCat = new JPDCategory(null);
            catList.add(newCat);
            this.add(newCat);
        }
        for(int i = 0; i < rows; i++){
            JPDBlankPanel newPanel = new JPDBlankPanel();
            blankList.add(newPanel);
            this.add(newPanel);
            List<JPDQuestion> nList = new ArrayList<>();
            qListList.add(nList);
            for(int j = 0; j < columns; j++){
                JPDQuestion newQuestion = new JPDQuestion();
                nList.add(newQuestion);
                this.add(newQuestion);
            }
        }

        this.revalidate();
        this.repaint();
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
            rowCol.setRows(rows);
            rowCol.setCols(columns);

            for(int i = 0; i < columns; i++){
                JPDCategory newCat = new JPDCategory(in.nextLine());
                catList.add(newCat);
                this.add(newCat);
            }

            for(int i = 0; i < rows; i++){
                JPDBlankPanel newBlank = new JPDBlankPanel();
                this.add(newBlank);
                blankList.add(newBlank);
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

            this.revalidate();
            this.repaint();

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

    private void clearBoard(){
        for(var a : catList){
            this.remove(a);
        }
        catList.clear();
        for(var a : blankList){
            this.remove(a);
        }
        blankList.clear();
        for(var a : qListList){
            for(var b : a){
                this.remove(b);
            }
            a.clear();
        }
        qListList.clear();
    }

    void refreshBoard(int rows, int columns){
        int newRows = rowCol.getRows();
        int newColumns = rowCol.getCols();
        gl = new GridLayout(newRows + 1, newColumns + 1, 5, 5);
        this.setLayout(gl);

        for(var b : blankList){
            this.remove(b);
        } blankList.clear();
        for(var c : catList){
            this.remove(c);
        }
        for(var l : qListList){
            for(var q : l){
                this.remove(q);
            }
        }

        while(catList.size() > newColumns){
            catList.remove(newColumns);
        }
        for(var l : qListList){
            while(l.size() > newColumns){
                l.remove(newColumns);
            }
        }

        while(qListList.size() > newRows){
            qListList.remove(newRows);
        }

        while(catList.size() < newColumns){
            JPDCategory newCat = new JPDCategory("");
            catList.add(newCat);
        }
        for(var l : qListList){
            while(l.size() < newColumns){
                JPDQuestion newQuestion = new JPDQuestion();
                l.add(newQuestion);
            }
        }

        while(qListList.size() < newRows){
            List<JPDQuestion> newList = new ArrayList<>();
            for(int i = 0; i < newColumns; i++){
                JPDQuestion newQuestion = new JPDQuestion();
                newList.add(newQuestion);
            }
            qListList.add(newList);
        }

        for(var c : catList){
            this.add(c);
        }
        for(var l : qListList){
            JPDBlankPanel newBlank = new JPDBlankPanel();
            this.add(newBlank);
            blankList.add(newBlank);
            for(var q : l){
                this.add(q);
            }
        }

        rows = newRows;
        columns = newColumns;

        this.revalidate();
        this.repaint();
    }
}
