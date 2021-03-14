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
        //File paths to the images folder Windows Terminal (WT) uses
        String inFilePath = "C:\\Users\\aidan\\AppData\\Local\\Packages\\Microsoft.WindowsTerminal_8wekyb3d8bbwe\\RoamingState\\images.txt";

        //the JSON file uses this path for the background image setting, will later be concatenated with the filename
        String imagePath = "ms-appdata:///roaming/";

        //JSON settings path for WT
        String jsonPath = "C:\\Users\\aidan\\AppData\\Local\\Packages\\Microsoft.WindowsTerminal_8wekyb3d8bbwe\\LocalState\\settings.json";

        //Linked List that stores all the filenames
        LinkedList<String> filenames = new LinkedList<String>();

        //index chosen for the background
        int chosen = 0;

        //populates the linked list with filenames
        getFilenames(filenames, inFilePath);

        //chooses a random number within the size of the list to be the new image
        chosen = randomInt(0, filenames.size() -1);

        try {
            //new json reader
            FileReader reader = new FileReader(jsonPath);

            JSONParser joParser = new JSONParser();
            JSONObject jObject = (JSONObject) joParser.parse(reader);

            //System.out.println(jObject);

            //moving down the structure until we get to the defaults class that contains the customization options
            JSONObject profileObj = (JSONObject) jObject.get("profiles");
            JSONObject defaultObj = (JSONObject) profileObj.get("defaults");

            //this gets the current background image
            String bgImageObj = (String) defaultObj.get("backgroundImage");

            System.out.println("current image: " + bgImageObj);

            //concatenates the filename with the image path to replace the current one
            imagePath = imagePath + filenames.get(chosen);

            //replace current image with new one
            defaultObj.put("backgroundImage", imagePath);
            bgImageObj = (String) defaultObj.get("backgroundImage");

            System.out.println("bg image is now: " + bgImageObj);

            //write updated json back to the file
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

    //iterate through the txt file to get the filenames
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
