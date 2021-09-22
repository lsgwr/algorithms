//恐龙基地 
#include<bits/stdc++.h>
using namespace std;
const int maxn=1010;
int n,m,index;
int dfn[maxn],low[maxn];
vector<int> Map[maxn];

int tarjan(int u,int fa)
{
  low[u]=dfn[u]=++index;
  for(int i=0; i<Map[u].size(); ++i)
  {
    int v=Map[u][i];
    if(v==fa) continue;
    if(!dfn[v])
      low[u]=min(low[u],tarjan(v,u));
    else if(dfn[v]<dfn[u])
      low[u]=min(low[u],dfn[v]);
  }
  return low[u];
}

int main()
{
  scanf("%d%d",&n,&m);
  for(int i=1; i<=n; i++)
    Map[i].clear();
  for(int i=1; i<=m; i++)
  {
    int u,v;
    scanf("%d%d",&u,&v);
    Map[u].push_back(v);
    Map[v].push_back(u);
  }
  tarjan(1,-1);//得出所有节点的low值,每个不同的low值代表一个边双连通分量
  int degree[maxn]= {0};//用于统计度数
  for(int u=1; u<=n; u++) //遍历每条边
    for(int i=0; i<Map[u].size(); i++)
    {
      int v=Map[u][i];
      if(low[u]!=low[v])
        degree[low[v]]++;
    }
  int cnt=0;
  for(int i=1; i<=n; i++)
    if(degree[i]==1)
      cnt++;
  printf("%d\n",(cnt+1)/2 );
  return 0;
}

