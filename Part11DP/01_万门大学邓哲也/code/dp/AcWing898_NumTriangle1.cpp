/**
 * 数塔问题
 * https://www.acwing.com/problem/content/900/
 */

#include <iostream>
#include <vector>

using namespace std;

#define INF 0x3f3f3f3f

/**
 * 分解为最优子结构问题，先求局部最优解
 * @param i 第i行(下标从0开始)
 * @param j 第j列(下标从0开始)
 * @return 从上往下到达元素nums[i][j]的最大路径和
 */
int SubSolve(vector<vector<int>> &nums, int i, int j) {
    if (i < 0 || j < 0) return 0; // 必须保证退出
    // 决策
    if (j - 1 >= 0) {
        if (j <= i - 1) { // 左上和右上元素都有
            return nums[i][j] + max(SubSolve(nums, i - 1, j), SubSolve(nums, i - 1, j - 1)); // 决策集合包括左上和右上两个元素
        } else { // 没有右上元素
            return nums[i][j] + SubSolve(nums, i - 1, j - 1); // 决策集合只有左上元素
        }
    } else { // 没有左上元素
        return nums[i][j] + SubSolve(nums, i - 1, j); // 决策集合只有右上元素
    }
}

/**
 * 统计最后一行的所有子问题最优解，其中最大的就是最终结果
 * @param nums 存储三角形的数组
 * @return 最大的路径和
 */
int Solve(vector<vector<int>> &nums) {
    int res = -INF;
    int n = nums.size();
    for (int j = 0; j < n; j++) {
        res = max(res, SubSolve(nums, n - 1, j));
    }
    return res;
}

int main() {
    int N;
    cin >> N;
    vector<vector<int>> v(N, vector<int>(N, 1e9)); // 二维数组初始化为很大的数，防止干扰数据判断
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <= i; j++) {
            cin >> v[i][j];
        }
    }
    cout << Solve(v) << endl;
    return 0;
}