/***********************************************************
 * @Description : 96.不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T96_不同的二叉搜索树;

class Solution {
    public int numTrees(int n) {
        int[] memo = new int[n+1];
        memo[0] = 1;
        for(int i = 1;i <= n;i++){
            for(int j =1;j <= i;j++){
                memo[i] += memo[j-1] * memo[i-j];
            }
        }
        return memo[n];
    }
}