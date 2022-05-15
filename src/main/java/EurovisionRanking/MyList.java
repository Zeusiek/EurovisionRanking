package EurovisionRanking;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Lista krajów.
 * @since 0.2
 * @author Mateusz Targoński
 * @see MyFrame
 * @see MainPanel
 */
public class MyList extends JPanel{
    ArrayList<CountryLabel> countryLabels;
    MyList(ArrayList<CountryLabel> countryLabels){
        this.countryLabels = countryLabels;
        init();
    }
    MyList(){
        init();
    }

    /**
     * Inicjalizuje listę krajów.
     */
    private void init(){
        setBounds(0,55, MyFrame.DIMENSION.width-20, 1700);
        setAutoscrolls(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    /**
     * Odświeża listę krajów.
     */
    public void refresh(){
        clear();
        fetch();
    }

    /**
     * Pobiera i pokazuje listę krajów.
     */
    public void fetch(){
        for (CountryLabel countryLabel: countryLabels)
            add(countryLabel);
        repaint();
    }
    /**
     * Czyści listę krajów.
     */
    public void clear(){
        removeAll();
    }
    /**
     * Czyści listę krajów oraz listę paneli.
     */
    public void clearAndDump(){
        clear();
        countryLabels.clear();
    }

    /**
     * Ustawia listę paneli.
     * @param countryLabels
     */
    public void setCountryLabels(ArrayList<CountryLabel> countryLabels){
        this.countryLabels = countryLabels;
        refresh();
    }
}
