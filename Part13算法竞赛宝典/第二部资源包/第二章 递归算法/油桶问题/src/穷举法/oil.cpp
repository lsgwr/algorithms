//油桶问题－穷举法 
#include <iostream>
#include <cstdlib>
using  namespace std;

int main()
{
  freopen("oil.in","r",stdin);
  freopen("oil.out","w",stdout);
  int N,M,j,i,oil[100],total,t,t2;
  cin>>N>>M;
  for (i=0;i<N;i++)
  {
    cin>>oil[i];
    total+=oil[i];
  }
  if (total<M)
  {
    cout<<"no\n";
    goto end;
  }
  total=1<<N;
  for (i=0;i<total;i++) //穷举所有可能 
  {  
    t=0; t2=i;  //t为当前情况的油量 
    for (j=0;j<N;j++)
    {
      if (t2&1)    //如果这个油桶有被选择 
	    t+=oil[j];  //加上这个油桶的油 
      t2>>=1;  //检查下一个油桶 
    }
    if (t==M)   //如果相等
    {
	  cout<<"yes\n";
      goto end;
    }
  }
  cout<<"no\n";
  end:
  return 0;
}
