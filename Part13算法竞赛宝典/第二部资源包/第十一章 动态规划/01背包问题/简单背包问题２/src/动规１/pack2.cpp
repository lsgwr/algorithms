//简单背包问题２－动规１ 
#include <iostream>
using namespace std;

int f[31][20001];

int main()
{
  freopen("pack2.in","r",stdin);
  freopen("pack2.out","w",stdout);
  int V,n,i,j;
  cin>>V>>n;
  int a[n+1];
  for(i=1;i<=n;i++)
    cin>>a[i];
  for(i=1;i<=n;i++)
    for(j=1;j<=V;j++)
      if(j<a[i])
        f[i][j]=f[i-1][j];
      else
        f[i][j]=max(f[i-1][j],f[i-1][j-a[i]]+a[i]);
  cout<<V-f[n][V]<<'\n';
  return 0;
}
