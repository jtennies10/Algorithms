package Sorting;


import static Sorting.FileWorker.writeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Joshua Tennies
 */
public class MergeSort {

    public static void main(String[] args) {
        String fileString = "temp.dat"; //change this for different files
        File file1 = new File(fileString);
        ArrayList<Double> list1;
        try {
            list1 = new ArrayList(FileWorker.readInOneD(file1));
            System.out.println(list1.toString());
            mergeSort(list1);
            System.out.println(list1.toString());
            writeFile(list1, fileString.substring(0, fileString.length() - 4) 
                + "Sorted" + fileString.substring(fileString.length() - 4));
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        }
    }

    /**
     * Sorts a list using merge sort.
     *
     * @param listA is the list to be sorted
     * @return the sorted list
     */
    public static ArrayList<Double> mergeSort(ArrayList<Double> listA) {
        ArrayList<Double> listB = new ArrayList();
        ArrayList<Double> listC = new ArrayList();
        int middle;
        if (listA.size() > 1) {
            middle = listA.size() / 2;
            for(int i = 0; i < middle; i++) {
                listB.add(listA.get(i));
            }
            for(int i = middle; i < listA.size(); i++) {
                listC.add(listA.get(i));
            }
            listB = mergeSort(listB);
            listC = mergeSort(listC);
            merge(listB, listC, listA);
        }
        return listA;
    }

    /**
     * Merges together two sublists
     * @param listB is the left list to be merged
     * @param listC is the right list to be merged
     * @param listA is the merged list
     */
    public static void merge(ArrayList<Double> listB, ArrayList<Double> listC, 
            ArrayList<Double> listA) {
        int i = 0; 
        int j = 0;
        int k = 0;
        while(i < listB.size() && j < listC.size()) {
            if(listB.get(i) <= listC.get(j)) {
                listA.set(k, listB.get(i));
                i++;
            } else {
                listA.set(k, listC.get(j));
                j++;
            }
            k++;
        }
        ArrayList<Double> extra;
        int extraIndex;
        if(i >= listB.size()) {
            extra = listC;
            extraIndex = j;
        } else {
            extra = listB;
            extraIndex = i;
        }
        for(int m = extraIndex; m < extra.size(); m++) {
            listA.set(k, extra.get(m));
            k++;
        }
    }
}
