package creator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPDRowColumn extends JPanel {

    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 28;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);
    int smallerFontSize = 8;
    Font smallFont = new Font("UTM Helvetins", Font.BOLD, smallerFontSize);

    int rows;
    int columns;
    JTextField rowField;
    JLabel xText;
    JTextField colField;
    JButton refreshButton;

    JPDFileMaker parentHolder;

    public JPDRowColumn(JPDFileMaker parent, int rows, int columns){
        parentHolder = parent;
        this.setLayout(new GridLayout(2, 3));
        this.setBackground(backgroundColor);

        this.rows = rows;
        this.columns = columns;

        rowField = new JTextField();
        colField = new JTextField();
        rowField.setPreferredSize(new Dimension(32, 50));
        colField.setPreferredSize(new Dimension(32, 50));
        rowField.setText("" + rows);
        colField.setText("" + columns);
        rowField.setFont(defaultFont);
        colField.setFont(defaultFont);

        xText = new JLabel("x");
        xText.setFont(defaultFont);

        refreshButton = new JButton("Refresh");
        refreshButton.setFocusable(false);
        refreshButton.setFont(smallFont);
        refreshButton.addActionListener(e -> parentHolder.refreshBoard(this.rows, this.columns));

        this.add(rowField);
        this.add(xText);
        this.add(colField);
        this.add(new JPDBlankPanel());
        this.add(refreshButton);
    }

    public void setRows(int rows){
        this.rows = rows;
        rowField.setText("" + rows);
    }

    public void setCols(int cols){
        this.columns = cols;
        colField.setText("" + cols);
    }

    public int getRows(){
        rows = Integer.parseInt(rowField.getText());
        return rows;
    }

    public int getCols(){
        columns = Integer.parseInt(colField.getText());
        return columns;
    }
}