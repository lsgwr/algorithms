//数的划分－ＤＰ１ 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;
int f[10][201][201];
int main()
{
  freopen("Numdismantle.in","r",stdin);
  freopen("Numdismantle.out","w",stdout);
  int n,ans=0,m;
  cin>>n>>m;
  f[0][0][0]=1;
  for(int i=1;i<=m;i++)
    for(int j=1;j<=n;j++) 
      for(int k=1;k<=j;k++)
        for(int L=0;L<=k;L++)
          f[i][j][k]+=f[i-1][j-k][L];
  for(int i=1;i<=n;i++)
   ans+=f[m][n][i];
  cout<<ans<<endl;
  return 0;
}
