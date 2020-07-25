//双塔问题-数学归纳+高精度
#include <iostream>
#include <cstdlib>
using namespace std;

const int one=10000;
int a[31],n,i,x;

void time()
{
  int i,x;
  x=0;
  for(i=1;i<=a[0];i++)
  {
    x=a[i]*2+x;
    a[i]=x%one;
    x=x/one;
  }
  while(x>0)
  {
    a[0]++;
    a[a[0]]=x%one;
    x=x/one;
  }
}

int main()
{
  freopen("hanoi2.in","r",stdin);
  freopen("hanoi2.out","w",stdout);    
  cin>>n;
  a[0]=1;
  a[1]=1;
  for(i=1;i<=n+1;i++)
    time();
  a[1]-=2;
  cout<<a[a[0]];
  for(i=a[0]-1;i>=1;i--)
  {
    cout<<a[i]/100%10;
    cout<<a[i]/10%10;
    cout<<a[i]%10;
  } 
  return 0;  
}
