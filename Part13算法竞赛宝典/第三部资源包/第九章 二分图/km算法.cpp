//KM算法 
#include <algorithm> 
#include <iostream>
using namespace std;

const int MAX = 1024;

int n;            // X 的大小
int w[MAX] [MAX]; // w[i][j]表示从i到j的权重
int A[MAX],B[MAX]; // 顶标
bool VisitX [MAX], VisitY [MAX];//标記是否被搜索过
int match [MAX]; // match[1]=3表示顶点1与顶点3匹配

void init (int size)// 初始化权重
{
  n = size;
  for (int i = 0; i < n; i ++)
    for (int j = 0; j < n; j ++)
      cin>>w[i][j];   
}

bool dfs(int u)// 从 X(u)寻找增广路
{
  VisitX [u] = true;
  for (int v = 0; v < n; v ++)
    if (!VisitY[v] && A[u]+B[v]==w[u][v])//满足A[u]+B[v]==w[u][v]才可加入 
    {
      VisitY[v]=true;
      if (match[v]==-1 || dfs(match[v]))
      {
        match [v]=u;//重置匹配 
        return true;//有增广路则返回true  
      }
    }
  return false;
}

int km(bool maxsum)
{
  int i, j;
  if (!maxsum)//如果是求最小权 
    for (i = 0; i < n; i ++)
      for (j = 0; j < n; j ++)
        w[i] [j] = -w[i] [j];
    
  for (i = 0; i < n; i ++)// 初始化标号,即A[i]=max,B[i]=0
  {
    A[i] = -0x1FFFFFFF;
    B[i] = 0;              
    for (j = 0; j < n; j ++)
      if (A[i] < w[i] [j])
        A[i] = w[i] [j]; 
  }
  memset (match, -1, sizeof (match));//均未匹配 
  for (int u = 0; u < n; u ++)
    while (1)
    {
      memset (VisitX, 0, sizeof (VisitX)); 
      memset (VisitY, 0, sizeof (VisitY));
      if (dfs (u))//如有增广路径，退出 
        break;

      int d=0x7FFFFFFF;// 如无增广路径，计算d值 
      for (i = 0; i < n; i ++)
        if (VisitX [i])
          for (j = 0; j < n; j ++)
            if(!VisitY [j])
              d= min(A[i]+B[j]-w[i][j],d);

      for (i = 0; i < n; i ++)//更改标号 
      {
        if (VisitX [i])
          A[i] -= d;
        if (VisitY [i])
          B[i] += d;
      }
    }

  int ans = 0; 
  for (i = 0; i < n; i ++)
    ans += w[match[i]][i];

  if (!maxsum)//如果是最小权 
  {
    ans = -ans;
    for (i = 0; i < n; i ++)
      for (j = 0; j < n; j ++)
        w[i] [j] = -w[i][j];//恢复原weight[][]的值 
  }
  return ans;
}

int main()
{
  int n;
  cin>>n;
  init (n);
  int cost = km(true);//求最大权 
  cout<<cost<<endl;
  return 0;
} 
