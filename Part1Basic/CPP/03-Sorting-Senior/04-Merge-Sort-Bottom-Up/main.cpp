/***********************************************************
 * @Description : 归并排序的自底向上的实现，不用递归，只需迭代即可
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 17:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SortTestHelper.h"
using namespace std;

// 对当前轮的元素进行归并:将arr[left...middle]和arr[mid+1...right]两部分进行归并。注意都是闭区间
template<typename T>
void __merge(T arr[], int left, int middle, int right){
    // 声明辅助数组
    T aux[right - left + 1];
    // 元素统一拷贝到指定数组
    for(int i = left; i <= right; i++){
        // 注意数组元素的偏移量是l
        aux[i-left] = arr[i];
    }
    // 开始合并
    // 左侧游标
    int i = left;
    // 右侧游标
    int j = middle + 1;
    // k为要在arr中放入元素的位置
    for(int k = left; k <= right; k++){
        // 先判断特殊情况，一边已经快全部被拿走了，另一边还没动
        if(i > middle){
            // 说明左边的元素排完了，只能用右边地了
            arr[k] = aux[j - left];
            j++;
        }else if(j > right){
            // 右边元素已经排完了，只能用左边的了
            arr[k] = aux[i - left]; 
            i++;
        }else if(aux[i-left] < aux[j-left]){
            // 左半边 < 右半边
            arr[k] = aux[i-left];
            i++;
        }else{
            // 右半边 < 左半边
            arr[k] = aux[j-left];
            j++;
        }
    }

}

template<typename T>
void mergeSortBU(T arr[], int n){
    // 每次增大一倍步幅去自底向上归并
    for(int size = 1; size <= n; size += size){
        //关键点1： i + size < n是为了防止下面__merge的时候越界(因为i + size可能大于n了)
        for( int i = 0; i + size < n; i += size + size){
            // 对arr[i...i+size-1]和arr[i+size...i+size+size-1] 进行归并
            // 关键点2：虽然前面保证了1 + size < n，但是1 + size + size 也可能超过最大下标n-1,
            //         此处取它和n-1的更小值来保证merge的时候确实是两部分
            __merge(arr, i, i + size - 1, min(i + size + size -1, n - 1));
        }
    }
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    SortTestHelper::testSort("Merge Sort Bottom Up", mergeSortBU, arr, N);
    // SortTestHelper::printArray(arr, N);
    delete[] arr;
    return 0;
}