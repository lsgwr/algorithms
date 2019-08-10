# 第6章 图论问题建模和 floodfill

> floodfill(洪泛)实际就是图的遍历

## 6.1 图论问题例子：判断二分图


题目来源：[LeetCode 785 is-graph-bipartite](https://leetcode-cn.com/problems/is-graph-bipartite):，判断二分图，

因为题目中已经给出了邻接表，相当于已经给出了Graph，所以直接用二分图的核心算法即可，参考[DFS实现二分图检测](src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/GraphDFSBiPartitionDetect.java)

+ [实现代码](src/main/java/Chapter06GraphModellingAndFloodfill/Section1LeetCodeBiPartite/Solution.java)


## 6.2~6.3 DFS实现解决floodfill的问题

> 核心思想如下：

![https://img1.sycdn.imooc.com/szimg/5d4e73b50001312b17281080.jpg](https://img1.sycdn.imooc.com/szimg/5d4e73b50001312b17281080.jpg)

## 6.5 更多floodfill的问题

![更多floodfilee算法的问题1](https://img.mukewang.com/szimg/5d4e7d70000167ef17281080.jpg)

![更多floodfilee算法的问题2](https://img.mukewang.com/szimg/5d4e7d8f0001de2b17281080.jpg)

![更多floodfilee算法的问题3](https://img.mukewang.com/szimg/5d4e7dd10001ae4617281080.jpg)

![更多floodfilee算法的问题4](https://img.mukewang.com/szimg/5d4e7de70001149f17281080.jpg)
