//苹果树 
#include <bits/stdc++.h>
#define MAXN 100005
using namespace std;

int t[MAXN], a[MAXN],Left[MAXN], Right[MAXN];
vector<vector<int>>Edge(MAXN);//POJ卡vector，要这样写，或不用vector
int N,M,Time;

int LowBit(int x)
{
  return x&(-x);
}

void DFS(int node)//为每一个node添加时间戳（左值和右值）
{
  Left[node] = Time;
  for(int i=0; i<Edge[node].size(); i++)
  {
    Time++;
    DFS(Edge[node][i]);
  }
  Right[node] = Time;
}

void Update(int k, int num)//修改节点k，添加为1，删除为-1
{
  for(int i=k; i<=N; i+=LowBit(i)) //n为数组长度
    t[i]+=num;
}

int GetSum(int x)
{
  int sum=0;
  for(int i=x; i>0; i-=LowBit(i))
    sum +=t[i];
  return sum;
}

int main()
{
  while(scanf("%d", &N)!=EOF)
  {
    memset(Left, 0, sizeof(Left));
    memset(Right, 0, sizeof(Right));
    memset(a, 0, sizeof(a));
    memset(t, 0, sizeof(t));
    for(int i=0; i<MAXN; i++)
      Edge[i].clear();
    int x,y;
    for(int i=1; i<N; i++) //存入x，y边
    {
      scanf("%d%d", &x, &y);
      Edge[x].push_back(y);
    }
    Time = 1;
    DFS(1);//每个结点对应一个左边界和右边界，它的管辖范围就是左边界到右边界
    for(int i=1; i<=N; i++)
    {
      a[i] = 1;//最初每个a[]上都有一个苹果,即节点都是一样的
      Update(i,1);//同时更新树状数组的值
    }
    scanf("%d%*c", &M);
    char ch;
    for(int i=0; i<M; i++)
    {
      scanf("%c %d%*c", &ch, &y);
      if(ch == 'Q')//b的子树就是[Left[b], right[b]]
        printf("%d\n", GetSum(Right[y]) - GetSum(Left[y]-1));
      else
      {
        a[y]?Update(Left[y],-1):Update(Left[y],1);
        a[y] = !a[y];//变为相反的状态
      }
    }
  }
  return 0;
}
