//指针变量的应用
#include <iostream>
using namespace std;

#include <stdio.h>
void swap(int *p1,int *p2)//注意当指针变量作参数时形参的形式
{
     int temp;
     temp=*p1; *p1=*p2; *p2=temp;
}

main()
{
    int *_point1,*_point2,a=3,b=4;
    _point1=&a;  _point2=&b;  //将a,b的地址赋给_point1,_point2
    if(a<b)
        swap(_point1,_point2);//将指针变量作函数参数
    cout<<a<<" "<<b;
}
