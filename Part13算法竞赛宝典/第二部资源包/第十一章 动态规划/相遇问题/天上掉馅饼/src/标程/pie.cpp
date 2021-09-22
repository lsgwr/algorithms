//天上掉馅饼 
#include <iostream>
#include <stdlib.h>
#include <math.h>
using namespace std;
#define MAX 100001

int dp[MAX][12];//dp[i][j]表示在i时刻落在j点的馅饼数量

int Max2(int a, int b)
{
  return (a > b) ? a : b;
}

int Max3(int a, int b, int c)
{
  int max = (a > b) ? a : b;
  return (max > c) ? max : c;
}

int work(int max_time)
{
  int i, j, max;
  for (i = max_time - 1; i >= 0; --i)
  {
    dp[i][0] = Max2(dp[i+1][0], dp[i+1][1]) + dp[i][0];
    for (j = 1; j < 10; ++j)
    {
      dp[i][j] = Max3(dp[i+1][j-1],dp[i+1][j],dp[i+1][j+1])+dp[i][j];
	}
	dp[i][10] = Max2(dp[i+1][10], dp[i+1][9]) + dp[i][10];
  }
  return dp[0][5];
}

int main()
{
  freopen("pie.in","r",stdin);
  freopen("pie.out","w",stdout);  
  int n,i,time,location,max_time;
  while (scanf("%d", &n) != EOF && n != 0)
  {
    memset(dp, 0, sizeof(dp));
    max_time = -1;
    for (i=1; i<=n; ++i)
    {
      scanf ("%d%d", &location, &time);
      ++dp[time][location];
      if (max_time < time)
		max_time = time;
    }
    printf ("%d\n", work(max_time));
  }
  return 0;
}
