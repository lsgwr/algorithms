#include <bits/stdc++.h>
using namespace std;
const int N=2010;
int n,tim;
int DfsNode[N],father[N];
vector<int>edg[N];
bool s[N],Set[N];//s[]为覆盖点,Set[]为支配集，注意Set不能小写

void dfs(int u,int fa)
{
  DfsNode[tim++]=u;
  for(int i=0; i<edg[u].size(); i++)
  {
    int v=edg[u][i];
    if(v!=fa)
    {
      father[v]=u;
      dfs(v,u);
    }
  }
}

int Check()
{
  int ans=0;
  for(int i=n-1; i>=0; i--)//按DFS反向序列检查
  {
    int t=DfsNode[i];
    if(!s[t])//当前点未被覆盖，既它不属于支配集，也不与支配集中的点相连
    {
      if(!Set[father[t]])//当前点的父亲结点不属于支配集
      {
        Set[father[t]]=true;//将父结点加入支配集
        ans++;
      }
      s[t]=true;//标记当前结点被覆盖
      s[father[t]]=true;//标记当前结点的父结点被覆盖
      s[father[father[t]]]=true;//标记当前结点的父结点的父结点被覆盖
    }
  }
  return ans;
}

int main()
{
  scanf("%d",&n);
  int u,v;
  for(int i=1; i<n; i++)//建图
  {
    scanf("%d%d",&u,&v);
    edg[u].push_back(v);
    edg[v].push_back(u);
  }
  dfs(1,0);//从结点1深搜
  printf("%d\n",Check());
  return 0;
}
