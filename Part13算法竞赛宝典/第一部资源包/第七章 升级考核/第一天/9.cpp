#include <iostream>
using namespace std;

int fun(int a,int b);
int main()
{
  int k=4,m=1,p;
  p=fun(k,m);
  cout<<p<<",";
  p=fun(k,m);
  cout<<p<<endl;
  getchar();
}

int fun(int a,int b)
{
  static int m=0,i=2;
  i+=m+1;m=i+a+b;
  return m;     
}
