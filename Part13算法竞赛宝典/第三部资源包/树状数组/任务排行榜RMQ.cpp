//任务排行榜 
#include <bits/stdc++.h>
#define N 100010
using namespace std;
int dp1[N][20];//存放区域最大值
int dp2[N][20];//存放区域最小值
int n,m;

void RMQ_init(int n)
{
  for(int j=1; j<20; j++)
    for(int i=1; (i+(1<<j)-1)<=n; i++)
    {
      dp1[i][j]=max(dp1[i][j-1],dp1[i+(1<<(j-1))][j-1]);
      dp2[i][j]=min(dp2[i][j-1],dp2[i+(1<<(j-1))][j-1]);
    }
}

int RMQ(int L,int R)
{
  int k=(int)(log(R-L+1.0)/log(2.0));
  int Max=max(dp1[L][k],dp1[R-(1<<k)+1][k]);
  int Min=min(dp2[L][k],dp2[R-(1<<k)+1][k]);
  return Max-Min;
}

int main()
{
  scanf("%d %d",&n,&m);
  for(int i=1; i<=n; i++)
  {
    scanf("%d",&dp1[i][0]);
    dp2[i][0]=dp1[i][0];
  }
  RMQ_init(n);
  int l,r;
  for(int i=1; i<=m; i++)
  {
    scanf("%d%d",&l,&r);
    printf("%d\n",RMQ(l,r));
  }
  return 0;
}
