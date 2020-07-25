//无所不在的宗教
#include <bits/stdc++.h>
using namespace std;

int father[50001];
int Rank[50001];//rank值记录树的高度

int Find(int X)//查找，并把查找路径上的结点都指向跟，以减小树的高度
{
  return father[X]==X?X:father[X]=Find(father[X]);
}

void Union(int x,int y)//合并
{
  int r1=Find(x);
  int r2=Find(y);
  if(r1==r2)//两元素如在同一集合则退出
    return;
  if(Rank[r1]<Rank[r2])////rank值较小的集合合并到大的集合中
    father[r2]=r1;
  else
  {
    if(Rank[r1] == Rank[r2])//rank值相等的树合并后rank要增加一
      Rank[r2]++;
    father[r1]=r2;
  }
}

int main()
{
  int m,n,a,b,Case=0;
  while(cin>>n>>m && n+m!=0)
  {
    Case++;
    memset(Rank,0,sizeof(Rank));//树的初始深度为0
    for(int i=0; i<n; i++)
      father[i]=i;//注意根结点指向自身
    for(int i=0; i<m; i++)
    {
      cin>>a>>b;
      if(Find(a)!=Find(b))//如在不同的集合，则合并
      {
        n--;//宗教数减一
        Union(Find(a),Find(b));
      }
    }
    cout<<"Case "<<Case<<": "<<n<<endl;
  }
  return 0;
}

