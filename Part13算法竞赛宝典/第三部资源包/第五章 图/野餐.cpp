//度限制生成树－野餐
#include<bits/stdc++.h>
using namespace std;
const int INF=99999999,N=100;
int G[N][N], Set[N],ans,n,m,cnt;//n为边的数量,m表示限度值,cnt:计算出来的结点数
bool flag[N][N];                //该边是否在最小生成树的标记
map<string,int> Map;
struct node
{
  int x,y,v;
} a[N*N];

struct edge
{
  int x,y,v;
} dp[N];                        //dp[v]为路径v0-v上与v0无关联且权值最大的边

int get_num(string s)           //将字符串映射到数字节点
{
  if(Map.find(s)==Map.end())    //没有搜索到该键值
    Map[s]=++cnt;               //对应建图,将字符串映射到数字节点
  return Map[s];
}

bool cmp(node a,node b)
{
  return a.v<b.v;
}

int find_father(int x)
{
  if(x!=Set[x]) Set[x]=find_father(Set[x]);
  return Set[x];
}

inline void union_set(int x,int y)
{
  Set[y]=x;
}

void kruskal()                  //求m个连通分量的最小生成树
{
  for(int i=1; i<=n; i++)
  {
    if(a[i].x==1||a[i].y==1) continue;
    int x=find_father(a[i].x);
    int y=find_father(a[i].y);
    if(x==y) continue;
    flag[a[i].x][a[i].y]=flag[a[i].y][a[i].x]=true;//标记为最小生成树
    Set[y]=x;                   //合并集合
    ans+=a[i].v;                //最小生成树权值累加
  }
}

/*设dp(v)为路径v0-v上与v0无关联且权值最大的边;定义father(v)为v的父结点,由此
可以得到状态转移方程: dp(v)=max(dp(father(v)),ω(father(v),v));边界条件为
dp[v0]=-∞(因每次找的是最大边,所以-∞不被考虑),dp[v']=-∞|(v0,v')∈E(T);*/
void dfs(int x,int father)
{
  for(int i=2; i<=cnt; i++)
    if(i!=father && flag[x][i])  //如果边（x,i）是在生成树上,且不是自身节点循环
    {
      if(dp[i].v==-1)            //如果不是V0到i点的度
        if(dp[x].v>G[x][i])      //dp(v)=max(dp(father(v)),ω(father(v),v));
          dp[i]=dp[x];
        else
        {
          dp[i].v=G[x][i];       //更新环路上最大边的值
          dp[i].x=x;
          dp[i].y=i;
        }
      dfs(i,x);                  //接着从i节点开始进行dfs
    }
}

void solve()
{
  int tmp[N],Min[N];
  for(int i=1; i<=cnt; i++)
    Min[i]=INF;
  sort(a+1,a+1+n,cmp);          //对节点进行排序
  kruskal();                    //最小生成树
  for(int i=2; i<=cnt; i++)     //枚举除V0的所有节点
    if(G[1][i]!=-1)             //如果V0与枚举的i点有边相边
    {
      int t=find_father(i);     //获得i点属于哪个连通分量
      if(Min[t]>G[1][i])        //求每个连通分量中和顶点1连接的最小权边
      {
        tmp[t]=i;               //确定该连通分量将要与V0连接的点
        Min[t]=G[1][i];
      }
    }
  int t=0;                      //t表示最小限度
  for(int i=1; i<=cnt; i++)     //从V0各连一条边到各连通分量，即求K的最小限制值
    if(Min[i]!=INF)
    {
      t++;
      flag[1][tmp[i]]=flag[tmp[i]][1]=true;//该连接的边标记为最小生成树
      ans+=G[1][tmp[i]];        //更新最小生成树权值
    }
  for(int i=t+1; i<=m; i++)     //枚举t到m的所有最小生成树,即逐步将v0点的度加1到m
  {
    memset(dp,-1,sizeof(dp));   //dp[v]为路径v0-v上与v0无关联且权值最大的边;
    dp[1].v=-INF;
    for(int j=2; j<=cnt; j++)
      if(flag[1][j]) dp[j].v=-INF;//如V0到j点为生成树上的边,该边的权值为负无穷大
    dfs(1,-1);                  //深度优先搜索
    int tmp,Min=INF;
    for(int j=2; j<=cnt; j++)   //找到从V0到各连通分量的最小边
      if(G[1][j]!=-1)
      {
        if(Min>G[1][j]-dp[j].v)
        {
          Min=G[1][j]-dp[j].v;
          tmp=j;
        }
      }
    if(Min>=0)break;           //找不到这样的边,就说明已经求出
    flag[1][tmp]=flag[tmp][1]=true;//将新增的度添加到MST中
    int x=dp[tmp].x;
    int y=dp[tmp].y;           //删除环上的这条最大边
    flag[x][y]=false;
    flag[y][x]=false;
    ans+=Min;
  }
  cout<<"Total miles driven:"<<ans<<endl;
}

void init()
{
  ans=0;
  cnt=1;
  Map["Park"]=1;
  memset(flag,0,sizeof(flag));
  memset(G,-1,sizeof(G));
  cin>>n;
  for(int i=1; i<N; i++) Set[i]=i;//并查集初始化
  string s;
  for(int i=1; i<=n; i++)
  {
    cin>>s;
    a[i].x=get_num(s);            //获得该地址在图中的节点序号
    cin>>s;
    a[i].y=get_num(s);
    cin>>a[i].v;
    if(G[a[i].x][a[i].y]==-1)
      G[a[i].x][a[i].y]=G[a[i].y][a[i].x]=a[i].v;
    else                          //有重边
      G[a[i].x][a[i].y]=G[a[i].y][a[i].x]=min(G[a[i].y][a[i].x],a[i].v);
  }
  cin>>m;                         //m表示限度值
}

int main()
{
  init();
  solve();
  return 0;
}
