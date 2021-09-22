/**
 * 数塔问题
 * https://www.acwing.com/problem/content/900/
 */

#include <iostream>
#include <vector>

#define INF 0x3f3f3f3f

using namespace std;

/**
 * 统计最后一行的所有子问题最优解，其中最大的就是最终结果
 * @param nums 存储三角形的数组
 * @return 最大的路径和
 */
int Solve(vector<vector<int>> &nums) {
    int res = -INF;
    int n = nums.size();
    vector<vector<int>> dp(n, vector<int>(n, -INF)); // 求最大距离，因此初始化为最小值
    dp[0][0] = nums[0][0];
    for (int i = 1; i < n; i++) { // 阶段
        for (int j = 0; j <= i; j++) { // 状态(变量)
            // 决策
            if (j - 1 >= 0) {
                if (j <= i - 1) { // 左上和右上元素都有
                    dp[i][j] = nums[i][j] + max(dp[i - 1][j], dp[i - 1][j - 1]); // 决策集合包括左上和右上两个元素
                } else { // 没有右上元素
                    dp[i][j] = nums[i][j] + dp[i - 1][j - 1]; // 决策集合只有左上元素
                }
            } else { // 没有左上元素
                dp[i][j] = nums[i][j] + dp[i - 1][j]; // 决策集合只有右上元素
            }
        }
    }
    for (int j = 0; j < n; j++) {
        res = max(res, dp[n - 1][j]);
    }
    return res;
}

int main() {
    int N;
    cin >> N;
    vector<vector<int>> v(N, vector<int>(N, -INF)); // 二维数组初始化为很大的数，防止干扰数据判断
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <= i; j++) {
            cin >> v[i][j];
        }
    }
    cout << Solve(v) << endl;
    return 0;
}