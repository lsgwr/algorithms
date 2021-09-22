/**
 * 滑雪：不好划分阶段，只能用记忆化搜索做，不能用动态规划做
 * https://www.acwing.com/problem/content/903/
 */

#include <iostream>
#include <vector>

using namespace std;

int R, C;
int dirs[4][2] = {
        {0,  1},
        {1,  0},
        {-1, 0},
        {0,  -1}
};
vector<vector<int>> grid;
vector<vector<int>> dp;

bool inGrid(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}

/**
 * 从位置(r, c)开始滑雪，看能滑的长度距离
 */
int solve(int r, int c) {
    if (dp[r][c] > 0) { // 遍历过直接返回了，起到了访问数组的作用
        return dp[r][c];
    }

    int dis = 0; // (r, c的邻接点中可以滑雪的最大长度
    for (auto &dir : dirs) {
        int rNext = r + dir[0];
        int cNext = c + dir[1];
        if (inGrid(rNext, cNext) && grid[rNext][cNext] < grid[r][c]) {
            int childMax = solve(rNext, cNext);
            dis = max(dis, childMax);
        }
    }
    dp[r][c] = dis + 1; // + 1是因为当前点(r, c)也算一个距离
    return dp[r][c];
}

int main() {
    /** 1.初始化数据 */
    cin >> R >> C;
    grid.resize(R, vector<int>(C, 0));
    dp.resize(R, vector<int>(C, 0)); // 记忆每个子节点的状态
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            cin >> grid[i][j];
        }
    }

    /** 2.从每个点开始尝试DFS */
    int res = 0; // 求最大值，所以初始化为最小值
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            res = max(res, solve(r, c));
        }
    }
    cout << res << endl;
    return 0;
}