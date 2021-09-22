//指针变量指向数组元素的应用
#include <iostream>
using namespace std;
int main()
{
   int *p,i,a[10]={1,2,3,4,5,6,7,8,9,10};
   for(p=a;p<(a+10);p++)  //注：a为首地址赋给p，p++是指向下一元素而非加1
     cout<<*p<<" ";
}
