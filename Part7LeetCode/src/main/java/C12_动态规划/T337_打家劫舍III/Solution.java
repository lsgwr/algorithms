/***********************************************************
 * @Description : 337.打家劫舍III
 * https://leetcode-cn.com/problems/house-robber-iii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T337_打家劫舍III;

import C04_二叉树.TreeNode;

class Solution {
    public int rob(TreeNode root) {
        int[] res = doRob(root);
        // 返回抢根节点和不抢根节点两种情况下的最大值
        return Math.max(res[0], res[1]);
    }

    /**
     * 在遍历中找最大值
     */
    private int[] doRob(TreeNode node) {
        int[] res = new int[2];
        if (node == null) {
            return res;
        }
        int[] left = doRob(node.left);
        int[] right = doRob(node.right);
        // 不抢当前的根节点node，直接比较左右子树即可
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 抢当前根节点node，那么左右子树的根节点就不能抢了，所以left[1]和right[1]就不用考虑了
        res[1] = left[0] + right[0] + node.val;
        return res;
    }
}
