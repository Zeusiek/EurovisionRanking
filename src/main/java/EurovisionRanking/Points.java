package EurovisionRanking;

/**
 * Klasa zawierająca punkty kraju.
 */
public class Points{
    private final Country country;
    private int voice;
    private int looks;
    private int melody;
    private int camera;
    private int stage;
    private int choreography;
    final int maxPoints = 100;

    /**
     * Konstruktor punktów.
     * @param country
     * @param voice
     * @param looks
     * @param melody
     * @param camera
     * @param stage
     * @param choreography
     */
    Points(Country country, int voice, int looks, int melody, int camera, int stage, int choreography){
        this.country = country;
        this.voice = voice;
        this.looks = looks;
        this.melody = melody;
        this.camera = camera;
        this.stage = stage;
        this.choreography = choreography;
    }

    /**
     * Czyści punkty.
     * @see #clear() 
     * @see #setPoints(Type, int) 
     */
    public void clear(){
        this.voice = 0;
        this.looks = 0;
        this.melody = 0;
        this.camera = 0;
        this.stage = 0;
        this.choreography = 0;
        sumUp();
    }

    /**
     * Sumuje punkty.
     * @see #clear() 
     * @see #setPoints(Type, int) 
     * @return Suma punktów
     */
    public int sumUp(){
        int a = melody+camera+stage+voice+looks+choreography;
        country.setPoints(a);
        return a;
    }

    /**
     * Ustawia punkty i zabezpiecza przed złą wartością.
     * @param type 
     * @param value
     * @see #clear() 
     * @see #sumUp() 
     * @throws BadValueException
     */
    public void setPoints(Type type, int value) throws BadValueException{
        if(value < -100 || value > maxPoints)
            throw new BadValueException();
        switch (type){
            case LOOKS -> looks = value;
            case STAGE -> stage = value;
            case CAMERA -> camera = value;
            case VOICE -> voice = value;
            case MELODY -> melody = value;
            case CHOREOGRAPHY -> choreography = value;
        }
    }
    public enum Type{
        VOICE, LOOKS, MELODY, CAMERA, STAGE, CHOREOGRAPHY
    }

    @Override
    public String toString(){
        return voice + "-"+ looks + "-" + melody + "-" + camera + "-" + stage+"-"+choreography;
    }

    public int getCamera(){
        return camera;
    }
    public int getLooks(){
        return looks;
    }
    public int getVoice(){
        return voice;
    }
    public int getStage(){
        return stage;
    }
    public int getMelody(){
        return melody;
    }
    public int getChoreography(){
        return choreography;
    }
}
