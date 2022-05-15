package EurovisionRanking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CountryLabel extends JPanel{
    final Country country;
    final Points points;
    final JTextField voicePoints = new JTextField();
    final JTextField looksPoints = new JTextField();
    final JTextField stagePoints = new JTextField();
    final JTextField cameraPoints = new JTextField();
    final JTextField melodyPoints = new JTextField();
    final JTextField choreography = new JTextField();
    final JTextField[] fields = new JTextField[]{
            voicePoints, looksPoints, melodyPoints,
            cameraPoints, stagePoints, choreography
    };
    final JLabel sumUpLabel;
    final JLabel position = new JLabel("0");
    public int myPosition = 0;

    CountryLabel(Country country, Integer[] integers){
        this.country = country;
        this.points = new Points(country,integers[0],integers[1],integers[2],integers[3],integers[4], integers[5]);
        for (int i = 0; i < fields.length; i++)
            fields[i].setText(String.valueOf(integers[i]));
        this.sumUpLabel = new JLabel(String.valueOf(points.sumUp()));
        Font font = new Font("Arial", Font.BOLD, 15);
        this.setBorder(BorderFactory.createLineBorder(
                Color.GRAY, 1, false)
        );
        JLabel[] labels = new JLabel[]{
                position,
                new JLabel(country.name),
                new JLabel(country.band),
                new JLabel(country.songName)
        };

        for (JLabel label: labels){
            label.setFont(font);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            if(label.getText().matches("\\d*?"))
                label.setPreferredSize(new Dimension(25, 45));
            else label.setPreferredSize(new Dimension(130, 45));
            this.add(label);
        }
        for (int i = 0; i < fields.length; i++){
            JTextField field = fields[i];
            field.setPreferredSize(new Dimension(110,30));
            field.setFont(font);
            field.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    field.setText("");
                }
            });
            int finalI = i;

            field.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e){
                    refresh();
                    field.setBackground(ColorType.STANDARD.color);
                }
                @Override
                public void keyReleased(KeyEvent e){
                    try{
                        int transfer = Integer.parseInt(field.getText());
                        setPoints(Points.Type.values()[finalI], transfer);
                        field.setBackground(ColorType.GOOD.color);
                    } catch(BadValueException | NumberFormatException ex){
                        field.setBackground(ColorType.ERROR.color);
                    }finally {
                        refresh();

                    }
                }
            });
            add(field);
        }

        sumUpLabel.setPreferredSize(new Dimension(130,30));
        sumUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumUpLabel.setFont(font);
        add(sumUpLabel);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setPreferredSize(new Dimension(MyFrame.DIMENSION.width-40,70));

    }

    public void refresh(){
        for (int i = 0; i < fields.length; i++){
            int a;
            try{
                a = Integer.parseInt(fields[i].getText());
                setPoints(Points.Type.values()[i], a);
            } catch(BadValueException | NumberFormatException ignored){
            }
        }
        sumUpLabel.setText(String.valueOf(points.sumUp()));
        switch (position.getText()){
            case "I" -> setColour(ColorType.VICTORY);
            case "II" -> setColour(ColorType.SECOND);
            case "III" -> setColour(ColorType.THIRD);
            default -> setColour(ColorType.STANDARD);
        }

        repaint();
    }


    public void setPoints(Points.Type type, int val) throws BadValueException{
        switch (type){
            case VOICE -> points.setPoints(Points.Type.VOICE, val);
            case STAGE -> points.setPoints(Points.Type.STAGE, val);
            case LOOKS -> points.setPoints(Points.Type.LOOKS, val);
            case CAMERA -> points.setPoints(Points.Type.CAMERA,val);
            case MELODY -> points.setPoints(Points.Type.MELODY,val);
        }


    }


    public void setColour(ColorType type){
        this.setBackground(type.color);
        this.repaint();
    }
    public void setPosition(int number){
        myPosition = number+1;
        switch (myPosition){
            case 1 -> position.setText("I");
            case 2 -> position.setText("II");
            case 3 -> position.setText("III");
            default -> position.setText(String.valueOf(myPosition));
        }
        refresh();
    }
    public enum ColorType{
        STANDARD(Color.decode("#eeeeee")),
        VICTORY(Color.decode("#ebc034")),
        SECOND(Color.decode("#cccccc")),
        THIRD(Color.decode("#9c4300")),
        GOOD(Color.GREEN),
        ERROR(Color.RED);
        final Color color;
        ColorType(Color color){
            this.color = color;
        }
    }

    public Country getCountry(){
        return country;
    }

    @Override
    public String toString(){
        return "EurovisionRanking.CountryLabel{" + "country=" + country + " | Place: "+ myPosition + '}';
    }

    public int getMyPosition(){
        return myPosition;
    }
}
