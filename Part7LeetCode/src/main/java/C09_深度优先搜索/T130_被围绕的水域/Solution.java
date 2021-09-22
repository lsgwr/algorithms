package C09_深度优先搜索.T130_被围绕的水域;

class Solution {
    private int R;
    private int C;
    private char[][] board;
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1},{1, 0}, {0, -1}, {-1, 0}};

    private boolean inGrid(int r, int c){
        return r >=0 && r < R && c >= 0 && c < C;
    }

    private boolean onBorder(int r, int c){
        return r == 0 || r == R -1 || c == 0 || c == C - 1;
    }

    public void solve(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        R = board.length;
        C = board[0].length;
        this.board = board;
        visited = new boolean[R][C];
        // 从所有的边界点O向里面进行DFS遍历，能联通的所有O标记为visited，最终没被访问到地O就是该被X填充地
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(!visited[r][c] && onBorder(r, c) && board[r][c] == 'O'){
                    dfs(r, c);
                }
            }
        }
        // 修改board
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(!visited[r][c] && board[r][c] == 'O'){
                    board[r][c] = 'X';
                }
            }
        }
    }

    private void dfs(int r, int c){
        visited[r][c] = true;
        for(int[] dir : dirs){
            int rNext = r + dir[0];
            int cNext = c + dir[1];
            if(inGrid(rNext, cNext) && !visited[rNext][cNext] && board[rNext][cNext] == 'O'){
                dfs(rNext, cNext);
            }
        }
    }
}