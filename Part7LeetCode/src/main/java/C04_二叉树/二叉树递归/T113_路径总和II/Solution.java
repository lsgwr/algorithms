/***********************************************************
 * @Description : 113.路径总和II
 * https://leetcode-cn.com/problems/path-sum-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T113_路径总和II;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSum(root, sum, path, pathList);
        return pathList;
    }

    public void pathSum(TreeNode node, int sum, List<Integer> path, List<List<Integer>> pathList) {
        if (node == null) {
            return;
        }
        // 当前节点
        path.add(node.val);
        // 左右子树都为空说明到了叶子节点，把当前路径加到路径列表中
        if (node.left == null && node.right == null) {
            if (node.val == sum) {
                // 必须从path中新建一个来存储当前路径，否则后续修改path会会影响
                pathList.add(new ArrayList<>(path));
            }
        }
        pathSum(node.left, sum - node.val, path, pathList);
        pathSum(node.right, sum - node.val, path, pathList);
        path.remove(path.size() - 1);
    }
}