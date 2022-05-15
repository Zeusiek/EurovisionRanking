package EurovisionRanking;

public class BadValueException extends Exception{
    BadValueException(){
        super("Value is too big or small.");
    }
}
