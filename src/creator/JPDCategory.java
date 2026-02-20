package creator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPDCategory extends JPanel{
    String category;
    JTextField textField;

    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 12;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);
    public JPDCategory(String category){
        if(category == null) this.category = "";
        else this.category = category;

        this.setLayout(new FlowLayout());

        textField = new JTextField(this.category);
        textField.setFont(defaultFont);
        textField.setPreferredSize(new Dimension(96, 50));;

        this.add(textField);

        this.setBackground(backgroundColor);
    }

    public String getCategoryString(){
        return textField.getText();
    }
}
