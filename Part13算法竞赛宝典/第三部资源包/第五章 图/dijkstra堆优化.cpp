//dijkstra堆优化 
#include<iostream>
using namespace std;
#define Max 0xfffffff
int n,m,ms;
int map[1000+1][1000+1];//存储图 
int visit[1000+1];//设置访问标记 
int d[1000+1];//源点到各节点的最小距离 
int heap[1000+1];//堆 
int pos[1000+1],posh[1000+1];//pos[i]存储堆中第i点在图中的位置，posh[i]存储图中的点i在堆中的位置 

void minheap(int x)//维护最小堆 
{
  int l,r,t;
  t=x;
  l=t<<1;
  r=(t<<1)+1;
  if(l<=ms && heap[l]<heap[t])
    t=l;
  if(r<=ms && heap[r]<heap[t])
    t=r;
  if(t!=x)
  {
    swap(heap[x],heap[t]);
    swap(posh[pos[x]],posh[pos[t]]);
    swap(pos[x],pos[t]);
    minheap(t);
  }
}

void build()//构建堆 
{
  int i;
  for(i=1;i<=n;i++)
    pos[i]=posh[i]=i;
  for(i=n>>1;i>=1;i--)
    minheap(i);
}

void insert(int x)//改变堆中某个元素的值 
{
  while(x>1 && heap[x]<heap[x>>1])
  {
    swap(heap[x],heap[x>>1]);
    swap(posh[pos[x]],posh[pos[x>>1]]);
    swap(pos[x],pos[x>>1]);
    x>>=1;
  }
}

void Dijkstra(int x)//求从x点到其他的单源最短路径 
{
  int i,j,Min,p,q;
  for(i=1;i<=n;i++)//初始化堆中元素 
    heap[i]=map[1][i];
  heap[x]=0;
  build();//建堆
  for(i=1;i<n;i++)
  {
    p=pos[1];//将最小的元素提出来，并标记该点 
    q=heap[1];
    visit[p]=1;
    swap(heap[1],heap[ms]);
    swap(posh[pos[1]],posh[pos[ms]]);
    swap(pos[1],pos[ms]);//将最大的元素和最小的元素交换，这样才能维护堆 
    ms--;//每确定一个点，堆都会缩小1 
    minheap(1);//维护最小堆 
    for(j=1;j<=n;j++)//更新未查找顶点的最小距离总和 
      if(!visit[j] && q+map[p][j]<heap[posh[j]])
      {
        heap[posh[j]]=q+map[p][j];
        insert(posh[j]);//修改j点在堆里的位置 
      }
  }
  for(i=1;i<=n;i++)//打印最短路径 
    cout<<heap[posh[i]]<<' ';
  cout<<endl;
}

void init()
{
  cin>>n;//n个节点 
  int i,j;
  for(i=1;i<=n;i++)
    for(j=1;j<=n;j++)
    {
      cin>>map[i][j];
      if(map[i][j]==0)//若不存在路径,则为+∞,计算时要小心溢出 
        map[i][j]=Max;
    }
  ms=n;
} 
                    
int main()
{
  init(); //读入图 
  Dijkstra(1); 
  return 0;
}
