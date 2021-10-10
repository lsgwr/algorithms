//
// 合并果子
// Created by lsg on 2021/10/8.
//
#include <iostream>
#include <climits>

using namespace std;
#define MAX_N 110
int n, a[MAX_N], s[MAX_N], f[MAX_N][MAX_N];

int solve()
{
    for (int i = 1; i <= n; i++) {
        f[i][i] = 0;  // 区间长度只有1，即只有一堆，不需要额外费体力
    }
    for (int len = 2; len <= n; len++) {  // 枚举闭区间的区间长度
        int j;
        for (int i = 1; (j = i + len - 1) <= n; i++) {  // 注意是闭区间，所以计算区间长度需要减去1. j-i+1=len所以j=i+len-1
            int j = i + len - 1;
            for (int k = i; k < j; k++) {
                f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j] + s[j] - s[i - 1]);
            }
        }
    }
    return f[1][n];
}

int main()
{
    scanf("%d", &n);
    a[0] = 0, s[0] = 0;
    for (int i = 1; i <= n; i++) {
        scanf("%d", &a[i]);
        s[i] = s[i - 1] + a[i];
    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            f[i][j] = INT_MAX;
        }
    }
    printf("%d\n", solve());
    return 0;
}
