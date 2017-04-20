
package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Joshua Tennies
 */
public class MatrixLoader {

    public static int[][] squareMatrixReader(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String sizing = sc.nextLine();
        System.out.println(sizing);
        String[] a = sizing.split(",");
        int[][] matrix = new int[a.length][a.length];
        for (int row = 0; row < matrix.length; row++) {
            String current;
            if(row == 0) {
                current = sizing;
            } else {
                current = sc.nextLine();
            }
            String[] splitLine = current.split(",");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = Integer.parseInt(splitLine[col]);
            }

        }
        return matrix;
    }
}
