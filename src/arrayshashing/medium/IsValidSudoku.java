package arrayshashing.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class IsValidSudoku {
    /**
     * can be solved by traversing the rows then columns then the sub-boxes until we hit duplicates (using hashSet to catch the duplicates)
     * time complexity O(n^2) or we can express it another way O(row * columns) since it's the only way to traverse the 2D array is by
     * visiting all the cells
     * space complexity O(row * columns) we used hashSets to store the values and knowing the duplicates
     */
    public static boolean isValidSudoku(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        int rows = board.length;
        int columns = board[0].length;

        //check for rows
        for (int row = 0; row < rows; row++) {
            Set<Character> rowSet = new HashSet<>();
            for (int col = 0; col < columns; col++) {
                if (board[row][col] == '.') {
                    continue;
                }

                if(!rowSet.add(board[row][col])){
                    return false;
                }
            }
        }
        //check for columns
        for (int column = 0; column < columns; column++) {
            Set<Character> columnSet = new HashSet<>();
            for (int row = 0; row < rows; row++) {
                if (board[row][column] == '.') {
                    continue;
                }

               if(! columnSet.add(board[row][column])){
                   return false;
               }
            }
        }
        //check for 3 x 3 sub-boxes
        for (int row = 0; row < rows; row = row + 3) {
            for (int col = 0; col < columns; col = col + 3) {
                if (!checkSubBox(board, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkSubBox(char[][] board, int row, int col) {
        int rows = row + 3;
        int colums = col + 3;
        Set<Character> subboxSet = new HashSet<>();
        for (int i = row; i < rows; i++) {
            for (int j = col; j < colums; j++) {
                if (board[i][j] == '.') {
                    continue;
                }

                if(!subboxSet.add(board[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] su = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                                 , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                                 , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                                 , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                                 , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                                 , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                                 , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                                 , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                                 , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(su));

        su = new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(su));

    }
}
