package edu.utdallas;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;

//i want to make a simple program that will randomly choose a background image/gif to use in
//in windows terminal :)
public class RandomBackground
{
    public static void main( String[] args ) throws FileNotFoundException{
        String inFilePath = "C:\\Users\\aidan\\AppData\\Local\\Packages\\Microsoft.WindowsTerminal_8wekyb3d8bbwe\\RoamingState\\images.txt";
        LinkedList<String> filenames = new LinkedList<String>();

        getFilenames(filenames, inFilePath);

        for (int i = 0; i < filenames.size(); i++) {
            System.out.println("File " + i + ": " + filenames.get(i));
        }

    }

    public static void getFilenames(LinkedList<String> list, String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }
    }
}
