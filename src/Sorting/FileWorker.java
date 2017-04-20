package Sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Joshua Tennies
 */
public class FileWorker {

    /**
     * Reads data from a file into a list
     * 
     * @param file is the file to be read from
     * @return a list that holds the file contents
     * @throws FileNotFoundException 
     */
    public static List readInOneD(File file) throws FileNotFoundException {
        List<Double> list = new ArrayList();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] splitLine = line.split(",");
            for (String i : splitLine) {
                list.add(Double.parseDouble(i));
            }
        }
        return list;
    }

    /**
     * Reads data from a file into a matrix/2D list
     * 
     * @param file is the file to be read from
     * @return a list that holds the file contents
     * @throws FileNotFoundException 
     */
    public static List readInTwoD(File file) throws FileNotFoundException {
        List<List<Double>> list = new ArrayList();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] splitLine = line.split(",");
            List<Double> subList = new ArrayList();
            for (String i : splitLine) {
                subList.add(Double.parseDouble(i));
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * Creates a new file and writes the passed in list to it
     * 
     * @param list is the list to be written to the file
     * @param fileString is the name of the file to be created
     * @throws FileNotFoundException 
     */
    public static void writeFile(List list, String fileString) throws FileNotFoundException {
        File file = new File(fileString);
        if (file.exists()) {
            System.out.println("The sorted file already exists.");
        } else {
            PrintWriter output = new PrintWriter(file);
            for (int i = 0; i < list.size() - 1; i++) {
                output.append(list.get(i) + ", ");
            }
            output.append(list.get(list.size() -1) + "");
            output.close();
        }

    }
    

}
