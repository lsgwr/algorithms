// �ǵݹ鷨
#include <iostream>

#define MAX 10001

using namespace std;

int a[MAX], target, n;

void search() {
    int left = 1, right = n, mid;
    while (left <= right) {
        mid = (left + right) / 2;
        if (target == a[mid]) {
            cout << mid << endl;
            return;
        } else if (target < a[mid]) {
            left = mid - 1;
        } else {
            right = mid + 1;
        }
    }
    cout << -1 << endl;
}

int main() {
    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    cin >> target;
    if (target < a[1] || target > a[n]) {
        cout << -1 << endl;
    } else {
        search();
    }
    return 0;
}