# 第9章 动态规划

## 9.1 什么是动态规划

> 以菲波那切数列求和为例，通过普通的递归-->引入记忆数组memo-->自下而上地解决问题三个步骤引出了动态递归

代码见[动态递归逐步深入](Section1Fibonacci)

### 动态规划的定义


> dynamic programming (also known as dynamic optimization) is a method for solving a complex problem by breaking it down into a collection of simpler subproblems, solving each of those subproblems just once, and storing their solutions – ideally, using a memory-based data structure.

> 将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案。


体会其中自下而上地解决问题的思路，有点类似数学归纳法

### 递归与动态规划的联系和区别

![递归与动态规划的联系和区别](Section1Fibonacci/递归与动态规划的联系和区别.png)