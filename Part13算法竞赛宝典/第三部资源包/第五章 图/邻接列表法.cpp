//邻接列表法
#include <bits/stdc++.h>
using namespace std;

int Head[100100];//Head[i]代表起点是i的边的编号
int Cnt;//表示边的编号

struct Node
{
  int u;                              //边的起点
  int v;                              //边的终点
  int w;                              //边的权值
  int next;                           //指向上一条边的编号
} edge[10000];

void Add(int u,int v,int w)           //加边
{
  edge[++Cnt].u=u;
  edge[Cnt].v=v;
  edge[Cnt].w=w;
  edge[Cnt].next=Head[u];             //链接
  Head[u]=Cnt;
}

int main()
{
  int n,e,u,v,w;
  cin>>n>>e;                          //输入顶点数和边数
  for(int i=0; i<e; i++)
  {
    cin>>u>>v>>w;
    Add(u,v,w);                       //加边到邻接列表
  }
  for(int k=1; k<=n; ++k)             //输出邻接列表
  {
    cout<<k;
    for(int i=Head[k]; i; i=edge[i].next)
      cout<<"->"<<edge[i].v<<"("<<edge[i].w<<")";
    cout<<endl;
  }
  return 0;
}

