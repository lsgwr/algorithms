#include <iostream>

using namespace std;

// 二分查找法，在有序数组中arr中，查找target
// 找到target就返回target对应的索引，找不到就返回-1
template<typename T>
int binarySearch(T arr[], int n, T target){
    // 在arr[left...right]之中查找target
    int left = 0, right = n-1;
    while(left <= right){
        // middle = (left+right)/2中，left + right 可能越界;，完美的实现方式如下
        int middle = left + (right - left)/2;
        if(arr[middle] == target){
            return middle;
        }else if(arr[middle] < target){
            // 在右半部分中[middle+1...right]
            left = middle + 1;
        }else{
            // 在左半部分中[left...middle-1]
            right = middle - 1;
        }
    }
    return -1;
}

// 递归方式实现二分法
template<typename T>
int __binarySearch2(T arr[], int left, int right, T target){
    if(left > right){
        return -1;
    }
    // middle = (left+right)/2中，left + right 可能越界;，完美的实现方式如下
    int middle = left + (right - left)/2;
    if(arr[middle] == target){
        return middle;
    }else if(arr[middle] < target){
        // 在右半部分中[middle+1...right]
        return __binarySearch2(arr, middle + 1, right, target);
    }else{
        // 在左半部分中[left...middle-1]
        return __binarySearch2(arr, left, middle - 1, target);
    }
}

template<typename T>
int binarySearch2(T arr[], int n, T target){
    return __binarySearch2(arr, 0, n-1, target);
}
int main(void){
    int n = 1000000;
    int* arr = new int[n];
    for(int i = 0; i < n;i++){
        arr[i] = i;
    }
    // 1.测试非递归方法
    clock_t startTime = clock();
    // 对于我们的待查找数组[0...N)
    // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
    // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
    for(int i = 0; i < 2*n; i++){
        int v = binarySearch(arr, n, i);
        if(i < n){
            assert(v == i);
        }else{
            assert(v == -1);
        }
    }
    clock_t endTime = clock();
    cout << "Binary Search(Without Recursion):" << double(endTime - startTime) / CLOCKS_PER_SEC << " s" << endl;


    // 2.测试递归方法
    startTime = clock();
    // 对于我们的待查找数组[0...N)
    // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
    // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
    for(int i = 0; i < 2*n; i++){
        int v = binarySearch2(arr, n, i);
        if(i < n){
            assert(v == i);
        }else{
            assert(v == -1);
        }
    }
    endTime = clock();
    cout << "Binary Search(With Recursion):" << double(endTime - startTime) / CLOCKS_PER_SEC << " s" << endl;
    delete[] arr;
    return 0;
}