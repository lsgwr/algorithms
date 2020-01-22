/***********************************************************
 * @Description : 113.路径总和
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/22 10:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.LeetCode113路径总和2;

import Chapter07BSTAndRecursion.BSTUtil;
import Chapter07BSTAndRecursion.TreeNode;

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
        // 当前层的递归退出时要删除当前节点
        path.remove(path.size() - 1);
    }

    /**
     * [5,4,8,11,null,13,4,7,2,null,null,5,1]
     * 22
     * <p>
     * [-2,null,-3]
     * -5
     */
    public static void main(String[] args) {
        String data = "[5,4,8,11,null,13,4,7,2,null,null,5,1]";
        int sum = 22;
        Codec codec = new Codec();
        TreeNode root = codec.deserialize(data);
        System.out.println(new Solution().pathSum(root, sum));
    }
}
