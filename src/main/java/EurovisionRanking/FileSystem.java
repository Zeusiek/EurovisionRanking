package EurovisionRanking;

import java.io.*;
import java.util.Scanner;

public class FileSystem{
    public static void write(File file, String s){
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(s);
            writer.flush();
            writer.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @Deprecated(since = "0.2", forRemoval = false)
    public static String read(File file) throws FileNotFoundException{
        Scanner s = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (s.hasNextLine()){
            String nextLine = s.nextLine();
            if(nextLine.equals("") || nextLine.equals("\n")) continue;
            builder.append(nextLine).append('\n');
        }
        s.close();
        return builder.toString();
    }
    public static String readReader(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append('\n');
        }
        return builder.toString();
    }
}
