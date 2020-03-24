package C12_动态规划.T221_最大正方形;

// 动态规划法：https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
class Solution2 {
    private int min3(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
    public int maximalSquare(char[][] matrix) {
        // 判空
        if (matrix == null) {
            return 0;
        }
        int R = matrix.length;
        if (R == 0) {
            return 0;
        }
        int C = matrix[0].length;
        if (C == 0) {
            return 0;
        }
        // 取正方形都是从当前点向右或者向下走，dp[i][j]意思应该是表示 在左上角矩阵中由matrix[i-1][j-1]参与构成的最大正方形边长
        // 之所以减1是因为dp下标从1开始
        int[][] dp = new int[R + 1][C + 1];
        int maxWidth = 0;
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                // 注意dp中的i和j是从下标1开始哒
                if(matrix[i-1][j-1] == '1'){
                    // 为1的点才进行动态规划
                    dp[i][j] = min3(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
                    maxWidth = Math.max(maxWidth, dp[i][j]);
                }
            }
        }
        return maxWidth * maxWidth;
    }
}