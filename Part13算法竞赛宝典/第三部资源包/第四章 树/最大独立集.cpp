//最大独立集 
#include <bits/stdc++.h>
using namespace std;

int Dp[6010][2];//状态

vector<int>Edg[6010];

void MIS(int x,int fa)
{
  Dp[x][1]=1;
  for(int i=0; i<Edg[x].size(); i++) //枚举儿子
  {
    int v=Edg[x][i];
    if(v==fa)//如果儿子等于父亲
      continue;//忽略
    MIS(v,x);//递归计算下层节点
    Dp[x][1]+=Dp[v][0];
    Dp[x][0]+=max(Dp[v][0],Dp[v][1]);
  }
}

int main()
{
  int n;
  scanf("%d",&n);
  for(int i=1; i<n; i++)
  {
    int x,y;
    scanf("%d%d",&x,&y);//输入自定义的父亲和儿子结点
    Edg[x].push_back(y);
    Edg[y].push_back(x);//存入数组
  }
  MIS(1,0);//把结点1当成根，父亲为0
  printf("%d\n",max(Dp[1][0],Dp[1][1]));
}
