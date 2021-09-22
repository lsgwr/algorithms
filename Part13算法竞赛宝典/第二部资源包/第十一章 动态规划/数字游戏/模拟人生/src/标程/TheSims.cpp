//模拟人生 
#include <stdio.h>
#include <stdlib.h>
#define MAX 10000000
#define MIN -10000000
int line[200],n,m,Min,Max,sum[200];
int f[200][20];//f[i][j]:第1-i个数分成j份的最大值
int g[200][20];//g[i][j]:第1-i个数分成j份的最小值

int min(int a,int b)
{
  if(a>b)
    return b;
  return a;
}

int max(int a,int b)
{
  if(a>b)
    return a;
  return b;
}

void dp(int a[])
{
  int i,j,k;
  for(i=1;i<=n;i++)
    sum[i]=sum[i-1]+a[i];
  for(i=0;i<=n;i++)
    for(j=0;j<=m;j++)
    {
	  f[i][j]=0;
	  g[i][j]=-1u>>1;
    }
  for(i=1;i<=n;i++)
  {
    f[i][1]=g[i][1]=(sum[i]%10+10)%10;
  }
  f[0][0]=1;
  g[0][0]=1;
  for(j=2;j<=m;j++)
    for(i=j;i<=n;i++)
      for(k=j-1;k<i;k++)
      {
        f[i][j]=max(f[i][j],f[k][j-1]*(((sum[i]-sum[k])%10+10)%10));
        g[i][j]=min(g[i][j],g[k][j-1]*(((sum[i]-sum[k])%10+10)%10));
      }
  Max=max(Max,f[n][m]);
  Min=min(Min,g[n][m]);
}

int main()
{ 
  freopen("TheSims.in","r",stdin);
  freopen("TheSims.out","w",stdout);
  int i,j,k;
  Max=0;
  Min=-1u>>1;
  scanf("%d%d",&n,&m);
  for(i=1;i<=n;i++)
  {
    scanf("%d",&line[i]);
    line[i+n]=line[i];
  }
  for(i=0;i<n;i++)
    dp(line+i);
  printf("%d\n%d\n",Min,Max);
  return 0;
}
