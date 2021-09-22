# 二分查找

> https://leetcode-cn.com/leetbook/detail/binary-search/

## 一、背景
### 识别和模板简介

**如何识别二分查找？**

------

如前所述，二分查找是一种在每次比较之后*将查找空间一分为二*的算法。每次需要查找集合中的索引或元素时，都应该考虑二分查找。如果集合是无序的，我们可以总是在应用二分查找之前先对其进行排序。


**成功的二分查找的 3 个部分**

------

二分查找一般由三个主要部分组成：

+ 1. **预处理** —— 如果集合未排序，则进行排序。
+ 2. **二分查找** —— 使用循环或递归在每次比较后将查找空间划分为两半。
+ 3. **后处理** —— 在剩余空间中确定可行的候选者。


**3 个二分查找模板**

------

当我们第一次学会二分查找时，我们可能会挣扎。我们可能会在网上研究数百个二分查找问题，每次我们查看开发人员的代码时，它的实现似乎都略有不同。尽管每个实现在每个步骤中都会将问题空间划分为原来的 1/2，但其中有许多问题：

- 为什么执行方式略有不同？
- 开发人员在想什么？
- 哪种方法更容易？
- 哪种方法更好？

经过许多次失败的尝试并拉扯掉大量的头发后，我们找到了三个主要的二分查找模板。为了防止脱发，并使新的开发人员更容易学习和理解，我们在接下来的章节中提供了它们。

## 二、模板I
### 二分查找模板 I
> C++模板
```cpp
int binarySearch(vector<int>& nums, int target){
  if(nums.size() == 0)
    return -1;

  int left = 0, right = nums.size() - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

> Java模板
```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

> Python模板

```python
def binarySearch(nums, target):
    """
    :type nums: List[int]
    :type target: int
    :rtype: int
    """
    if len(nums) == 0:
        return -1

    left, right = 0, len(nums) - 1
    while left <= right:
        mid = (left + right) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            left = mid + 1
        else:
            right = mid - 1

    # End Condition: left > right
    return -1
```

**关键属性**

------

- 二分查找的最基础和最基本的形式。
- 查找条件可以在不与元素的两侧进行比较的情况下确定（或使用它周围的特定元素）。
- 不需要后处理，因为每一步中，你都在检查是否找到了元素。如果到达末尾，则知道未找到该元素。

 

**区分语法**

------

- 初始条件：`left = 0, right = length-1`
- 终止：`left > right`
- 向左查找：`right = mid-1`
- 向右查找：`left = mid+1`