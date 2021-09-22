//积木游戏 
#include <iostream>
#include <cstdlib>
using namespace std;

const int MAXN = 110;
int dp[MAXN][MAXN][MAXN][3];
int a[MAXN], b[MAXN], c[MAXN];// 0 bc, 1 ac, 2 ab

int main() 
{
  freopen("juggle.in","r",stdin);
  freopen("juggle.out","w",stdout);  
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 1; i <= n; i++) 
  {
    cin>>a[i]>>b[i]>>c[i];
    if (a[i] > b[i]) 
      swap(a[i], b[i]);
    if (b[i] > c[i]) 
      swap(b[i], c[i]);
    if (a[i] > b[i]) 
      swap(a[i], b[i]);
  }
  //t, s 控制上、下积木朝向。每次交换边的值，只让 b, c 比较即可
  for (int k = 1; k <= m; k++) 
    for (int i = 1; i <= n; i++) 
      for (int j = 0; j < i; j++) 
        for (int t = 0; t <= 2; t++) 
        {
          if (t == 1) 
            swap(b[i], a[i]);
          if (t == 2) 
            swap(c[i], b[i]), swap(a[i], b[i]);
          for (int s = 0; s <= 2; s++) 
          {
            if (s == 1) 
              swap(b[j], a[j]);
            if (s == 2) 
              swap(c[j], b[j]), swap(a[j], b[j]);
            if (b[i] <= b[j] && c[i] <= c[j])
              dp[k][i][i][t] = max(dp[k][i][i][t], dp[k][i-1][j][s] + a[i]);
            if (s == 2) 
              swap(a[j], b[j]), swap(b[j], c[j]);
            if (s == 1) 
              swap(b[j], a[j]);
            dp[k][i][i][t] = max(dp[k][i][i][t], dp[k-1][i-1][j][s] + a[i]);
            dp[k][i][j][s] = dp[k][i-1][j][s];
          }
          if (t == 2) 
            swap(a[i], b[i]), swap(c[i], b[i]);
          if (t == 1) 
            swap(b[i], a[i]);
        }
  int ans = 0;
  for (int j = 1; j <= n; j++)
    for (int s = 0; s <= 2; s++)
      ans = max(ans, dp[m][n][j][s]);
  cout<<ans<<endl;
  return 0;
}
