# 11.1 什么是SVM
> SVM:supported Vector Machine

## SVM的用途
+ 1.解决分类问题
+ 2.解决回归问题

## SVM的含义

+ SVM尝试寻找一个最优的决策边界，使得两个类别离这个边界的距离越远。
+ 距离两个类别的最近的样本最远
+ 解决线性可分
+ SVM的目标就是要最大化margin
  + Hard Margin SVM:切切实实找到了一个最大的margin
  + Soft Margin SVM:针对线性不可分的问题

![SVM的含义](images/SVM的含义.jpg)