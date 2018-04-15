/***********************************************************
 * @Description : 快速排序的优化：处理含有大量重复元素的数组,双路处理法，
 *                可以把重复的元素均匀地分散到左右两边，从而有效地防止
 *                partition失衡
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 19:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SortTestHelper.h"
using namespace std;
 
// 返回合适的且分点，最终结果如下：返回p，使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p] 
template<typename T> 
int __partition(T arr[], int left, int right){
    swap(arr[left], arr[rand()%(right-left+1) + left]);
    // 选择后面所有元素都要与之比较的元素，一般直接选第一个就好
    T ref = arr[left];
    //开始考察元素： arr[left+1...j] <= v ; arr[j...right] >= v
    int i = left+1;
    int j = right;
    while(true){
        while(i <= right && arr[i] < ref){
            i++;
        }
        while(j >= left +1 && arr[j] > ref){
            j--;
        }
        if(i > j){
            break;
        }
        // arr[i] >= v 并且 arr[j] <= v的时候该把i和j的元素进行交换的情况
        swap(arr[i], arr[j]);
        i++;
        j--;
    }
    // 最后把参考元素置换到中间去
    swap(arr[left], arr[j]);
    // 最后j的位置就是切分点应该在的位置
    return j;
}

// 对arr[left...right]内的元素进行快速排序，需要用到递归
template <typename T>
void __quickSort(T arr[], int left, int right){
    // 左右指针相遇的时候，就直接退出
    if(left >= right){
        return;
    }
    // 选择切分点，执行__partition使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p] 
    int partition = __partition(arr, left, right);
    // 递归进行左右两侧排序
    __quickSort(arr, left, partition-1);
    __quickSort(arr, partition+1, right);
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
    SortTestHelper::testSort("Quick Sort", quickSort, arr, N);
    // SortTestHelper::printArray(arr, N);
    delete[] arr;
    return 0;
}