//位优化快速幂运算 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

long long Pow(long long x, long long n) 
{     
  long long result;     
  if (n == 0)         
    return 1; 
  else
  {
    while ((n & 1) == 0)//当n为偶数时         
    {             
      n >>= 1;//即n=n/2             
      x *= x;         
    }     
  }     
  result = x;     
  n >>= 1;     
  while (n != 0)     
  {             
    x *= x;         
    if ((n & 1) != 0)             
      result *= x;         
    n >>= 1;     
  }     
  return result; 
}

int main()
{
  freopen("power.in","r",stdin);
  freopen("power.out","w",stdout); 
  long long x,n;
  cin>>x>>n;
  cout<<Pow(x,n)<<endl;
  return 0;     
}
