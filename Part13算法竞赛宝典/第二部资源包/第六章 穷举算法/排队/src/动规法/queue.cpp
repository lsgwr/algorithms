//排队－动态规划法 
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;
int n,i,min;
int a[30002],f[30002],g[30002];

int main()
{
  freopen("queue.in","r",stdin);
  freopen("queue.out","w",stdout);
  cin>>n;
  for(int i=1;i<=n;++i)
     cin>>a[i];
  int min=2000000000;
  if (a[1]==1) 
    f[1]=0;
  else 
    f[1]=1;
  if(a[n]==2)
    g[n]=0; 
  else 
    g[n]=1;
  for(int i=2;i<=n;++i) 
    f[i]=f[i-1]+(a[i]-1);
  for(int i=n-1;i>=1;--i)
    g[i]=g[i+1]+(a[i]%2);
  for(int i=0;i<=n;++i)
    if(f[i]+g[i+1]<min)
      min=f[i]+g[i+1];
  cout<<min<<'\n';
  return 0;
}
