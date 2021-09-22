# 语法基础课
> 这里仅记录下比较好的题目。https://www.acwing.com/activity/content/21/

## 第五讲 字符串
### [AcWing 779. 最长公共字符串后缀](https://www.acwing.com/problem/content/781/)
```cpp
#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

int main()
{
    int n;
    while(cin >> n, n) { // 多个表达式取最后一个表达式的值
        string s, a;
        int MAX = 200; // 最长是200
        cin >> s; // 获取第一个字符串
        for (int i = 1; i < n; i ++ ) { // 和后面的n-1个字符串比较，
            int res = 0;
            cin >> a;
            for (int j = 0; j < a.size() && j < s.size(); j ++ ) { // 都和s进行比较，长度为a和s中较短的
                if(a[a.size() - 1 - j] == s[s.size() - 1 - j]) res++; // 从两个字符串的最后往前逐个字符比较
                else break; // 一旦有一个对应位置的字符不相等，就可以退出了，res就是a和s的公共后缀地长度
            }
            MAX = min(MAX, res); // 不算更新s和所有剩下字符串比较得到的最长公共后缀长度
        }
        if(MAX) cout << s.substr(s.size() - MAX) << endl; // 公共后缀长度不为0才输出
        else cout << endl; // 否则输出孔
    }
}
```

### [AcWing 771. 字符串中最长的连续出现的字符](https://www.acwing.com/problem/content/773/)
```cpp
#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;

int main()
{
    int n;
    cin >> n;
    while (n -- ) {
        string s;
        cin >> s;
        char cMax; // 长度最长的字符
        int lenMax = 0; // 最长字符的长度
        char cPre; // 前一个字符
        // 双指针法
        int r = 0, len = 0;
        while(r < s.size()) { // 右侧尝试移入字符
            char c = s[r]; // 即将移入窗口的字符
            if (c !=  cPre) { // 换新的字符了，就重新开始
                if (len > lenMax) { // 更新最大字符和其长度
                    lenMax = len;
                    cMax = cPre;
                }
                len = 1; // 当前只有一个字符
            } else len++; // 字符相等的话
            r++;
            cPre = c; // 前一个字符记录一下
        }
        if (len > lenMax) { // 更新最大字符和其长度
            lenMax = len;
            cMax = cPre;
        }
        printf("%c %d\n", cMax, lenMax);
    }
}
```

## 第七讲 结构体、类、指针、引用

### [AcWing 66. 两个链表的第一个公共结点](https://www.acwing.com/problem/content/62/)
```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
 /**
  * 题解:https://www.acwing.com/solution/content/26708/
  * 1. 用两个指针 p1，p2 分别指向两个链表 headA，headB 的头结点，同时向后遍历。
  * 2. 当指针到达链表末尾时，重新定位到另一个链表的头结点。
  * 3. 当它们相遇时，所指向的结点就是第一个公共结点。
  **/
class Solution {
public:
    ListNode *findFirstCommonNode(ListNode *headA, ListNode *headB) {
        // 1.pA和pB一起开始往后运动
        ListNode *pA = headA;
        ListNode *pB = headB;
        while(pA != NULL && pB != NULL) {
            pA = pA->next;
            pB = pB->next;
            if(pA == NULL) pA = headB;
            if(pB == NULL) pB = headA;
            if(pA == pB) return pA;
        }
        return NULL;
    }
};
```


## 第八讲 STL容器、位运算与常用库函数

