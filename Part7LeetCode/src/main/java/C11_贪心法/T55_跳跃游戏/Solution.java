/***********************************************************
 * @Description : 55.跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T55_跳跃游戏;

/**
 * Jump Game
 * 思路一： 正向， 从0出发， 一层一层网上跳， 看最后能不能超过最高层， 能超过， 说明能到达， 否则不能到达。
 * <p>
 * 思路1， 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public boolean canJump(int[] nums) {
        // 最右能跳到哪里
        int reach = 1;
        for (int i = 0; i < reach && reach < nums.length; ++i) {
            reach = Math.max(reach, i + 1 + nums[i]);
        }
        return reach >= nums.length;
    }
}
