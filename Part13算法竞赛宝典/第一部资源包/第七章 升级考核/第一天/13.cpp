#include <iostream>
using namespace std;

int func(int a,int b)
{
  return(a+b);  
}

int main()
{
  int x=2,y=5,z=8,r;
  r=func(func(x,y),z);
  cout<<r<<endl;
  getchar();     
}
