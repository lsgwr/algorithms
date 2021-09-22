#include <bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;
const int maxn = 1e6 + 5;
const int mod = 100003;

int n, m;
vector<int> v[maxn];

#define pr pair<int, int>
#define mp make_pair
priority_queue<pr, vector<pr>, greater<pr> > q;
int dis[maxn], num[maxn];
bool vis[maxn];

void dijkstra(int s)
{
  for(int i = 1; i <= n; ++i) dis[i] = INF;
  dis[s] = 0;
  num[s] = 1;
  q.push(mp(dis[0], s));
  while(!q.empty())
  {
    int now = q.top().second;
    q.pop();
    if(vis[now]) continue;
    vis[now] = 1;
    for(int i = 0; i < (int)v[now].size(); ++i)
    {
      if(dis[now] + 1 < dis[v[now][i]])        //此题边权都是1
      {
        dis[v[now][i]] = dis[now] + 1;
        num[v[now][i]] = num[now];
        q.push(mp(dis[v[now][i]], v[now][i]));
      }
      else if(dis[now] + 1 == dis[v[now][i]])
        num[v[now][i]] = (num[v[now][i]] + num[now]) % mod;
    }
  }
}

int main()
{
  cin>>n>>m;
  for(int i = 1; i <= m; ++i)
  {
    int x,y;
    cin>>x>>y;
    v[x].push_back(y);
    v[y].push_back(x);
  }
  dijkstra(1);
  for(int i = 1; i <= n; ++i)
  {
    cout<<num[i]<<endl;
    cout<<endl;
  }
  return 0;
}
