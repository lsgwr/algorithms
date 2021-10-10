#include <iostream>

using namespace std;
#define MAX_N 70
int f[MAX_N][MAX_N];

int solve(string &s)
{
    int n = s.length();
    for (int i = 0; i < n; i++) {
        f[i][i] = 1; // 字符串只有一个字符时，回文串就是其本身
    }
    for (int len = 2; len <= 2; len ++) {
        int j;
        for (int i = 0; (j = i + len - 1) < n; i++) {
            if (s[i] == s[j]) {
                f[i][j] = f[]
            }
        }
    }
}

int main()
{
    int N;
    cin >> N;
    while (N-- > 0) {
        string s;
        cin >> s;
    }
    return 0;
}