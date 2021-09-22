//机器分配 
#include <iostream>
#include <cstdlib>
using namespace std;

int m,n;
long i,j,k,Max;
int f[11][16],value[11][16];

void show(long i,long j)
{
  long k;
  if(i==0)
    return;
  for(k=0;k<=j;k++)
    if(Max==f[i-1][k]+value[i][j-k])
    {
      Max=f[i-1][k];
      show(i-1,k);
      printf("%ld %ld\n",i,j-k);
      return;
    }
}

int main()
{
  freopen("machine.in","r",stdin);
  freopen("machine.out","w",stdout);  
  scanf("%d%d",&n,&m);
  for(i=1;i<=n;i++)
    for(j=1;j<=m;j++)
      scanf("%d",&value[i][j]);
  for(i=1;i<=n;i++)
    for(j=1;j<=m;j++)
    {
      Max=0;
      for(k=0;k<=j;k++)
        if(f[i-1][k]+value[i][j-k]>Max)
          Max=f[i-1][k]+value[i][j-k];
      f[i][j]=Max;
    }
  printf("%d\n",f[n][m]);//输出最大盈利值 
  //show(n,m);//输出分配情况 
  return 0;
}
