//拓扑排序
#include<iostream>
#define MAXN 1001
using namespace std;

int n,m;/*n为节点数,m为边数*/
int top;/*栈顶指针*/
int mark[MAXN],ind[MAXN],stack[MAXN];
/*mark节点的拓扑序,ind为节点的入度数,stack为一个栈*/
bool w[MAXN][MAXN];/*邻接表*/

void sort()
{
  int i,u,t;
  top = -1;
  /*将入度为0的节点入栈并且做出标记*/
  for(i = 1 ; i <= m ; i ++)
    if(ind[i] == 0 )
    {
	  mark[i] = 1;
	  stack[++top] = i;
	  ind[i] = -1;
    }

  /*用DFS的方式标记节点的拓扑序*/
  do{
    /*取出栈中的一个节点并将该与该节点相邻的节点的入度减1*/
    u = stack[top--];
    for(i=1;i<=n;i++)
      if(w[u][i] and ind[i] not_eq -1)
	ind[i]--;

    /*查找当前是否有入度为0且没有入栈的节点并将其入栈*/
    for(i=1;i<=n;i++)
      if(ind[i] == 0)
  	  {
	    mark[i] = mark[u] + 1;
	    stack[++top] = i;
	    ind[i] = -1;
	  }
  }while(top >= 0);

  for(i=1;i<=n;i++)
    cout<<mark[i]<<" ";
  cout<<"\n";
  return ;
}

int main()
{
  int i,u,v;
  cin>>n>>m;//输入一个符合拓扑序的图的节点数和边数n,m

  for(i=1;i<=m;i++)
  {
    cin>>u>>v;
    w[u][v] = true;
    ind[v]++;
  }
  sort();
  return 0;
}
