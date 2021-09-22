//乒乓球队
#include <bits/stdc++.h>
using namespace std;

int n, m, k;
int  Link[101]; //例如Link[1]=3代表女1与男3相匹配 　　
bool visit [101]; //记录女运动员结点是否被访问过 　　
bool Map[101][101];//Map[a][b]=1代表a,b顶点有边相连 　　

bool dfs(int boy)
{
  for (int girl=1; girl<=m; girl++) //遍历女运动员
    if (Map[boy][girl]==1 && !visit[girl]) //如girl与boy有边且未被访问
    {
      visit[girl] = true; //标记i为已访问
      if(Link[girl]==0||dfs(Link[girl]))//如girl未匹配或girl匹配的boy有增广路 　
      {
        Link[girl]=boy; //异或反转
        return true; //返回 　　
      }
    }
  return false;//无增广路则返回false
}

int main()
{
  int a,b,ans=0;
  cin>>n>>m>>k;//n,m为男、女运动员个数，k为边数
  for(int i=1; i<=k; i++)
  {
    cin>>a>>b;
    Map[a][b]=true;
  }
  for (int i=1; i<=n; i++)//穷举男运动员顶点
  {
    memset(visit, 0, sizeof(visit));//清空上次结点访问标记
    if (dfs(i))
      ans++; //从结点i尝试扩展
  }
  cout<<ans<<endl;
  return 0;
}

