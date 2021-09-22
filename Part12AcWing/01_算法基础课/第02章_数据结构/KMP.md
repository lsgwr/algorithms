## 参考教程
+ [漫画：什么是字符串匹配算法？](https://blog.csdn.net/bjweimengshu/article/details/103966767)
+ [漫画：如何优化 “字符串匹配算法”？](https://blog.csdn.net/bjweimengshu/article/details/104368394)
+ [漫画：什么是KMP算法？](https://blog.csdn.net/bjweimengshu/article/details/104528964):重要
+ [阮一峰：字符串匹配的KMP算法](http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html)

## KMP是什么，做什么用的
KMP全称为Knuth Morris Pratt算法，三个单词分别是三个作者的名字。KMP是一种高效的字符串匹配算法，用来在主字符串中查找模式字符串的位置(比如在`hello,world`主串中查找`world`模式串的位置)。

## KMP算法的高效体现在哪
高效性是通过和其他字符串搜索算法对比得到的，在这里拿暴力朴素算法做一下对比。主要的思想是在主串的`[0, n-m]`区间内依次截取长度为`m`的子串，看子串是否和模式串一样(`n`是主串的长度，`m`是子串的长度)。举个例子如下：给定文本串S为`aaaababaaba`，和模式串P为`ababa`，现在要拿模式串P去跟文本串S匹配，整个过程如下所示：

![暴力匹配的过程](http://kcorg.oss-cn-hangzhou.aliyuncs.com/cb0e3aa3095d5ca18c73347ba24ee19e.png)

暴力算法的时间复杂度是`O(N*N)`，存在很大优化空间。

当模式串和主串匹配时，遇到模式串中某个字符不能匹配的情况，对于模式串中已经匹配过的那些字符，如果我们能找到一些规律，将模式串多往后移动几位，而不是像暴力算法算法一样，每次把模式串移动一位，就可以提高算法的效率。

kmp算法给我们提供的思路是：对于模式串，将每一个字符在匹配失败时可以向后移动的最大距离保存在一个next数组里。这样当匹配失败时就可以按照next数组中保存的数字向后多移动几位。从而提高算法的效率。

## next数组
在KMP算法中有个关键的数组，叫做前缀数组，也有的叫next数组，每一个子串有一个固定的next数组，它记录着字符串匹配过程中失配情况下可以向后多跳几个字符，其实也是子串的前缀和后缀相同的最长长度。说不明白，上图：

![前缀数组的原理](http://kcorg.oss-cn-hangzhou.aliyuncs.com/82ebbb042f05917581db8f172c0da4b7.png)

怎么求这个数组我们放在最后说，先说怎么使用这个前缀数组来实现kmp算法
## 使用next数组计算匹配位置
思路好像也已经说过了，就是在暴力的算法的基础上，在匹配失败的时候往后多跳几位，而跳几位保存在前缀数组中。

接下来我们看一下原理是什么样的，为什么前缀数组就可以作为跳几步的依据。

举个例子，下图中已经写好了总串`s`和模式串`p`,模式串的前缀数组为[0,0,1,2,3],且所以下标都是从1开始。

看图中当i=8，j=4时，`s[i] != p[j + 1]`，即将要匹配失败了，图中红色圈住的是子串的后缀。黄圈圈住的是前缀。蓝色圈圈住的是已经和后缀匹配过的部分，那么下一次将模式串后移`prefix[j]=2`位时，原来的前缀正好对着蓝色圈圈部分，因为前缀=后缀=蓝色圈圈部分，所以移动后的橙色部分就不用再判断了。

![KMP算法的移动过程](http://kcorg.oss-cn-hangzhou.aliyuncs.com/6d8c051918c52265b8de337c74f6bc35.png)

再用上一个双指针算法思路。`i`遍历总串`s`，`j`遍历模式串`p`，判断`s[i]` 和 `p[j + 1]`是否匹配。不匹配就将`j`重置为前缀数组中`prefix[j]`的值。匹配的话j忘后移动一位。当匹配了n个字符后即代表完全匹配。此时答案即为`i-n`，如果要继续搜索，要将j再置为`prefix[j]`。
```cpp
// kmp匹配
for (int i = 1, j = 0; i <= m; i++) {
    while (j != 0 && s[i] != p[j + 1]) j = prefix[j]; // s[i] != p[j + 1]即不匹配，则往后移动
    if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符的匹配
    if (j == n) { // 匹配了n字符了即代表完全匹配了
        System.out.print(i - n + " "); // 如果只匹配一个就可以了，这里返回return i-n即可
        j = prefix[j]; // 完全匹配后继续搜索
    }
}
```
## 怎么求前缀数组
前缀数组是kmp里面最难的部分，网上也有很多种求法。比如利用后一个元素和前面的元素之间存在数学公式关系来求，我们这里使用的方式是和上面的匹配过程类似的方法，也就是将前缀看作模式串，在p中匹配他。也就是字符串p自己找自己的匹配串。

## 代码步骤
+ 1. 对模式串预处理，生成next数组
+ 2. 进入主循环，遍历主串
  + 2.1. 比较主串和模式串的字符
  + 2.2. 如果发现坏字符，查询next数组，得到匹配前缀所对应的最长可匹配前缀子串，移动模式串到对应位置
  + 2.3.如果当前字符匹配，继续循环

## 模板代码Java
> 参考：https://www.acwing.com/solution/content/11332/ 为了方便下标都是从1开始
```java
import java.util.*;

public class KMP {
    /**
     * KMP算法主体逻辑。str是主串，pattern是模式串
     *
     * @param str     总串，长度为m
     * @param pattern 模式串，长度为n
     * @return 模式串pattern在纵穿str中第一次出现的位置
     */
    public static int kmp(String str, String pattern) {
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int n = pattern.length(); // 模式串p长度
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int m = str.length(); // 总串s长度
        char[] s = (" " + str).toCharArray(); // 总串s
        // 主循环，遍历主串字符
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = next[j]; // s[i] != p[j + 1]即不匹配，则往后移动
            if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符得匹配
            if (j == n) return i - n;  // 匹配了n字符了即代表完全匹配了
        }
        return -1;
    }

    /**
     * KMP算法主体逻辑。str是主串，pattern是模式串
     *
     * @param str     总串，长度为m
     * @param pattern 模式串，长度为n
     * @return 模式串pattern在总串str中出现的所有位置
     */
    public static List<Integer> kmpAll(String str, String pattern) {
        List<Integer> result = new ArrayList<>();
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int n = pattern.length(); // 模式串p长度
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int m = str.length(); // 总串s长度
        char[] s = (" " + str).toCharArray(); // 总串s
        // 主循环，遍历主串字符
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = next[j]; // s[i] != p[j + 1]即不匹配，则往后移动
            if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符得匹配
            if (j == n) {
                result.add(i - n); // 匹配成功，记录一次下标，
                j = next[j]; // 更新j
            }
        }
        return result;
    }

    /**
     * 生成Next数组
     *
     * @param pattern 模式串
     * @return 根据模式串生成next数组(next[j]表示在j在不匹配时可以一次最多往后移动几个位置再开始匹配)
     */
    private static int[] getNexts(String pattern) {
        int n = pattern.length();
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            // i从2开始，因为prefix[1]肯定为0
            while (j != 0 && p[i] != p[j + 1]) j = next[j];
            if (p[i] == p[j + 1]) j++;
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "ATGTGAGCTGGTGTGTGCFAA";
        String pattern = "GTGTGCF";
        int index = kmp(str, pattern);
        System.out.println("首次出现位置：" + index);
        System.out.println("首次出现位置：" + str.indexOf(pattern)); // 用JDK自带的方法
        pattern = "TG";
        System.out.println("所有出现位置：" + kmpAll(str, pattern));
    }
}
```

输出如下：
```txt
首次出现位置：12
首次出现位置：12
所有出现位置：[1, 3, 8, 11, 13, 15]
```

## 题目
> [AcWing 831.KMP字符串](https://www.acwing.com/problem/content/description/833/)
```java
// 参考：https://www.acwing.com/solution/content/11332/  为了方便下标都是从1开始

import java.util.*;

public class Main {
	 /**
     * KMP算法主体逻辑。str是主串，pattern是模式串
     *
     * @param str     总串，长度为m
     * @param pattern 模式串，长度为n
     * @return 模式串pattern在纵穿str中第一次出现的位置
     */
    public static int kmp(String str, String pattern) {
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int n = pattern.length(); // 模式串p长度
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int m = str.length(); // 总串s长度
        char[] s = (" " + str).toCharArray(); // 总串s
        // 主循环，遍历主串字符
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) {
                j = next[j]; // s[i] != p[j + 1]即不匹配，则往后移动
            }
            if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符得匹配
            if (j == n) return i - n;  // 匹配了n字符了即代表完全匹配了
        }
        return -1;
    }

    /**
     * KMP算法主体逻辑。str是主串，pattern是模式串
     *
     * @param str     总串，长度为m
     * @param pattern 模式串，长度为n
     * @return 模式串pattern在总串str中出现的所有位置
     */
    public static List<Integer> kmpAll(String str, String pattern) {
        List<Integer> result = new ArrayList<>();
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int n = pattern.length(); // 模式串p长度
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int m = str.length(); // 总串s长度
        char[] s = (" " + str).toCharArray(); // 总串s
        // 主循环，遍历主串字符
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = next[j]; // s[i] != p[j + 1]即不匹配，则往后移动
            if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符得匹配
            if (j == n) {
                result.add(i - n); // 匹配成功，记录一次下标，
                j = next[j]; // 更新j
            }
        }
        return result;
    }

    /**
     * 生成Next数组
     *
     * @param pattern 模式串
     * @return 根据模式串生成next数组(next[j]表示在j在不匹配时可以一次最多往后移动几个位置再开始匹配)
     */
    private static int[] getNexts(String pattern) {
        int n = pattern.length();
        char[] p = (" " + pattern).toCharArray(); // 模式串p
        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            // i从2开始，因为prefix[1]肯定为0
            while (j != 0 && p[i] != p[j + 1]) j = next[j];
            if (p[i] == p[j + 1]) j++;
            next[i] = j;
        }
        return next;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();
        String pattern = sc.next();
        sc.next();
        String str = sc.next();
        List<Integer> result = kmpAll(str, pattern);
        StringBuilder sb = new StringBuilder();
        for(int index : result) sb.append(index + " ");
        System.out.println(sb.toString());
    }
}
```

## Java自带的字符串匹配的实现
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt(); // 模式串p长度
        String p = sc.next(); // 模式串p
        sc.nextInt(); // 总串s长度
        String s = sc.next(); // 总串s
        int index = 0, start = 0;
        while(index >= 0) {
            index = s.indexOf(p, start);
            if(index == -1) break;
            System.out.print(index + " ");
            start = index + 1;
        }
    }
}
```

## 题目
> [831.KMP字符串](https://www.acwing.com/problem/content/833/) 参考题解：https://www.acwing.com/solution/content/11332/

```java
// 参考：https://www.acwing.com/solution/content/11332/
import java.util.Scanner;
public class Main {
    // 借助上面的kmpAll算法
    public static List<Integer> kmpAll(String str, String pattern) {
        // ...... 实现见上面
    }

    private static int[] getNexts(String pattern) {
        // ...... 实现见上面
    }

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        sc.next();
        String pattern = sc.next();
        sc.next();
        String str = sc.next();
        List<Integer> result = kmpAll(str, pattern);
        StringBuilder sb = new StringBuilder();
        for(int index : result) sb.append(index + " ");
        System.out.println(sb.toString());
    }
}
```


## 其实大部分高级语言有这种匹配算法的自带实现
+ java：`str.indexOf(pattern, start)`：返回第一次出现的位置，可以改变start来实现所有匹配位置的获取
+ C++：`str.find(pattern, start)`：类似java
+ Python：`re.search(pattern, line)`：返回第一个成功的匹配。
