//
// Created by lsg on 2021/10/10.
//

#include <iostream>

using namespace std;

#define MAX_N 5010
int n; // 字符串长度
string s; // 字符串
short int f[MAX_N][MAX_N];// 如果直接DP，需要开5001*5001的数组，用int会超内存

int solve() {
    for (int i = n - 1; i >= 0; i--) { // 之所以i从n-1向0从大到小遍历，就是为了能先计算出i+1；
        for (int j = i + 1; j < n; j++) { // j从i向n从小到大遍历，就是为了能先计算出j - 1
            if (s[i] == s[j]) {
                f[i][j] = f[i + 1][j - 1];
            } else {
                f[i][j] = min(f[i][j - 1], f[i + 1][j]) + 1; // f[i][j - 1]和f[i + 1][j]肯定都提前算好了
            }
        }
    }
    return f[0][n - 1];
}

int main() {
    cin >> n >> s;
    cout << solve() << endl;
    return 0;
}