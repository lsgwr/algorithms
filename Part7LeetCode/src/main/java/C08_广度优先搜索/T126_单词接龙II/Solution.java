/***********************************************************
 * @Description : 126.单词接龙II
 * https://leetcode-cn.com/problems/word-ladder-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 12:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T126_单词接龙II;

import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        // 如果不含有结束单词，直接结束，不然后边会造成死循环
        if (!wordList.contains(endWord)) {
            return ans;
        }
        // 利用 BFS 得到所有的邻居节点,以及每个节点的所在层数
        HashMap<String, Integer> mapWordDistance = new HashMap<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs(beginWord, endWord, wordList, map, mapWordDistance);
        ArrayList<String> path = new ArrayList<>();
        // temp 用来保存当前的路径
        path.add(beginWord);
        dfs(beginWord, endWord, map, mapWordDistance, path, ans);
        return ans;
    }

    private void dfs(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                     HashMap<String, Integer> mapWordDistance, List<String> path, List<List<String>> pathList) {
        if (beginWord.equals(endWord)) {
            pathList.add(new ArrayList<>(path));
            return;
        }
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            //判断层数是否符合
            if (mapWordDistance.get(beginWord) + 1 == mapWordDistance.get(neighbor)) {
                path.add(neighbor);
                dfs(neighbor, endWord, map, mapWordDistance, path, pathList);
                path.remove(path.size() - 1);
            }
        }
    }

    public void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map, HashMap<String, Integer> distance) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean isFound = false;
        int depth = 0;
        Set<String> dict = new HashSet<>(wordList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                // 一次性得到所有的下一个的节点
                ArrayList<String> neighbors = getOneCharDiffWords(temp, dict);
                map.put(temp, neighbors);
                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, depth);
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                        }
                        queue.offer(neighbor);
                    }

                }
            }
            if (isFound) {
                break;
            }
        }
    }

    private ArrayList<String> getOneCharDiffWords(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) {
                    continue;
                }
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }
}
