//я╡йс 
#include <iostream>
#include <stdlib.h>
#include <math.h>
using namespace std;
#define INF 0x3fffffff

struct node
{
  int x,y;
}g[11],save[11];

int n,m,k;
int sx,sy;
int mark[11];
int mi;

void cmp()
{
  int sum=0;
  sum=fabs(save[0].x-sx)+fabs(save[0].y-sy);
  int tx,ty;
  tx=save[0].x;
  ty=save[0].y;
  for(int i=1;i<k;i++)
  {
    sum+=fabs(save[i].x-tx)+fabs(save[i].y-ty);
    tx=save[i].x;
    ty=save[i].y;
  }
  sum+=(fabs(tx-sx)+fabs(ty-sy));
  if(sum<mi) 
    mi=sum;
}

void dfs(int s)
{
  if(s==k)
    cmp();
  for(int i=1;i<=k;i++)
  {
    if(mark[i]==0)
    {
      mark[i]=1;
      save[s].x=g[i].x;
      save[s].y=g[i].y; 
      dfs(s+1);
      mark[i]=0;
    }
  }
}

int main()
{
  freopen("Patrol.in","r",stdin);
  freopen("Patrol.out","w",stdout);  
  int  t;
  scanf("%d",&t);
  while(t--)
  {
    mi=INF;
    scanf("%d%d",&n,&m);
    scanf("%d%d",&sx,&sy);
    scanf("%d",&k);
    for(int i=1;i<=k;i++)
      scanf("%d%d",&g[i].x,&g[i].y);
    dfs(0);
    printf("The shortest path has length %d\n",mi);
  }
  return 0;
}
