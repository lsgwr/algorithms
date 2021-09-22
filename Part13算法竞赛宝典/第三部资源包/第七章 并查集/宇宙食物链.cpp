//宇宙食物链
#include <bits/stdc++.h>
using namespace std;

int father[50005],Rank[50005];

//查找x的集合，回溯时压缩路径，并修改x与father[x]的关系
int FindFather(int x)
{
  int t;
  if(x!=father[x])
  {
    t = father[x];
    father[x]= FindFather(father[x]);
    Rank[x] = (Rank[x]+Rank[t])%3;//更新x与father[X]的关系
  }
  return father[x];
}

void Union(int x,int y,int d)//合并x,y所在的集合
{
  int xf = FindFather(x);
  int yf = FindFather(y);
  father[xf] = yf;//将集合xf合并到yf集合上
  Rank[xf]=(Rank[y]-Rank[x]+3+d)%3;//更新 xf 与father[xf]的关系
}

int main()
{
  int total=0,n,k,x,y,d,xf,yf;
  cin>>n>>k;
  memset(Rank,0,sizeof(Rank));
  for(int i=1; i<=n; ++i)
    father[i]=i;
  while(k--)
  {
    cin>>d>>x>>y;
    if(x>n||y>n||(d==2 && x == y))//如果x或y比n大，或x吃x，是假话
      total++;//假话数加一
    else
    {
      xf = FindFather(x);
      yf = FindFather(y);
      if(xf == yf)//如果x，f的父结点相同,则可判断给的关系是否正确的
      {
        if((Rank[x]-Rank[y]+3)%3 != d-1)
          total++;
      }
      else
        Union(x,y,d-1);//否则合并x，y
    }
  }
  cout<<total<<endl;
  return 0;
}


