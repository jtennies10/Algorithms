
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
public class InsertionSort {

    /**
     * Runs insertion sort on a list
     * 
     * @param data is the list to be sorted
     * @return the sorted list
     */
    public static List<Double> insertionSort(List<Double> data) {
        for(int i = 2; i < data.size(); i++) {
            Double key = data.get(i);
            int j = i - 1;
            while(j >= 0 && data.get(j) > key) {
                data.set(j+1, data.get(j));
                j = j - 1;
                data.set(j+1, key);
            }
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
        String fileString = "C:\\Spring 17\\SortingTest.txt"; //change this for different files
        File file1 = new File(fileString);
        List<Double> list1;
        try {
            list1 = new ArrayList(FileWorker.readInOneD(file1));
            System.out.println(list1.toString());
            insertionSort(list1);
            System.out.println(list1.toString());
            writeFile(list1, fileString.substring(0, fileString.length() - 4) 
                + "Sorted" + fileString.substring(fileString.length() - 4));
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        }

    }
}
