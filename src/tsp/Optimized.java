package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import static tsp.MatrixLoader.squareMatrixReader;
import static tsp.TSP.printPermAndSum;

/**
 *
 * @author Joshua Tennies
 */
public class Optimized {

    public static void main(String[] args) {
        String fileName = "C:\\Spring 17\\Analysis of Algorithms\\TestRunsForYou\\TestRunsForYou\\15in.txt";
        File file = new File(fileName);
        try {
            long before = System.currentTimeMillis();
            int[][] m = squareMatrixReader(file);
            lexPermAndTSP(m);
            long after = System.currentTimeMillis();
            long time = after - before;
            int seconds = (int) (time / 1000) % 60;
            int minutes = (int) ((time / (1000 * 60)) % 60);
            System.out.println("M: " + minutes + " S: " + seconds);
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        }
    }

    /**
     * Generates the next permutation of a given array if there is adjacent
     * increasing elements.
     *
     * @param m is the weighted matrix
     */
    public static void lexPermAndTSP(int[][] m) {
        boolean consecIncreasing = true;
        int[] order = new int[m.length];
        int tempSum = 0;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        int[] solution = new int[order.length];
        while (consecIncreasing) {
            int next;
            tempSum = 0;
            for (int i = 0; i < order.length - 1; i++) {
                next = i + 1;
                tempSum += m[order[i]][order[next]];
                if (tempSum > best && next != order.length-2) {
                    reverse(order, i+1);
                    break;
                }
            }
            if (tempSum < best) {
                tempSum += m[order[order.length - 1]][order[0]];
                if (tempSum < best) {
                    System.out.print("New Best: ");
                    printPermAndSum(order, tempSum);
                    best = tempSum;
                    solution = order.clone();
                }
            }

            consecIncreasing = false;
            for (int l = 0; l < order.length - 1; l++) {
                if (order[l] < order[l + 1]) {
                    consecIncreasing = true;
                    break;
                }
            }
            if (consecIncreasing) {
                int largeI = 0;

                //find largeI
                for (int i = 1; i < order.length - 1; i++) {
                    if (order[i] < order[i + 1]) {
                        largeI = i;
                    }
                }

                //find largeJ
                int largeJ = largeI + 1;
                for (int j = largeI + 2; j < order.length; j++) {
                    if (order[largeI] < order[j]) {
                        largeJ = j;
                    }
                }

                //swap array[largeI] and array[largeJ]
                int temp = order[largeI];
                order[largeI] = order[largeJ];
                order[largeJ] = temp;

                //reverse array from largeI+1 to end of array
                for (int k = largeI + 1; k < (order.length - largeI + 1) / 2 + largeI; k++) {
                    int temp2 = order[k];
                    order[k] = order[order.length - (k - largeI)];
                    order[order.length - (k - largeI)] = temp2;
                }
            }
        }
        System.out.println("\n\nSolution");
        printPermAndSum(solution, best);
    }

    public static void reverse(int[] order, int start) {
        for (int k = start + 1; k < (order.length - start + 1) / 2 + start; k++) {
            int temp2 = order[k];
            order[k] = order[order.length - (k - start)];
            order[order.length - (k - start)] = temp2;
        }
    }

    /**
     * Prints out an array
     *
     * @param array is array to be printed
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length - 1]);
        System.out.println("");
    }

}
