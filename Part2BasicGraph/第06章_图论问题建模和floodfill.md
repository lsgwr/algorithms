# 第6章 图论问题建模和 floodfill

> floodfill(洪泛)实际就是图的遍历

## 6.1 图论问题例子：判断二分图


题目来源：[LeetCode 785 is-graph-bipartite](https://leetcode-cn.com/problems/is-graph-bipartite):，判断二分图，

因为题目中已经给出了邻接表，相当于已经给出了Graph，所以直接用二分图的核心算法即可，参考[DFS实现二分图检测](src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/GraphDFSBiPartitionDetect.java)

+ [实现代码](src/main/java/Chapter06GraphModellingAndFloodfill/Section1LeetCodeBiPartite/Solution.java)


## 6.2 图的建模和二维网格中的小技巧

> 核心是为了解决[LeetCode 695.岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island),本节和下一节先用图建模和DFS来解决，6.4开始会提取成更为常用floodfill算法(洪泛，类似洪水向四周蔓延)

### 核心思想如下

695.岛屿的最大面积 建模成图论问题，转化成求所有连通分量里元素最多的一个，返回其元素个数即可
需要把平面中二维的点映射成一维的点，然后就可以用检测连通分量那套来做这个问题了

![695.岛屿的最大面积](https://img1.sycdn.imooc.com/szimg/5d4e73b50001312b17281080.jpg)


### 四联通分量(上下左边挨着的点的矢量差)

图中使用的坐标系是屏幕坐标系，不是笛卡尔坐标系的(x, y),而是(行, 列)。四连通分量就是当前的`(行, 列)`坐标点上右下左四个点和当前点的矢量差，当前点加上四联通分量得到的点就是上右下左四个点，如图中(2, 1)代表第3行第2列(下标从0开始)的点，(1, 1)、(2, 2)、(3, 1)、(2, 0)分别对应(2, 1)加上四联通分量后对应的点，其实就是(2, 1)的上、右、下、左(顺时针转一下即可)对应的点

![四联通分量](https://img1.sycdn.imooc.com/szimg/5d4e8c9100014f1d17281080.jpg)

## 6.3 DFS实现解决floodfill的问题

## 6.5 更多floodfill的问题

![更多floodfilee算法的问题1](https://img.mukewang.com/szimg/5d4e7d70000167ef17281080.jpg)

![更多floodfilee算法的问题2](https://img.mukewang.com/szimg/5d4e7d8f0001de2b17281080.jpg)

![更多floodfilee算法的问题3](https://img.mukewang.com/szimg/5d4e7dd10001ae4617281080.jpg)

![更多floodfilee算法的问题4](https://img.mukewang.com/szimg/5d4e7de70001149f17281080.jpg)
