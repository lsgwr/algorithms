#include <stdio.h>  
#include <string.h>  
#define MAX 110  
#define INF 1000000000  
  
  
int state[MAX][MAX], one[MAX][MAX];         //state[i][j]表示第i行第j个合法状态，one表示第i行第j个合法状态中含1的个数  
int ans, stnum[MAX], sum[MAX * 20];         //stnum[i]表示i行合法的状态数，sum[i]为i状态下1的个数  
int n, m, map[MAX][MAX], dp[MAX][70][70];   //dp[i][j][k]表示第i行第j个状态第-1行第k个状态含有的最多1的个数  
  
  
inline int max(int a, int b) 
{  
  return a > b ? a : b;  
} 
 
void Initial() 
{  
  ans = 0;  
  memset(dp, 0, sizeof (dp));  
  memset(map, 0, sizeof (map));  
  memset(one, 0, sizeof (one));  
  memset(stnum, 0, sizeof (stnum));  
} 
 
void GetOneSum() 
{  
  //预处理，先把每个状态里含有的1的数量算出来  
  for (int i = 0; i <= (1 << 10); ++i) 
  {  
    int tp = 0;  
    for (int j = 0; j <= 10; ++j)  
      if (i & (1 << j)) 
        tp++;  
    sum[i] = tp;  
  }  
} 
 
int Check(int x) 
{  
  //x&(x>>1)是判断当前列是否和前一列冲突,x>>2就是前两列  
  if (x > 1 && (x & (x >> 1))) 
    return 0;  
  if (x > 2 && (x & (x >> 2))) 
    return 0;  
  return 1;  
} 
 
void FindState(int x, int tot) 
{  
  //预处理，把第x行中合法的状态全部找出来，存到state数组中，tot是本行所有的p点压缩起来的一个状态  
  for (int i = 0; i < (1 << m); ++i)  
    if (Check(i) && (i & tot) == i) 
    {  
      //（i&tot） == i表示集合i是集合tot的子集合，意思是i里面的含有的列都是p点  
      stnum[x]++;  
      int tp = stnum[x];  
      state[x][tp] = i;  
      one[x][tp] = sum[i];  
    }  
}  
  
int main()   
{  
  int i, j, k, s;  
  char tp[MAX];  
  GetOneSum();  
  while (scanf("%d%d", &n, &m) != EOF) 
  {  
    Initial(), stnum[0] = 70;  
    for (i = 1; i <= n; ++i) 
    {  
      scanf("%s", tp);  
      for (k = j = 0; j < m; ++j) 
      {         
        map[i][j] = (tp[j] == 'P' ? 1 : 0);  
        k += (map[i][j] ? (1 << j) : 0);  
      }  
      FindState(i, k);  
    }  
  
    //初始化第1行  
    for (j = 1; j <= stnum[1]; ++j)  
      for (k = 1; k <= stnum[0]; ++k)  
        dp[1][j][k] = one[1][j];  
    //状态转移  
    for (i = 2; i <= n; ++i)  
      for (j = 1; j <= stnum[i]; ++j)  
        for (k = 1; k <= stnum[i - 1]; ++k)  
          if (!(state[i][j] & state[i - 1][k])) 
          {                 
            //判断两个状态是否有冲突  
            int tpmax = 0;  
            for (s = 0; s <= stnum[i - 2]; ++s) 
            {  
              if (!(state[i][j] & state[i-2][s])              //判断三个状态是否有冲突  
              && !(state[i - 1][k] & state[i - 2][s]))  
                tpmax = max(dp[i - 1][k][s], tpmax);  
            }  
            dp[i][j][k] = tpmax + one[i][j];  
          }  
  
    //Update Answer  
    for (j = 1; j <= stnum[n]; ++j)  
      for (k = 1; k <= stnum[n-1]; ++k)  
        ans = max(ans, dp[n][j][k]);  
    printf("%d\n", ans);  
  }
  return 0;  
}  
