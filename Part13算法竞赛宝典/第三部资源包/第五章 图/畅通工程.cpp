//畅通工程
#include <bits/stdc++.h>
using namespace std;
const int MAX=0x3f3f3f3f;
int Line[10010], dis[205], w[205][205], b[205][205];
bool vis[205];
int n,m,s,t;

void spfa(int s)
{
  for(int i=0; i<=n; i++)
    dis[i]=MAX;
  dis[s]=0;//队列的初始状态,s为起点
  vis[s]=1;
  Line[1]=s;
  int v, head=0, tail=1;
  while (head<tail) //队列不为空时
  {
    v=Line[++head];//取队首元素
    vis[v]=0;//释放结点，出队
    for(int i=1; i<=b[v][0]; i++)
      if (dis[b[v][i]] > dis[v]+w[v][b[v][i]])
      {
        dis[b[v][i]] = dis[v]+w[v][b[v][i]];//修改最短路
        if (vis[b[v][i]]==0) //扩展结点入队
        {
          Line[++tail]=b[v][i];
          vis[b[v][i]]=1;
        }
      }
  }
}

int main()
{
  cin>>n>>m;
  int x,y,z;
  for(int i=0; i<m; i++)
  {
    cin>>x>>y>>z;
    if (w[x][y]!=0 && z>w[x][y])//保留两顶点间最小的一条路
      continue;
    b[x][++b[x][0]]=y;//b[x,0]表示以x为一个结点的边数
    b[y][++b[y][0]]=x;
    w[x][y]=w[y][x]=z;
  }
  cin>>s>>t;
  spfa(s);
  dis[t]==MAX?printf("-1\n"):printf("%d\n",dis[t]);
  return 0;
}


