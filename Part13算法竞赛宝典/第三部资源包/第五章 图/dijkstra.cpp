//dijkstra算法 
#include<iostream>
using namespace std;
#define Max 0xfffffff
int n;
int map[1000+1][1000+1];//存储图 
int visit[1000+1];//设置访问标记 
int d[1000+1];//源点到各节点的最小距离 

void Dijkstra(int x) //求从x点到其他的单源最短路径
{
  int i,j,Min,p;
  for(i=1;i<=n;i++)//算出从起始点到各顶点的最短距离
    d[i]=map[x][i];
  visit[x]=1;//设源点为已访问过
  d[x]=0;//自身到自身为零
  for(i=1;i<n;i++)
  {
    Min=Max;//最小边 
    for(j=1;j<=n;j++)//找出总和最短的路径
      if(!visit[j] && Min>d[j])//找没查找过的邻接边
      {
        p=j;//找出最短边
        Min=d[j];
      }
    visit[p]=1;
    for(j=1;j<=n;j++)//更新未查找顶点的最小距离总和 
      if(!visit[j] && Min+map[p][j]<d[j])
        d[j]=Min+map[p][j];
  }
  for(int i=1;i<=n;i++)//打印出从点x到任意点的最短距离 
    cout<<d[i]<<' ';
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
}
                     
int main()
{
  init(); //读入图 
  Dijkstra(1); 
  return 0;
}
