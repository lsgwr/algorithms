//通信网络
#include <bits/stdc++.h>
using namespace std;
const int NN=105;
int n,root,root_son,index;
int  dfn[NN],low[NN];
bool cut[NN];//割点数组
vector<int> Map[NN];

void tarjan(int u)//求割顶是tarjan算法中的一种
{
  dfn[u]=low[u]=++index;
  for (int i=0; i<Map[u].size(); i++)
  {
    int v=Map[u][i];
    if (!dfn[v])//如果没有访问过v 
    {
      tarjan(v);
      if (u==root)
        root_son++;
      else  //如果非根结点
      {
        low[u]=min(low[u],low[v]);
        if (low[v]>=dfn[u])
          cut[u]=true;//后继结点搜不到比该点更早的点，则该点是割点
      }
    }
    else
      low[u]=min(low[u],dfn[v]);
  }
}

void init_input()
{
  for (int i=1; i<=n; i++)//图清空
    Map[i].clear();
  memset(dfn,0,sizeof(dfn));
  memset(cut,false,sizeof(cut));
  int u,v;
  while (scanf("%d",&u),u)//注意输入格式
    while (getchar()!='\n')
    {
      scanf("%d",&v);
      Map[u].push_back(v);
      Map[v].push_back(u);
    }
}

int main()
{
  while (scanf("%d",&n)!=EOF && n)
  {
    init_input();//输入初始化
    root=1;
    root_son=0;
    tarjan(root);
    if (root_son>1) //有两个子树的根是割点
      cut[root]=true;
    int sum=0;
    for (int i=1; i<=n; i++)//累计割点
      sum+=cut[i];
    printf("%d\n",sum);
  }
  return 0;
}

