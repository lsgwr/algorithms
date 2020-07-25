//次小生成树２ 
#include<cstdio>
#include<cstring>
#include<cstdlib>
#include <iostream>
using namespace std;
const int inf = 0x3f3f3f3f;
const int MAXN = 105;

bool Tlink[MAXN][MAXN],vis[MAXN];//Tlink记录边是否在MST中,vis[i]表示i点是否在MST 
int w[MAXN][MAXN];//w[i][j]为i到j的边的权值 
int lowc[MAXN], pre[MAXN];
int Max[MAXN][MAXN];//Max[i,j]为MST中连结i,j唯一路中最大边权值
int n, m;

int MAX( int a, int b)
{
    return a > b ? a : b;
}

int prim()
{
  int i, j, p, k;
  int minc, res = 0;
  memset( vis, false, sizeof vis);
  memset( pre, 0, sizeof pre);
  memset( Max, 0, sizeof Max);
  vis[1] = 1;//从第一个节点开始 
  pre[1] = 1;//前驱是节点１ 
  for( i = 2; i <= n; i ++)//初始化  
  { 
    lowc[i] = w[1][i]; 
    pre[i] = 1;
  }
  
  for( i = 2; i <= n; i ++)//prim算法 
  {
    minc = inf; p = -1;
    for( j = 1; j <= n; j ++) 
    {
      if( !vis[j] && lowc[j] < minc)//找出节点没有被加入MST的最小权值边 
      {
        minc = lowc[j];
        p = j;
      }
    }
    vis[p] = true;//该节点加入MST 
    res += minc;//最小树权值累加 
    Max[ pre[p] ][p] = minc;
    Tlink[ pre[p] ][p] = true;//将这条边标记为最小树的边 
    Tlink[p][ pre[p] ] = true;//将这条边标记为最小树的边 
    for( k = 1; k <= n; k ++)
      Max[k][p] = MAX( Max[ pre[p] ][p], Max[k][p]);//更新连接该边的最大值 
    for( j = 1; j <= n; j ++)//更新p点到j点的最小边权值 
      if( !vis[j] && lowc[j] > w[p][j]) 
      {
        lowc[j] = w[p][j];
        pre[j] = p;//确定前驱节点 
      }
  }
  return res;
}

int main()
{
  int s, e, t, Ans, ans;
  bool ok = true;//是否唯一最小生成树的标志 
  cin>>n>>m;
  for( int i = 1; i <= n; i ++)
    for( int j = 1; j <= n; j ++)
      w[i][j] = inf;
  memset( Tlink, false, sizeof Tlink);
  for( int i = 1; i <= m; i ++)
  {
    cin>>s>>e>>t;
    w[s][e] = t;
    w[e][s] = t;
  }
  Ans = prim();//prim算法求出最小生成树权值 
  for( int i = 1; i <= n; i ++)
  {
    for( int j = 1; j <= n; j ++)
    {
      if( w[i][j] == inf || Tlink[i][j])//如果该点在最小生成树或者无边则忽略 
        continue;
      ans = Ans + w[i][j] - Max[i][j];//Max[i,j]:MST中连结i,j中最大边权值
      if( Ans == ans)//如果发现加非最小树的一条边后权值与原最小树权值相等 
      {
        ok = false;//则非唯一最小树 
        break;
      }
    }
    if( !ok) 
      break;
  }
  if( ok) 
    printf( "%d\n", Ans);
  else 
    printf( "Not Unique!\n");
  return 0;
}
