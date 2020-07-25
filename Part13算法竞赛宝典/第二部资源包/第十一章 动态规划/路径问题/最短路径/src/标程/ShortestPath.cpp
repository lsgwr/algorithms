//最短路径 
#include <cstdlib>
#include <iostream>
using namespace std;
#define IMAX 99999999
int n;

int main()
{
  freopen("ShortestPath.in","r",stdin);
  freopen("ShortestPath.out","w",stdout);  
  cin>>n;
  int a[n+1][n+1];
  int f[n+1],c[n+1]; //f保存最短路径,c保存经过的路径 
  int i,j,x;
  int o,t,th; 
  for(i=0;i<=n+1;i++)
    f[i]=0;
  for(i=0;i<=n+1;i++)
    c[i]=0;
  for(i=1;i<=n+1;i++)
    for(j=1;j<=n+1;j++)
      a[i][j]=-1;
  while(o!=0 && t!=0 && th!=0) 
  {
    cin>>o>>t>>th;
    a[o][t]=th;
  }
    
  for(i=1;i<=n-1;i++)//均初始化为最大值 
    f[i]=IMAX;  
  f[n]=0;

  for(i=n-1;i>=1;i--)
    for(j=n;j>i;j--)
      if((a[i][j]>0)&&(f[j]!=IMAX)&&(f[j]+a[i][j]<f[i]))
      {
        f[i]=f[j]+a[i][j];
        c[i]=j;
      }
  cout<<f[1]<<'\n';

  /*x=1;
  while(x!=0)//打印路径 
  {
    printf("%5d",x);
    x=c[x];
  }*/
  return 0;
}
