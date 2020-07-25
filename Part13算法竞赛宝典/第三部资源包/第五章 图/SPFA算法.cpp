//ＳＰＦＡ算法 
#include <iostream>
#define MAX 99999999
using namespace std;
struct node//用链表存储边降低空间复杂度，如用数组可降低代码复杂度
{
  int n;
  int v;//边的权值 
  node *next;
  node()//初始化 
  {
    n=0;
    next=NULL;
  }
}*e[1001];//存放每一个点到别的点的边 
int d[1001];//估计值 
bool c[1001];//判断该顶点是否存在于队列 
int line[2002];//队列
int rear;//队列末尾 
int n,m;//n为顶点数，m为边数 

void init()
{
  cin>>n>>m;
  node *p;
  int i,j;
  int x,y,v;
  for(i=1;i<=m;i++)
  {
    cin>>x>>y>>v;
    p=new node();
    p->n=y;
    p->v=v;
    if(e[x]==NULL)
      e[x]=p;
    else
    {
      p->next=e[x]->next;
      e[x]->next=p;
    }
  }
}

void add(int x)
{
  rear++;
  line[rear]=x;
}

void SPFA(int x)
{
  int i,j;
  node *p;
  add(x);//将起始点加入队列 
  for(i=1;i<=n;i++)
    d[i]=MAX;//初始化估计值 
  d[x]=0;
  for(i=1;i<=rear;i++)//读取队列 
  {
    p=e[line[i]];//和line[i]点连接的边 
    while(p!=NULL)//如果还有边的话 
    {
      if(d[line[i]]+p->v<d[p->n])//若通过line[i]点到达p->n点的距离比原来短 
      { 
        d[p->n]=d[line[i]]+p->v;//更新距离 
        if(!c[p->n])//如果p->n点不在队列里
        { 
          c[p->n]=1;//标记为已加入队列
          add(p->n);//加入队列 
        }
      }
      p=p->next;//继续i点的下一点边 
    }
    c[line[i]]=0;//line[i]点出队 
  }
  for(i=1;i<=n;i++)//打印从x点到任意点的最短距离 
    cout<<d[i]<<' ';
  cout<<endl;
}

int main()
{
  init();
  SPFA(1);
  return 0;
}
