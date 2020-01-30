/***********************************************************
 * @Description : 124.二叉树中的最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T124_二叉树中的最大路径和;

import C04_二叉树.TreeNode;

/**
 * Binary Tree Maximum Path Sum
 * 时间复杂度O(n)， 空间复杂度O(logn)
 * <p>
 * 这题很难， 路径可以从任意节点开始， 到任意节点结束。
 * 可以利用“最大连续子序列和”问题的思路， 见这节Maximum Subarray。 如果说Array只有一个方向的话， 那
 * 么Binary Tree其实只是左、 右两个方向而已， 我们需要比较两个方向上的值。
 * 不过， Array可以从头到尾遍历， 那么Binary Tree怎么办呢， 我们可以采用Binary Tree最常用的dfs来进行
 * 遍历。 先算出左右子树的结果L和R， 如果L大于0， 那么对后续结果是有利的， 我们加上L， 如果R大于0，
 * 对后续结果也是有利的， 继续加上R。
 *
 * @author liangshanguang
 */
public class Solution {
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }

    private int maxSum;

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        int sum = root.val;
        if (l > 0) {
            sum += l;
        }
        if (r > 0) {
            sum += r;
        }
        maxSum = Math.max(maxSum, sum);
        return Math.max(r, l) > 0 ? Math.max(r, l) + root.val : root.val;
    }
}
