//周年晚会2
#include <bits/stdc++.h>
using namespace std;
#define MAXN 110
int n,root;
int Dp[MAXN][2],Happy[MAXN],Fa[MAXN];//Happy为开心值，Fa为父亲编号
vector<int>Edg[MAXN];
map<string,int>N;//字符串对应编号
map<int,string>MapFa;//编号对应父亲的字符串

void MIS(int x)
{
  Dp[x][1]=Happy[x];
  Dp[x][0]=0;
  for(int i=0; i<Edg[x].size(); i++)
  {
    int v=Edg[x][i];
    MIS(v);
    Dp[x][1]+=Dp[v][0];
    Dp[x][0]+=max(Dp[v][0],Dp[v][1]);
  }
}

int main()
{
  scanf("%d",&n);
  for(int i=1; i<=n; i++)
  {
    string x,y;
    int z;
    cin>>x;
    scanf("%d",&z);
    cin>>y;
    N[x]=i;
    MapFa[i]=y;
    Happy[i]=z;
  }
  for(int i=1; i<=n; i++) //开始转化编号
  {
    Fa[i]=N[MapFa[i]];
    Edg[Fa[i]].push_back(i);
    if(!Fa[i])
      root=i;
  }
  MIS(root);
  printf("%d\n",max(Dp[root][0],Dp[root][1]));
}

