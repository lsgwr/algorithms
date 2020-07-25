#include <iostream>
using namespace std;

int main()
{
  int *p1,*p2,*p;
  int a=10,b=12;
  p1=&a;p2=&b;
  if(a<b)
  {
    p=p1;p1=p2;p2=p;       
  }
  cout<<*p1<<","<<*p2<<",";
  cout<<a<<","<<b<<endl;
  getchar();
}
