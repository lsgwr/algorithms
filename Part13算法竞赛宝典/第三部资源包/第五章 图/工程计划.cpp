//工程计划 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <algorithm>
#include <queue>
using namespace std;

const int Ns=20005;
const int Ms=20005;

int N,M,im[Ns],vmin[Ns];

int p[Ns],cnt;

struct edge
{
  int v,w,next;
}e[Ms];

void adde(int u,int v,int w)
{
  e[++cnt]=(edge){v,w,p[u]};
  p[u]=cnt;
}

int Min(int a,int b)
{
  if (!a) 
    return b;
  else
  {
    if (a<b) 
      return a;
    else
      return b;
  }
}

void work()
{
  int i,u,v,w;
  while(scanf("%d%d",&N,&M)!=EOF)
  {
    memset(im,0,sizeof(im));
    memset(p,0,sizeof(p));cnt=0;
    memset(vmin,0,sizeof(vmin));
    memset(e,0,sizeof(e));
        
    for (i=0;i<M;++i)
    {
      scanf("%d%d%d",&u,&v,&w);
      adde(u+1,v+1,w);
      ++im[v+1];
    }
        
    queue<int> q;
    for (i=1;i<=N;++i)
      if (!im[i])  
        q.push(i);
    while(!q.empty())
    {
      u=q.front();q.pop();
      for (i=p[u];i;i=e[i].next)
      {
     	if (!vmin[e[i].v]) 
          vmin[e[i].v]=e[i].w+vmin[u];
     	else
  		  vmin[e[i].v]=max(vmin[e[i].v],e[i].w+vmin[u]);
        --im[e[i].v];
		if (!im[e[i].v]) 
          q.push(e[i].v);
      }
    }
    int ans=0;
    for (i=1;i<=N;++i)
      ans=max(ans,vmin[i]);
    printf("%d\n",ans+1);
  }
}

int main()
{
  work();
  return 0;
}
