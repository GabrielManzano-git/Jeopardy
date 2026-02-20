package creator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class JPDBlankPanel extends JPanel{
    Color backgroundColor = new Color(0x060ce9);
    Color fontColor = new Color(0xFFD32C);
    int fontSize = 28;
    Font defaultFont = new Font("UTM Helvetins", Font.BOLD, fontSize);

    JPDBlankPanel(){
        this.setBackground(backgroundColor);
        this.setOpaque(true);
    }
}
