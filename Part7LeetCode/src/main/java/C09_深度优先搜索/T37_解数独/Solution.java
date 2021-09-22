/***********************************************************
 * @Description : 37.解数独
 * https://leetcode-cn.com/problems/sudoku-solver/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T37_解数独;

class Solution {
    final int N = 9;
    int[] row = new int[N], col = new int[N];
    //ones数组表示0~2^9 - 1的整数中二进制表示中1的个数:如ones[7] = 3 ones[8] = 1
    //map数组表示2的整数次幂中二进制1所在位置（从0开始） 如 map[1] = 0,map[2] = 1, map[4] = 2
    int[] ones = new int[1 << N], map = new int[1 << N];
    int[][] cell = new int[3][3];

    public void solveSudoku(char[][] board) {
        init();
        int cnt = fill_state(board);
        dfs(cnt, board);
    }

    void init() {
        for (int i = 0; i < N; i++) {
            row[i] = col[i] = (1 << N) - 1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = (1 << N) - 1;
            }
        }
        //以上2个循环把数组的数初始化为二进制表示9个1，即一开始所以格子都可填
        for (int i = 0; i < N; i++) {
            map[1 << i] = i;
        }
        //统计0~2^9 - 1的整数中二进制表示中1的个数
        for (int i = 0; i < 1 << N; i++) {
            int n = 0;
            for (int j = i; j != 0; j ^= lowBit(j)) {
                n++;
            }
            ones[i] = n;
        }
    }

    int fill_state(char[][] board) {
        int cnt = 0;    //统计board数组空格('.')的个数
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '.') {
                    int t = board[i][j] - '1';
                    //数独中 i,j位置为数组下标，修改row col cell数组中状态
                    change_state(i, j, t);
                } else {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    boolean dfs(int cnt, char[][] board) {
        if (cnt == 0) {
            return true;
        }
        int min = 10, x = 0, y = 0;
        //剪枝，即找出当前所以空格可填数字个数最少的位置 记为x y
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    int k = ones[get(i, j)];
                    if (k < min) {
                        min = k;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        //遍历当前 x y所有可选数字
        for (int i = get(x, y); i != 0; i ^= lowBit(i)) {
            int t = map[lowBit(i)];

            change_state(x, y, t);
            board[x][y] = (char) ('1' + t);

            if (dfs(cnt - 1, board)) {
                return true;
            }

            //恢复现场
            change_state(x, y, t);
            board[x][y] = '.';
        }
        return false;
    }

    void change_state(int x, int y, int t) {
        row[x] ^= 1 << t;
        col[y] ^= 1 << t;
        cell[x / 3][y / 3] ^= 1 << t;
    }

    //对维护数组x行,y列的3个集合(行、列、九宫格)进行&运算
    //就可以获得可选数字的集合(因为我们用1标识可填数字)
    int get(int x, int y) {
        return row[x] & col[y] & cell[x / 3][y / 3];
    }

    int lowBit(int x) {
        return -x & x;
    }
}
