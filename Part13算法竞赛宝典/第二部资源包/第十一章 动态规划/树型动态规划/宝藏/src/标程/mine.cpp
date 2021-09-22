//宝藏 
#include<stdlib.h>
#include<string.h>
#include<iostream>
#include<algorithm>
using namespace std;

struct list
{
  int l;
  int r;
}node[2001];

int val[2001];//价值 
int vis[2001][101];//记忆化搜索，加快递归速度 
int m,n;

int dp(int x,int m)
{
  if(m==0)//如果无人，返回0 
    return 0;
  if(vis[x][m]!=-1)//如果已算出值，直接返回 
    return vis[x][m];
  if(x==0)//无路可走 
    return 0;
  int ans=0;
  ans=dp(node[x].r,m);//走右兄弟节点，无需留人 
  //遍历所有情况，即确定几人往左儿子走，几人往右兄弟走
  for(int i=0;i<m;i++)//走左儿子，需留一人
    ans=max(ans,dp(node[x].l,i)+val[x]+dp(node[x].r,m-i-1));
  vis[x][m]=ans;//记忆化搜索 
  return ans;
}

int main()
{  
  freopen("mine.in","r",stdin);
  freopen("mine.out","w",stdout);      
  int i;
  scanf("%d%d",&n,&m);
  memset(vis,-1,sizeof(vis));
  for(i=1;i<=n;i++)
    scanf("%d",&val[i]);
  for(i=0;i<=n;i++)//初始化 
    node[i].l=node[i].r=0;
  for(i=1;i<=n;i++)//多叉树变二叉树 
  {
    int a,b;
    scanf("%d%d",&a,&b);
    node[b].r=node[a].l;
    node[a].l=b;
  }
  cout<<dp(node[0].l,m)<<endl;
  return 0;
}


