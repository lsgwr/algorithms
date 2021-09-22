//矩阵连乘 
#include <iostream>
#include <stdlib.h>
const int MAXN=110;

int	F[MAXN][MAXN];
int	R[MAXN],C[MAXN],N;//将维度分开存入两个数组，左和右数组 

int main()
{
  freopen ("matrix.in", "r", stdin);
  freopen ("matrix.out", "w", stdout);

  scanf ("%d", &N);
  for (int i = 1; i <= N; ++i)//此处i从１编号 
    scanf ("%d%d", &R[i], &C[i]);

  memset (F, 0, sizeof (F));//初始化 
  for (int l = 2; l <= N; ++l)//l确定几个矩阵合并，为2时，是2个矩阵合并 
    for (int i = 1; i <= N - l + 1; ++i)//求某个l值下第i行的格子的值
    {
      int j = i + l - 1;//确定右边的界限 
	  F[i][j] = 1000000000;
      for (int k = i; k < j; ++k)//从i至j的l个矩阵，在k位置分隔成两部分
        if (F[i][k]+F[k+1][j]+R[i]*C[k]*C[j]<F[i][j])
		  F[i][j]=F[i][k]+F[k+1][j]+R[i]*C[k]*C[j];
     }

  printf ("%d\n", F[1][N]);
  return 0;
}
