# 双向DFS

## 题目

### [171.送礼物](https://www.acwing.com/problem/content/description/173/)
```txt
达达帮翰翰给女生送礼物，翰翰一共准备了N个礼物，其中第i个礼物的重量是G[i]。

达达的力气很大，他一次可以搬动重量之和不超过W的任意多个物品。

达达希望一次搬掉尽量重的一些物品，请你告诉达达在他的力气范围内一次性能搬动的最大重量是多少。
```

> 参考答案：https://www.acwing.com/solution/content/20100/

```java
import java.io.*;
import java.util.*;
class Main{
    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    static int N, W, left;
    static int[] a;
    static Set<Integer> table = new HashSet();
    static List<Integer> tableList;
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        String[] ss = read.readLine().split(" ");
        N = Integer.valueOf(ss[1]);
        W = Integer.valueOf(ss[0]);
        a = new int[N];
        for(int i = 0; i < N; i ++) a[i] = Integer.valueOf(read.readLine());
        Arrays.sort(a);
        reverse(a);
        left = N / 2 + 2;
        dfs1(0, 0);
        tableList = new ArrayList(table);
        Collections.sort(tableList);
        dfs2(left, 0);
        System.out.println(ans);
    }

    public static void dfs1(int idx, int sum){
        //打表, 去重
        table.add(sum);
        if(idx >= left) return;
        //add
        if((long)sum + a[idx] <= W) dfs1(idx + 1, sum + a[idx]);
        //not add
        dfs1(idx + 1, sum);

    }

    public static void dfs2(int idx, int sum){
        if(idx >= N){
            //找到小于等于target的最大值
            int target = W - sum, l = 0, r = tableList.size() - 1;
            while(l < r){
                int mid = l + r + 1 >> 1;
                //如果小于target
                if(tableList.get(mid) <= target) l = mid;
                else r = mid - 1;
            }
            ans = Math.max(ans, sum + tableList.get(l));
            return;
        }
        if((long)sum + a[idx] <= W) dfs2(idx + 1, sum + a[idx]);
        dfs2(idx + 1, sum);
    }

    public static void reverse(int[] a){
        int l = 0, r = a.length - 1, t;
        while(l < r){
            t = a[l];
            a[l++] = a[r];
            a[r--] = t;
        }
    }
}
```