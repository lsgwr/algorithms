//æµ 
#include<iostream>
#include<cstdlib>
using namespace std;

#define INF 1000000
#define MAX_LIMT 110

int get_max(int x,int y)
{
  return x>y?x:y;
}

int main()
{
  freopen("busy.in","r",stdin);
  freopen("busy.out","w",stdout);  
  int n,t;
  scanf("%d%d",&n,&t);
  int i,j,k,dp[MAX_LIMT][MAX_LIMT];
  memset(dp,0,sizeof(dp));
  for(i=1;i<=n;i++)
  {
    int m,s,cost[MAX_LIMT],val[MAX_LIMT];
    scanf("%d%d",&m,&s);
    for(j=1;j<=m;j++)
      scanf("%d%d",&cost[j],&val[j]);
    if(s==0)
    {
      for(j=0;j<=t;j++)
        dp[i][j]=-INF;
      for(j=1;j<=m;j++)
        for(k=t;k>=cost[j];k--)
        {
          dp[i][k]=get_max( dp[i][k],dp[i][k-cost[j]]+val[j]);
          dp[i][k]=get_max( dp[i][k],dp[i-1][k-cost[j]]+val[j]);
        }
    }
    else if(s==1)
    {
      for(j=0;j<=t;j++)
        dp[i][j]=dp[i-1][j];
      for(j=1;j<=m;j++)
        for(k=t;k>=cost[j];k--)
          dp[i][k]=get_max(dp[i][k],dp[i-1][k-cost[j]]+val[j]);
    }
    else
    {
      for(j=0;j<=t;j++)
        dp[i][j]=dp[i-1][j];
      for(j=1;j<=m;j++)
        for(k=t;k>=cost[j];k--)
        {
          dp[i][k]=get_max( dp[i][k],dp[i][k-cost[j]]+val[j]);
          dp[i][k]=get_max( dp[i][k],dp[i-1][k-cost[j]]+val[j]);
        }
    }
  }
  dp[n][t]=get_max(dp[n][t],-1);
  printf("%d\n",dp[n][t]);
  return 0;
}
