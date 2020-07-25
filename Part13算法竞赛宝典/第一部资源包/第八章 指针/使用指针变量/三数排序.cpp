//ÈýÊýÅÅÐò 
#include <iostream>
using namespace std;

void swap(int *p1,int *p2)
{
     int temp;
     temp=*p1;
     *p1=*p2;
     *p2=temp;
}

void exchange(int *q1,int *q2,int *q3)
{
  if(*q1<*q2)swap(q1,q2);
  if(*q1<*q3)swap(q1,q3);
  if(*q2<*q3)swap(q2,q3);
}

main()
{
    int *_point1,*_point2,*_point3,a,b,c;
    cin>>a>>b>>c;
    _point1=&a;  _point2=&b; _point3=&c;
    exchange(_point1,_point2,_point3);
    cout<<a<<" "<<b<<" "<<c;
}
