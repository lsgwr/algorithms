/***********************************************************
 * @Description : 292.Nim游戏
 * https://leetcode-cn.com/problems/nim-game/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T292_Nim游戏;

/**
 * Nim Game
 * Time Complexity: O(1), Space Complexity: O(1)
 */
public class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
