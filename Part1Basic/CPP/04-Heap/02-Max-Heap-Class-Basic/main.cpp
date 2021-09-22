#include <iostream>
using namespace std;

template<typename Item>
class MaxHeap{

private:
    // 数据内容
    Item* data;
    // 元素数量
    int count;
public:
    // 构造函数
    MaxHeap(int capacity){
        data = new Item[capacity + 1];
        count = 0;
    }
    // 析构函数
    ~MaxHeap(){
        delete[] data;
    }

    // 获取最大堆的大小
    int size(){
        return count;
    }

    // 判断最大堆是否为空
    bool isEmpty(){
        return count == 0;
    }
};

int main(){
    MaxHeap<int> maxHeap = MaxHeap<int>(100);
    cout << maxHeap.size() << endl;
    return 0;
}