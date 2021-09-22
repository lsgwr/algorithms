/***********************************************************
 * @Description : 45.跳跃游戏II
 * https://leetcode-cn.com/problems/jump-game-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T45_跳跃游戏II;

/**
 * Jump Game II
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int jump(int[] nums) {
        // 最小步数
        int step = 0;
        int left = 0;
        // [left, right]是当前能覆盖的区间
        int right = 0;
        if (nums.length == 1) {
            return 0;
        }
        // 尝试从每一层跳最远
        while (left <= right) {
            ++step;
            final int oldRight = right;
            for (int i = left; i <= oldRight; ++i) {
                int newRight = i + nums[i];
                if (newRight >= nums.length - 1) {
                    return step;
                }
                if (newRight > right) {
                    right = newRight;
                }
            }
            left = oldRight + 1;
        }
        return 0;
    }
}
