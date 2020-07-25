//拓扑排序算法2 
#include<iostream>
using namespace std;
const int MAX = 100;
int List[MAX],color[MAX],k;
struct
{
  int n;
  int adjvex[MAX];
}Edge[MAX];

void DFS(int cur)
{
  int i,next;
  color[cur] = -1;
  for(i=0;i<Edge[cur].n;i++)//遍历所有的连接边 
  {
     next = Edge[cur].adjvex[i];//找到下一个连接边 
     if(color[next]==0) 
       DFS(next);
     else if(color[next]==-1)//有回边出现意味着有回路
     {
       cout<<"Circuit exists!"<<endl;
       system("pause");
       exit(0);
     }
  }  
  color[cur] = 1;
  List[--k] = cur;//按节点完成的顺序入列，逆序保存 
}

int main()
{
    int n,e,a,b;
    int i,j; 
    cin>>n>>e;//输入顶点数n和边数e 
    for(i=1;i<=n;i++)
        Edge[i].n = 0;
    for(i=0;i<e;i++)//输入边 
    {
      cin>>a>>b;
      Edge[a].adjvex[ Edge[a].n++ ] = b;
    }
    k=n;
    DFS(1);
    for(i=0;i<n;i++)
        cout<<List[i]<<" ";
    system("pause");
    return 0;
}
