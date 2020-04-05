/***********************************************************
 * @Description : https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/4 22:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T429_N叉树的层序遍历;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    /**
     * N叉树的层序遍历
     *          1
     *        / | \
     *       /  |  \
     *      3   2  4
     *     / \
     *    5  6
     * 层序遍历结果：[[1], [3,2,4], [5,6]]
     * @param root N叉树的根节点
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            // 一次性把当前层的节点全部弹出
            List<Integer> levelList = new ArrayList<>();
            // size的作用在于区分树的层，不用单独处理层了，和BFS一起对比看下好好学体会下
            // BFS的实现 https://gitee.com/lsgwr/algorithms/blob/master/Part2BasicGraph/src/main/java/Chapter05BreadthFirstTraversal/Section2GraphBFS/GraphBFS.java#L41
            while(size-- > 0){
                Node node = queue.remove();
                levelList.add(node.val);
                for (Node child : node.children) {
                    if (child!=null){
                        queue.add(child);
                    }
                }
            }
            list.add(levelList);
        }
        return list;
    }
}
