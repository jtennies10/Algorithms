package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import static tsp.LexPermTSP.lexPerm;
import static tsp.MatrixLoader.squareMatrixReader;

/**
 * The solver does not use A,B,C,.. but rather 0,1,2,... for indexes. In other
 * words, 0 is A, 1 is B, 2 is C, and so on.
 *
 * @author Joshua Tennies
 */
public class TSP {

    public static void main(String[] args) {
        String fileName = "C:\\Spring 17\\Analysis of Algorithms\\TestRunsForYou\\TestRunsForYou\\10.txt";
        File file = new File(fileName);
        try {
            long before = System.currentTimeMillis();
            int[][] a = squareMatrixReader(file);
            tspSolver(a);
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
     * Solves the traveling salesman problem exactly
     *
     * @param a is the weighted matrix
     */
    public static void tspSolver(int[][] a) {
        int[] order = new int[a.length];
        int[] solution = order.clone();
        int tempSum = 0;
        int best;
        int permsRemaining = 1;
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
            permsRemaining *= (i + 1);
        }
        int next;
        for (int i = 0; i < order.length - 1; i++) {
            next = i + 1;
            tempSum += a[order[i]][order[next]];
        }
        tempSum += a[order[order.length - 1]][order[0]];
        permsRemaining--;
        best = tempSum;
        printPermAndSum(order, tempSum);
        while (permsRemaining > 0) {
            lexPerm(order);
            tempSum = 0;
            next = 0;
            for (int i = 0; i < order.length - 1; i++) {
                next = i + 1;
                tempSum += a[order[i]][order[next]];
            }
            tempSum += a[order[order.length - 1]][order[0]];
            printPermAndSum(order, tempSum);
            permsRemaining--;
            if (tempSum < best) {
                System.out.println("new solution");
                printPermAndSum(solution, best);
                best = tempSum;
                solution = order.clone();
            }
        }
        System.out.println("\n\nSolution");
        printPermAndSum(solution, best);
    }

    /**
     * Prints out an array
     *
     * @param perm is array to be printed
     * @param sum is the sum of the current sequence
     */
    public static void printPermAndSum(int[] perm, int sum) {
        for (int i = 0; i < perm.length; i++) {
            System.out.print(perm[i] + ",");
        }
        System.out.print(perm[0] + " sum = " + sum + "\n");
    }
}
