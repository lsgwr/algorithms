/***********************************************************
 * @Description : 最小堆，专门为本次的Lazy-Prim做得
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/10 00:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _MIN_HEAP_H_
#define _MIN_HEAP_H_
#include <iostream>
#include <cmath>
using namespace std;

template<typename Item>
class MinHeap{

private:
    // 数据内容
    Item* data;
    // 元素数量
    int count;
    // 数组容量
    int capacity;
    // 上浮的私有函数
    void shiftUp(int k){
        // k > 1是因为到根节点时就没父节点了，不需要再比较了
        while(k > 1 && data[k / 2] > data[k]){
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还大，
            // 则不符合最小堆的定义，那么就交换一下
            swap(data[k / 2], data[k]);
            k /= 2;
        }
    }
    // 把移上来的最后一个元素下移到合适位置
    void shiftDown(int k){
        // 当当前k节点有子节点的时候(有左孩子不一定有右孩子，但有右孩子一定有左孩子。因为生成堆得时候是从左向右地)
        // 等于号别漏
        while(2 * k <= count ){
            // 获取子孩子下标
            int j = 2 * k;
            // 存在右孩子并且右孩子小于左孩子，那么右孩子有父节点交换(左右两个子节点的较小值)
            if(j+1 <= count && data[j+1] < data[j]){
                // 选择右节点作为下面与k处节点进行交换
                j++;
            }
            // 父节点比两个子节点的较小值还小，那么不需要交换
            if(data[k] <= data[j]){
                break;
            }
            // 父节点大于孩子节点的较小值，那么就和较小值的子节点交换
            swap(data[k], data[j]);
            // 把换后的子节点作为父节点，接着往下走
            k = j;
        }
    }
public:
    // 构造函数
    MinHeap(int capacity){
        data = new Item[capacity + 1];
        count = 0;
        this->capacity = capacity;
    }

    // 直接输入完整的数组进行最小堆化(Heapify)
    // 核心在于从(n/2结果取整)下标位置的元素开始到下标为0的元素依次执行shiftDown,好好理解4-5老师前8分钟的图示
    MinHeap(Item arr[], int n){
        // 声明空间，还没赋值
        data = new Item[n + 1];
        capacity = n;
        // 挨个赋值
        for(int i = 0; i < n; i++){
            data[i + 1] = arr[i];
        }
        // 堆中元素个数为n了
        count = n;
        // 核心 : 从floor(n/2)下标位置的元素开始到下标为0的元素一次执行shiftDown,好好理解4-5老师前8分钟的图示
        // 执行完，就根据数组生成一个最小堆了
        for(int i = count / 2; i >= 1; i--){
            shiftDown(i);
        }
    }
    // 析构函数
    ~MinHeap(){
        delete[] data;
    }

    // 获取最小堆的大小
    int size(){
        return count;
    }

    // 判断最小堆是否为空
    bool isEmpty(){
        return count == 0;
    }

    // 插入元素
    void insert(Item item){
        // 防止后面的++count越界
        assert( count + 1 <= capacity);
        // 插入新元素，元素数加1,之所以用++count而不用count++是因为数组下标从1开始
        data[++count] = item;
        // 把新加入的元素向上浮动到合适位置
        shiftUp(count);
    }

    // 弹出最小值(根节点的对象)
    Item popMin(){
        // 保证堆不为空
        assert( count >0);
        // 最小元素为第一个元素
        Item min = data[1];
        // 移出最小元素后，需要把最下面的元素移到上面去
        swap(data[1], data[count]);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        return min;
    }

    // 获取堆中的最小元素(即堆顶元素)
    Item getMin(){
        assert( count > 0);
        return data[1];
    }
};
#endif // _MIN_HEAP_H_