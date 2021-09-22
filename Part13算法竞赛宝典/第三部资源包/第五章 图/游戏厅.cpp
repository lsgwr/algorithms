//游戏厅
#include <bits/stdc++.h>
using namespace std;
const MAX=0x3f3f3f3f;

int x[10005],y[10005],e[10005];
int d[10005];
int n,m,s,t;

void init()
{
  cin>>n>>m;//读入点的个数和边的个数
  for(int i=1; i<=m; i++)//采取前向星方式读入
    cin>>x[i]>>y[i]>>e[i];
  cin>>s>>t;
}
void bellman_ford(int s)//bellman-ford
{
  for(int i=1; i<=n; i++)
    d[i]=MAX;
  d[s]=0;
  for(int i=1; i<n; i++)
    for(int j=1; j<=m; j++)
      if(d[x[j]]!=MAX && d[y[j]]>d[x[j]]+e[j])
        d[y[j]]=d[x[j]]+e[j];
  if(d[t]!=MAX)
    cout<<d[t]<<endl;
  else
    cout<<"No Solution!"<<endl;
}

int main()
{
  init();
  bellman_ford(s);
  return 0;
}

