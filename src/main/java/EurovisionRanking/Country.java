package EurovisionRanking;

public class Country{
    final String name;
    final String songName;
    final String band;
    private int points;


    public Country(String name, String songName, String band){
        this.name = name;
        this.songName = songName;
        this.band = band;
    }

    public void setPoints(int points){
        this.points = points;
    }
    public int getPoints(){
        return points;
    }

    @Override
    public String toString(){
        return name + "-" + songName + "-" + band;
    }



}
