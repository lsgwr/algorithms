//数字三角形－深度优先搜索 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#define maxn 100
using namespace std;

int a[maxn][maxn],N,Max,used[maxn],s;

void dfs(int i,int j)
{
  int x;
  s+=a[i][j];
  if (i==N-1)//结束条件 
  {
    if(s>Max) 
      Max=s; 
    return ; 
  }
  for (x=0;x<2;x++)
  {
    dfs(i+1,j+x);
    s-=a[i+1][j+x];
  }
}

int main()
{
  FILE *in =fopen("tower.in","r");
  FILE *out =fopen("tower.out","w");
  int i,j;
  fscanf(in,"%d",&N);
  for (i=0;i<N;i++)
    for (j=0;j<=i;j++)
      fscanf(in,"%d",&a[i][j]);
  dfs(0,0);
  fprintf(out,"%d\n",Max);
  return 0;
}
