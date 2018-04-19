#include <iostream>
#include "SortTestHelper.h"
using namespace std;

// 挨个插入元素构成堆
/**
 * 把移上来的最后一个元素下移到合适位置。注意此处最顶元素的下标是从0开始地
 * @param arr  待排序数组
 * @param n    当前要排序的堆元素数
 * @param k    要下沉的元素的位置
 * */
template<typename T>
void __shiftDown(T arr[], int n, int k){
    // 当当前k节点有子节点的时候(有左孩子不一定有右孩子，但有右孩子一定有左孩子。因为生成堆得时候是从左向右地).注意没等号哦
    while(2*k+1 < n ){
        // 获取子孩子下标
        int j=2*k+1;
        // 存在右孩子并且右孩子大于左孩子，那么右孩子与父节点交换，j++是先记录下要与父节点进行交换的子节点的下标
        if(j+1 < n && arr[j+1] > arr[j]){
            // 选择右节点作为下面与k处节点进行交换
            j++;
        }
        // 父节点比两个子节点的较大值还大，那么不需要交换
        if(arr[k] >= arr[j]){
            break;
        }
        // 父节点小于孩子节点的较大值，那么就和较大值的子节点交换
        swap(arr[k], arr[j]);
        // 把换后的子节点作为父节点，接着往下走
        k = j;
    }
}

// 原地堆排序：不断把最大元素换到堆数组的最后面，然后堆大小减1(换到最后面的元素就相当于pop出去了，但是没有额外新申请空间)
//           然后对换到根元素(数组中下标为0，注意不是1)位置的新元素在新堆中下沉到合适位置，新的堆形成，然后从头再开始最开始的操作...
template<typename T>
void heapSort(T arr[], int n){
    // 先对整个数组进行堆化
    for( int i = (n-1)/2; i >= 0; i--){
        __shiftDown(arr, n, i);
    }

    for(int i = n-1; i > 0; i--){
        // 把最大的元素换到最后面去，这时就不是严格的堆了
        swap(arr[0], arr[i]);
        // 对新的堆(最大元素移到最后面相当于pop了)进行重新调整，使其成为有效的堆(其实就是把上一步换到0位置的元素下沉到合适位置即可)
        __shiftDown(arr, i, 0);
    }
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    //  SortTestHelper::testSort("Heap Sort", heapSort, arr, N);
     SortTestHelper::testSort("Heap Sort with Less Space", heapSort, arr, N);
    delete[] arr;
    return 0;
}