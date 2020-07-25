//汽车拉力赛
//将弯道作为点，直道作为边，将弯道的权值加到出度或入度即可
#include <bits/stdc++.h>
using namespace std;
#define maxn 2010
#define INF 1000000

int n,m,a,b,c,dist[maxn][maxn],ans=INF,t[maxn];

int main()
{
  scanf("%d%d",&n,&m);
  for(int i=1; i<=n; i++)
    scanf("%d",&t[i]);
  for(int i=1; i<=n; i++)
    for(int j=i+1; j<=n; j++)
      dist[i][j]=dist[j][i]=INF;
  for(int i=1; i<=m; i++)
  {
    scanf("%d%d%d",&a,&b,&c);
    dist[a][b]=min(c+t[a],dist[a][b]);
  }
  for(int i=1; i<=n; i++)
    for(int j=1; j<=n; j++)
      for(int k=1; k<=n; k++)
        dist[i][j]=min(dist[i][j],dist[i][k]+dist[k][j]);
  for(int i=2; i<=n; i++)
    ans=min(ans,dist[1][i]+dist[i][1]);
  if(ans<INF)
    printf("%d",ans);
  else
    puts("-1");
  return 0;
}

