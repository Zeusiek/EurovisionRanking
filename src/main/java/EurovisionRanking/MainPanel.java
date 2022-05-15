package EurovisionRanking;

import javax.swing.*;
import java.awt.*;

/**
 * Główny panel aplikacji.
 * @see MyFrame
 * @author Mateusz Targoński
 * @since 0.1
 */
public class MainPanel extends JPanel{
    MainPanel(){
        setBackground(Color.decode("#2450ff"));
        setBounds(0,0,MyFrame.DIMENSION.width,70);
        setLayout(null);
        JLabel[] jLabels = new JLabel[]{
                new JLabel("Lp."),
                new JLabel("KRAJ"),
                new JLabel("PIOSENKA"),
                new JLabel("WYKONAWCA"),
                new JLabel("WOKAL"),
                new JLabel("STRÓJ"),
                new JLabel("MELODIA"),
                new JLabel("KAMERA"),
                new JLabel("SCENA"),
                new JLabel("CHOREOGRAFIA"),
                new JLabel("SUMA")
        };
        Font font = new Font("Arial", Font.BOLD, 15);

        JPanel categories = new JPanel();
        categories.setBounds(-60,20,MyFrame.DIMENSION.width+100, 40);
        for (JLabel jLabel: jLabels){
            if(jLabel.getText().equals("Lp."))
                jLabel.setPreferredSize(new Dimension(25, 45));
            else jLabel.setPreferredSize(new Dimension(120, 45));
            jLabel.setFont(font);
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            categories.add(jLabel);
        }
        add(categories);
    }
}