### [AcWing 51. 数字排列](https://www.acwing.com/problem/content/47/)
```cpp
class Solution {
public:
    map<int, bool> visited; // 访问数组
    map<int, int> pre; // 记录访问节点的前一个节点
    set<vector<int>> s;
    int cnt = 0;
    int n;

    void dfs(vector<int> &nums, int index) {
        visited[index] = true; // 访问节点已经访问
        cnt++;
        if (cnt == n) {
            stack<int> st;
            st.push(index);
            int m = pre[index];
            for (int j = 1; j < n; j++) {
                st.push(m);
                m = pre[m];
            }
            vector<int> path;
            while (!st.empty()) {
                path.push_back(nums[st.top()]);
                st.pop();
            }
            s.insert(path);
        }
        for (int k = 0; k < nums.size(); ++k) {
            if (!visited[k]) {
                pre[k] = index;
                dfs(nums, k);
                visited[k] = false;
                cnt--;
            }
        }
    }

    vector<vector<int>> permutation(vector<int> &nums) {
        n = nums.size();
        for (int i = 0; i < nums.size(); ++i) {
            visited.clear();
            pre.clear();
            cnt = 0;
            dfs(nums, i); // 排列组合获取所有的排列到set中
        }
        vector<vector<int>> res;
        res.assign(s.begin(), s.end());
        return res;
    }
};

int main() {
    Solution solution;
    vector<int> nums = {1, 1, 2};
    vector<vector<int>> res = solution.permutation(nums);
    cout << res.size() << endl;
    return 0;
}
```
### [AcWing 26. 二进制中1的个数](https://www.acwing.com/problem/content/25/)
```cpp
class Solution {
public:
    /**
     * n&(n-1)的结果：n最右边的1变成0，比如n为6
     * 110&101-》100, 循环直到n为0为止
     **/
    int NumberOf1(int n) {
        int res = 0;
        while(n) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
};
```

### [AcWing 20. 用两个栈实现队列](https://www.acwing.com/problem/content/36/)
```cpp
// 参考：https://www.acwing.com/solution/content/725/
class MyQueue {
public:
    stack<int> st1, st2;
    /** Initialize your data structure here. */
    MyQueue() {

    }

    void copy(stack<int> &a, stack<int> &b) {
        while(!a.empty()) {
            b.push(a.top());
            a.pop();
        }
    }

    /** Push element x to the back of queue. */
    void push(int x) {
        st1.push(x);
    }


    /** Removes the element from in front of queue and returns that element. */
    int pop() {
        copy(st1, st2);
        int x = st2.top();
        st2.pop();
        copy(st2, st1);
        return x;
    }

    /** Get the front element. */
    int peek() {
        copy(st1, st2); // 所有元素从主栈中弹出，压入辅助栈中。
        int x = st2.top(); // 则辅助栈的栈顶元素就是我们要弹出的元素，将其弹出即可
        copy(st2, st1); // 再将辅助栈中的元素全部弹出，压入主栈中
        return x;
    }

    /** Returns whether the queue is empty. */
    bool empty() {
        return st1.empty();
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * bool param_4 = obj.empty();
 */
```

### [AcWing 68. 0到n-1中缺失的数字](https://www.acwing.com/problem/content/64/)
```cpp
// 参考：https://www.acwing.com/solution/content/12977/
class Solution {
public:
    int getMissingNumber(vector<int>& nums) {
        if(nums.empty()) return 0;
        int l = 0, r = nums.size() - 1;
        while(l < r) {
            int mid = l + r >> 1;
            if (nums[mid] != mid) r = mid; // 不相等说明要找的数在左侧
            else l = mid + 1;
        }
        if (nums[r] == r) r++; // 当所有数都满足nums[i] = i时，说明缺少的数是n
        return r;
    }
};
```

### [AcWing 67. 数字在排序数组中出现的次数](https://www.acwing.com/problem/content/63/)
> lower_bound和upper_bound
```cpp
class Solution {
public:
    int getNumberOfK(vector<int>& nums , int k) {
        auto p1 = lower_bound(nums.begin(), nums.end(), k);
        auto p2 = upper_bound(nums.begin(), nums.end(), k);
        return p2 - p1 + 1;
    }
};
```
> count
```cpp
class Solution {
public:
    int getNumberOfK(vector<int>& v , int k) {
        return count(v.begin(), v.end(), k);
    }
};
```

