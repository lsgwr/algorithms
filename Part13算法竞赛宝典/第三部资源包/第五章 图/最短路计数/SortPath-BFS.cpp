//最短路计数-BFS算法 
#include<bits/stdc++.h>
using namespace std;
const int MAXN=1000010;

bool vis[MAXN];
vector<int>G[MAXN];
int dep[MAXN],cnt[MAXN];

int main()
{
  int N,M,x,y;
  scanf("%d%d",&N,&M);
  for(int i=1; i<=M; i++)
  {
    scanf("%d%d",&x,&y);
    G[x].push_back(y);
    G[y].push_back(x);
  }
  queue<int>Q;//使用队列进行BFS,所有的边权都为1
  vis[1]=1;
  cnt[1]=1;
  Q.push(1);
  while(!Q.empty())//一个点的最短路就相当于它在BFS搜索树中的深度 
  {
    int u=Q.front();
    Q.pop();
    for(int i=0; i<G[u].size(); i++)
    {
      int t=G[u][i];
      if(!vis[t])
      {
        vis[t]=1;
        dep[t]=dep[u]+1;
        Q.push(t);
      }
      if(dep[t]==dep[u]+1)//最短路上一层一定比下一层少1（否则不是最短路）
        cnt[t]=(cnt[t]+cnt[u])%100003;
    }
  }
  for(int i=1; i<=N; i++)
    printf("%d\n",cnt[i]);
  return 0;
}
