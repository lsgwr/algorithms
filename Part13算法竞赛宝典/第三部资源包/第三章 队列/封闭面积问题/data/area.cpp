//封闭面积问题
#include <bits/stdc++.h>
using namespace std;
#define MAXN 90000

struct img
{
  int x;
  int y;
} a[MAXN];

int main()
{
  freopen("area.in","r",stdin);
  freopen("area.out","w",stdout);
  char temp;
  int dx[4]= {0,1,0,-1},dy[4]= {1,0,-1,0}; //建立方向偏移数组
  int m,n,xing=0,ttxx,ttyy;
  cin>>m>>n;
  bool pos[n+2][m+2];
  memset(pos,0,sizeof(pos));

  for(int i=1; i<=n; ++i) //输入图像
    for(int j=1; j<=m; ++j)
    {
      cin>>temp;
      if(temp=='*')//统计*号数量
      {
        pos[i][j]=1;
        xing++;
      }
      if(temp=='0')
        pos[i][j]=0;
    }
  for(int i=1; i<=n+1; ++i)//将边框围起来
    pos[i][m+1]=1,pos[i][0]=1;
  for(int i=1; i<=m+1; ++i)
    pos[n+1][i]=1,pos[0][i]=1;

  int sum=1,front,rear;
  int tt=1;
  int kk=1;
  a[1].x=1;
  a[1].y=1;
  pos[1][1]=1;

  while(tt<=kk)//当队列不为空时,宽度优先搜索
  {
    for(int i=0; i<=3; ++i) //各个方向各试一遍
    {
      front=tt%MAXN;
      ttxx=a[front].x+dx[i];
      ttyy=a[front].y+dy[i];
      if(pos[ttyy][ttxx]==0)
      {
        kk++;
        rear=kk%MAXN;
        a[rear].x=ttxx;
        a[rear].y=ttyy;
        sum++;
        pos[ttyy][ttxx]=1;
      }
    }
    tt++;
  }
  cout<<m*n-sum-xing<<endl;
  return 0;
}

