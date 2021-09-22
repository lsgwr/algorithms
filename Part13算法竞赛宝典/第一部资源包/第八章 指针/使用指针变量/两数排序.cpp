//两数排序 
#include <iostream>
using namespace std;

int main()
{
    int *p1,*p2,*p,a=3,b=4;
    p1=&a;  p2=&b;   //p1,p2的值本来是指向a,b的
    if(a<b)
    {p=p1;p1=p2;p2=p;}  //地址值互相交换
    cout<<a<<" "<<b;
    cout<<*p1<<" "<<*p2;
}
