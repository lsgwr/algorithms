## [力扣上的 DP 问题分类汇总](https://leetcode-cn.com/circle/article/NfHhXD/)

> 本文主要把我之前刷过的 DP 的题按照下面这些分类整理了一下。之前发在了其它地方，经过 [@jinlinpang](https://leetcode-cn.com/u/jinlinpang/) 同学建议，在这里也发一下，一起刷爆力扣。
> 同一个分类的思路都是比较类似的，这里只把每个分类的题目整理了，每个分类的具体做法和相关知识需要额外找相应的资料学习。

### 1、线性 DP

- 最经典单串：
  [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)
- 最经典双串：
  [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence)
- 经典问题：
  [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle)
  [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray)
  [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray)
  [887. 鸡蛋掉落（DP+二分）](https://leetcode-cn.com/problems/maximum-product-subarray)
  [354. 俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes)
- 打家劫舍系列: (打家劫舍3 是树形DP)
  [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber)
  [213. 打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii)
- 股票系列:
  [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock)
  [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii)
  [123. 买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii)
  [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv)
  [309. 最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown)
  [714. 买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)
- 字符串匹配系列
  [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance)
  [44. 通配符匹配](https://leetcode-cn.com/problems/wildcard-matching)
  [10. 正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching)

### 2、区间 DP

[516. 最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence)
[730. 统计不同回文子字符串](https://leetcode-cn.com/problems/count-different-palindromic-subsequences)
[1039. 多边形三角剖分的最低得分](https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon)
[664. 奇怪的打印机](https://leetcode-cn.com/problems/strange-printer)
[312. 戳气球](https://leetcode-cn.com/problems/burst-balloons)

### 3、背包 DP

[416. 分割等和子集 (01背包-要求恰好取到背包容量)](https://leetcode-cn.com/problems/partition-equal-subset-sum)
[494. 目标和 (01背包-求方案数)](https://leetcode-cn.com/problems/target-sum)
[322. 零钱兑换 (完全背包)](https://leetcode-cn.com/problems/coin-change)
[518. 零钱兑换 II (完全背包-求方案数)](https://leetcode-cn.com/problems/coin-change-2)
[474. 一和零 (二维费用背包)](https://leetcode-cn.com/problems/ones-and-zeroes)

### 4、树形 DP

[124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum)
[1245. 树的直径 (邻接表上的树形DP)](https://leetcode-cn.com/problems/tree-diameter)
[543. 二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree)
[333. 最大 BST 子树](https://leetcode-cn.com/problems/largest-bst-subtree)
[337. 打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii)

### 5、状态压缩 DP

[464. 我能赢吗](https://leetcode-cn.com/problems/can-i-win)
[526. 优美的排列](https://leetcode-cn.com/problems/beautiful-arrangement)
[935. 骑士拨号器](https://leetcode-cn.com/problems/knight-dialer)
[1349. 参加考试的最大学生数](https://leetcode-cn.com/problems/maximum-students-taking-exam)

### 6、数位 DP

[233. 数字 1 的个数](https://leetcode-cn.com/problems/number-of-digit-one)
[902. 最大为 N 的数字组合](https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set)
[1015. 可被 K 整除的最小整数](https://leetcode-cn.com/problems/smallest-integer-divisible-by-k)

### 7、计数型 DP

计数型DP都可以以组合数学的方法写出组合数，然后dp求组合数
[62. 不同路径](https://leetcode-cn.com/problems/unique-paths)
[63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii)
[96. 不同的二叉搜索树 (卡特兰数)](https://leetcode-cn.com/problems/unique-binary-search-trees)
[1259. 不相交的握手 (卢卡斯定理求大组合数模质数)](https://leetcode-cn.com/problems/handshakes-that-dont-cross)

### 8、递推型 DP

所有线性递推关系都可以用矩阵快速幂做，可以O(logN)，最典型是斐波那契数列
[70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs)
[509. 斐波那契数](https://leetcode-cn.com/problems/fibonacci-number)
[935. 骑士拨号器](https://leetcode-cn.com/problems/knight-dialer)
[957. N 天后的牢房](https://leetcode-cn.com/problems/prison-cells-after-n-days)
[1137. 第 N 个泰波那契数](https://leetcode-cn.com/problems/n-th-tribonacci-number)

### 9、概率型 DP

求概率，求数学期望
[808. 分汤](https://leetcode-cn.com/problems/soup-servings)
[837. 新21点](https://leetcode-cn.com/problems/new-21-game)

### 10、博弈型 DP

策梅洛定理，SG 定理，minimax

- 翻转游戏
  [293. 翻转游戏](https://leetcode-cn.com/problems/flip-game)
  [294. 翻转游戏 II](https://leetcode-cn.com/problems/flip-game-ii)
- Nim游戏
  [292. Nim 游戏](https://leetcode-cn.com/problems/nim-game)
  石子游戏
  [877. 石子游戏](https://leetcode-cn.com/problems/stone-game)
  [1140. 石子游戏 II](https://leetcode-cn.com/problems/stone-game-ii/)
- 井字游戏
  [348. 判定井字棋胜负](https://leetcode-cn.com/problems/design-tic-tac-toe)
  [794. 有效的井字游戏](https://leetcode-cn.com/problems/valid-tic-tac-toe-state)
  [1275. 找出井字棋的获胜者](https://leetcode-cn.com/problems/find-winner-on-a-tic-tac-toe-game)

### 11、记忆化搜索

本质是 dfs + 记忆化，用在状态的转移方向不确定的情况
[329. 矩阵中的最长递增路径](https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix)
[576. 出界的路径数](https://leetcode-cn.com/problems/out-of-boundary-paths)