/***********************************************************
 * @Description : 快速排序的优化:三路快速排序，相对于Section7可以
 *                节省掉大量重复元素置换的时间，对于含大量重复元素
 *                的数组更加有效了
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 21:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SortTestHelper.h"
using namespace std;

// 三路快速排序处理arr[left...right]
// 将arr[left...right]分为 < v、=v、> v三部分
// 之后递归对 < v、 > v两部分继续进行三路快速排序
template <typename T>
void __quickSort(T arr[], int left, int right){
    // 左右指针相遇的时候，就直接退出
    if(left >= right){
        return;
    }
    // 下面是切分部分
     swap(arr[left], arr[rand()%(right-left+1) + left]);
    // 选择后面所有元素都要与之比较的元素，一般直接选第一个就好
    T ref = arr[left];
    // lt-less than,含义是：arr[left+1...lt] < v
    int lt = left;
    // gt-greater than 含义是 arr[left+1...lt] > v
    int gt = right + 1;
    // arr[lt+1...i] == v
    int i = left + 1;
    // 还没相遇之前,gt是不断--地
    while(i < gt){
        if(arr[i] < ref){
            swap(arr[i], arr[lt+ 1]);
            lt++;
            i++;
        }else if(arr[i] > ref){
            swap(arr[i], arr[gt-1]);
            gt--;
            // 注意i不要++,因为交换后地元素还没判断，交给下一轮处理就好啦
        }else{
            // 当
            i++;
        }
    }
    swap(arr[left], arr[lt]);
    // 递归处理
    __quickSort(arr, left, lt-1);
    __quickSort(arr, gt, right);
}

template <typename T>
void quickSort(T arr[], int n){
    // 设置随机种子
    srand(time(NULL));
    __quickSort(arr, 0, n-1);
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, 10);
    // 测试数组是否有序
    SortTestHelper::testSort("Quick Sort 3 Way", quickSort, arr, N);
    // SortTestHelper::printArray(arr, N);
    delete[] arr;
    return 0;
}