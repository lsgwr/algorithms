//滑雪 
#include <stdio.h>
#include<iostream>
const int dx[4]={-1,0,1,0};//x的坐标增量 
const int dy[4]={0,1,0,-1};//y的坐标增量 
long r,c,i,j,p,t,ans;
using namespace std;
unsigned int m[100+1][100+1];
unsigned int f[100+1][100+1];

unsigned int search(long x,long y)//求到[x,y]点的最长路径 
{
  unsigned int result;
  long i,t,tmp,nx,ny;
  if(f[x][y]>0)  //如此点长度已求出，则不必进行递归。保证每个点长度只求一次 
    return f[x][y];
  t=1;
  for(i=0;i<=3;i++)//从四个点搜索能到达[x,y]的点 
  {
    nx=x+dx[i];//加上横坐标增量 
    ny=y+dy[i];//加上纵坐标增量 
    if((nx>=1)&&(nx<=r)&&(ny>=1)&&(ny<=c)&&(m[x][y]<m[nx][ny]))//边界限制 
    {
      tmp=search(nx,ny)+1;//递归进行记忆化搜索 
      if(tmp>t)
        t=tmp;
    }
  }
  f[x][y]=t;
  return t;
}

int main()
{
  freopen("ski.in","r",stdin);
  freopen("ski.out","w",stdout);
  scanf("%ld%ld",&r,&c);
  for(i=1;i<=r;i++)
    for(j=1;j<=c;j++)
      scanf("%u",&m[i][j]);
  ans=0;
  for(i=1;i<=r;i++)
    for(j=1;j<=c;j++)
    {
      t=search(i,j);
      f[i][j]=t;
      if(t>ans)
        ans=t;
   
      
    } 
  printf("%ld\n",ans);
  return 0;
}
