//TC - O(m * n * 3 ^ L)
//SC - O(L) ~ recursion stck space

/*
We scan the entire board and start a DFS from any cell that matches the first character of the word.
From each starting cell, the DFS tries all possible paths by moving in four directions while matching characters and backtracking when a path fails.
This exploration continues until the full word is matched or all possible paths from that cell are exhausted.
*/

public class Problem2 {
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for(int i = 0;i<m;i++) {
            for(int j = 0;j<n;j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(isExists(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isExists(char[][] board, String word, int row, int col, int idx) {

        if(idx == word.length()) {
            return true;
        }

        if(row < 0 || col < 0 || row >= board.length || col >= board[row].length || board[row][col] != word.charAt(idx)) {
            return false;
        }


        char ch = board[row][col];
        board[row][col] = '$';

        for(int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];

            if(isExists(board, word, nr, nc, idx+1)) {
                return true;
            }
        }

        board[row][col] = ch;
        return false;

    }
}
