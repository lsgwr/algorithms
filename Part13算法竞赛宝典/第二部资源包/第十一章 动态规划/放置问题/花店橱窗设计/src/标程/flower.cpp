//»¨µê³÷´°Éè¼Æ 
#include <stdio.h>
#include <stdlib.h>
int f[101][101];
int map[101][101];
int p[101][101];

void output(int a, int b)
{
  if(a == 1)
  {
    printf("%d", b);
    return;
  }
  output(a - 1, p[a][b]);
  printf(" %d", b);
}

int main(int argc, char **argv)
{
  freopen("flower.in","r",stdin);
  freopen("flower.out","w",stdout);
  int i, j, k;
  int m, n;
  scanf("%d%d", &n, &m);
  for(i = 1; i <= n; i++)
  {
    for(j = 1; j <= m; j++)
    {
      scanf("%d", &map[i][j]);
    }
  }
  for(i = 1; i <= n; i++)
  {
    for(j = i; j <= m - n + i; j++)
    {
      f[i][j] = -100000000;
      for(k = i - 1; k < j; k++)
      {
        if(f[i - 1][k] + map[i][j] > f[i][j])
        {
           f[i][j] = f[i - 1][k] + map[i][j];
           p[i][j] = k;
        }
      }
    }
  }
  for(i = n, k = 0; i <= m; i++)
  {
    if(k < f[n][i])
    {
      k = f[n][i];
      j = i;
    }
  }
  printf("%d\n", f[n][j]);
  output(n, j);
  printf("\n");
  return 0;
}
