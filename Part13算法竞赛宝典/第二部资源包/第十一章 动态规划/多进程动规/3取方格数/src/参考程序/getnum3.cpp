#include <stdio.h>
#include <string.h>
#define max(a,b) a>b?a:b
int min(int a, int b)
{
       if (a < b)
              return a;
       else
              return b;
}
int f[41][21][21][21], a[21][21];
int main()
{
   freopen("getnum3.in","r",stdin);
  freopen("getnum3.out","w",stdout);      
       int n, i , j, k, x1, x2, x3, temp, s1, s2, s3;
       scanf("%d", &n);
       for (i = 1; i <= n; i++)
       {
              for (j = 1; j <= n; j++)
                     scanf("%d", &a[i][j]);
       }
       memset(f, 0, sizeof(f));
       f[1][1][1][1] = a[1][1];
       for (k = 2; k <= n + n -1; k++)
       {
              for (x1 = 1; x1 <= min(k, n); x1++)
              {
                     for (x2 = 1; x2 <= min(k, n); x2++)
                     {
                            for (x3 = 1; x3 <= min(k, n); x3++)
                            {
                                   temp = a[k - x1 + 1][x1] + a[k - x2 + 1][x2] + a[k - x3 + 1][x3];
                                   if (x1 == x2)
                                          temp -= a[k - x1 + 1][x1];
                                   if (x1 == x3)
                                          temp -= a[k - x1 + 1][x1];
                                   if (x2 == x3)
                                          temp -= a[k - x2 + 1][x2];
                                   if (x1 == x2 && x1 == x3)
                                          temp += a[k - x1 + 1][x1];
                                   for (s1 = -1; s1 <= 0; s1++)
                                   {
                                          for (s2 = -1; s2 <= 0; s2++)
                                          {
                                                 for (s3 = -1; s3 <= 0; s3++)
                                                 {
                                                        f[k][x1][x2][x3] = max(f[k][x1][x2][x3], f[k - 1][x1 + s1][x2 + s2][x3 + s3] + temp);
                                                 }
                                          }
                                   }
                            }
                     }
              }
       }
       printf("%d\n", f[n + n - 1][n][n][n]);
       return 0;
}
