package com.validitytest.validity.optimizer;

public class LevenshteinCalculation {
    public static int getLevenshteinDistance(String str1, String str2) {

        char s1arr[] = str1.toCharArray();
        char s2arr[] = str2.toCharArray();
        int x = str1.length();
        int y = str2.length();
        int[][] matrix = new int[x + 1][y + 1];

        for (int i = 0; i < x; i++) //fill the first column of the matrix
            matrix[i][0] = i;
        for (int i = 0; i < y; i++) //fill the first row of the matrix
            matrix[0][i] = i;

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (s1arr[i - 1] == s2arr[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    int deletion = matrix[i - 1][j] + 1;
                    int addition = matrix[i][j - 1] + 1;
                    int changing = matrix[i - 1][j - 1] + 1;
                    matrix[i][j] = Math.min(deletion, Math.min(addition, changing));
                }
            }
        }
        return matrix[x - 1][y - 1];
    }
}
