// �ݹ鷨
#include <iostream>

#define MAX 10001

using namespace std;

int a[MAX], target;

int search(int left, int right) {
    int mid;
    if (left <= right) {
        mid = (left + right) / 2;
        if (target == a[mid]) {
            cout << mid << endl;
            return 0;
        } else if (target < a[mid]) {
            search(left, right - 1);
        } else {
            search(mid + 1, right);
        }
    } else {
        cout << -1 << endl;
        return 0;
    }
}

int main() {
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    cin >> target;
    search(1, n);
    return 0;
}