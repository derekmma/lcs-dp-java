/* COMP3011 Assignment 2 Question 1
 * MA Mingyu Derek, 14110562D
 * 
 * A program calculating longest common subsequence of three sequeces
 * 
 * To run this program: 
 *   - change input in `Inp14110562d.txt` under same directory
 *   - divide three string in the .txt file by new line
 *   - compile and run this java program in Terminal under the directory containing this file
 *     `javac LCS14110562D.java`
 *     `java LCS14110562D`
 */

import javax.net.ssl.ExtendedSSLSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LCS14110562D {

    public static void main(String []args) {
        String X = new String();
        String Y = new String();
        String Z = new String();
        try {
			File file = new File("Inp14110562D.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int count = 0;
			while ((line = bufferedReader.readLine()) != null) {
                if (count == 0)
                    X = line;
                else if (count == 1)
                    Y = line;
                else{
                    Z = line;
                }
                count++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
        }
        System.out.println("String X: " + X);
        System.out.println("String Y: " + Y);
        System.out.println("String Z: " + Z);

        int m = X.length();
        int n = Y.length();
        int o = Z.length();
        // int [][][] b = lcsLength3(X, Y, Z, m, n, o);
        // System.out.println("---");
        // String result = printLcs3(b, X, m, n, o);
        int[][] b = lcsLength(X, Y, m, n);
        // String X1 = printLcs(b, X, m, n);
        // System.out.println("-LCS of \'" + X + "\' and \'"+Y+"\' is: " + X1);
        // m = X1.length();
        // n = Z.length();
        // b = lcsLength(X, Z, m, n);
        String result = printLcs(b, X, m, n);
        System.out.println("--The LCS of three strings is: " + result);
    }

    private static int[][][] lcsLength3(String X, String Y, String Z, int m, int n, int o){
        int[][][] b = new int[m][n][o];
        int[][][] c = new int[m+1][n+1][o+1];
        // index 0 lines of x, y, z axis of this cube are initialized to 0 already
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < o; k++){
                    if (X.charAt(i) == Y.charAt(j) && X.charAt(i) == Z.charAt(k)){
                        c[i+1][j+1][k+1] = c[i][j][k]+1;
                        b[i][j][k] = 8;
                    }
                    else if ((c[i][j+1][k+1] >= c[i+1][j][k+1]) && (c[i][j+1][k+1] >= c[i+1][j+1][k])){
                        c[i+1][j+1][k+1] = c[i][j+1][k+1];
                        b[i][j][k] = 1;
                    }
                    else if ((c[i+1][j][k+1] >= c[i][j+1][k+1]) && (c[i+1][j][k+1] >= c[i+1][j+1][k])){
                        c[i+1][j+1][k+1] = c[i+1][j][k+1];
                        b[i][j][k] = 2;
                    } else {
                        c[i+1][j+1][k+1] = c[i+1][j+1][k];
                        b[i][j][k] = 3;
                    }
                }
            }
        }
        return b;
    }

    private static int[][] lcsLength(String X, String Y, int m, int n){
        int[][] b = new int[m][n];
        int[][] c = new int[m + 1][n + 1];
        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (X.charAt(i) == Y.charAt(j)){
                    c[i+1][j+1] = c[i][j] + 1;
                    b[i][j] = 8;
                }
                else if (c[i][j+1] >= c[i+1][j]){
                    c[i+1][j+1] = c[i][j+1];
                    b[i][j] = 1;
                }
                else {
                    c[i+1][j+1] = c[i+1][j];
                    b[i][j] = 2;
                }
            }
        }
        System.out.println("Table b for \'" + X + "\' and \'" + Y + "\': ");
        printMatrix(b);
        System.out.println("Table c for \'" + X + "\' and \'" + Y + "\': ");
        printMatrix(c);
        return b;
    }

    private static String printLcs3(int[][][] b, String X, int m, int n, int o){
        StringBuffer sb = new StringBuffer();
        for (int x = m-1, y = n-1, z = o-1; x>=0 && y>=0 && z>=0;){
            if (b[x][y][z] == 1){
                x--;
            } else if (b[x][y][z] == 2){
                y--;
            } else if (b[x][y][z] == 3){
                z--;
            } else {
                assert b[x][y][z] == 8;
                sb.append(X.charAt(x));
                x--;
                y--;
                z--;
            }
        }
        return sb.reverse().toString();
    }

    private static String printLcs(int[][] b, String X, int i, int j){
        StringBuffer sb = new StringBuffer();
        for (int x = i-1, y = j-1; x >= 0 && y >= 0; ) {
            if (b[x][y] == 1){
                x--;
            } else if (b[x][y] == 2){
                y--;
            } else{
                assert b[x][y] == 8;
                sb.append(X.charAt(x));
                x--;
                y--;
            }
        }
        return sb.reverse().toString();
    }

    private static void printMatrix(int[][] grid) {
        for(int r=0; r<grid.length; r++) {
           for(int c=0; c<grid[r].length; c++)
               System.out.print(grid[r][c] + " ");
           System.out.println();
        }
    }
}