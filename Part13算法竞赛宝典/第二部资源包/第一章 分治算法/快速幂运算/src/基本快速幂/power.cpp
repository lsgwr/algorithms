// 基本快速幂运算 
#include <cstdio>
#include <cstdlib>
#include <iostream>
using namespace std;

int pow(int a,int b)
{
  if(b==1)
    return a;
  else
  {
    int c=pow(a,b/2);
    if((b%2)==0)
      return c*c;
    else
      return c*c*a;      
   }     
}

int main()
{
  freopen("power.in","r",stdin);
  freopen("power.out","w",stdout);  
  int X,n;
  cin>>X>>n;
  cout<<pow(X,n)<<endl;
  return 0; 
}
