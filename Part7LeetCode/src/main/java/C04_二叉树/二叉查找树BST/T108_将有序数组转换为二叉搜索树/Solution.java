/***********************************************************
 * @Description : 108.将有序数组转换为二叉搜索树
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T108_将有序数组转换为二叉搜索树;

import C04_二叉树.TreeNode;

class Solution {
    /**
     * 升序数组是中序遍历的结果，答案是层序遍历的结果
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
     */
    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        // 等效于mid = (l + r) / 2，下面的实现是为了防止整型溢出
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, l, mid - 1);
        root.right = buildTree(nums, mid + 1, r);
        return root;
    }
}
