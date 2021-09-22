//１５数码问题 
#include<iostream>
#include<cstdlib>
#include<cmath>
#define size 4
using namespace std;

int move[4][2]={{-1,0},{0,-1},{0,1},{1,0}};//上,左,右,下增量 
char op[4]={'U','L','R','D'};
int map[size][size],map2[size*size],limit,path[100];
int flag,length;
//int goal_st[3][3]={{1,2,3},{4,5,6},{7,8,0}};//目标状态 
//goal存储目标位置，即０存在（３,３）,1存在（0,0）... 
int goal[16][2]= {{3,3},{0,0},{0,1}, {0,2},
                  {0,3},{1,0},{1,1}, {1,2}, 
                  {1,3},{2,0},{2,1}, {2,2},
                  {2,3},{3,0},{3,1}, {3,2}};
                    
int h(int a[size*size])//求逆序数 
{
  int i,j,num,w,x,y;
  num=0;
  for(i=0;i<size*size;i++)
  {
    if(a[i]==0)
      w=i;
    for(j=i+1;j<size*size;j++)
    {
      if(a[i]>a[j])
		num++;
    }
  }
  x=w/size;
  y=w%size;
  num+=abs(x-3)+abs(y-3);
  if(num%2==1)
    return 1;
  else
    return 0;
}

int manhattan(int a[][size])//计算曼哈顿距离，小等于实际总步数
{
  int i,j,cost=0;
  for(i=0;i<size;i++)
    for(j=0;j<size;j++)
    {
      int w=map[i][j];
      cost+=abs(i-goal[w][0])+abs(j-goal[w][1]);
    }
  return cost;
}

void swap(int*a,int*b)
{
  int tmp;
  tmp=*a;
  *a=*b;
  *b=tmp;
}

void dfs(int sx,int sy,int dep,int pre_move)//sx,sy是空格的位置
{
  int i,j,nx,ny;
  if(flag)
    return;
  int dv=manhattan(map);
  if(dep==limit)
  {
    if(dv==0)
    {
      flag=1;
      length=dep;
	  return;
    }
    else
      return;
  }
  else if(dep<limit)
  {
    if(dv==0)
    {
	  flag=1;
      length=dep;
      return;
    }
  }
  for(i=0;i<4;i++)//４个方向尝试 
  {
    if(i+pre_move==3&&dep>0)//不和上一次移动方向相反，对第二步以后而言
      continue;    
    nx=sx+move[i][0];
    ny=sy+move[i][1];
    if(0<=nx && nx<size && 0<=ny&&ny<size)//如果可以移动 
    {
	   swap(&map[sx][sy],&map[nx][ny]);//交换两位置 
       int p=manhattan(map);
       if(p+dep<=limit&&!flag)
       {
	 	 path[dep]=i;
		 dfs(nx,ny,dep+1,i);
		 if(flag)
		   return;
       }
       swap(&map[sx][sy],&map[nx][ny]);
    }
  }
}

int main()
{
  freopen("Puzzle15.in","r",stdin);
  freopen("Puzzle15.out","w",stdout);  
  int i,j,k,l,m,n,sx,sy;
  char c,g;
  i=0;
  scanf("%d",&n);
  while(n--)
  {
    flag=0;length=0;
    memset(path,-1,sizeof(path));
    for(i=0;i<16;i++)
    {
      scanf("%d",&map2[i]);
      if(map2[i]==0)
      { 
		map[i/size][i%size]=0;
		sx=i/size;sy=i%size;
      }
      else
      {
		map[i/size][i%size]=map2[i];
      }
    }
    if(h(map2)==1)//该状态可达
    {
      limit=manhattan(map);
      while(!flag&&length<=50)//题中要求50步之内到达
      {
        dfs(sx,sy,0,0);
		if(!flag)
		  limit++; //得到的是最小步数
      }
      if(flag)
      {
		for(i=0;i<length;i++)
		  printf("%c",op[path[i]]);
		printf("\n");
      }
    }
    else if(!h(map2)||!flag)
      printf("This puzzle is not solvable.\n");
  }
  return 0;
}

