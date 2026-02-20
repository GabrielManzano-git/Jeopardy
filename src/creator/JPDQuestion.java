package creator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPDQuestion extends JPanel {
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 28;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    String question;
    String answer;
    int value;

    JTextField qField;
    JTextField aField;
    JTextField vField;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    
    JPDQuestion(){
        this("", "", 0);
    }

    JPDQuestion(String question, String answer, int value){
        qField = new JTextField(question);
        compFormatter(qField);
        this.question = question;

        aField = new JTextField(answer);
        compFormatter(aField);
        this.answer = answer;

        vField = new JTextField("" + value);
        compFormatter(vField);
        this.value = value;

        label1 = new JLabel("Question");
        compFormatter(label1);
        label2 = new JLabel("Answer");
        compFormatter(label2);
        label3 = new JLabel("Value");
        compFormatter(label3);

        this.setLayout(new GridLayout(3, 2));
        this.add(label1);
        this.add(qField);
        this.add(label2);
        this.add(aField);
        this.add(label3);
        this.add(vField);

        this.setBackground(backgroundColor);
    }

    private void compFormatter(JComponent comp){
        comp.setFont(defaultFont);
        comp.setPreferredSize(new Dimension(96, 20));
    }

    public String getQuestion(){
        return qField.getText();
    }
    public String getAnswer(){
        return aField.getText();
    }
    public int getValue(){
        return Integer.parseInt(vField.getText());
    }
}
