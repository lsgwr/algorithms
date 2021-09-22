//乘法游戏-动态规划 
#include <iostream>
using namespace std;
#define MAXINT 0x7FFFFFFF
#define maxn 110
int f[maxn][maxn];
int w[maxn];



int main()
{
  freopen("Puzzle.in","r",stdin);
  freopen("Puzzle.out","w",stdout);    
  int i,j,k,n;  
  cin>>n;
  for(i=1;i<=n;++i)
    for(j=1;j<=n;++j)
      f[i][j]=MAXINT;
  for(i=1;i<=n;++i)
    cin>>w[i];
  for(i=1;i<=n;++i)//初始化很关键 
    f[i][i-1]=f[i][i]=f[i][i+1]=0;
  for (i=2;i<n;++i)//初始化很关键 
    f[i-1][i+1]=w[i-1]*w[i]*w[i+1];
  for(i=n-2;i>0;--i)//动态规划 
    for(j=i+2;j<=n;++j)
      for(k=i+1;k<j;++k)
        if (f[i][j]>f[i][k]+w[i]*w[k]*w[j]+f[k][j])
          f[i][j]=f[i][k]+w[i]*w[k]*w[j]+f[k][j];
    
  cout<<f[1][n]<<endl;
  return 0;
}
