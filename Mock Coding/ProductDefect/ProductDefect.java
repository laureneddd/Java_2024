
/*
 * Return the size of the largest square submatrix in a two-dimensional matrix
 * Complete the method: largestArea:
 *         parameter: a two-dimensional array of Integers
 *         return: an integer that represents the size of the largest square sub-matrix 
 */

public class ProductDefect {
    public static int largestArea(int[][] samples) {

        int len = samples.length;

        if(len == 0) {
            return 0;
        }

        // create a new array and initialize to record the cell which has been calculated
        int[][] calSquare = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                calSquare[i][j] = -1;
            }
        }

        int maxSize = 0;

        // call findMaxSquare() and update maxSize
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (samples[i][j] == 1) {
                    maxSize = Math.max(maxSize, findMaxSquare(samples, i, j, calSquare));
                }
            }
        }        

        return maxSize;

    }

    public static int findMaxSquare(int[][] samples, int i, int j, int[][] calSquare) {
        if (i < 0 || j < 0 || samples[i][j] == 0) return 0;

        //if the current cell has been calculated, then return 
        if (calSquare[i][j] != -1) return calSquare[i][j];

        // get the minimum size of square of teh cell above, on the left side and above the left 
        calSquare[i][j] = 1 + Math.min(findMaxSquare(samples, i-1, j, calSquare),
                                  Math.min(findMaxSquare(samples, i, j-1, calSquare),
                                           findMaxSquare(samples, i-1, j-1, calSquare)));

        return calSquare[i][j];
    }

    public static void main(String[] args) {
        int[][] test1 = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1}
        };

        System.out.println("Test1: " + largestArea(test1));

        int[][] test2 = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        System.out.println("Test2: " + largestArea(test2));

        int[][] test3 = {
            {0, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        System.out.println("Test3: " + largestArea(test3));
    }
}
