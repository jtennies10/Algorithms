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
public class QuickSort {

    public static void main(String[] args) {
        String fileString = "temp.dat"; //change this for different files
        File file1 = new File(fileString);
        ArrayList<Double> list1;
        try {
            list1 = new ArrayList(FileWorker.readInOneD(file1));
            System.out.println(list1.toString());
            quickSort(list1);
            System.out.println(list1.toString());
            writeFile(list1, fileString.substring(0, fileString.length() - 4) 
                + "Sorted" + fileString.substring(fileString.length() - 4));
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        }
    }

    /**
     * Sorts a list
     * 
     * @param list the list to be sorted
     */
    public static void quickSort(List<Double> list) {
        if(0 < list.size()-1) {
            int s = partition(list);
            quickSort(list.subList(0, s));
            quickSort(list.subList(s+1, list.size()));
        }
    }

    /**
     * Partitions a list via a pivot so that everything to the left of the pivot
     * is less than the pivot and everything to the right is greater than the pivot.
     * 
     * @param list is the list to be partitioned
     * @return the integer value of the pivot after partitioning
     */
    public static int partition(List<Double> list) {
        double p = list.get(0);
        int i = 0;
        int j = list.size()-1;
        while (i < j) {
            while (list.get(i) < p) {
                i++;
            }
            while (list.get(j) > p) {
                j--;
            }
            double temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        double temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        return j;
    }

}
