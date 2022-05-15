package EurovisionRanking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Główne okno aplikacji.
 * @see MainPanel
 * @see #DIMENSION
 * @author Mateusz Targoński
 * @since 0.1
 */
public class MyFrame extends JFrame implements WindowListener{

    final static Dimension DIMENSION = new Dimension(1300,700);
    final MainPanel mainPanel = new MainPanel();
    private final ArrayList<CountryLabel> countryLabels = new ArrayList<>();
    final MyList myList;
    final ArrayList<Country> countries = new ArrayList<>();

    public MyFrame() throws IOException{
        this.add(mainPanel);
        this.myList = new MyList();
        boolean reload = createInitCountryList();
        this.myList.setCountryLabels(countryLabels);
        if(reload) {
            this.setCountryPositions();
            this.myList.refresh();
        }
        //Lista krajów
        JScrollPane scrollPane = new JScrollPane(myList);
        scrollPane.createVerticalScrollBar();
        scrollPane.remove(scrollPane.getHorizontalScrollBar());
        scrollPane.setBounds(0,70, DIMENSION.width-20, DIMENSION.height-100);
        this.add(scrollPane);
        ImageIcon imageIcon = new ImageIcon("logo.png");
        //Menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setVisible(true);

        JMenu menu = new JMenu("Główne opcje.");
        JMenuItem menuSumUp = new JMenuItem("Podsumuj");
        menuSumUp.getAccessibleContext().setAccessibleDescription(
                "Podsumuj wszystkie punkty i określ .");
        menuSumUp.addActionListener(e->{
            setCountryPositions();
            myList.refresh();
            repaint();
        });
        JMenuItem menuReset = new JMenuItem("Resetuj");
        menuReset.getAccessibleContext().setAccessibleDescription(
                "Zresetuj wszystkie punkty do stanu początkowego.");
        menuReset.addActionListener(e->{
            int x = JOptionPane.showConfirmDialog(this, "Czy na pewno?", "RESET", JOptionPane.YES_NO_OPTION);
            if(x==0) dump();
        });
        menu.add(menuSumUp);
        menu.add(menuReset);
        menuBar.add(menu);

        //Okno
        this.setJMenuBar(menuBar);
        this.addWindowListener(this);
        this.setSize(DIMENSION);
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(Color.decode("#a8a8a8"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("My Eurovision Rating");
        this.setLayout(null);
        this.setMinimumSize(new Dimension(DIMENSION.width, DIMENSION.height-700));
        this.setMaximumSize(new Dimension(DIMENSION.width, DIMENSION.height-100));
        this.setResizable(true);
        this.setVisible(true);
    }
    protected boolean fileReload(boolean fromExistingFile) throws IOException{
        File file = new File("myResults.txt");
        File fileReserve = new File("copy.txt");
        String[] strings;
        boolean shouldFromFile = fromExistingFile && file.exists();
        if(shouldFromFile) strings = FileSystem.readReader(file).split("\n");
        else strings = FileSystem.readReader(fileReserve).split("\n");
        ArrayList<Integer[]> integers = new ArrayList<>();
        for (String string: strings){
            String[] data = string.split("-");
            this.countries.add(new Country(data[0], data[1], data[2]));
            Integer[] ints = new Integer[6];
            //System.out.println(Arrays.toString(data));
            for (int i = 3; i < 9; i++)
                ints[i-3] = Integer.parseInt(data[i]);
            integers.add(ints);
        }
        for (int i = 0; i < this.countries.size(); i++)
            this.countryLabels.add(new CountryLabel(this.countries.get(i), integers.get(i)));
        return file.exists();
    }
    private boolean createInitCountryList() throws IOException{
        return fileReload(true);
    }
    private void setCountryPositions(){
        this.forceSumUp();
        this.countries.sort(Comparator.comparingInt(Country::getPoints).reversed());
        this.countryLabels.sort(Comparator.comparingInt(o -> o.getCountry().getPoints()));
        Collections.reverse(this.countryLabels);
        for (int i = 0; i < countries.size(); i++)
            this.countryLabels.get(i).setPosition(i);
    }
    private void forceSumUp(){
        for (CountryLabel countryLabel: this.countryLabels){
            countryLabel.refresh();
            countryLabel.points.sumUp();
        }
    }
    @Override
    public void windowOpened(WindowEvent e){

    }
    @Override
    public void windowClosing(WindowEvent e){
        setCountryPositions();
        StringBuilder builder = new StringBuilder();
        for (CountryLabel countryLabel: this.countryLabels){
            Country country = countryLabel.country;
            Points points = countryLabel.points;
            builder.append(country.toString()).append("-").append(points.toString()).append('\n');
        }
        String a = builder.substring(0,builder.length()-1);
        File file = new File("myResults.txt");
        if(!file.exists()){
            try{
                System.out.println(file.createNewFile());
            } catch(IOException ignored){}
        }
        FileSystem.write(file, a);
    }
    protected void dump(){
        for (CountryLabel countryLabel: this.countryLabels){
            countryLabel.points.clear();
            Arrays.stream(countryLabel.fields).forEach(a->a.setText("0"));
        }
        this.setCountryPositions();
        this.myList.clearAndDump();
        this.countries.clear();
        try{
            this.fileReload(false);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        this.myList.fetch();
        this.repaint();
    }

    @Override
    public void windowClosed(WindowEvent e){

    }
    @Override
    public void windowIconified(WindowEvent e){

    }
    @Override
    public void windowDeiconified(WindowEvent e){

    }
    @Override
    public void windowActivated(WindowEvent e){

    }
    @Override
    public void windowDeactivated(WindowEvent e){

    }

}
