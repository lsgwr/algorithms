//清扫计划 
#include <iostream>
#include <cstdlib>
#define INF 100000000
using namespace std;
int dis[12][12];
int dp[1<<11][12];
int n,ans,_min;

int main()
{
  freopen("cls.in","r",stdin);
  freopen("cls.out","w",stdout);
  while(scanf("%d",&n) && n)
  {
    for(int i = 0;i <= n;++i)
      for(int j = 0;j <= n;++j)
		scanf("%d",&dis[i][j]);
    for(int k = 0;k <= n;++k)//floyd算法 
      for(int i = 0;i <= n;++i)
		 for(int j = 0;j <= n;++j)
		   if(dis[i][k] + dis[k][j] < dis[i][j])
			 dis[i][j] = dis[i][k] + dis[k][j];
		
    for(int State = 0;State<= (1<<n)-1;++State)//枚举所有状态，位运算表示
      for(int i = 1;i <= n;++i)
      {
		if(State & (1<<(i-1)))//状态S中已经经过城市i
		{
		  if(State == (1<<(i-1)))//状态S只经过城市I	
            dp[State][i] = dis[0][i];//最优解自然是dis[0][i],即DP的边界 
		  else//如果S有经过多个城市
		  {
		    dp[State][i] = INF;
            //在没经过城市I的状态中，寻找合适的中间点J使得距离更短，和FLOYD一样
		    for(int j = 1;j <= n;++j)
		      if(State & (1<<(j-1)) && j != i)//枚举不是城市I的其他城市j 
				dp[State][i] = min(dp[State^(1<<(i-1))][j] + dis[j][i],dp[State][i]);
		  }
		}
      }
    ans = dp[(1<<n)-1][1] + dis[1][0];
    for(int i = 2;i <= n;++i)//找到最优解 
      if(dp[(1<<n)-1][i] + dis[i][0] < ans)
        ans = dp[(1<<n)-1][i] + dis[i][0];
    printf("%d\n",ans);
  }
  return 0;
}
