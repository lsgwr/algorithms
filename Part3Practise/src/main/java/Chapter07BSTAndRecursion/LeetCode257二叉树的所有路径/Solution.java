/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/22 0:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.LeetCode257二叉树的所有路径;

import Chapter07BSTAndRecursion.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        // 1.递归终止条件
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.add(root.val + "");
            return result;
        }
        List<String> leftResultList = binaryTreePaths(root.left);
        for (String leftResult : leftResultList) {
            result.add(root.val + "->" + leftResult);
        }
        List<String> rightResultList = binaryTreePaths(root.right);
        for (String rightResult : rightResultList) {
            result.add(root.val + "->" + rightResult);
        }
        return result;
    }
}
