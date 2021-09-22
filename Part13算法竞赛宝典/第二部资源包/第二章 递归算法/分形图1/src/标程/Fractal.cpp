//分形图１ 
#include<iostream>
#include<cstdio>
#include<cmath>
using namespace std;

char map[731][731];

void dfs(int n,int x,int y)
{
	int size;
	if(n==1)
	{
		map[x][y]='X';
		return ;
	}
	size=pow(3.0,n-2);
	dfs(n-1,x,y);       //左上角
	dfs(n-1,x,y+2*size);       //右上角
	dfs(n-1,x+size,y+size);    //中间
	dfs(n-1,x+2*size,y);       //左下角
	dfs(n-1,x+2*size,y+2*size);   //右下角
}
int main(void)
{
  freopen("Fractal.in","r",stdin);
  freopen("Fractal.out","w",stdout);  
  int i,j,n,size;
  while(scanf("%d",&n)!=EOF)
  {
    if(n==-1)
      break;
    size=pow(3.0,n-1);
    for(i=1;i<=size;i++)//初始化 
      for(j=1;j<=size;j++)
      map[i][j]=' ';
    dfs(n,1,1);
    for(i=1;i<=size;i++)
    {
      for(j=1;j<=size;j++)
		printf("%c",map[i][j]);
      printf("\n");
    }
    printf("-\n");
  }
  return 0;
}
