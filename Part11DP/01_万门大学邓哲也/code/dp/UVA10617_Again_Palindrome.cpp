#include <iostream>

using namespace std;
#define MAX_N 70
long long f[MAX_N][MAX_N];

/**
 * 注意下面这个用例，用int的话会溢出的
 * 1
 * AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
 * 结果：1152921504606846975
 */
long long solve(string &s) {
    int n = s.length();
    for (int i = 0; i < n; i++) {
        f[i][i] = 1; // 字符串只有一个字符时，回文串就是其本身
    }
    for (int len = 2; len <= n; len++) {
        int j;
        for (int i = 0; (j = i + len - 1) < n; i++) {
            if (s[i] == s[j]) {
                f[i][j] = f[i + 1][j] + f[i][j - 1] + 1;
            } else {
                f[i][j] = f[i + 1][j] + f[i][j - 1] - f[i + 1][j - 1];
            }
        }
    }
    return f[0][n - 1];
}

int main() {
    int N;
    cin >> N;
    while (N-- > 0) {
        string s;
        cin >> s;
        cout << solve(s) << endl;
    }
    return 0;
}