/***********************************************************
 * @Description : 129.求根到叶子节点数字之和
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T129_求根到叶子节点数字之和;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sumNumbers(TreeNode root) {
        List<List<Integer>> pathList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSum(root, path, pathList);
        int sum = 0;
        for (List<Integer> pathTmp : pathList) {
            int num = 0;
            for (Integer numSingle : pathTmp) {
                num = num * 10 + numSingle;
            }
            sum += num;
        }
        return sum;
    }

    public void pathSum(TreeNode node, List<Integer> path, List<List<Integer>> pathList) {
        if (node == null) {
            return;
        }
        // 当前节点
        path.add(node.val);
        // 左右子树都为空说明到了叶子节点，把当前路径加到路径列表中
        if (node.left == null && node.right == null) {
            // 必须从path中新建一个来存储当前路径，否则后续修改path会会影响
            pathList.add(new ArrayList<>(path));
        }
        pathSum(node.left, path, pathList);
        pathSum(node.right, path, pathList);
        // 当前层的递归退出时要删除当前节点
        path.remove(path.size() - 1);
    }
}
