# 图论基础

## 图的存储结构：邻接矩阵与邻接表（稠密图与稀疏图）

> 平时遇到的图多是稀疏图，用邻接表表示，邻接矩阵用于稠密图

+ 稠密图用 邻接矩阵存储

+ 稀疏图用 邻接表存储

原因：

+ 邻接表只存储非零节点，而邻接矩阵则要把所有的节点信息(非零节点与零节点)都存储下来。
+ 稀疏图的非零节点不多，所以选用邻接表效率高，如果选用邻接矩阵则效率很低，矩阵中大多数都会是零节点！
+ 稠密图的非零界点多，零节点少，选用邻接矩阵是最适合不过！

## 图实现的改进

> 源于刘宇波老师的图论课 https://coding.imooc.com/lesson/370.html#mid=27328

代码见[Section4ReadGraphOptimize](Section4ReadGraphOptimize)

+ 对于边的编号不能超过边的总数vertices
  ```java
  public void validateVertex(int v) {
      assert (v >= 0 && v < vertices);
  }
  ```
+ 稀疏图的邻接表实现改进:vector换成TreeSet，把查询邻接表的时间复杂度从O(n)提高到了O(logN),而且元素还是有序地
  ```java
   /**
     * 邻接表，采用vector套vector的形式
     */
   private TreeSet<Integer>[] adj;
  ```
+ 添加degree()函数，用于统计图里每个定点的度(即每个定点有几个临边)
  ```java
  // 稀疏图，adj是TreeSet
  public int degree(int v) {
      return adj[v].size();
  }
  ```
  
  ```java
  // 稠密图，adj是二维数组
  public int degree(int v) {
      return adj[v].length;
  }
  ```
  
## 图的多种表示方法和实现的比较

后面所有的图都会用 **基于TreeSet的邻接表** 来表示

![图的多种表示方式以及实现的比较](Section4ReadGraphOptimize/图的多种表示方式以及实现的比较.png)


