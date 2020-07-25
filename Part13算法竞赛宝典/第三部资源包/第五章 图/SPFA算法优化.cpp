//畅通工程—spfa优化 
#include <bits/stdc++.h>
using namespace std;
const int MAX=0x3f3f3f3f;

int  dis[201], w[201][201], b[201][201];
bool vis[201];

void spfa(int s)
{
  for(int i=1; i<=b[s][0]; i++)
    if (dis[b[s][i]]>dis[s]+w[s][b[s][i]])
    {
      dis[b[s][i]]=dis[s]+w[s][b[s][i]];
      spfa(b[s][i]);
    }
}

int main()
{
  int n,m,s,t,x,y,z;
  cin>>n>>m;
  for(int i=0; i<m; i++)
  {
    cin>>x>>y>>z;
    if(w[x][y]!=0 && z>w[x][y])
      continue;
    b[x][++b[x][0]]=y;
    b[y][++b[y][0]]=x;
    w[x][y]=w[y][x]=z;
  }
  cin>>s>>t;
  for(int i=0; i<=n; i++)
    dis[i]=MAX;
  dis[s]=0;
  spfa(s);
  dis[t]==MAX?printf("-1\n"):printf("%d\n",dis[t]);
  return 0;
}

