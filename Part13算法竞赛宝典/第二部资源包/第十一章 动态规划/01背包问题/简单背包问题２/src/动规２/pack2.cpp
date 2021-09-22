//简单背包问题２-动规２ 
#include <iostream>
#include <cstdlib>
using namespace std;
int f[20001];//f[j]表示前i个物品装入获得的最大体积

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
    for(j=V;j>=1;j--)
      if(j<a[i])
        f[j]=max(f[j-1],f[j]);//虽然f[j]总是大于f[j-1],但这句不能少
      else 
        f[j]=max(f[j],f[j-a[i]]+a[i]);
  cout<<V-f[V];
  return 0;
}
