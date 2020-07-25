//รินฌฮสฬโ-STL 
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <queue>
using namespace std;

#define MAX 105

int tx[9]={0,0,1,1,1,0,-1,-1,-1};
int ty[9]={0,1,1,0,-1,-1,-1,0,1};

int map[MAX][MAX];

struct node
{
	int x,y;
}pre[MAX][MAX];

int m,n; 
// stl ฟํหั 

void init()
{
  int i,j;
  scanf("%d",&n);
  scanf("%d",&m);
  for(i=1;i<=m;i++)
    for(j=1;j<=n;j++)
      scanf("%d",&map[j][i]);
  for(i=0;i<=n;i++)
    map[i][0]=map[i][m+1]=1;
  for(i=0;i<=m;i++)
    map[0][i]=map[n+1][i]=1;
}

void print(int x,int y)
{
  if(x==1 && y==1)
  {
    printf("1 1\n");
    return ;
  }
  print(pre[x][y].x,pre[x][y].y);
  printf("%d %d\n",x,y);
}

void bfs()
{
  int i,j,k;
  queue<node> q;
  node first;
  first.x=1;
  first.y=1;
  q.push(first);
  while(!q.empty())
  {
    first=q.front();
    q.pop();
    for(i=1;i<=8;i++)
    {
	  int dx=first.x+tx[i];
      int dy=first.y+ty[i];
      if( map[dx][dy]==0 )
      {
	    node now;
		now.x=dx;
		now.y=dy;
		q.push(now);
		pre[dx][dy].x=first.x;
		pre[dx][dy].y=first.y;
		map[dx][dy]=-1;
		if(dx==n && dy==m)
		{
		  print(dx,dy);
		  return;
        }
      }
    }
  }
  printf("-1\n");
}

int main()
{
  freopen("labyrinth.in","r",stdin);
  freopen("labyrinth.out","w",stdout);
  init();
  bfs();
  return 0;
}
