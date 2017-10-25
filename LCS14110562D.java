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
import java.util.Random;

public class LCS14110562D {
    public static void main(String []args) {
        // Get three strings from file
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

        // Get length of three sequences
        int m = X.length();
        int n = Y.length();
        int o = Z.length();
        // Call lcsLength3 function to calculate b table
        int [][][] b = lcsLength3(X, Y, Z, m, n, o);
        // Call printLcs3 function to get a LCS
        String result = printLcs3(b, X, m, n, o);
        System.out.println("--The LCS of three strings is: " + result);
        int r = result.length();
        System.out.println("--Length of the LCS: " + r);
    }

    private static int[][][] lcsLength3(String X, String Y, String Z, int m, int n, int o){
        // Initialize table b and c for saving direction and length of LCS
        int[][][] b = new int[m][n][o];
        int[][][] c = new int[m+1][n+1][o+1];
        // index 0 lines of x, y, z axis of this cube are initialized to 0 already

        // Loop every element in the three-dimentional cube, and update tables b and c
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < o; k++){
                    if (X.charAt(i) == Y.charAt(j) && X.charAt(i) == Z.charAt(k)){
                        c[i+1][j+1][k+1] = c[i][j][k]+1;
                        // set b table to 8 to present the direction
                        b[i][j][k] = 8;
                    }
                    else if ((c[i][j+1][k+1] >= c[i+1][j][k+1]) && (c[i][j+1][k+1] >= c[i+1][j+1][k])){
                        c[i+1][j+1][k+1] = c[i][j+1][k+1];
                        // set b table value to 1 to indicate direction
                        b[i][j][k] = 1;
                    }
                    else if ((c[i+1][j][k+1] >= c[i][j+1][k+1]) && (c[i+1][j][k+1] >= c[i+1][j+1][k])){
                        c[i+1][j+1][k+1] = c[i+1][j][k+1];
                        // set b table value to 1 to indicate direction
                        b[i][j][k] = 2;
                    } else {
                        c[i+1][j+1][k+1] = c[i+1][j+1][k];
                        // set b table value to 1 to indicate direction
                        b[i][j][k] = 3;
                    }
                }
            }
        }
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
                // find a common character
                sb.append(X.charAt(x));
                x--;
                y--;
                z--;
            }
        }
        return sb.reverse().toString();
    }
}