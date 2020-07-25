//指针变量的应用
#include <iostream>
using namespace std;

int main()
{
    int a=3,b=4;
    int *_point1,*_point2;//定义了两个指针变量 *_point1,*_point2
    _point1=&b;           //把变量b的地址值赋给_point1
    _point2=&a;           //把变量a的地址值赋给_point2
    cout<<a<<' '<<b;
    cout<<*_point1<<' '<<*_point2);
}
