/**
 * 买电脑
 * https://ac.nowcoder.com/acm/problem/108083
*/
#include <iostream>
#include <vector>

#define INF 0x3f
#define MAX_YEAR 10050

using namespace std;
int c, n;

int m[MAX_YEAR][MAX_YEAR], dp[MAX_YEAR];; // 数组尽量不要用vector，因为用例每次跑回累积上一次的结果的，用vector还得记得清理

int solve() {
    memset(dp, INF, sizeof(dp)); // 初始化为最大值
    dp[0] = 0; // 没有电脑花费自然为0
    for (int i = 1; i <= n; i++) { // 阶段i
        for (int j = 1; j <= i; j++) { // 状态j
            dp[i] = min(dp[i], dp[j - 1] + m[j][i] + c); // 状态j转移中更新最小值
        }
    }
    return dp[n];
}

int main() {
    // 用scanf和printf而不是cin和cout，性能会提高很多(从1563ms降到了344ms)
    while (scanf("%d%d", &c, &n) != EOF) { // 还没读到结尾就继续读
        for (int i = 1; i <= n; i++) { // 因为题目中说明了m[i][j]中的i和j都是从下标1开始
            for (int j = i; j <= n; j++) {
                scanf("%d", &m[i][j]); // 注意i和j的顺序
            }
        }
        printf("%d\n", solve());
    }
    return 0;
}