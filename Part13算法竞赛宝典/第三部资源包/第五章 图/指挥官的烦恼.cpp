//指挥官的烦恼 
#include <bits/stdc++.h>
using namespace std;
#define MAXN 1005
#define INF 0x7f7f7f7f

int pre[MAXN],id[MAXN],vis[MAXN],n,m,pos;
long long in[MAXN];//存最小入边权,pre[v]为该边的起点
struct node//边的权和顶点
{
  int u,v;
  long long w;
} edge[MAXN * MAXN];

long long Zhu_Liu(int root,int N,int E)
{
  long long Ans = 0;//存最小树形图总权值
  while(true)
  {
    //1.找最短弧集合A
    for(int i = 0; i < N; i++)
      in[i] = INF;//初始化为无穷大
    for(int i = 0; i < E; i++)//遍历每条边
    {
      int u = edge[i].u;
      int v = edge[i].v;
      if(edge[i].w < in[v] && u != v)//更新为权值更小的入边
      {
        pre[v] = u;//节点u指向v
        in[v] = edge[i].w;//最小入边
        if(u == root)//这个点就是实际的起点
          pos = i;
      }
    }
    for(int i = 0; i < N; i++)//判断是否存在最小树形图
      if(i!=root && in[i] == INF) return -1;//有点没有入边,无最小树形图
    //2.找环
    int Ring = 0;//记录环数
    memset(id, -1, sizeof(id));
    memset(vis, -1, sizeof(vis));
    in[root] = 0;
    for(int i = 0; i < N; i++) //标记每个环
    {
      Ans += in[i];//记录权值
      int v = i;
      while(vis[v] != i && id[v] == -1 && v != root)
      {
        vis[v] = i;
        v = pre[v];
      }
      if(v != root && id[v] == -1)
      {
        for(int u = pre[v]; u != v; u = pre[u])
          id[u] = Ring;//标记节点u为第几个环
        id[v] = Ring++;
      }
    }
    if(Ring == 0) break; //无环break
    for(int i = 0; i < N; i++)
      if(id[i] == -1)
        id[i] = Ring++;
    //3.建立新图，缩点,重新标记
    for(int i = 0; i < E; i++)
    {
      int u = edge[i].u;
      int v = edge[i].v;
      edge[i].u = id[u];
      edge[i].v = id[v];
      if(id[u] != id[v])
        edge[i].w -= in[v];
    }
    N = Ring;
    root = id[root];
  }
  return Ans;
}

int main()
{
  while(scanf("%d%d", &n, &m) != EOF)
  {
    long long sum = 0;
    for(int i = 0; i<m; i++)
    {
      scanf("%d%d%lld",&edge[i].u,&edge[i].v,&edge[i].w);
      edge[i].u++;
      edge[i].v++;
      sum+=edge[i].w;
    }
    sum++;//sum++是将虚根连的边与原本的边完全区分开 
    for(int i=m; i<m+n; i++) //增加虚根
    {
      edge[i].u=0;
      edge[i].v=i-m+1;
      edge[i].w=sum;//虚根到其余各个结点的边权相同
    }
    long long ans=Zhu_Liu(0, n+1,m+n);//n+1为总结点数,m+n为总边数
    if(ans==-1 || ans-sum>=sum)
      printf("impossible\n");
    else
      printf("%lld %d\n",ans-sum,pos-m);
    printf("\n");
  }
  return 0;
}
