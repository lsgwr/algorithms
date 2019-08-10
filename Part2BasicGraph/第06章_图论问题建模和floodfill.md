# 第6章 图论问题建模和 floodfill

> floodfill(洪泛)实际就是图的遍历

## 6.1 图论问题例子：判断二分图


题目来源：[LeetCode 785 is-graph-bipartite](https://leetcode-cn.com/problems/is-graph-bipartite):，判断二分图，

因为题目中已经给出了邻接表，相当于已经给出了Graph，所以直接用二分图的核心算法即可，参考[DFS实现二分图检测](src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/GraphDFSBiPartitionDetect.java)

+ [实现代码](src/main/java/Chapter06GraphModellingAndFloodfill/Section1LeetCodeBiPartite/Solution.java)


## 6.2 图的建模和二维网格中的小技巧

> 核心是为了解决[LeetCode 695.岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island),本节和下一节先用图建模和DFS来解决，6.4开始会提取成更为常用floodfill算法(洪泛，类似洪水向四周蔓延)

### 核心思想如下

图中使用的坐标系是屏幕坐标系，不是笛卡尔坐标系的(x, y)而是(行, 列)。四连通分量就是在当前的(行, 列)坐标上加上四联通分量得到的点就是四联通分量要找的点，如图中(2, 1)代表第3行第2列(下标从0开始)的点，(1, 1)、(2, 2)、(3, 1)、(2, 0)分别对应(2, 1)加上四联通分量后对应的点，其实就是(2, 1)的上、右、下、左(顺时针转一下即可)对应的点。下面的伪代码说明了怎么获取上右下左四个点的坐标。此外八联通有时也会用到，就是指当前点为中心的九宫格以外的其他8个点，可以用类似的方法进行表示(顺时针旋转，试试列出八联通的dirs)

![695.岛屿的最大面积](https://img1.sycdn.imooc.com/szimg/5d4e73b50001312b17281080.jpg)

### 四联通分量(上下左边挨着的点的矢量差)

图中使用的坐标系是屏幕坐标系，不是笛卡尔坐标系的(x, y),而是(行, 列)。四连通分量就是当前的`(行, 列)`坐标点上右下左四个点和当前点的矢量差，当前点加上四联通分量得到的点就是上右下左四个点，如图中(2, 1)代表第3行第2列(下标从0开始)的点，(1, 1)、(2, 2)、(3, 1)、(2, 0)分别对应(2, 1)加上四联通分量后对应的点，其实就是(2, 1)的上、右、下、左(顺时针转一下即可)对应的点

![四联通分量](https://img1.sycdn.imooc.com/szimg/5d4e90770001fc0e17281080.jpg)

### 八联通情况下的 dirs 数组定义 

在八连通的情况下，dirs 数组应该是 8*2 的二维数组，具体如下： 

```java
int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
``` 

> 请在纸笔上模拟，看看能不能理解，数组中的每一个元素(包含两个坐标的位移偏差)，代表一个坐标位置对应的哪个相邻位置的坐标？

## 6.3 DFS实现解决floodfill的问题

## 6.5 更多floodfill的问题

![更多floodfilee算法的问题1](https://img.mukewang.com/szimg/5d4e7d70000167ef17281080.jpg)

![更多floodfilee算法的问题2](https://img.mukewang.com/szimg/5d4e7d8f0001de2b17281080.jpg)

![更多floodfilee算法的问题3](https://img.mukewang.com/szimg/5d4e7dd10001ae4617281080.jpg)

![更多floodfilee算法的问题4](https://img.mukewang.com/szimg/5d4e7de70001149f17281080.jpg)
