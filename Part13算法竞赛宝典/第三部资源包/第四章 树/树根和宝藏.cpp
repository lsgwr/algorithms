//Ê÷¸ùºÍ±¦²Ø
#include <bits/stdc++.h>
using namespace std;
int tree[105];

int main()
{
  int n,m,x,y,Root,sum,Max=0,MaxRoot;
  cin>>n>>m;
  for(int i=1; i<=m; ++i)
  {
    cin>>x>>y;
    tree[y]=x;
  }
  for(int i=1; i<=n; i++)
  {
    if(tree[i]==0)
      Root=i;
    else
    {
      sum=0;
      for(int j=1; j<=n; ++j)
        if(tree[j]==i)
          sum++;
      if(sum>Max)
        MaxRoot=i;
    }
  }
  cout<<Root<<" "<<MaxRoot;
  return 0;
}

