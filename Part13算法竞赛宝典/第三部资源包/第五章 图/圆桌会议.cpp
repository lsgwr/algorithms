//圆桌会议 
#include <bits/stdc++.h>
using namespace std;
#define M 410

int  ans[M],g[M][M];
bool vis[M];
int n,m,t,ansi;//ansi为环的个数 

void Reverse(int ans[M],int s,int t)//将ans数组中s到t的部分倒置
{
  while(s<t)
    swap(ans[s++],ans[t--]);
}

void Expand()//从t点开始寻找边向外扩展
{
  while(1)
  {
    bool flag=0;
    for(int i=1; i<=n; ++i)
      if(g[t][i] && !vis[i])
      {
        ans[ansi++]=i;
        vis[i]=true;
        t=i;
        flag=1;
        break;
      }
    if(!flag) break;
  }
}

void Hamilton()
{
  int i,j,s=1;//初始化s取1号点
  for(i=1; i<=n; i++)//查找连接s的点
    if(g[s][i]) break;
  t=i;//取任意连接s的点为t
  vis[s]=vis[i]=true;//标记已访问
  ans[0]=s;//将点存入环
  ans[1]=t;
  ansi=2;//统计环的个数
  while(1)
  {
    Expand();//从t点开始向外扩展
    Reverse(ans,0,ansi-1);//将当前得到的序列倒置
    swap(s,t);//s和t互换
    Expand();//从t继续扩展，实际是从原序列的s继续扩展
    if(!g[s][t])//如果s和t不相邻，进行调整
    {
      for(i=1; i<ansi-2; i++)//从g序列中找点i
        if(g[ans[i]][t] && g[s][ans[i+1]])//使ans[i]与t连,ans[i+1]与s连
          break;
      t=ans[++i];
      Reverse(ans,i,ansi-1);//将从ans[i+1]到t部分的ans[]倒置
    }
    if(ansi==n)//如果当前s和t相连
      return;//如果当前序列中包含n个元素，算法结束
    for(j=1; j<=n; j++)//如个数<n,找点ans[i],使ans[i]与ans[]外一点连接 
    {
      if(vis[j]) continue;//如已访问过，忽略
      for(i=1; i<ansi-2; i++)//从ans[i]点处把回路断开,就变回了一条路径
        if(g[ans[i]][j])     //从断点处继续扩展就可以了
          break;
      if(g[ans[i]][j]) break;
    }
    s=ans[i-1];
    t=j;
    Reverse(ans,0,i-1);//将ans[]中s到ans[i-1]部分的ans[]倒置
    Reverse(ans,i,ansi-1);//将ans[]中ans[i]到t的部分倒置
    ans[ansi++]=j;//将点j加入到ans[]的尾部
    vis[j]=true;
  }
}

int main()
{
  while(scanf("%d%d",&n,&m),n|m)//当n,m不为0时 
  {
    n*=2;
    memset(vis,0,sizeof(vis));
    memset(ans,0,sizeof(ans));
    for(int i=0; i<=n; i++)
      for(int j=0; j<=n; j++)
        i==j?g[i][j]=0:g[i][j]=1;//反图初始化
    int a,b;
    for(int i=1; i<=m; i++)
    {
      scanf("%d%d",&a,&b);
      g[a][b]=g[b][a]=0;//建立反图
    }
    Hamilton();
    for(int i=0; i<n; i++)
      i!=n-1?printf("%d ",ans[i]):printf("%d\n",ans[i]);
  }
  return 0;
}
