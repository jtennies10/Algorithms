
package tsp;

import java.util.Scanner;

/**
 *
 * @author Joshua Tennies
 */
public class LexPermTSP {
    /**
     * Generates the next permutation of a given array if there is adjacent
     * increasing elements.
     * 
     * @param array is the array used to generate permutations
     */
    public static void lexPerm(int[] array) {
        boolean consecIncreasing = false;
        for(int l = 0; l < array.length - 1; l++) {
            if(array[l] < array[l+1]) {
                consecIncreasing = true;
                break;
            }
        }
        if(consecIncreasing) {
            int largeI = 0;

            //find largeI
            for(int i = 1; i < array.length - 1; i++) {
                if(array[i] < array[i+1]) {
                    largeI = i;
                }
            }

            //find largeJ
            int largeJ = largeI + 1;
            for(int j = largeI+2; j < array.length; j++) {
                if(array[largeI] < array[j]) {
                    largeJ = j;
                }
            }

            //swap array[largeI] and array[largeJ]
            int temp = array[largeI];
            array[largeI] = array[largeJ];
            array[largeJ] = temp;
            
            //reverse array from largeI+1 to end of array
            for(int k = largeI + 1; k < (array.length-largeI+1)/2 + largeI; k++) {
                int temp2 = array[k];
                array[k] = array[array.length-(k-largeI)];
                array[array.length-(k-largeI)] = temp2;
            }
        }
        
        
    }
    
    /**
     * Prints out an array
     * 
     * @param array is array to be printed
     */
    public static void printArray(int[] array) {
        for(int i = 0; i < array.length-1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length-1]);
        System.out.println("");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sequence of numbers separated by commas: ");
        String sequence = sc.nextLine();
        String[] stringArray = sequence.split(",");
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        System.out.print("How many permutations do you want to see? ");
        int perms = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < perms; i++) {
            lexPerm(intArray);
        }
    }
}
