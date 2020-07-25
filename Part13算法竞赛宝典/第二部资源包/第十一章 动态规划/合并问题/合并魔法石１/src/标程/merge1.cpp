//合并魔法石１ 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int n;
int a[101][101];
int b[101][101];

main()
{
  freopen("merge1.in","r",stdin);
  freopen("merge1.out","w",stdout);    
  int i,j,k,Min;
  cin>>n;//输入堆数 
  for(i=1;i<=n;i++)
    cin>>a[1][i];//输入每堆的数量 

  for(i=2;i<=n;i++)//动态规划
    for(j=1;j<=n-i+1;j++)
    {
      a[i][j]=a[i-1][j]+a[1][j+i-1];
      Min=b[i-1][j];
      for(k=i-1;k>=1;k--)
        if(Min>(b[k][j]+b[i-k][j+k]))
          Min=b[k][j]+b[i-k][j+k];
        b[i][j]=a[i][j]+Min;         
     }
  cout<<b[n][1]<<'\n';
  return 0;
}
