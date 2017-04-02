package Sorting;

import static Sorting.FileWorker.writeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joshua Tennies
 */
public class SelectionSort {

    /**
     * Runs selection sort on a list
     * 
     * @param data is the list to be sorted
     * @return the sorted list
     */
    public static List<Double> selectionSort(List<Double> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j) < data.get(min)) {
                    min = j;
                }
            }
            double temp = data.get(i);
            data.set(i, data.get(min));
            data.set(min, temp);
        }
        return data;
    }

    /**
     * This activity runs the sort and outputs a new file in the same directory
     * as the original file but with "Sorted" appended to the end of the filename
     * 
     * @param args 
     */
    public static void main(String[] args) {
        String fileString = "temp.dat"; //change this for different files
        File file1 = new File(fileString);
        List<Double> list1;
        try {
            list1 = new ArrayList(FileWorker.readInOneD(file1));
            System.out.println(list1.toString());
            selectionSort(list1);
            System.out.println(list1.toString());
            writeFile(list1, fileString.substring(0, fileString.length() - 4) 
                + "Sorted" + fileString.substring(fileString.length() - 4));
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        }

    }
}
