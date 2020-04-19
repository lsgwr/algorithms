/***********************************************************
 * @Description : 1273.删除树节点
 * https://leetcode-cn.com/problems/delete-tree-nodes/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 15:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T1273_删除树节点;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * 统计现在剩余的点的个数
     */
    private int result = 0;
    /**
     * children[i]表示节点i所有的孩子节点
     */
    private Set<Integer>[] children;
    /**
     * parent[i]表示第i个节点的父节点
     */
    private int[] parent;
    /**
     * value[i]表示第i个节点的值
     */
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        this.parent = parent;
        this.value = value;
        // 记录每个节点的孩子节点
        children = new Set[nodes];
        for (int i = 0; i < nodes; i++) {
            children[i] = new HashSet<>();
        }
        // 记录每个节点的所有孩子节点
        for (int j = 0; j < nodes; j++) {
            if (parent[j] != -1) {
                children[parent[j]].add(j);
            }
        }
        // dfs求每个子树的和，并在回溯过程中删除节点值之和为0的子树(把parent节点的children清空即可)
        dfs(0);
        sumNodes(0);
        return result;
    }

    /**
     * dfs过程中求子树的和，把为空的子树删除掉
     *
     * @param node 当前的节点编号
     */
    private int dfs(int node) {
        // 到了叶子节点，直接返回叶子节点的值即可
        if (children[node].size() == 0) {
            if (value[node] == 0) {
                // 叶子节点值为空，可以看作空子树，可以直接删除点了
                children[parent[node]].remove(node);
                return 0;
            } else {
                return value[node];
            }
        }
        // 当前节点所在子树的和
        int treeSum = value[node];
        for (int child : children[node]) {
            // 遍历节点node的每个子树
            treeSum += dfs(child);
        }
        // 看看子树的节点总和是否为0了
        if (treeSum == 0) {
            // 为0则要把当前的节点node从其父节点中删除
            children[parent[node]].remove(node);
            // 把0返回给父节点，因为已经清空了
            return 0;
        } else {
            // 和不为空，则把当前子树的和继续往上传递
            return treeSum;
        }
    }

    /**
     * DFS过程中
     *
     * @param node 当前遍历到的节点
     */
    private void sumNodes(int node) {
        // 访问到一个节点就+1
        result++;
        for (int child : children[node]) {
            // 遍历每个child，继续dfs
            sumNodes(child);
        }
    }

    public static void main(String[] args) {
        int nodes = 7;
        int[] parent = {-1, 0, 0, 1, 2, 2, 2};
        int[] value = {1, -2, 4, 0, -2, -1, -1};
        System.out.println(new Solution().deleteTreeNodes(nodes, parent, value));
    }
}
