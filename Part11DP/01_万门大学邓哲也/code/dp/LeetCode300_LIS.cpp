/**
 * 最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int lengthOfLIS(vector<int> &a) {
        if (a.empty()) return 0;

        int N = a.size();
        // f[i]表示[a[0]~a[i]]组成的序列中且最后一个数是a[i]的最长上升子序列的长度
        vector<int> f(N, 1); // 初始化一个都为1的二维数组,即每个元素至少自己都是一个上升子序列
        int res = 1; // 初始化最小值为1
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
            res = max(res, f[i]);
        }
        return res;
    }
};


int main() {
    vector<int> nums = {10, 9, 2, 5, 3, 7, 101, 18};
    Solution s;
    cout << s.lengthOfLIS(nums) << endl;
    return 0;
}