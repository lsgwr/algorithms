/***********************************************************
 * @Description : 2-2 选择排序的优化，编程模板形式
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 01:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

#include <iostream>
#include "Student.h"

using namespace std;
/**
 * 选择排序的核心实现,默认是从小到大进行排序
 * @param arr 待排序的数组
 * @param n   数据的长度(元素的个数)
 */
template<typename T>
void selectionSort(T arr[], int n) {
    for (int i = 0; i < n; ++i) {
        // 寻找[i, n]区间内的最小值
        int minIndex = i;
        // 找出本轮的最小元素
        for (int j = i + 1; j < n; ++j) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        // 把本轮的最小元素的位置和循环的起始位置进行置换
        swap(arr[i], arr[minIndex]);
    }
}

int main() {
    // 1.整型数组排序
    int a[10] = {10, 9, 5, 6, 8, 7, 2, 1, 3, 4};
    selectionSort(a, 10);
    for (int i = 0; i < 10; ++i) {
        cout << a[i] << " ";
    }
    cout << endl;
    // 2.对浮点数进行排序
    float b[4] = {2.2, 1.1, 4.4, 3.3};
    selectionSort(b, 4);
    for(int i = 0; i < 4; i++){
        cout << b[i] << " ";
    }
    cout << endl;
    // 3.字符数组排序
    char c[4] = {'B', 'A', 'D', 'C' };
    selectionSort(c, 4);
    for(int i = 0; i < 4; i++){
        cout << c[i] << " ";
    }
    cout << endl;
    // 4.字符串数组排序
    string d[4] = {"dba","abd","bbc","abc"};
    selectionSort(d, 4);
    for(int i = 0; i < 4; i++){
        cout << d[i] << " ";
    }
    cout << endl;

    // 5.自定义对象排序
    Student s[4] = {{"B",78}, {"A", 86},{"G",34}, {"D",78}};
    selectionSort(s,4);
    for(int i = 0; i < 4; i++){
        cout<<s[i];
    }
    cout<<endl;
    return 0;
}