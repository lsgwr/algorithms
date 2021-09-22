//网络巡视2
#include <bits/stdc++.h>
using namespace std;

int n,vis[4200];
vector <int> Edg[4200];
int dp[4200][2];

void dfs(int u)
{
  vis[u]=1;
  dp[u][0]=1;//初始化
  dp[u][1]=0;
  for(int i=0; i<Edg[u].size(); i++)
  {
    int v=Edg[u][i];
    if(!vis[v])
    {
      dfs(v);
      dp[u][0]+=min(dp[v][0],dp[v][1]);//子节点以下的边要被覆盖
      dp[u][1]+=dp[v][0];//点v必须属于集合 才能使得edge(u,v)被覆盖
    }
  }
}

int main()
{
  while(cin>>n)
  {
    memset(vis,0,sizeof(vis));
    for(int i=0; i<n; i++)
      Edg[i].clear();
    int root=-1;
    for(int k=1; k<=n; k++)
    {
      int u,num,v;
      scanf("%d:(%d)",&u,&num);//注意格式的输入
      if(root==-1)
        root=u;
      for(int i=1; i<=num; i++)
      {
        scanf("%d",&v);
        Edg[u].push_back(v);
        Edg[v].push_back(u);//双向边
      }
    }
    dfs(root);
    cout<<min(dp[root][0],dp[root][1])<<endl;
  }
  return 0;
}

