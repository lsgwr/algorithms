#include<iostream>
#include<cstdio>
#include<cstring> 
#include<algorithm>   
#include<queue>
using namespace std; 
#define N 6

struct Map
{
  int x;
  int y;
}pre[N][N];
bool vist[N][N];
int map[N][N];

int a[4][2]={{-1,0},{0,-1},{1,0},{0,1}};//控制四个方向 

bool in(int x,int y)//判断坐标是否越界 
{
  if (x<0||x>4||y<0||y>4)
    return false;
  return true;
}

void bfs()
{
  int i,j,k;
  queue<Map> q;//定义一个队列 
  while(!q.empty())//清空队列 
    q.pop();
  vist[0][0]=true;//该坐标标记为已走过 
  Map cur;
  cur.x=0;
  cur.y=0;
  q.push(cur);
  while (!q.empty())
  {
    cur=q.front();
    q.pop();
    for (i=0;i<4;i++)//尝试四个方向 
    {
      int x=a[i][0]+cur.x;
      int y=a[i][1]+cur.y;
      if (in(x,y) && !vist[x][y] && !map[x][y])//如果该坐标可走 
      {
		Map temp;
		vist[x][y]=true;
		temp.x=x;
		temp.y=y;
		q.push(temp);
		pre[x][y].x=cur.x;
		pre[x][y].y=cur.y;
      }
    }
  }
}

void PrintWay(int x,int y)//递归输出路径 
{
  int i,j,k;
  if (x==0&&y==0)
  {
    printf("%d %d\n",x+1,y+1);
    return;
  }
  PrintWay(pre[x][y].x,pre[x][y].y);
  printf("%d %d\n",x+1,y+1);
}

int main()
{
  freopen("labyrinth.in","r",stdin);
  freopen("labyrinth.out","w",stdout);
  int i,j,k;
  for (i=0;i<5;i++)
    for (j=0;j<5;j++)
      cin>>map[i][j];
  memset(vist,false,sizeof(vist));
  bfs();
  PrintWay(4,4);
  return 0;
}
