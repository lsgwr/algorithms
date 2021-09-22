/***********************************************************
 * @Description : 单词拆分
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/25 15:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode139单词拆分;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length() + 1];
        return dfs(s, 0, wordDict, visited);
    }

    private boolean dfs(String s, int start, List<String> wordDict, boolean[] visited) {
        visited[start] = true;
        for (int i = start; i < s.length(); i++) {
            // 防止递归回退的时候再次经过这个字符
            if (visited[i + 1]) {
                continue;
            }
            String substr = s.substring(start, i + 1);
            if (wordDict.contains(substr)) {
                if (i + 1 == s.length() || dfs(s, i + 1, wordDict, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 输入: s = "leetcode", wordDict = ["leet", "code"]  输出: true
     * <p>
     * 输入："aaaaaaa"  输出：["aaaa","aaa"]
     */
    public static void main(String[] args) {
        String s = "aaaaaaa";
        String[] words = {"aaaa", "aaa"};
        List<String> wordDict = new ArrayList<>(Arrays.asList(words));
        System.out.println(new Solution().wordBreak(s, wordDict));
    }
}
