//TC - O(n! * n)
//SC - O(n^2) + O(n) 

/*
We place one queen in each row and try all possible columns using backtracking, moving to the next row only when a safe position is found.
Before placing a queen, we check the column and both upper diagonals to ensure it does not conflict with previously placed queens.
The board is updated in place and restored during backtracking, while recursion proceeds row by row until a valid arrangement is formed.
*/


import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        helper(board, 0);
        return res;
    }

    private void helper(boolean[][] board, int row) {
        //base
        if(row == board.length) {
            List<String> li = new ArrayList<>();
            for(int i = 0;i<board.length;i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0;j<board[i].length;j++) {
                    if(board[i][j]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                li.add(sb.toString());
            }
            res.add(li);
            return;
        }

        //logic
        for(int c = 0;c<board[row].length;c++) {
            if(isSafe(board, row, c)) {
                //action
                board[row][c] = true;
                //recurse
                helper(board, row+1);
                //backtrack
                board[row][c] = false;
            }
        }
    }

    private boolean isSafe(boolean[][] board, int row, int col) {
        //up
        for(int i = 0;i<=row;i++) {
            if(board[i][col]) {
                return false;
            }
        }

        //diagonal up right
        int i = row;
        int j = col;
        while(i >= 0 && j < board[i].length) {
            if(board[i][j]) {
                return false;
            }
            i--;
            j++;
        }

        //diagonal up left
        i = row;
        j = col;
        while(i >= 0 && j >=0) {
            if(board[i][j]) {
                return false;
            }
            i--;
            j--;
        }

        return true;
    }
}