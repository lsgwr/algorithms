/***********************************************************
 * @Description : 174.地下城游戏
 * https://leetcode-cn.com/problems/dungeon-game/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T174_地下城游戏;

/**
 * Dungeon Game
 * Time Complexity: O(mxn), Space Complexity: O(mxn)
 */
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        final int m = dungeon.length;
        final int n = dungeon[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        final int[][] memo = new int[m][n];
        memo[m - 1][n - 1] = Math.max(1, -dungeon[m - 1][n - 1] + 1);
        for (int i = m - 2; i >= 0; --i) {
            memo[i][n - 1] = Math.max(1, -dungeon[i][n - 1] + memo[i + 1][n - 1]);
        }
        for (int j = n - 2; j >= 0; --j) {
            memo[m - 1][j] = Math.max(1, -dungeon[m - 1][j] + memo[m - 1][j + 1]);
        }
        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                memo[i][j] = Math.max(1, -dungeon[i][j] + Math.min(memo[i + 1][j], memo[i][j + 1]));
            }
        }
        return memo[0][0];
    }
}
