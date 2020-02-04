/***********************************************************
 * @Description : 255.验证前序遍历序列二叉搜索树
 * https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 20:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T255_验证前序遍历序列是否是BST;

class Solution {
    public boolean verifyPreorder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        return verify(arr, 0, arr.length);
    }

    /**
     * 前序遍历的结果一定会让根节点在开始，小于根节点的元素在左边，大于根节点地在右侧
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private boolean verify(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        // 寻找左右侧的分界线
        int less = -1;
        int more = start;
        for (int i = start + 1; i < end; i++) {
            if (arr[start] > arr[i]) {
                less = i;
            } else {
                more = more == start ? i : more;
            }
        }
        if (less == -1 || more == start) {
            // 只有左子树或右子树
            return verify(arr, start + 1, end);
        }
        if (less != more - 1) {
            // 左右边界对应的元素必须是挨着地，否则肯定不是后续的结果了
            return false;
        }
        // 继续递归校验左右子树
        return verify(arr, start + 1, less) && verify(arr, more, end);
    }

    /**
     * 三个典型用例
     * [10,7,4,8,6,40,23]
     * <p>
     * [5,2,6,1,3]  ==> false
     *
     * [5,2,1,3,6]  ==> true
     * <p>
     * [2,3,1]  ==> false
     */
    public static void main(String[] args) {
        int[] arr = {2, 3, 1};
        System.out.println(new Solution().verifyPreorder(arr));
    }
}
