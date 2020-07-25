//虫洞 
#include <iostream>
using namespace std;
int w[1000+1][1000+1],d[1000+1];
int n,m;
char change;

void init()
{
  cin>>n>>m;//读入星系数和蛀洞的个数
  int x,y,v,i,j;
  for(i=1;i<=n;i++)
    for(j=1;j<=n;j++)
      w[i][j]=INT_MAX; 
  for(i=1;i<=m;i++) 
  {
    cin>>x>>y>>v;
    w[x][y]=v; //注意此为单向通道
  }    
}

void bellman_ford(int x)
{
  int i,j,k; 
  for(i=1;i<=n;i++)
    d[i]=w[x][i];
  d[x]=0;
  for(k=1;k<=n-1;k++)//松弛n-1次 
    for(j=1;j<=n;j++)//枚举每个点，每个点都有可能会更新最短路径
      for(i=1;i<=n;i++)//经过某点i到达j 
        if((w[i][j]!=INT_MAX)&&(d[i]!=INT_MAX)&&(d[j]>d[i]+w[i][j])) 
          d[j]=d[i]+w[i][j];
  change=1;//判断有无回路,默认不存在，change=1
  for(i=1;i<=n;i++)//松驰操作判断是否存在负权回路
    for(j=1;j<=n;j++)
      if((w[i][j]!=INT_MAX)&&(d[i]!=INT_MAX)&&(d[j]>d[i]+w[i][j])) //如果d[j]还能缩短，则存在负权回路 
        change=0; //设置change=0
  if(change)
    cout<<"Not possible"<<endl;
  else
    cout<<"Possible"<<endl;
}

int main()
{
  init();
  bellman_ford(1);//从顶点１出发
  return 0;
}
