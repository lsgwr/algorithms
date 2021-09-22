//货币面值 -01背包 
#include <iostream>
#include <cstdlib>
using namespace std;
 
int N,MAX; 
int value[101],dp[10001];
   
int Compare(const void * p, const void * q)
{
  return *(int *)p - *(int *)q;
}
   
int Max(int a, int b)
{
  return (a > b) ? a : b;
}
   
int ZeroOnePack()
{
  int i, j;
  memset(dp, 0, sizeof(dp));
  for (i = 1; i <= N; ++i)
    for (j = MAX; j >= value[i]; --j)
      dp[j] = Max(dp[j], dp[j-value[i]] + value[i]);
  for (i = 1; i <= MAX; ++i)
    if (dp[i] != i)
      return i;
}
   
int main()
{
  freopen("Currency.in","r",stdin);
  freopen("Currency.out","w",stdout);  
  int i;
  while (scanf("%d", &N) != EOF)
  {
    MAX = 0;
    for (i = 1; i <= N; ++i)
    {
      scanf("%d", &value[i]);
      MAX += value[i];
    }
    qsort(value, N, sizeof(int), Compare);
    cout<<ZeroOnePack()<<endl;
  }
  return 0;
}
