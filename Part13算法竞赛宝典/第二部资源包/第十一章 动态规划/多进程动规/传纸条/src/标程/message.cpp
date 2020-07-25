//传纸条 
#include<stdio.h>
#include<stdlib.h>

int f[52][52][52][52] = {0}, n, G[52][52];

int max(int a, int b, int c, int d)//返回4个值的最大值 
{
  if (a < b) a= b;
  if (a < c) a= c;
  if (a < d) a= d;
  return a;
}

int main()
{
  freopen("message.in","r",stdin);
  freopen("message.out","w",stdout);
  int m, n, i, j, k, l;
  scanf("%d%d", &m, &n);
  for (i = 1; i <= m; i++)
    for (j = 1; j <= n; j++)
      scanf("%d", &G[i][j]);
  for (i = 1; i <= m; i++)
    for (j = 1; j <= n; j++)
      for (k = 1; k <= m; k++)
      {
        if (i+j-k > 0) 
          l = i+j-k; 
        else 
          continue;
        f[i][j][k][l]=max(f[i-1][j][k-1][l],f[i-1][j][k][l-1],f[i][j-1][k-1][l],f[i][j-1][k][l-1])+G[i][j]+G[k][l];
        if (i == k && j == l) 
          f[i][j][k][l] -= G[i][j];
      }
  printf("%d\n", f[m][n][m][n]);
}
