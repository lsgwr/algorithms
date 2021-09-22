/***********************************************************
 * @Description : 根据后续数组重建BST
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 20:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T255_扩展_验证后续遍历序列是否是BST;

public class Solution {
    public boolean verifyPostOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return verify(arr, 0, arr.length - 1);
    }

    /**
     * 后续遍历的结果一定会让根节点在最后，小于根节点的元素在左边，大于根节点地在右侧
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
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        if (less == -1 || more == end) {
            // 只有左子树或右子树
            return verify(arr, start, end - 1);
        }
        if (less != more - 1) {
            // 左右边界对应的元素必须是挨着地，否则肯定不是后续的结果了
            return false;
        }
        // 继续递归校验左右子树
        return verify(arr, start, less) && verify(arr, more, end - 1);
    }
}
