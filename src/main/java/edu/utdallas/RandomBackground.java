package edu.utdallas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//i want to make a simple program that will randomly choose a background image/gif to use in
//in windows terminal :)
public class RandomBackground
{
    public static void main( String[] args ) throws FileNotFoundException{
        String inFilePath = "C:\\Users\\aidan\\AppData\\Local\\Packages\\Microsoft.WindowsTerminal_8wekyb3d8bbwe\\RoamingState\\images.txt";
        String imagePath = "ms-appdata:///roaming/";
        String jsonPath = "C:\\Users\\aidan\\AppData\\Local\\Packages\\Microsoft.WindowsTerminal_8wekyb3d8bbwe\\LocalState\\settings.json";
        LinkedList<String> filenames = new LinkedList<String>();
        int chosen = 0;

        getFilenames(filenames, inFilePath);

        chosen = randomInt(0, filenames.size());

        try {
            FileReader reader = new FileReader(jsonPath);

            JSONParser joParser = new JSONParser();
            JSONObject jObject = (JSONObject) joParser.parse(reader);

            //System.out.println(jObject);

            JSONObject profileObj = (JSONObject) jObject.get("profiles");
            JSONObject defaultObj = (JSONObject) profileObj.get("defaults");
            String bgImageObj = (String) defaultObj.get("backgroundImage");

            System.out.println("current image: " + bgImageObj);

            imagePath = imagePath + filenames.get(chosen);

            defaultObj.put("backgroundImage", imagePath);

            bgImageObj = (String) defaultObj.get("backgroundImage");

            System.out.println("bg image is now: " + bgImageObj);

            FileWriter file = new FileWriter(jsonPath);
            file.write(jObject.toJSONString());
            file.flush();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static void getFilenames(LinkedList<String> list, String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }
}
